package drehfraehsim.entities;

/**
 * @author Lennart
 */
public record Vector3(double x, double y, double z) {

	public Vector3 add(Vector3 otherVec) {
		return new Vector3(x + otherVec.x, y + otherVec.y, z + otherVec.z);
	}

	/**
	 * Entgegen als wie die Uhr
	 * @param winkel
	 * @return
	 */
	public Vector3 rotiereUmZAchse(double winkel) {
		var neuesX = x * Math.cos(winkel) + y * (-Math.sin(winkel));
		var neuesY = x * Math.sin(winkel) + y * Math.cos(winkel);

		return new Vector3(neuesX, neuesY, z);
	}

	public Vector3 minus(Vector3 otherVec) {
		return new Vector3(x - otherVec.x, y - otherVec.y, z - otherVec.z);
	}

	public double länge() {
		return Math.sqrt(x*x + y*y + z*z);
	}

	public Vector3 skalieren(double skalar) {
		return new Vector3(x * skalar, y * skalar, z * skalar);
	}

}
