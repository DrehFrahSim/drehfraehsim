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

	}

	private void doTick(long tick) {
		werkstück.dreheZu(werkstückEinstellWinkelInTick(tick));
		werkzeug.fahreZu(werkzeugPositionInTick(tick));
	}

	private Vector2 werkzeugPositionInTick(long tick) {
		var z = (tick % werkzeugAbfahrDauerInTicks() ) * werkzeugStreckeProTick();
		var schnittTiefe = schnittTiefeInTick(tick);
		return new Vector2(z, -schnittTiefe);
	}

	private double schnittTiefeInTick(long tick) {
		int wievieltesMalSchneiden = (int)(tick / werkzeugAbfahrDauerInTicks() + 1);
		return wievieltesMalSchneiden * prozessParameter.operationsParameter().schnittTiefe();
	}

	private double werkstückEinstellWinkelInTick(long tick) {

	}

	private double werkzeugStreckeProTick() {

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
