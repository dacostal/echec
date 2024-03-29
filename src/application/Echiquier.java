package application;

public class Echiquier {

	private static final String NOIR = "noir";
	private static final String BLANC = "blanc";
	private Case[][] cases = new Case[8][8];
	private Case posRB, posRN;

	public Echiquier() {

		// initialisation de la position des cases
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				this.cases[i][j] = new Case(i, j);
			}
		}

		// pièces noires première ligne
		this.cases[0][0].addPiece(new Tour(NOIR));
		this.cases[0][1].addPiece(new Cavalier(NOIR));
		this.cases[0][2].addPiece(new Fou(NOIR));
		this.cases[0][3].addPiece(new Dame(NOIR));
		this.cases[0][4].addPiece(new Roi(NOIR));
		this.cases[0][5].addPiece(new Fou(NOIR));
		this.cases[0][6].addPiece(new Cavalier(NOIR));
		this.cases[0][7].addPiece(new Tour(NOIR));

		// pions noirs deuxième ligne
		for(int j = 0; j < 8; j++) {
			this.cases[1][j].addPiece(new Pion(NOIR));
		}

		// pions blancs avant dernière ligne
		for(int j = 0; j < 8; j++) {
			this.cases[6][j].addPiece(new Pion(BLANC));
		}

		// pièces blanches dernière ligne
		this.cases[7][0].addPiece(new Tour(BLANC));
		this.cases[7][1].addPiece(new Cavalier(BLANC));
		this.cases[7][2].addPiece(new Fou(BLANC));
		this.cases[7][3].addPiece(new Dame(BLANC));
		this.cases[7][4].addPiece(new Roi(BLANC));
		this.cases[7][5].addPiece(new Fou(BLANC));
		this.cases[7][6].addPiece(new Cavalier(BLANC));
		this.cases[7][7].addPiece(new Tour(BLANC));

		// positions des rois
		this.posRB = this.cases[7][4];
		this.posRN = this.cases[0][4];
	}

	public Case getCase(int ligne, int colonne) {
		return this.cases[ligne][colonne];
	}

	public Case getCaseRB() {
		return this.posRB;
	}

	public Case getCaseRN() {
		return this.posRN;
	}

	public void movePiece(Case depart, Case arrivee) {
		Piece p = depart.getPiece();

		if(p instanceof Pion) {
			((Pion) p).setFirstMove(false);
		} else if(p instanceof Tour) {
			((Tour) p).setFirstMove(false);
		} else if(p instanceof Roi) {
			((Roi) p).setFirstMove(false);

			if(p.getCouleur().equals(BLANC)) {
				this.posRB = arrivee;
			} else {
				this.posRN = arrivee;
			}
		}

		arrivee.addPiece(p);
		depart.removePiece();
	}
}