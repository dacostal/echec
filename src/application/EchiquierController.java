package application;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class EchiquierController {

	private Echiquier board;
	private int tour = 0;
	private int nbClics = 0;
	private String style;

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
		if (e.getTarget() instanceof ImageView) {

			// si choix de la pièce (premier clic)
			if (this.nbClics == 0) {
				this.piece = (ImageView) e.getTarget();
				this.source = (Pane) this.piece.getParent();

				// si tour du joueur blanc et pièce blanche
				// ou si tour du joueur noir et pièce noire
				if ((this.tour % 2 == 0 && isPieceBlanche()) || (this.tour % 2 != 0 && !isPieceBlanche())) {
					this.style = this.source.getStyle();
					this.source.setStyle(this.style + " -fx-border-color: yellow; -fx-border-width: 3; -fx-border-style: solid;");
					this.nbClics = 1;
				}
			}
			// si choix de la destination (deuxième clic)
			else {
				this.destination = (Pane) ((ImageView) e.getTarget()).getParent();

				// si choix d'une autre pièce
				if (isPieceAlliee()) {
					this.source.setStyle(this.style);
					this.nbClics = 0;
					move(e);
				}
				// TODO si déplacement permis sur pièce adverse
				/*else if (isLegalMove()) {
					this.source.getChildren().remove(this.piece);
					this.source.setStyle(this.style);
					this.destination.getChildren().removeAll();
					this.destination.getChildren().add(this.piece);
					this.nbClics = 0;
					this.tour++;
				}*/
			}
		}
		// si clic sur case et choix de la destination (deuxième clic)
		else if (e.getTarget() instanceof Pane && this.nbClics == 1) {
			this.destination = (Pane) e.getTarget();

			// TODO si déplacement permis sur case vide (selon le type)
			//if (isLegalMove()) {
				movePiece();
				this.source.getChildren().remove(this.piece);
				this.source.setStyle(this.style);
				this.destination.getChildren().add(this.piece);
			//}

			this.nbClics = 0;
			this.tour++;
		}
	}

	/**
	 * Méthode qui indique si la pièce sur la case source est blanche.
	 * @return boolean
	 */
	private boolean isPieceBlanche() {
		return this.board.getCase(GridPane.getRowIndex(this.source), GridPane.getColumnIndex(this.source))
			.getPiece().getCouleur().equals("blanc");
	}

	/**
	 * Méthode qui indique si la pièce sur la case source est une alliée.
	 * @return boolean
	 */
	private boolean isPieceAlliee() {
		Case src = this.board.getCase(GridPane.getRowIndex(this.source), GridPane.getColumnIndex(this.source));
		Case dst = this.board.getCase(GridPane.getRowIndex(this.destination), GridPane.getColumnIndex(this.destination));

		return src.getPiece().getCouleur().equals(dst.getPiece().getCouleur());
	}

	/**
	 * Méthode qui déplace la pièce au niveau de l'échiquier (modèle).
	 */
	private void movePiece() {
		Case src = this.board.getCase(GridPane.getRowIndex(this.source), GridPane.getColumnIndex(this.source));
		Case dst = this.board.getCase(GridPane.getRowIndex(this.destination), GridPane.getColumnIndex(this.destination));

		this.board.movePiece(src, dst);
	}
}