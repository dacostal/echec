package application;

import java.util.ArrayList;
import java.util.List;

public class Pion extends Piece {

	private boolean firstMove;

	public Pion(String couleur) {
		this.couleur = couleur;
		this.firstMove = true;
	}

	public void setFirstMove(boolean b) {
		this.firstMove = b;
	}

	@Override
	public List<Case> getLegalMoves(Echiquier board, Case position) {
		List<Case> moves = new ArrayList<>();

		int ligne = position.getLigne();
		int colonne = position.getColonne();

		// 4 déplacements possibles
		Case c1, c2, c3, c4;

		if (this.couleur.equals("blanc") && ligne - 1 >= 0) {

			c1 = board.getCase(ligne - 1, colonne);

			if (c1.isEmpty()) {
				moves.add(c1);

				// si premier déplacement
				if (this.firstMove) {
					c2 = board.getCase(ligne - 2, colonne);

					if (c2.isEmpty()) {
						moves.add(c2);
					}
				}
			}

			if (colonne - 1 >= 0) {
				// diagonale gauche
				c3 = board.getCase(ligne - 1, colonne - 1);

				// si diagonale gauche occupée par une pièce adverse
				if (!c3.isEmpty() && !c3.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c3);
				}
			}

			if (colonne + 1 < 8) {
				// diagonale droite
				c4 = board.getCase(ligne - 1, colonne + 1);

				// si diagonale droite occupée par une pièce adverse
				if (!c4.isEmpty() && !c4.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c4);
				}
			}

		} else if(ligne + 1 >= 0) {
			c1 = board.getCase(ligne+1, colonne);

			if(c1.isEmpty()) {
				moves.add(c1);

				// si premier déplacement
				if(this.firstMove) {
					c2 = board.getCase(ligne+2, colonne);

					if(c2.isEmpty()) {
						moves.add(c2);
					}
				}
			}

			if(colonne-1 >= 0) {
				// diagonale gauche
				c3 = board.getCase(ligne+1, colonne-1);

				// si diagonale gauche occupée par une pièce adverse
				if(!c3.isEmpty() && !c3.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c3);
				}
			}

			if(colonne+1 < 8) {
				// diagonale droite
				c4 = board.getCase(ligne+1, colonne+1);

				// si diagonale droite occupée par une pièce adverse
				if(!c4.isEmpty() && !c4.getPiece().getCouleur().equals(this.couleur)) {
					moves.add(c4);
				}
			}
		}

		return moves;
	}
}