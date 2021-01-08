package application;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece {

	private boolean firstMove;

	public Roi(String couleur) {
		this.couleur = couleur;
		this.firstMove = true;
	}

	public boolean getFirstMove() {
		return this.firstMove;
	}

	public void setFirstMove(boolean b) {
		this.firstMove = b;
	}

	@Override
	public List<Case> getLegalMoves(Echiquier board, Case position) {
		List<Case> moves = new ArrayList<>();

		int ligne = position.getLigne();
		int colonne = position.getColonne();

		// cases adjacentes
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {

				// si pas la position initiale du roi
				if(!(i == 0 && j == 0) && ligne+i >= 0 && ligne+i < 8 && colonne+j >= 0 && colonne+j < 8) {
					Case c = board.getCase(ligne+i, colonne+j);

					if(c.isEmpty() || !c.getPiece().getCouleur().equals(this.couleur)) {
						moves.add(c);
					}
				}
			}
		}

		return moves;
	}
}