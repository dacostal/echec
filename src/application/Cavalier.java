package application;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends Piece {

	public Cavalier(String couleur) {
		this.couleur = couleur;
	}

	@Override
	public List<Case> getLegalMoves(Echiquier board, Case position) {
		List<Case> moves = new ArrayList<>();

		int ligne = position.getLigne();
		int colonne = position.getColonne();

		// déplacements en L
		for(int i = -2; i <= 2; i+=4) {
			for(int j = -1; j <= 1; j+=2) {

				if(ligne+i >= 0 && ligne+i < 8 && colonne+j >= 0 && colonne+j < 8) {
					Case c1 = board.getCase(ligne+i, colonne+j);

					if(c1.isEmpty() || !c1.getPiece().getCouleur().equals(this.couleur)) {
						moves.add(c1);
					}
				}

				if(ligne+j >= 0 && ligne+j < 8 && colonne+i >= 0 && colonne+i < 8) {
					Case c2 = board.getCase(ligne+j, colonne+i);

					if(c2.isEmpty() || !c2.getPiece().getCouleur().equals(this.couleur)) {
						moves.add(c2);
					}
				}
			}
		}

		return moves;
	}
}