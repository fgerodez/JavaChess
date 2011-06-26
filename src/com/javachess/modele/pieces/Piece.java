package com.javachess.modele.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.modele.plateau.Echiquier;

//TODO: une piece A un mode de déplacement ? Ca pourrait être plus pratique...

/**
 * Cette classe représente le template d'une pièce d'échec. Chaque pièce est
 * représentée par une couleur et une position.
 * 
 * @author Ouzned
 */
public abstract class Piece {
	private Couleur color;
	
	public Piece(Couleur couleur) {
		this.color = couleur;
	}
	
	/**
	 * Vérifie si la pièce peut se déplacer à newPos Cette méthode ne vérifie
	 * pas les mouvements d'attaque d'une pièce (mouvement diagonal pour le pion
	 * par exemple)
	 * 
	 * @param newPos
	 * @return Vrai si la pièce peut se déplacer en newPos
	 */
	public abstract boolean attaquePossible(Coup coup, Echiquier echiquier);

	/**
	 * Vérifie si la pièce peut attaquer sur newPos.
	 * 
	 * @param newPos
	 * @return Vrai si la pièce peut attaquer (et prendre une pièce adverse)
	 */
	public abstract boolean mouvementPossible(Coup coup, Echiquier echiquier);

	/**
	 * La pièce est-elle capable de sauter au dessus d'un autre ?
	 * 
	 * @return
	 */
	public boolean canJumpOver() {
		return false;
	}

	public Couleur getColor() {
		return color;
	}

	public void setColor(Couleur color) {
		this.color = color;
	}
}
