package drehfraehsim.services;

import drehfraehsim.entities.ProzessParameter;

public class CLI {

	public void process(String[] args) {
		// TODO parsen und so
		new Simulator(ProzessParameter.beispiel()).run();
	}

}