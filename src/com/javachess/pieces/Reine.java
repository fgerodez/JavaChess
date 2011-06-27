package com.javachess.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.Direction;
import com.javachess.helpers.PositionConverter;
import com.javachess.helpers.Sens;
import com.javachess.jeu.Case;
import com.javachess.jeu.Echiquier;

public class Reine extends Piece {
	
	public Reine(Couleur couleur, Case position) {
		super(couleur, position);
	}

	@Override
	protected boolean attaquePossible(Coup coup, Echiquier echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	protected boolean mouvementPossible(Coup coup, Echiquier echiquier) {
		
		if (PositionConverter.getDirection(coup) == Direction.AUTRE)
			return false;
		
		Sens sens = PositionConverter.getSensCoup(coup);
		
		return echiquier.caseIntermVides(coup.getCaseSource(),
				coup.getCaseDestination(), sens);
	}
}
