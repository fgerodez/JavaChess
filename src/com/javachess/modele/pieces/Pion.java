package com.javachess.modele.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.PositionConverter;
import com.javachess.modele.plateau.Case;
import com.javachess.modele.plateau.Echiquier;

public class Pion extends Piece {

	public Pion(Couleur couleur, Case position) {
		super(couleur, position);
	}

	@Override
	public boolean attaquePossible(Coup coup, Echiquier echiquier) {

		if (this.getColor() == Couleur.BLANC) {
			return coup.getCaseDestination().equals(
					PositionConverter.getCaseDiagHautDroite(coup
							.getCaseSource()))
					|| coup.getCaseDestination().equals(
							PositionConverter.getCaseDiagHautGauche(coup
									.getCaseSource()));
		} else {
			return coup.getCaseDestination()
					.equals(PositionConverter.getCaseDiagBasDroite(coup
							.getCaseSource()))
					|| coup.getCaseDestination().equals(
							PositionConverter.getCaseDiagBasGauche(coup
									.getCaseSource()));
		}
	}

	@Override
	public boolean mouvementPossible(Coup coup, Echiquier echiquier) {

		if (this.getColor() == Couleur.BLANC) {
			if (getPositionInitiale().equals(getPosition())) {
				if (coup.getCaseDestination().equals(PositionConverter.getCaseHaut(coup.getCaseSource(), 2)))
					return true;
			}
			
			return coup.getCaseDestination().equals(
					PositionConverter.getCaseHaut(coup.getCaseSource()));
		} else {
			if (getPositionInitiale().equals(getPosition())) {
				if (coup.getCaseDestination().equals(PositionConverter.getCaseBas(coup.getCaseSource(), 2)))
					return true;
			}
			
			return coup.getCaseDestination().equals(
					PositionConverter.getCaseBas(coup.getCaseSource()));
		}
	}
}
