package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FinDePartieController {

	@FXML
	private Parent root;

	@FXML
	private Label text;

	public void setData(String etat) {
		text.setText(etat);
	}

	@FXML
	public void newGame(MouseEvent event) {
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
	public void menu(MouseEvent event) {
		try {
			// récupération du menu
			Parent newroot = FXMLLoader.load(getClass().getResource("Menu.fxml"));

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
	public void exit(MouseEvent e) {
		Platform.exit();
	}
}