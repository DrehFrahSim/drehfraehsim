package drehfraehsim.entities;

/**
 *
 * Stellt ein Werkstück dar, dass durch ein Werkzeug bearbeitet werden kann.
 *
 */
public class Werkstück {

	private final Punktwolke punkte;

	private final double initialRadius;
	private final double initialLänge;

	private double winkel;

	public Werkstück(double initialRadius, double initialLänge) {
		this.initialRadius = initialRadius;
		this.initialLänge = initialLänge;
		this.punkte = Punktwolke.zylinder(initialLänge, initialRadius);
	}

	public void dreheZu(double winkel) {
		this.winkel = winkel;
	}

	public void schneiden(Werkzeug werkzeug) {

	}

}
