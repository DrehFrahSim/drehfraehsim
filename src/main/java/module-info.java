module drehfraehsim {
	requires commons.cli;
	requires javafx.graphics;
	requires org.lwjgl;
	requires org.lwjgl.opengl;
	requires org.lwjgl.glfw;

	exports drehfraehsim to javafx.graphics;
	exports drehfraehsim.view to javafx.graphics;
}