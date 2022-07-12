package drehfraehsim.entities;

public class HarmonischeOszillation {
	private final double eigenkreisFrequenz;
	private final double dämpfungsKoeffizient;
	private final double modaleMasse;


	private double x0;
	private double v0;

	public HarmonischeOszillation(double eigenkreisFrequenz, double dämpfungsKoeffizient, double modaleMasse) {
		this.eigenkreisFrequenz = eigenkreisFrequenz;
		this.dämpfungsKoeffizient = dämpfungsKoeffizient;
		this.modaleMasse = modaleMasse;

		x0 = 0;
		v0 = 0;
	}

	public double oszillation(double kraft/*F*/, double vergangeneZeit /*dT*/) {
		x0 = oszillationsFormel(x0, eigenkreisFrequenz, vergangeneZeit, v0, kraft, modaleMasse, dämpfungsKoeffizient);
		v0 = geschwindigkeitsFormel(kraft, vergangeneZeit, v0, modaleMasse, eigenkreisFrequenz, x0, dämpfungsKoeffizient);
		return x0;
	}

	private static double oszillationsFormel(double x0, double eigenkreisFrequenz, double deltaT, double v0, double kraft, double modaleMasse, double dämpfungsKoeffizient) {
		return (
				 x0 * Math.cos(eigenkreisFrequenz * deltaT)
				 + (v0 + (kraft * deltaT) / modaleMasse + x0 * dämpfungsKoeffizient ) / modaleMasse * Math.sin(eigenkreisFrequenz * deltaT)
				) * Math.exp(-dämpfungsKoeffizient * deltaT);
	}

	private static double geschwindigkeitsFormel(double kraft/*F*/, double deltaT /*dT*/, double v0, double modaleMasse, double eigenkreisFrequenz, double x0, double dämpfungsKoeffizient) {
		return 	(
				(v0 + (kraft * deltaT) / modaleMasse) * Math.cos(eigenkreisFrequenz * deltaT)
				  - (x0 * Math.pow(eigenkreisFrequenz, 2) + v0 * dämpfungsKoeffizient + x0 * Math.pow(dämpfungsKoeffizient, 2) + (kraft * deltaT) / modaleMasse) / eigenkreisFrequenz * Math.sin(eigenkreisFrequenz * deltaT)
				) * Math.exp(-dämpfungsKoeffizient * deltaT);
	}

}