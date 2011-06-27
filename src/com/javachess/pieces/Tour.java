package com.javachess.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.PositionConverter;
import com.javachess.helpers.Sens;
import com.javachess.jeu.Case;
import com.javachess.jeu.Echiquier;
//TODO : le rock
public class Tour extends Piece {

	public Tour(Couleur couleur, Case position) {
		super(couleur, position);
	}

	@Override
	protected boolean attaquePossible(Coup coup, Echiquier echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	protected boolean mouvementPossible(Coup coup, Echiquier echiquier) {
				
		Sens sens = PositionConverter.getSensCoup(coup);

		if (sens == Sens.HAUT || sens == Sens.BAS || sens == Sens.DROITE
				|| sens == Sens.GAUCHE) {

			return echiquier.caseIntermVides(coup.getCaseSource(),
					coup.getCaseDestination(), sens);
		}

		return false;
	}

}
