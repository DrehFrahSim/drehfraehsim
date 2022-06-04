package drehfraehsim.entities;

import drehfraehsim.entities.ProzessParameter.WerkzeugParameter;
import drehfraehsim.view.Renderer;

public class Werkzeug {
	private final double höhe;
	private final double breite;

	private final double tiefe;

	private final Punktwolke punkte;
	private final Renderer renderer;

	private Vector2 position = new Vector2(0, 0);

	public Werkzeug(WerkzeugParameter parameter, Renderer renderer) {
		this.höhe = parameter.höhe();
		this.breite = parameter.breite();
		this.tiefe = parameter.tiefe();
		this.punkte = Punktwolke.quader(höhe, breite, tiefe);
		this.renderer = renderer;

		renderer.initialiseWerkzeug(breite, tiefe, höhe);
	}

	public Punktwolke getPunkte() {
		return punkte;
	}

	public void fahreZu(Vector2 position) {
		this.position = position;
	}

	public void refreshRender() {
		renderer.bewegeWerkzeug(position); // TODO position absolut machen
	}

}
