package drehfraehsim.entities;

import drehfraehsim.entities.ProzessParameter.OperationsParameter;
import drehfraehsim.entities.ProzessParameter.WerkstückParameter;
import drehfraehsim.entities.ProzessParameter.WerkzeugParameter;

public record ProzessParameter(OperationsParameter operationsParameter, WerkzeugParameter werkzeugParameter, WerkstückParameter werkstückParameter) {
	public static record OperationsParameter(double einstellWinkel, double schnittGeschwindigkeit, double schnittTiefe, double vorschub, double zielRadius, double bearbeitungsLänge) {
		public OperationsParameter {
			if (einstellWinkel < 0 || einstellWinkel > 360) {
				throw new IllegalArgumentException("Einstellwinkel muss zwischen 0 und 360 sein, gegeben: " + einstellWinkel);
			}
		}
	}
	public static record WerkstückParameter(double radius, double länge) {}
	public static record WerkzeugParameter(double höhe, double breite, double tiefe) {}

	public static ProzessParameter beispiel() {
		return new ProzessParameter(new OperationsParameter(95, 1, 1, 1, 5, 50), new WerkzeugParameter(1, 1, 1), new WerkstückParameter(10, 100));
	}
}
