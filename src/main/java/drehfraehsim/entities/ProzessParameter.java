package drehfraehsim.entities;

import drehfraehsim.entities.ProzessParameter.OperationsParameter;
import drehfraehsim.entities.ProzessParameter.WerkstückParameter;
import drehfraehsim.entities.ProzessParameter.WerkzeugParameter;

public record ProzessParameter(OperationsParameter operationsParameter, WerkzeugParameter werkzeugParameter, WerkstückParameter werkstückParameter) {
	public static record OperationsParameter(double einstellWinkel, double schnittGeschwindigkeit /*mm/s*/, double schnittTiefe, double vorschub, double zielRadius, double bearbeitungsLänge) {
		public OperationsParameter {
			if (einstellWinkel < 0 || einstellWinkel > 360) {
				throw new IllegalArgumentException("Einstellwinkel muss zwischen 0 und 360 sein, gegeben: " + einstellWinkel);
			}
		}
	}

	public static record WerkstückParameter(double radius, double länge) {
		public double umfang() {
			return radius * 2 * Math.PI;
		}
	}

	public static record WerkzeugParameter(double höhe, double breite, double tiefe) {
		public WerkzeugParameter {
		}
	}

	public static ProzessParameter beispiel() {
		return new ProzessParameter(new OperationsParameter(95, 280d*1000d/60d, 2, 1.5, 20, 60),
				new WerkzeugParameter(10, 10, 10),
				new WerkstückParameter(30, 100));
	}
}
