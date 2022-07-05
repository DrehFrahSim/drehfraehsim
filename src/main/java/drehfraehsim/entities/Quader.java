package drehfraehsim.entities;

import java.util.stream.Stream;

public record Quader (Rechteck vorne, Rechteck hinten) {
	public Quader rotiereUmZAchse(double winkel) {
		return new Quader(vorne.rotiereUmZAchse(winkel), hinten.rotiereUmZAchse(winkel));
	}

	public Stream<Vector3> allePunkte() {
		return Stream.concat(vorne.allePunkte(), hinten.allePunkte());
	}

	public Quader verschiebeUm(Vector3 offset) {
		return new Quader(vorne.verschiebeUm(offset), hinten.verschiebeUm(offset));
	}

	public double länge() {
		return vorne.obenLinks().minus(hinten.obenLinks()).länge();
	}

	public double breite() {
		return vorne.obenLinks().minus(vorne.obenRechts()).länge();
	}

	public double höhe() {
		return vorne.untenLinks().minus(vorne.obenLinks()).länge();
	}

	public Vector3 mittelPunkt() {
		var diagonaleVonUntenRechtsNachObenLinks = vorne.obenLinks().minus(hinten.untenRechts());
		return diagonaleVonUntenRechtsNachObenLinks.skalieren(0.5d).add(hinten.untenRechts());
	}
}