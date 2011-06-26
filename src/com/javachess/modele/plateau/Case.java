package com.javachess.modele.plateau;

public class Case {
	private int colonne;
	private int ligne;
		
	public Case(int colonne, int ligne) {
		super();
		this.colonne = colonne;
		this.ligne = ligne;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colonne;
		result = prime * result + ligne;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (colonne != other.colonne)
			return false;
		if (ligne != other.ligne)
			return false;
		return true;
	}

	public int getColonne() {
		return colonne;
	}
	
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	public int getLigne() {
		return ligne;
	}
	
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
}
