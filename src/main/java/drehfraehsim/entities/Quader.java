package drehfraehsim.entities;

import java.util.stream.Stream;

public record Quader (Rechteck vorne, Rechteck hinten) {
	public Quader rotiereUmZAchse(double winkel) {
		return new Quader(vorne.rotiereUmZAchse(winkel), hinten.rotiereUmZAchse(winkel));
	}
	
	public Stream<Vector3> allePunkte() {
		return Stream.concat(vorne.allePunkte(), hinten.allePunkte()); 
	}
}