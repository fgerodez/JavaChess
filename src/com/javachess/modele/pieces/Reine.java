package com.javachess.modele.pieces;

import com.javachess.helpers.Color;
import com.javachess.helpers.Move;
import com.javachess.helpers.Direction;
import com.javachess.helpers.PositionConverter;
import com.javachess.helpers.Sens;
import com.javachess.jeu.Board;
import com.javachess.modele.plateau.Tile;

public class Reine extends Piece {
	
	public Reine(Color couleur, Tile position) {
		super(couleur, position);
	}

	@Override
	protected boolean attaquePossible(Move coup, Board echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	protected boolean mouvementPossible(Move coup, Board echiquier) {
		
		if (PositionConverter.getDirection(coup) == Direction.AUTRE)
			return false;
		
		Sens sens = PositionConverter.getSensCoup(coup);
		
		return echiquier.caseIntermVides(coup.getCaseSource(),
				coup.getCaseDestination(), sens);
	}
}
