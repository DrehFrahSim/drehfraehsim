package drehfraehsim.entities;

import drehfraehsim.entities.ProzessParameter.OperationsParameter;
import drehfraehsim.entities.ProzessParameter.WerkstückParameter;
import drehfraehsim.entities.ProzessParameter.WerkzeugParameter;

public record ProzessParameter(OperationsParameter operationsParameter, WerkzeugParameter werkzeugParameter, WerkstückParameter werkstückParameter) {
	public static record WerkstückParameter(double radius, double höhe) {}
	public static record WerkzeugParameter(double höhe, double breite) {}
	public static record OperationsParameter(double einstellWinkel, double schnittGeschwindigkeit, double schnittTiefe, double vorschub, double zielDurchmesser, double bearbeitungsLänge) {}
}
