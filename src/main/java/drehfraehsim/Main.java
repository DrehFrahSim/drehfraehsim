package drehfraehsim;

import drehfraehsim.services.CLI;

public class Main {
	public static void main(String[] args) {
		// if graphical
		// new GUI()
		// else
		new CLI().process(args);
	}
}
