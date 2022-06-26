package drehfraehsim.entities;

import java.util.ArrayList;
import java.util.Collection;
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

	private Collection<Vector3> entferntePunkte = new ArrayList<>();


	private Punktwolke() {
		punkte = new HashSet<>();
	}

	public HashSet<Vector3> getPunkteSet() {
		return punkte;
	}

	public static Punktwolke zylinder(double länge, double radius) {
		Punktwolke punktwolke = new Punktwolke();

		for (double zInkrement = 0; zInkrement <= länge; zInkrement = zInkrement + (1/GENAUIGKEIT)) {
			for (double xInkrement = -radius; xInkrement <= radius; xInkrement = xInkrement + (1/GENAUIGKEIT)) {
				for (double yInkrement = -radius; yInkrement <= radius; yInkrement = yInkrement + (1/GENAUIGKEIT)) {
					if (Vector2.distanzZwischenVectoren(xInkrement, yInkrement, 0, 0) <= radius)
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
	
	public void enferneQuaderVolumen(Quader quader) {
		var minZ = quader.allePunkte().mapToDouble(punkt -> punkt.z()).min().getAsDouble();
		var maxZ = quader.allePunkte().mapToDouble(punkt -> punkt.z()).max().getAsDouble();
		
		for (Vector3 punkt : punkte) {
			if (minZ > punkt.z() || punkt.z() > maxZ) {
				continue;
			}
			// TODO 
			// lokales Koordinatensystem der Werkzeugescheibe berechnen
			// Eckpunkte aus Weltkoordinatensystem in lokales transformieren
			// Transformieren Punkt in lokales System
			// Checken ob x&y in bounds von min/max x/y der Eckpunkte
		}
	}
	
	public Punktwolke entferneAnderePunktwolke (Punktwolke andere) {
		for (Vector3 p : andere.getPunkteSet()) {
			var wurdeEntfernt = this.punkte.remove(p);
			if (wurdeEntfernt) {
				entferntePunkte.add(p);
			}
		}

		return this;
	}

	public Collection<Vector3> getAndClearEntferntePunkte() {
		var entferntePunkte = this.entferntePunkte;
		this.entferntePunkte = new ArrayList<>();
		return entferntePunkte;
	}
}
