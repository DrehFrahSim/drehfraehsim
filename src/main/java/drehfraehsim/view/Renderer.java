package drehfraehsim.view;

import java.util.Collection;

import drehfraehsim.entities.Quader;
import drehfraehsim.entities.Vector3;

public interface Renderer {

	public void initialiseWerkstück(Collection<Vector3> punkte);


	public void entferneWerkstückPunkte(Collection<Vector3> punkte);

	void zeigeWerkzeug(Quader quader, double einstellWinkel);

}
