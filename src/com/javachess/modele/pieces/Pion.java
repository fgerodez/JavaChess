package com.javachess.modele.pieces;

import com.javachess.helpers.Color;
import com.javachess.helpers.Move;
import com.javachess.helpers.PositionConverter;
import com.javachess.jeu.Board;
import com.javachess.modele.plateau.Tile;

public class Pion extends Piece {

	public Pion(Color couleur, Tile position) {
		super(couleur, position);
	}

	@Override
	protected boolean attaquePossible(Move coup, Board echiquier) {

		if (this.getColor() == Color.BLANC) {
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
	protected boolean mouvementPossible(Move coup, Board echiquier) {

		if (this.getColor() == Color.BLANC) {
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
