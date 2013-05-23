package com.javachess.modele.pieces;

import com.javachess.helpers.Color;
import com.javachess.helpers.Move;
import com.javachess.helpers.PositionConverter;
import com.javachess.helpers.Sens;
import com.javachess.jeu.Board;
import com.javachess.modele.plateau.Tile;
//TODO : le rock
public class Tour extends Piece {

	public Tour(Color couleur, Tile position) {
		super(couleur, position);
	}

	@Override
	protected boolean attaquePossible(Move coup, Board echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	protected boolean mouvementPossible(Move coup, Board echiquier) {
				
		Sens sens = PositionConverter.getSensCoup(coup);

		if (sens == Sens.HAUT || sens == Sens.BAS || sens == Sens.DROITE
				|| sens == Sens.GAUCHE) {

			return echiquier.caseIntermVides(coup.getCaseSource(),
					coup.getCaseDestination(), sens);
		}

		return false;
	}

}
