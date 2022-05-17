package drehfraehsim.entities;

import drehfraehsim.entities.ProzessParameter.WerkzeugParameter;

public class Werkzeug {
	private final double höhe;
	private final double breite;

	private final Punktwolke punkte;

	private Vector position = new Vector(0, 0);

	public Werkzeug(WerkzeugParameter parameter) {
		this.höhe = parameter.höhe();
		this.breite = parameter.breite();
		this.punkte = Punktwolke.quader(höhe, breite);
	}

	public void fahreZu(Vector position) {
		this.position = position;
	}

}
