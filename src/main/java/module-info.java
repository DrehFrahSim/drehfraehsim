module drehfraehsim {
	requires commons.cli;
	requires javafx.graphics;
	requires org.locationtech.jts;

	exports drehfraehsim to javafx.graphics;
	exports drehfraehsim.view to javafx.graphics;
}