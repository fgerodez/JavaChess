package com.javachess.modele.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.helpers.Color;
import com.javachess.helpers.Utils;
import com.javachess.modele.plateau.Board;
import com.javachess.modele.plateau.Square;

public class Roi extends Piece {

	private boolean isEchec = false;

	public Roi(Color couleur, Square position) {
		super(couleur, position);
	}	

	@Override
	public List<Square> availableMoves(Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 0, 1));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 0, -1));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 1, 0));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, -1, 0));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, -1, 1));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, -1, -1));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 1, 1));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 1, -1));

		filterSameColorSquares(availableMoves, board);

		return availableMoves;
	}

	public boolean isEchec() {
		return isEchec;
	}

	public void setEchec(boolean isEchec) {
		this.isEchec = isEchec;
	}
}
