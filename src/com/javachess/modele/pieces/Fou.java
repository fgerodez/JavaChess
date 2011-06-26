package com.javachess.modele.pieces;

import javax.swing.text.Position;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.modele.plateau.Echiquier;

public class Fou extends Piece {

	public Fou(Couleur couleur) {
		super(couleur);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean attaquePossible(Coup coup, Echiquier echiquier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouvementPossible(Coup coup, Echiquier echiquier) {
		// TODO Auto-generated method stub
		return false;
	}


}
