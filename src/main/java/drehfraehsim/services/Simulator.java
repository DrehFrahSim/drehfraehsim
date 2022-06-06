package drehfraehsim.services;

import java.time.Instant;

import drehfraehsim.entities.*;
import drehfraehsim.view.Renderer;

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
	private Instant lastRender = Instant.MIN;

	public Simulator(ProzessParameter prozessParameter, Renderer renderer) {
		this.prozessParameter = prozessParameter;
		werkstück = new Werkstück(prozessParameter.werkstückParameter(), renderer);
		werkzeug = new Werkzeug(prozessParameter.werkzeugParameter(), renderer);
	}


	public void run() {
		System.out.println("Simulator.run() start");
		long tick = 0;
		do {
			doTick(tick);
			if (Instant.now().minusSeconds(1).isAfter(lastRender) || tick == 0) {
				lastRender = Instant.now();
				render();
			}
			tick++;
		} while (!processFinished(tick));
		render();
		System.out.println("Simulator.run() ende, ticks = " + tick);
	}

	private void render() {
		werkzeug.refreshRender();
		werkstück.refreshRender();
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
		// z.b 0.001 drehungen in einem tick
		var frequenzProTick = drehfrequenz() / TICKS_PRO_SEKUNDE;
		var winkelProTick  = frequenzProTick * 360;
		return (winkelProTick * tick ) % 360;
	}

	private double drehfrequenz() {
		return prozessParameter.operationsParameter().schnittGeschwindigkeit()
				/ (prozessParameter.werkstückParameter().umfang());
	}


	private double werkzeugStreckeProTick() {
		return drehfrequenz() / TICKS_PRO_SEKUNDE * prozessParameter.operationsParameter().vorschub();
	}

	/**
	 *
	 * @return wie viele Ticks das Werkzeug braucht um einmal die bearbeitungslänge abzufahren
	 */
	private long werkzeugAbfahrDauerInTicks() {
		return (long) (prozessParameter.operationsParameter().bearbeitungsLänge() / werkzeugStreckeProTick());
	}
}
