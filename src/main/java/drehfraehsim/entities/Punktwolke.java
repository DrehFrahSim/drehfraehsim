package drehfraehsim.entities;

/**
 *
 * Eine Punktwollte verwaltet ein 3D Dimemonsionales Quader aus Würfeln und kann
 * verschiedene Operationen mit anderen Punktwolken ausführen.
 *
 */
public class Punktwolke {
	// Würfel pro mm
	private static final int GENAUIGKEIT = 1;

	private boolean[][][] punkte; // vielleicht auch BitSet?

	private Punktwolke() {

	}

	/**
	 *
	 * @param länge in mm
	 * @param radius in mm
	 * @return
	 */
	public static Punktwolke zylinder(double länge, double radius) {
		return null;

	}

	/**
	 *
	 * @param höhe in mm
	 * @param breite in mm
	 * @return
	 */
	public static Punktwolke quader(double höhe, double breite) {
		return null;

	}
}
