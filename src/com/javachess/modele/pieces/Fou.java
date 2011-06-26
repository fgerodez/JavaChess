package com.javachess.modele.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.PositionConverter;
import com.javachess.helpers.Sens;
import com.javachess.modele.plateau.Echiquier;

public class Fou extends Piece {

	public Fou(Couleur couleur) {
		super(couleur);
	}

	@Override
	public boolean attaquePossible(Coup coup, Echiquier echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	public boolean mouvementPossible(Coup coup, Echiquier echiquier) {
		Sens sens = PositionConverter.getSensCoup(coup);

		if (sens == Sens.DiagBasDroite || sens == Sens.DiagBasGauche
				|| sens == Sens.DiagHautDroite || sens == Sens.DiagHautGauche) {

			return echiquier.caseLignesIntermVides(coup.getCaseSource(),
					coup.getCaseDestination(), sens);
		}

		return false;
	}

}
