package drehfraehsim.entities;

import drehfraehsim.entities.ProzessParameter.WerkzeugParameter;
import drehfraehsim.view.Renderer;

public class Werkzeug {
	private final Renderer renderer;
	private final Quader eckPunkte;

	private Vector2 position = new Vector2(0, 0);

	public Werkzeug(WerkzeugParameter parameter, Renderer renderer) {
		var breite = parameter.breite();
		var höhe = parameter.höhe();
		var tiefe = parameter.tiefe();
		this.renderer = renderer;

		// TODO um position verschieben
		this.eckPunkte = berechneEckPunkte(parameter);

		
		renderer.initialiseWerkzeug(breite, tiefe, höhe);
	}
	
	public Quader getEckPunkte() {
		return eckPunkte;
	}
	
	public void fahreZu(Vector2 position) {
		this.position = position;
	}
	
	public void refreshRender() {
		renderer.bewegeWerkzeug(position); // TODO position absolut machen
	}

	private static Quader berechneEckPunkte(WerkzeugParameter parameter) {
		var breite = parameter.breite();
		var höhe = parameter.höhe();
		var tiefe = parameter.tiefe();
		var einstellWinkel = parameter.einstellWinkel();
		/*
		 * y
		 * ^        -----
		 * |        |   |
		 * |        |   |
		 * |   _____-----_______
		 * | 
		 *  --------> z
		 */
		var untenLinks = new Vector2(-tiefe / 2, - höhe / 2);
		var untenRechts =new Vector2(tiefe / 2,  - höhe / 2);
		var obenLinks =  new Vector2(-tiefe / 2, höhe / 2);
		var obenRechts = new Vector2(tiefe / 2,  höhe / 2);
		
		return new Quader(
			rechteck3DAusZweiPunkten(untenLinks.rotiereUm(einstellWinkel), obenLinks.rotiereUm(einstellWinkel), breite),
			rechteck3DAusZweiPunkten(untenRechts.rotiereUm(einstellWinkel), obenRechts.rotiereUm(einstellWinkel), breite)
		);
		/*
		var untenLinksRotiert = untenLinks.rotiereUm(einstellWinkel);
		var vorneUntenLinks = new Vector3(- breite / 2, untenLinksRotiert.y(), untenLinksRotiert.x());
		var vorneUntenRechts = new Vector3(breite / 2, untenLinksRotiert.y(), untenLinksRotiert.x());
		
		return Stream.of(untenLinks, untenRechts, obenLinks, obenRechts)
				.map(ecke -> ecke.rotiereUm(parameter.einstellWinkel()))
				.<Vector3>mapMulti((rotierteEcke, downStream) -> {
					downStream.accept(new Vector3(breite / 2, rotierteEcke.y(), rotierteEcke.x()));
					downStream.accept(new Vector3(- breite / 2, rotierteEcke.y(), rotierteEcke.x()));
				})
				.toList();*/
	}
	/**
	 * Wir übersetzen aus
	 *  
	 * y
	 * ^        -
	 * |        |
	 * |        |
	 * |   _____-_______
	 * | 
	 *  --------> z
	 *  
	 *  ins 3D Koordinatensystem,
	 *  
	 *  y
	 *  ^	
	 *  |	----------
	 *  |	|	     |
	 *  |  	|	     |
	 *  |	|<breite>|
	 *  |   ---------
	 *  |
	 *  -----------> x
	 * @param unten
	 * @param oben
	 * @param breite
	 * @return
	 */
	private static Rechteck rechteck3DAusZweiPunkten(Vector2 unten, Vector2 oben, double breite) {
		var untenLinks = new Vector3(- breite / 2, unten.y(), unten.x());
		var untenRechts = new Vector3(breite / 2, unten.y(), unten.x());
		
		var obenLinks = new Vector3(- breite / 2, oben.y(), oben.x());
		var obenRechts = new Vector3(breite / 2, oben.y(), oben.x());
		
		return new Rechteck(untenLinks, untenRechts, obenLinks, obenRechts);
	}
}
