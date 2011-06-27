package com.javachess.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.Direction;
import com.javachess.helpers.PositionConverter;
import com.javachess.jeu.Case;
import com.javachess.jeu.Echiquier;

public class Cavalier extends Piece {

	public Cavalier(Couleur couleur, Case position) {
		super(couleur, position);
	}

	@Override
	protected boolean attaquePossible(Coup coup, Echiquier echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	protected boolean mouvementPossible(Coup coup, Echiquier echiquier) {
		Case caseSrc = coup.getCaseSource();
		Case caseDest = coup.getCaseDestination();

		Direction direction = PositionConverter.getDirection(coup);

		if (direction == Direction.AUTRE) {
			int diffLigne = Math.abs(caseDest.getLigne() - caseSrc.getLigne());
			int diffCol = Math
					.abs(caseDest.getColonne() - caseSrc.getColonne());

			return diffLigne + diffCol == 3;
		}

		return false;
	}

}
