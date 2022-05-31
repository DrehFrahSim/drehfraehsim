package drehfraehsim.entities;

import drehfraehsim.entities.ProzessParameter.WerkzeugParameter;

public class Werkzeug {
	private final double höhe;
	private final double breite;

	private final double tiefe;

	private final Punktwolke punkte;

	private Vector2 position = new Vector2(0, 0);

	public Werkzeug(WerkzeugParameter parameter) {
		this.höhe = parameter.höhe();
		this.breite = parameter.breite();
		this.tiefe = parameter.tiefe();
		this.punkte = Punktwolke.quader(höhe, breite, tiefe);
	}

	public Punktwolke getPunkte() {
		return punkte;
	}

	public void fahreZu(Vector2 position) {
		this.position = position;
	}

}
