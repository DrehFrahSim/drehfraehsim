package drehfraehsim.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import drehfraehsim.entities.Quader;
import drehfraehsim.entities.Vector3;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class FertigungsProzessSzene extends Scene implements Renderer {
	public static final int WIDTH = 1400;
	public static final int HEIGHT = 800;
	private Group werkstück = new Group();
	private Group rootGroup = new Group();

	private final Map<Vector3, Sphere> correspondingSphere = new HashMap<>();
	private Box werkzeug;

	public FertigungsProzessSzene() {
		super(new Group(), WIDTH, HEIGHT, true);
		rootGroup = (Group) getRoot();

		rootGroup.getChildren().add(werkstück);


		Camera camera = new PerspectiveCamera(true);
		camera.setTranslateX(300);
		camera.setTranslateZ(50);
		camera.setNearClip(1);
		camera.setFarClip(1000);
		camera.setRotationAxis(Rotate.Y_AXIS);
		camera.setRotate(-90);
		setFill(Color.SILVER);
		setCamera(camera);

		addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch (event.getCode()) {
			case W:
				camera.translateZProperty().set(camera.getTranslateZ() + 10);
				break;
			case S:
				camera.translateZProperty().set(camera.getTranslateZ() - 10);
				break;
			case A:
				camera.translateXProperty().set(camera.getTranslateX() + 10);
				break;
			case D:
				camera.translateXProperty().set(camera.getTranslateX() - 10);
				break;
			case Q:
				camera.translateYProperty().set(camera.getTranslateY() + 10);
				break;
			case E:
				camera.translateYProperty().set(camera.getTranslateY() - 10);
				break;
			default:
				break;

			/*case UP:
				werkzeug.translateYProperty().set(werkzeug.getTranslateY() + 10);
				checkShapeIntersection(werkzeug, Werkstück);
				break;
			case DOWN:
				werkzeug.translateYProperty().set(werkzeug.getTranslateY() - 10);
				checkShapeIntersection(werkzeug, Werkstück);
				break;
			case RIGHT:
				werkzeug.translateXProperty().set(werkzeug.getTranslateX() - 10);
				checkShapeIntersection(werkzeug, Werkstück);
				break;
			case LEFT:
				werkzeug.translateXProperty().set(werkzeug.getTranslateX() + 10);
				checkShapeIntersection(werkzeug, Werkstück);
				break;
			 */
			}
		});
	}


	@Override
	public void initialiseWerkstück(Collection<Vector3> punkte) {
		Platform.runLater(() -> {
			for (var punkt : punkte) {
				var sphere = sphereVonPunkt(punkt);
				correspondingSphere.put(punkt, sphere);
				werkstück.getChildren().add(sphere);
			}
		});
	}

	@Override
	public void zeigeWerkzeug(Quader quader, double einstellWinkel) {
		Platform.runLater(() -> {
			if (werkzeug != null) {
				rootGroup.getChildren().remove(werkzeug);
			}
			werkzeug = new Box(quader.breite(), quader.höhe(), quader.länge());

			werkzeug.setRotationAxis(Rotate.X_AXIS);
			werkzeug.setRotate(einstellWinkel);
			var mittelPunkt = quader.mittelPunkt();
			werkzeug.setTranslateX(mittelPunkt.x());
			werkzeug.setTranslateY(mittelPunkt.y());
			werkzeug.setTranslateZ(mittelPunkt.z());
			rootGroup.getChildren().add(werkzeug);
		});
	}

	@Override
	public void entferneWerkstückPunkte(Collection<Vector3> punkte) {
		Platform.runLater(() -> {
			for (var punkt : punkte) {
				werkstück.getChildren().remove(correspondingSphere.remove(punkt));
			}
		});
	}

	private Sphere sphereVonPunkt(Vector3 punkt) {
		Sphere sphere = new Sphere(1);
		sphere.setTranslateX(punkt.x());
		sphere.setTranslateY(punkt.y());
		sphere.setTranslateZ(punkt.z());
		return sphere;
	}

	/*private void checkShapeIntersection(Box Schneide, Group Werk) {
		var toRem = new Group();
		double wit = Schneide.getWidth();
		double dep = Schneide.getDepth();
		double hei = Schneide.getHeight();

		double Sx = Schneide.getTranslateX();
		double Sy = Schneide.getTranslateY();
		double Sz = Schneide.getTranslateZ();

		Werk.getChildren().removeIf(node -> {
			Sphere x = (Sphere) node;
			double xX = x.getTranslateX();
			double xY = x.getTranslateY();
			double xZ = x.getTranslateZ();

			if ((xX >= Sx - (wit / 2) && xX <= Sx + (wit / 2)) && (xY >= Sy - (hei / 2) && xY <= Sy + (hei / 2))
					&& (xZ >= Sz - (dep / 2) && xZ <= Sz + (dep / 2))) {
				return true;
			} else {
				return false;
			}
		});
	}*/

}
