package com.javachess.modele.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.Direction;
import com.javachess.helpers.PositionConverter;
import com.javachess.helpers.Sens;
import com.javachess.modele.plateau.Case;
import com.javachess.modele.plateau.Echiquier;

public class Fou extends Piece {

	public Fou(Couleur couleur, Case position) {
		super(couleur, position);
	}

	@Override
	public boolean attaquePossible(Coup coup, Echiquier echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	public boolean mouvementPossible(Coup coup, Echiquier echiquier) {

		if (PositionConverter.getDirection(coup) == Direction.AUTRE)
			return false;

		Sens sens = PositionConverter.getSensCoup(coup);

		if (sens == Sens.DIAGBASDROITE || sens == Sens.DIAGBASGAUCHE
				|| sens == Sens.DIAGHAUTDROITE || sens == Sens.DIAGHAUTGAUCHE) {

			return echiquier.caseIntermVides(coup.getCaseSource(),
					coup.getCaseDestination(), sens);
		}

		return false;
	}

}
