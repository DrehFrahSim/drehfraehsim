package drehfraehsim.entities;

import java.util.*;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

/**
 *
 * Eine Punktwolke verwaltet eine Menge aus Würfeln und kann
 * verschiedene Operationen mit anderen Punktwolken ausführen.
 *
 */
public class Punktwolke {
	// Würfel pro mm
	private static final int GENAUIGKEIT = 1;

	private HashSet<Vector3> punkte;

	private Collection<Vector3> entferntePunkte = new ArrayList<>();

	private GeometryFactory geometryFactory = new GeometryFactory();


	private Punktwolke() {
		punkte = new HashSet<>();
	}

	public HashSet<Vector3> getPunkteSet() {
		return punkte;
	}

	public static Punktwolke zylinder(double länge, double radius) {
		Punktwolke punktwolke = new Punktwolke();

		for (double zInkrement = 0; zInkrement <= länge; zInkrement = zInkrement + (1/GENAUIGKEIT)) {
			for (double xInkrement = -radius; xInkrement <= radius; xInkrement = xInkrement + (1/GENAUIGKEIT)) {
				for (double yInkrement = -radius; yInkrement <= radius; yInkrement = yInkrement + (1/GENAUIGKEIT)) {
					if (Vector2.distanzZwischenVectoren(xInkrement, yInkrement, 0, 0) <= radius)
						punktwolke.punktHinzufügen(new Vector3(xInkrement, yInkrement, zInkrement));
				}
			}
		}

		return punktwolke;
	}

	private void punktHinzufügen(Vector3 punkt) {
		punkte.add(punkt);
	}

	public void enferneQuaderVolumen(Quader quader) {
		var minZ = quader.allePunkte().mapToDouble(punkt -> punkt.z()).min().getAsDouble();
		var maxZ = quader.allePunkte().mapToDouble(punkt -> punkt.z()).max().getAsDouble();

		Coordinate[] eckPunkte = quader.allePunkte()
									.map(vec3 -> new Coordinate(vec3.x(), vec3.y(), vec3.z()))
									.toArray(Coordinate[]::new);

		Coordinate[] ring = Arrays.copyOf(eckPunkte, 9);
		ring[8] = ring[0];

		Geometry quaderHülle = geometryFactory.createPolygon(ring).convexHull();

		var punktIterator = punkte.iterator();
		while(punktIterator.hasNext()) {
			var punkt = punktIterator.next();

			if (minZ > punkt.z() || punkt.z() > maxZ) {
				continue;
			}
			if (quaderHülle.contains(geometryFactory.createPoint(new Coordinate(punkt.x(), punkt.y(), punkt.z())))) {
				punktIterator.remove();
				entferntePunkte.add(punkt);
			}
		}
	}

	public Collection<Vector3> getAndClearEntferntePunkte() {
		var entferntePunkte = this.entferntePunkte;
		this.entferntePunkte = new ArrayList<>();
		return entferntePunkte;
	}
}
