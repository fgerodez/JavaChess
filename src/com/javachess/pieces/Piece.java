package com.javachess.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.jeu.Case;
import com.javachess.jeu.Echiquier;

/**
 * Cette classe représente le template d'une pièce d'échec. Chaque pièce est
 * représentée par une couleur et une position.
 * 
 * @author Ouzned
 */
public abstract class Piece {
	private Couleur color;
	private Case position;
	private Case positionInitiale;
	
	public Piece(Couleur couleur, Case position) {
		this.color = couleur;
		this.position = position;
		this.positionInitiale = position;
	}
	
	/**
	 * Vérifie si la pièce peut se déplacer à newPos Cette méthode ne vérifie
	 * pas les mouvements d'attaque d'une pièce (mouvement diagonal pour le pion
	 * par exemple)
	 * 
	 * @param newPos
	 * @return Vrai si la pièce peut se déplacer en newPos
	 */
	protected abstract boolean attaquePossible(Coup coup, Echiquier echiquier);

	/**
	 * Vérifie si la pièce peut attaquer la case du coup spécifié.
	 * 
	 * @param newPos
	 * @return Vrai si la pièce peut attaquer (et prendre une pièce adverse)
	 */
	protected abstract boolean mouvementPossible(Coup coup, Echiquier echiquier);

	/**
	 * Indique si le déplacement indiquait par le coup est possible
	 * @param coup
	 * @param echiquier
	 * @param isAttaque
	 * @return
	 */
	public boolean deplacementPossible(Coup coup, Echiquier echiquier, boolean isAttaque) {
		if (isAttaque)
			return this.attaquePossible(coup, echiquier);
		else
			return this.mouvementPossible(coup, echiquier);
	}

	public Couleur getColor() {
		return color;
	}

	public Case getPosition() {
		return position;
	}

	public void setPosition(Case position) {
		this.position = position;
	}

	public Case getPositionInitiale() {
		return positionInitiale;
	}
}
