package drehfraehsim.entities;

/**
 * @author Lennart
 */
public record Vector3(double x, double y, double z) {
	
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
	
}
