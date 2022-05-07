package drehfraehsim.entities;

public class Werkzeug {
	private final double höhe;
	private final double breite;

	private final Punktwolke punkte;

	private Vector position = new Vector(0, 0);

	public Werkzeug(double höhe, double breite) {
		this.höhe = höhe;
		this.breite = breite;
		this.punkte = Punktwolke.quader(höhe, breite);
	}

	public void fahreZu(Vector position) {
		this.position = position;
	}

}
