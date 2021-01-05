package application;

import java.util.ArrayList;
import java.util.List;

public class Dame extends Piece {

	public Dame(String couleur) {
		this.couleur = couleur;
	}

	@Override
	public List<Case> getLegalMoves(Echiquier board, Case position) {
		List<Case> moves = new ArrayList<>();

		int ligne = position.getLigne();
		int colonne = position.getColonne();
		
		
		int i = ligne+1;
		int j = colonne+1;
		for (; i <8 && j<8 ; i++, j++) 
		{
			Case c = board.getCase(i, j);

			if (c.isEmpty()) 
			{
				moves.add(c);
			} 
			else 
			{
				if(!c.getPiece().getCouleur().equals(this.couleur)) 
				{
					moves.add(c);
				}
				break;
			}
		}
		
		
		
		i = ligne-1;
		j = colonne-1;
		for (; i >=0 && j>=0 ; i--, j--) 
		{
			Case c = board.getCase(i, j);

			if (c.isEmpty()) 
			{
				moves.add(c);
			} 
			else 
			{
				if(!c.getPiece().getCouleur().equals(this.couleur)) 
				{
					moves.add(c);
				}
				break;
			}
		}
		
		i = ligne-1;
		j = colonne+1;
		for (; i >=0 && j<8 ; i--, j++) 
		{
			Case c = board.getCase(i, j);

			if (c.isEmpty()) 
			{
				moves.add(c);
			} 
			else 
			{
				if(!c.getPiece().getCouleur().equals(this.couleur)) 
				{
					moves.add(c);
				}
				break;
			}
		}
		
		i = ligne+1;
		j = colonne-1;
		for (; i <8 && j>=0 ; i++, j--) 
		{
			Case c = board.getCase(i, j);

			if (c.isEmpty()) 
			{
				moves.add(c);
			} 
			else 
			{
				if(!c.getPiece().getCouleur().equals(this.couleur)) 
				{
					moves.add(c);
				}
				break;
			}
		}
		List<Case> moves = new ArrayList<>();

		int ligne = position.getLigne();
		int colonne = position.getColonne();

		// v�rification des cases en haut
		for ( i = ligne-1; i >= 0; i--) {
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

		// vérification des cases en bas
		for ( i = ligne+1; i < 8; i++) {
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

		// vérification des cases à gauche
		for ( j = colonne-1; j >= 0; j--) {
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

		// vérification des cases à droite
		for (j = colonne+1; j < 8; j++) {
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