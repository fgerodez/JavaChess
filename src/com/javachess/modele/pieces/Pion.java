package com.javachess.modele.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.PositionConverter;
import com.javachess.modele.plateau.Echiquier;
// TODO: déplacement initial de 2 cases permis.
public class Pion extends Piece {

	public Pion(Couleur couleur) {
		super(couleur);
	}

	@Override
	public boolean attaquePossible(Coup coup, Echiquier echiquier) {

		if (this.getColor() == Couleur.WHITE) {
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

		if (!echiquier.isCaseVide(coup.getCaseDestination()))
			return false;

		if (this.getColor() == Couleur.WHITE) {
			return coup.getCaseDestination().equals(
					PositionConverter.getCaseHaut(coup.getCaseSource()));
		} else {
			return coup.getCaseDestination().equals(
					PositionConverter.getCaseBas(coup.getCaseSource()));
		}
	}
}
