package drehfraehsim;

import drehfraehsim.entities.ProzessParameter;
import drehfraehsim.services.Simulator;
import drehfraehsim.view.FertigungsProzessSzene;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXStart extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setWidth(1000);
		primaryStage.setHeight(1000);

		var szene = new FertigungsProzessSzene();

		var simulator = new Simulator(ProzessParameter.beispiel(), szene);

		primaryStage.setScene(szene);
		primaryStage.show();

		new Thread(simulator::run).start();
	}

}
