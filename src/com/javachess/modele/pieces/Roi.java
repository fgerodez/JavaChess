package com.javachess.modele.pieces;

import com.javachess.helpers.Color;
import com.javachess.helpers.Move;
import com.javachess.helpers.PositionConverter;
import com.javachess.jeu.Board;
import com.javachess.modele.plateau.Tile;

//TODO: le rock : le roi se d�place de deux cases en ligne vers la tour. Aucune case ne doit �tre menac�e et ni la tour ni le roi ne doivent avoir boug�s

public class Roi extends Piece {

	private boolean isEchec = false;

	public Roi(Color couleur, Tile position) {
		super(couleur, position);
	}

	@Override
	protected boolean attaquePossible(Move coup, Board echiquier) {
		return mouvementPossible(coup, echiquier);
	}

	@Override
	protected boolean mouvementPossible(Move coup, Board echiquier) {
		Tile caseSrc = coup.getCaseSource();
		Tile caseDest = coup.getCaseDestination();

		Tile[] casesPossible = PositionConverter.getCasesAdjacentes(caseSrc);

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
