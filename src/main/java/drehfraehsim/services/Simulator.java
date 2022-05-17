package drehfraehsim.services;

import drehfraehsim.entities.ProzessParameter;

/**
 *
 * Berechnet fürs Werkzeug und Werkstück wie man's dreht und wendet 😎
 *
 */
public class Simulator {
	private static final int TICKS_PRO_SEKUNDE = 1000;
	private final ProzessParameter prozessParameter;
	private final Werkstück werkstück;
	private final Werkzeug werkzeug;

	public Simulator(ProzessParameter prozessParameter) {
		this.prozessParameter = prozessParameter;
		werkstück = new Werkstück(prozessParameter.werkstückParameter());
		werkzeug = new Werkzeug(prozessParameter.werkzeugParameter());
	}


	public void run() {

	}
}
