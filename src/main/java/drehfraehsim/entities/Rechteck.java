package drehfraehsim.entities;

import java.util.stream.Stream;

public record Rechteck (Vector3 untenLinks, Vector3 untenRechts, Vector3 obenLinks, Vector3 obenRechts) {

	public Rechteck rotiereUmZAchse(double winkel) {
		return new Rechteck(
			untenLinks.rotiereUmZAchse(winkel), 
			untenRechts.rotiereUmZAchse(winkel), 
			obenLinks.rotiereUmZAchse(winkel), 
			obenRechts.rotiereUmZAchse(winkel)
		);
	}

	public Stream<Vector3> allePunkte() {
		return Stream.of(untenLinks, untenRechts, obenLinks, obenRechts);
	}

}
