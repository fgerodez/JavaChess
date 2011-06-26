package com.javachess.modele.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.modele.plateau.Echiquier;

public class Reine extends Piece {

	public Reine(Couleur couleur) {
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
