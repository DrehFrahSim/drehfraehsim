package drehfraehsim;

public class Main {
	public static void main(String[] args) {
		// JavaFX weigert sich sonst bei Linux mit AMD Graka 🙄
		if (aufLinux()) {
			System.setProperty("prism.forceGPU", "true"); 
		}
		
		JavaFXStart.launch(JavaFXStart.class, args);
	}
	
	private static final boolean aufLinux() {
		return System.getProperty("os.name").toLowerCase().contains("linux");
	}
}
