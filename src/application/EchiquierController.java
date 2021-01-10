package application;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EchiquierController {

	private Echiquier board;
	private int tour = 0;
	private int nbClics = 0;
	private boolean isMove;
	private String style;

	@FXML
	private GridPane grid;

	@FXML
	private ImageView piece;

	@FXML
	private Pane source;

	@FXML
	private Pane destination;

	public EchiquierController() {
		this.board = new Echiquier();
	}

	@FXML
	public void move(MouseEvent e) {

		// si clic sur pièce
		if (e.getSource() instanceof ImageView) {

			// si choix de la pièce (premier clic)
			if (this.nbClics == 0) {
				this.piece = (ImageView) e.getSource();
				this.source = (Pane) this.piece.getParent();

				// si tour du joueur blanc et pièce blanche
				// ou si tour du joueur noir et pièce noire
				if ((this.tour % 2 == 0 && isPieceBlanche(this.source)) || (this.tour % 2 != 0 && !isPieceBlanche(this.source))) {
					this.style = this.source.getStyle();
					this.source.setStyle(this.style + " -fx-border-color: yellow; -fx-border-width: 3; -fx-border-style: solid;");
					this.nbClics = 1;
				}
			}
			// si choix de la destination (deuxième clic)
			else {
				this.destination = (Pane) ((ImageView) e.getSource()).getParent();

				// si roque possible
				if(isPieceAlliee(this.source, this.destination) && isRoque(this.source, this.destination)) {
					this.nbClics = 0;
					this.tour++;
					this.isMove = true;
				}
				// si choix d'une autre pièce
				else if (isPieceAlliee(this.source, this.destination)) {
					this.source.setStyle(this.style);
					this.nbClics = 0;
					move(e);
				}
				// si déplacement permis sur pièce adverse
				else if (isLegalMove(this.source, this.destination)) {
					movePiece(this.source, this.destination);
					this.source.getChildren().remove(this.piece);
					this.source.setStyle(this.style);
					this.destination.getChildren().remove(this.destination.getChildren().get(0));
					this.destination.getChildren().add(this.piece);

					this.nbClics = 0;
					this.tour++;
					this.isMove = true;
				}
			}
		}
		// si clic sur case et choix de la destination (deuxième clic)
		else if (e.getSource() instanceof Pane && this.nbClics == 1) {
			this.destination = (Pane) e.getSource();

			// si déplacement permis sur case vide (selon le type)
			if (isLegalMove(this.source, this.destination)) {
				movePiece(this.source, this.destination);
				this.source.getChildren().remove(this.piece);
				this.source.setStyle(this.style);
				this.destination.getChildren().add(this.piece);

				this.nbClics = 0;
				this.tour++;
				this.isMove = true;
			}
		}

		// si un déplacement a eu lieu
		if(this.isMove) {
			Case roi;
			String etat;

			if(this.tour % 2 == 0) {
				roi = this.board.getCaseRB();
				etat = "Joueur blanc gagne !";
			} else {
				roi = this.board.getCaseRN();
				etat = "Joueur noir gagne !";
			}

			// si échecs et mat
			if(isEchecsEtMat(roi)) {
				end(etat);
			}
		}

		// marque l'événement comme étant consommé
		e.consume();
	}

	@FXML
	public void giveUp(ActionEvent event) {
		// si joueur blanc abandonne
		if(this.tour % 2 == 0) {
			end("Joueur noir gagne !");

		// si joueur noir abandonne
		} else {
			end("Joueur blanc gagne !");
		}
	}

	@FXML
	public void menu(ActionEvent event) {
		try {
			// récupération du menu
			Parent newroot = FXMLLoader.load(getClass().getResource("Menu.fxml"));

			// création de la nouvelle interface
			Scene scene = new Scene(newroot);

			// fermeture de l'ancienne fenêtre
			Stage oldStage = (Stage) this.grid.getParent().getScene().getWindow();
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

	/**
	 * Méthode qui affiche la fenêtre de fin de partie.
	 */
	private void end(String etat) {
		try {
			// création d'un fxml loader
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("FinDePartie.fxml"));

			// récupération de la racine
			Parent newroot = fxmlloader.load();

			// récupérer le controller du fxml
			FinDePartieController controller = fxmlloader.getController();

			// passer le joueur gagnant au controller
			controller.setData(etat);

			// création de la nouvelle interface
			Scene scene = new Scene(newroot);

			// fermeture de l'ancienne fenêtre
			Stage oldStage = (Stage) this.grid.getParent().getScene().getWindow();
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

	/**
	 * Méthode qui indique si la pièce sur la case source est blanche.
	 * @return boolean
	 */
	private boolean isPieceBlanche(Pane source) {
		return this.board.getCase(GridPane.getRowIndex(source), GridPane.getColumnIndex(source))
			.getPiece().getCouleur().equals("blanc");
	}

	/**
	 * Méthode qui indique si la pièce sur la case destination est une alliée.
	 * @return boolean
	 */
	private boolean isPieceAlliee(Pane source, Pane destination) {
		Case src = this.board.getCase(GridPane.getRowIndex(source), GridPane.getColumnIndex(source));
		Case dst = this.board.getCase(GridPane.getRowIndex(destination), GridPane.getColumnIndex(destination));

		return src.getPiece().getCouleur().equals(dst.getPiece().getCouleur());
	}

	/**
	 * Méthode qui déplace la pièce au niveau de l'objet board de type Echiquier.
	 */
	private void movePiece(Pane source, Pane destination) {
		Case src = this.board.getCase(GridPane.getRowIndex(source), GridPane.getColumnIndex(source));
		Case dst = this.board.getCase(GridPane.getRowIndex(destination), GridPane.getColumnIndex(destination));

		this.board.movePiece(src, dst);
	}

	/**
	 * Méthode qui indique si la case destination appartient aux déplacements possibles.
	 */
	private boolean isLegalMove(Pane source, Pane destination) {
		Case src = this.board.getCase(GridPane.getRowIndex(source), GridPane.getColumnIndex(source));
		Case dst = this.board.getCase(GridPane.getRowIndex(destination), GridPane.getColumnIndex(destination));

		return src.getPiece().getLegalMoves(this.board, src).contains(dst);
	}

	/**
	 * Méthode qui indique si le roque s'est produit.
	 */
	private boolean isRoque(Pane source, Pane destination) {
		Case src = this.board.getCase(GridPane.getRowIndex(source), GridPane.getColumnIndex(source));
		Case dst = this.board.getCase(GridPane.getRowIndex(destination), GridPane.getColumnIndex(destination));

		if(src.getPiece() instanceof Roi && dst.getPiece() instanceof Tour 
			&& ((Roi) src.getPiece()).getFirstMove() && ((Tour) dst.getPiece()).getFirstMove()) {

			// tour dans le coin droit
			if(dst.getColonne() == 7) {
				for(int j = src.getColonne()+1; j < dst.getColonne(); j++) {
					if((src.getPiece().getCouleur().equals("blanc") && !this.board.getCase(7, j).isEmpty()) 
						|| !this.board.getCase(0, j).isEmpty()) {

						return false;
					}
				}

				// nouvelles cases du roi et de la tour
				Case first = this.board.getCase(src.getLigne(), src.getColonne()+2);
				Case scnd = this.board.getCase(src.getLigne(), src.getColonne()+1);

				// modifications du modèle et de la vue
				roque(first, scnd);

				return true;

			// tour dans le coin gauche
			} else {
				for(int j = src.getColonne()-1; j > dst.getColonne(); j--) {
					if((src.getPiece().getCouleur().equals("blanc") && !this.board.getCase(7, j).isEmpty()) 
						|| !this.board.getCase(0, j).isEmpty()) {

						return false;
					}
				}

				// nouvelles cases du roi et de la tour
				Case first = this.board.getCase(src.getLigne(), src.getColonne()-2);
				Case scnd = this.board.getCase(src.getLigne(), src.getColonne()-1);

				// modifications du modèle et de la vue
				roque(first, scnd);

				return true;
			}

		} else if(src.getPiece() instanceof Tour && dst.getPiece() instanceof Roi 
			&& ((Tour) src.getPiece()).getFirstMove() && ((Roi) dst.getPiece()).getFirstMove()) {

			// tour dans le coin gauche
			if(src.getColonne() == 0) {
				for(int j = src.getColonne()+1; j < dst.getColonne(); j++) {
					if((src.getPiece().getCouleur().equals("blanc") && !this.board.getCase(7, j).isEmpty()) 
						|| !this.board.getCase(0, j).isEmpty()) {

						return false;
					}
				}

				// nouvelles cases de la tour et du roi
				Case first = this.board.getCase(src.getLigne(), src.getColonne()+3);
				Case scnd = this.board.getCase(src.getLigne(), src.getColonne()+2);

				// modifications du modèle et de la vue
				roque(first, scnd);

				return true;

			// tour dans le coin droit
			} else {
				for(int j = src.getColonne()-1; j > dst.getColonne(); j--) {
					if((src.getPiece().getCouleur().equals("blanc") && !this.board.getCase(7, j).isEmpty()) 
						|| !this.board.getCase(0, j).isEmpty()) {

						return false;
					}
				}

				// nouvelles cases de la tour et du roi
				Case first = this.board.getCase(src.getLigne(), src.getColonne()-2);
				Case scnd = this.board.getCase(src.getLigne(), src.getColonne()-1);

				// modifications du modèle et de la vue
				roque(first, scnd);

				return true;
			}
		}

		return false;
	}

	/**
	 * Méthode qui réalise un roque.
	 */
	private void roque(Case first, Case scnd) {
		// récupération des panneaux correspondants
		Pane firstPane = getPaneByRowColumnIndex(first.getLigne(), first.getColonne());
		Pane scndPane = getPaneByRowColumnIndex(scnd.getLigne(), scnd.getColonne());

		// modifications du modèle
		movePiece(this.source, firstPane);
		movePiece(this.destination, scndPane);

		// modifications de la vue (première pièce)
		this.source.getChildren().remove(this.piece);
		this.source.setStyle(this.style);
		firstPane.getChildren().add(this.piece);

		// modifications de la vue (deuxième pièce)
		ImageView scndPiece = (ImageView) this.destination.getChildren().get(0);
		this.destination.getChildren().remove(scndPiece);
		scndPane.getChildren().add(scndPiece);
	}

	/**
	 * Méthode qui permet de récupérer un noeud en fonction de l'index de sa ligne et de sa colonne dans le gridpane.
	 */
	private Pane getPaneByRowColumnIndex(int ligne, int colonne) {
		for (Node p : this.grid.getChildren()) {
			if (GridPane.getRowIndex(p) == ligne && GridPane.getColumnIndex(p) == colonne) {
				return (Pane) p;
			}
		}

		return null;
	}

	/**
	 * Méthode qui indique s'il y a échecs au Roi.
	 */
	private boolean isEchecsAuRoi(Case srcCase) {
		Pane srcPane = getPaneByRowColumnIndex(srcCase.getLigne(), srcCase.getColonne());

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Case advCase = this.board.getCase(i, j);
				Pane advPane = getPaneByRowColumnIndex(i, j);

				if(!advCase.isEmpty() && !isPieceAlliee(srcPane, advPane) 
					&& advCase.getPiece().getLegalMoves(this.board, advCase).contains(srcCase)) {

					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Méthode qui indique s'il y a échecs et mat.
	 */
	private boolean isEchecsEtMat(Case roi) {

		if(!isEchecsAuRoi(roi)) {
			return false;
		}

		int nbEchecsAuRoi = 0;
		List<Case> moves = roi.getPiece().getLegalMoves(this.board, roi);

		for(Case c : moves) {
			if(isEchecsAuRoi(c)) {
				nbEchecsAuRoi++;
			}
		}

		return nbEchecsAuRoi == moves.size();
	}
}