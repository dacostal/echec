package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {

	@FXML
	private Parent root;

	@FXML
	public void play(MouseEvent event) {
		try {
			// récupération de l'échiquier
			Parent newroot = FXMLLoader.load(getClass().getResource("Echiquier.fxml"));

			// création de la nouvelle interface
			Scene scene = new Scene(newroot);

			// fermeture de l'ancienne fenêtre
			Stage oldStage = (Stage) root.getScene().getWindow();
			oldStage.close();

			// création de la nouvelle fenêtre
			Stage primaryStage = new Stage();
			primaryStage.setTitle("Echecs");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void rules(MouseEvent e) {
		// TODO
	}

	@FXML
	public void exit(MouseEvent e) {
		Platform.exit();
	}
}