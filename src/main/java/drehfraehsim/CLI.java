package drehfraehsim;

import drehfraehsim.entities.ProzessParameter;
import drehfraehsim.services.Simulator;

public class CLI {

	public static void main (String[] args) {
		// TODO parsen und so
		new Simulator(ProzessParameter.beispiel()).run();
	}

}