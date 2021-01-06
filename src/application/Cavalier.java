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
				Case c1 = board.getCase(ligne+i, colonne+j);
				Case c2 = board.getCase(ligne+j, colonne+i);

				if(c1.isEmpty()) {
					moves.add(c1);
				}

				if(c2.isEmpty()) {
					moves.add(c2);
				}
			}
		}

		return moves;
	}
}