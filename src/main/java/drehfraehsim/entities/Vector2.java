package drehfraehsim.entities;

public record Vector2(double x, double y) {
	
	public Vector2 add(Vector2 vec) {
		return new Vector2(this.x + vec.x, this.y + vec.y);
	}
	
	public Vector2 add(double x, double y) {
		return new Vector2(this.x + x, this.y + y);
	}
	
	public Vector2 minus(Vector2 vec) {
		return new Vector2(this.x - vec.x, this.y - vec.y);
	}
	
	/**
	 * Gegen Uhrzeigersinn
	 * @param winkel
	 * @return
	 */
	public Vector2 rotiereUm(double winkel) {
		return new Vector2(
				/*x' =*/ x * Math.cos(winkel) + y * (-Math.sin(winkel)),
				/*y' =*/ x * Math.sin(winkel) + y * Math.cos(winkel));
	}
	
    public static double distanzZwischenVectoren(Vector2 A, Vector2 B) {
        return Math.sqrt(Math.pow(A.x - B.x, 2) + Math.pow(A.y - B.y, 2));
    }

    public static double distanzZwischenVectoren(double Ax, double Ay, double Bx, double By) {
        return Math.sqrt(Math.pow(Ax - Bx, 2) + Math.pow(Ay - By, 2));
    }
}