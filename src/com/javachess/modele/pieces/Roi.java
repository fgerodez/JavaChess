package com.javachess.modele.pieces;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.PositionConverter;
import com.javachess.modele.plateau.Case;
import com.javachess.modele.plateau.Echiquier;

//TODO: le rock : le roi se déplace de deux cases en ligne vers la tour. Aucune case ne doit être menacée et ni la tour ni le roi ne doivent avoir bougés

public class Roi extends Piece {

	private boolean isEchec = false;

	public Roi(Couleur couleur, Case position) {
		super(couleur, position);
	}

	@Override
	public boolean attaquePossible(Coup coup, Echiquier echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	public boolean mouvementPossible(Coup coup, Echiquier echiquier) {
		Case caseSrc = coup.getCaseSource();
		Case caseDest = coup.getCaseDestination();

		Case[] casesPossible = PositionConverter.getCasesAdjacentes(caseSrc);

		for (int index = 0; index < casesPossible.length; index++) {
			if (casesPossible[index].equals(caseDest))
				return true;
		}

		return false;
	}

	public boolean isEchec() {
		return isEchec;
	}

	public void setEchec(boolean isEchec) {
		this.isEchec = isEchec;
	}
}
