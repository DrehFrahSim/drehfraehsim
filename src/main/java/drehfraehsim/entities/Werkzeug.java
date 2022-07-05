package drehfraehsim.entities;

import drehfraehsim.entities.ProzessParameter.WerkzeugParameter;
import drehfraehsim.view.Renderer;

public class Werkzeug {
	private final Renderer renderer;
	private final Quader eckPunkte;
	private final double einstellWinkel;

	private final double höhenOffset; // Korrektur damit niedrigste Ecke da liegt wo Simulator es möchte

	private Vector2 position = new Vector2(0, 0);
	private double schwingungYOffset;

	public Werkzeug(WerkzeugParameter parameter, Renderer renderer) {
		this.renderer = renderer;
		this.einstellWinkel = parameter.einstellWinkel();

		// TODO um position verschieben
		this.eckPunkte = berechneEckPunkte(parameter);
		var yDerUnterstenEcke = eckPunkte.allePunkte().mapToDouble(Vector3::y).min().getAsDouble();
		höhenOffset = - yDerUnterstenEcke;
	}

	public Quader getEckPunkte() {
		return eckPunkte.verschiebeUm(new Vector3(0, position.y() + höhenOffset + schwingungYOffset, position.x()));
	}

	public void fahreZu(Vector2 position) {
		this.position = position;
	}

	public void setzeSchwingungYOffset(double schwingungYOffset) {
		this.schwingungYOffset = schwingungYOffset;
	}

	public void refreshRender() {
		renderer.zeigeWerkzeug(getEckPunkte(), einstellWinkel);
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
	 *  yberechneEckPunkte
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
