package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// récupération du menu
			Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

			// création de l'interface
			Scene scene = new Scene(root);

			// configuration de la fenêtre
			primaryStage.setTitle("Echecs");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}