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
}