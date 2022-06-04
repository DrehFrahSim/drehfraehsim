package drehfraehsim.view;

import java.util.Collection;

import drehfraehsim.entities.Vector2;
import drehfraehsim.entities.Vector3;

public interface Renderer {

	public void initialiseWerkzeug(double breite, double länge, double höhe);

	public void initialiseWerkstück(Collection<Vector3> punkte);


	public void entferneWerkstückPunkte(Collection<Vector3> punkte);

	public void bewegeWerkzeug(Vector2 position);

}
