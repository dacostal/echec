package application;

import java.util.List;

public abstract class Piece {

	protected String couleur;

	public String getCouleur() {
		return this.couleur;
	}

	public abstract List<Case> getLegalMoves(Echiquier board, Case position);
}