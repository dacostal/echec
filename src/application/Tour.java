package application;

import java.util.ArrayList;
import java.util.List;

public class Tour extends Piece {

	public Tour(String couleur) {
		this.couleur = couleur;
	}

	@Override
	public List<Case> getLegalMoves(Echiquier board, Case position) {
		List<Case> moves = new ArrayList<>();

		int ligne = position.getLigne();
		int colonne = position.getColonne();

		for (int i = ligne-1; i >= 0; i--) {
			Case c = board.getCase(i, colonne);

			if (c.isEmpty()) {
				moves.add(c);
			} else {

				if(!c.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c);
				}

				break;
			}
		}

		for (int i = ligne+1; i < 8; i++) {
			Case c = board.getCase(i, colonne);

			if (c.isEmpty()) {
				moves.add(c);
			} else {

				if (!c.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c);
				}

				break;
			}
		}

		for (int j = colonne-1; j >= 0; j--) {
			Case c = board.getCase(ligne, j);

			if (c.isEmpty()) {
				moves.add(c);
			} else {

				if(!c.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c);
				}

				break;
			}
		}

		for (int j = colonne+1; j < 8; j++) {
			Case c = board.getCase(ligne, j);

			if (c.isEmpty()) {
				moves.add(c);
			} else {

				if (!c.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c);
				}

				break;
			}
		}

		return moves;
	}
}