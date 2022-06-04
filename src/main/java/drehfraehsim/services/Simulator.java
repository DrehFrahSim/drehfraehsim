package drehfraehsim.services;

import drehfraehsim.entities.*;

/**
 *
 * Berechnet fürs Werkzeug und Werkstück wie man's dreht und wendet 😎
 *
 */
public class Simulator {
	private static final int TICKS_PRO_SEKUNDE = 1000;
	private final ProzessParameter prozessParameter;
	private final Werkstück werkstück;
	private final Werkzeug werkzeug;

	public Simulator(ProzessParameter prozessParameter) {
		this.prozessParameter = prozessParameter;
		werkstück = new Werkstück(prozessParameter.werkstückParameter());
		werkzeug = new Werkzeug(prozessParameter.werkzeugParameter());
	}


	public void run() {
		long tick = 0;
		do {
			doTick(tick);
			tick++;
		} while (!processFinished(tick));
	}


	private boolean processFinished(long tick) {
		// Wenn wir in diesem Tick öfter schneiden würden als nötig dann stoppen wir
		return wievielMalSchneidenNötig() < wievieltesMalSchneiden(tick);
	}


	private void doTick(long tick) {
		werkstück.dreheZu(werkstückEinstellWinkelInTick(tick));
		werkzeug.fahreZu(werkzeugPositionInTick(tick));
		werkstück.schneiden(werkzeug);
	}

	private Vector2 werkzeugPositionInTick(long tick) {
		var z = (tick % werkzeugAbfahrDauerInTicks() ) * werkzeugStreckeProTick();
		var schnittTiefe = schnittTiefeInTick(tick);
		return new Vector2(z, prozessParameter.werkstückParameter().radius() - schnittTiefe);
	}

	private int wievielMalSchneidenNötig() {
		var radiusZuEntfernen = prozessParameter.werkstückParameter().radius() - prozessParameter.operationsParameter().zielRadius();

		double wieOftSchneiden = radiusZuEntfernen / prozessParameter.operationsParameter().schnittTiefe();

		return (int)Math.ceil(wieOftSchneiden);
	}

	private int wievieltesMalSchneiden(long tick) {
		return (int)(tick / werkzeugAbfahrDauerInTicks() + 1);
	}

	private double schnittTiefeInTick(long tick) {
		// TODO beim letzten Schneiden nicht die volle Tiefe wegnehmen.
		// (wenn 10 insgesamt weg soll, und wir schnitttiefe 3 haben, würden wir 2 zu viel wegnehmen (4*3 = 12))
		return wievieltesMalSchneiden(tick) * prozessParameter.operationsParameter().schnittTiefe();
	}

	private double werkstückEinstellWinkelInTick(long tick) {
		var schnittStreckeProTick = prozessParameter.operationsParameter().schnittGeschwindigkeit() / TICKS_PRO_SEKUNDE;
		var winkelProTick = kreisBogenZuWinkel(schnittStreckeProTick, prozessParameter.werkstückParameter().radius());
		return (winkelProTick * tick) % 360;
	}

	private static double kreisBogenZuWinkel(double bogenLänge, double radius) {
		// https://de.wikipedia.org/wiki/Kreisbogen
		return bogenLänge / (2*Math.PI) / radius * 360;
	}

	private double werkzeugStreckeProTick() {
		return prozessParameter.operationsParameter().schnittGeschwindigkeit() / TICKS_PRO_SEKUNDE;
	}

	/**
	 *
	 * @return wie viele Ticks das Werkzeug braucht um einmal die bearbeitungslänge abzufahren
	 */
	private long werkzeugAbfahrDauerInTicks() {
		var schnittGeschwindigkeit = prozessParameter.operationsParameter().schnittGeschwindigkeit();
		var bearbeitungslänge = prozessParameter.operationsParameter().bearbeitungsLänge();
		var abfahrDauerInSek = bearbeitungslänge / schnittGeschwindigkeit;
		return (long) (abfahrDauerInSek * TICKS_PRO_SEKUNDE);
	}
}
