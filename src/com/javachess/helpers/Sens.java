package com.javachess.helpers;

/**
 * Sens d'un coup. Les propriétés modifColonne et modifLigne sont utilisées pour
 * se déplacer de case en case dans le sens choisi.
 * 
 * @author Ouzned
 * 
 */
public enum Sens {
	HAUT(0, 1), 
	BAS(0, -1), 
	GAUCHE(-1, 0), 
	DROITE(1, 0), 
	DIAGHAUTGAUCHE(-1, 1), 
	DIAGHAUTDROITE(1, 1), 
	DIAGBASGAUCHE(-1, -1), 
	DIAGBASDROITE(1, -1);

	private final int modifColonne;
	private final int modifLigne;

	Sens(int modifColonne, int modifLigne) {
		this.modifColonne = modifColonne;
		this.modifLigne = modifLigne;
	}

	public int getModifColonne() {
		return modifColonne;
	}

	public int getModifLigne() {
		return modifLigne;
	}
}
