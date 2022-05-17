package drehfraehsim.entities;

import java.util.HashSet;

/**
 *
 * Eine Punktwolke verwaltet eine Menge aus Würfeln und kann
 * verschiedene Operationen mit anderen Punktwolken ausführen.
 *
 */
public class Punktwolke {
	// Würfel pro mm
	private static final int GENAUIGKEIT = 1;

	private HashSet<Vector3> punkte;

	private Punktwolke() {
		punkte = new HashSet<>();
	}

	public static Punktwolke zylinder(double länge, double radius) {
		Punktwolke punktwolke = new Punktwolke();

		for (double zInkrement = 0; zInkrement <= länge; zInkrement = zInkrement + (1/GENAUIGKEIT)) {
			for (double xInkrement = 0; xInkrement <= 2*radius; xInkrement = xInkrement + (1/GENAUIGKEIT)) {
				for (double yInkrement = 0; yInkrement <= 2*radius; yInkrement = yInkrement + (1/GENAUIGKEIT)) {
					if (Vector2.distanzZwischenVectoren(xInkrement, yInkrement, radius/2, radius/2) <= radius)
						punktwolke.punktHinzufügen(new Vector3(xInkrement, yInkrement, zInkrement));
				}
			}
		}

		return punktwolke;
	}

	public static Punktwolke quader(double höhe, double breite, double tiefe) {
		Punktwolke punktwolke = new Punktwolke();

		for (double xInkrement = -breite/2; xInkrement <= breite/2; xInkrement = xInkrement + (1/GENAUIGKEIT)) {
			for (double yInkrement = 0; yInkrement <= höhe; yInkrement = yInkrement + (1/GENAUIGKEIT)) {
				for (double zInkrement = 0; zInkrement >= -tiefe; zInkrement = zInkrement - (1/GENAUIGKEIT)) {
						punktwolke.punktHinzufügen(new Vector3(xInkrement, yInkrement, zInkrement));
				}
			}
		}

		return punktwolke;
	}

	private void punktHinzufügen(Vector3 punkt) {
		punkte.add(punkt);
	}
}
