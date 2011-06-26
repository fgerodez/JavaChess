package com.javachess.modele.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.Direction;
import com.javachess.helpers.PositionConverter;
import com.javachess.helpers.Sens;
import com.javachess.modele.plateau.Echiquier;

public class Tour extends Piece {

	public Tour(Couleur couleur) {
		super(couleur);
	}

	@Override
	public boolean attaquePossible(Coup coup, Echiquier echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	public boolean mouvementPossible(Coup coup, Echiquier echiquier) {
		Direction direction = PositionConverter.getDirection(
				coup.getCaseSource(), coup.getCaseDestination());

		if (direction == Direction.Colonne) {
			Sens sens = PositionConverter.getSensPourDirection(direction, coup,
					getColor());
			return echiquier.caseLignesIntermVides(coup.getCaseSource(),
					coup.getCaseDestination(), sens);
		}

		return false;
	}

}
