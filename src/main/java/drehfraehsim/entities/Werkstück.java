package drehfraehsim.entities;

import java.util.ArrayList;

import drehfraehsim.entities.ProzessParameter.WerkstückParameter;
import drehfraehsim.view.Renderer;

/**
 *
 * Stellt ein Werkstück dar, dass durch ein Werkzeug bearbeitet werden kann.
 *
 */
public class Werkstück {

	private final Punktwolke punkte;

	private final double initialRadius;
	private final double initialLänge;
	private final Renderer renderer;

	private double winkel;

	public Werkstück(WerkstückParameter parameter, Renderer renderer) {
		this.initialRadius = parameter.radius();
		this.initialLänge = parameter.länge();
		this.punkte = Punktwolke.zylinder(initialLänge, initialRadius);
		this.renderer = renderer;

		renderer.initialiseWerkstück(new ArrayList<>(this.punkte.getPunkteSet()));
	}

	public void dreheZu(double winkel) {
		this.winkel = winkel;
	}

	public void schneiden(Werkzeug werkzeug) {
		punkte.enferneQuaderVolumen(werkzeug.getEckPunkte().rotiereUmZAchse(winkel));
	}
	
	public void refreshRender() {
		renderer.entferneWerkstückPunkte(punkte.getAndClearEntferntePunkte());
	}

}
