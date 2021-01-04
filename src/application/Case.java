package application;

public class Case {

	private int ligne;
	private int colonne;
	private Piece piece;

	public Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public int getLigne() {
		return this.ligne;
	}

	public int getColonne() {
		return this.colonne;
	}

	public Piece getPiece() {
		return this.piece;
	}

	public void addPiece(Piece piece) {
		this.piece = piece;
	}

	public void removePiece() {
		this.piece = null;
	}

	public boolean isEmpty() {
		return this.piece == null;
	}
}