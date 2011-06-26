package com.javachess.helpers;

public enum Sens {
	Haut(0,1), 
	Bas(0,-1), 
	Gauche(-1,0), 
	Droite(1,0),
	DiagHautGauche(-1,1),
	DiagHautDroite(1,1),
	DiagBasGauche(-1,-1),
	DiagBasDroite(1,-1);
	
	private final int modifColonne;
	private final int modifLigne;
	
	Sens(int modifColonne, int modifLigne) {
		this.modifColonne = modifColonne;
		this.modifLigne = modifLigne;
	}
	
	public int getModifColonne() { return modifColonne; }
	public int getModifLigne() { return modifLigne; }
}
