module drehfraehsim {
	requires commons.cli;
	requires javafx.graphics;

	exports drehfraehsim to javafx.graphics;
	exports drehfraehsim.view to javafx.graphics;
}