package application;

import java.util.ArrayList;
import java.util.List;

public class Fou extends Piece {

	public Fou(String couleur) {
		this.couleur = couleur;
	}

	@Override
	public List<Case> getLegalMoves(Echiquier board, Case position) {
		List<Case> moves = new ArrayList<>();

		int ligne = position.getLigne();
		int colonne = position.getColonne();

		for (int i = ligne+1, j = colonne+1; i < 8 && j < 8; i++, j++) {
			Case c = board.getCase(i, j);

			if (c.isEmpty()) {
				moves.add(c);
			} else {

				if (!c.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c);
				}

				break;
			}
		}

		for (int i = ligne-1, j = colonne-1; i >= 0 && j >= 0; i--, j--) {
			Case c = board.getCase(i, j);

			if (c.isEmpty()) {
				moves.add(c);
			} else {

				if (!c.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c);
				}

				break;
			}
		}

		for (int i = ligne-1, j = colonne+1; i >= 0 && j < 8; i--, j++) {
			Case c = board.getCase(i, j);

			if (c.isEmpty()) {
				moves.add(c);
			} else {

				if (!c.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c);
				}

				break;
			}
		}

		for (int i = ligne+1, j = colonne-1; i < 8 && j >= 0; i++, j--) {
			Case c = board.getCase(i, j);

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