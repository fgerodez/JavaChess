package com.javachess.modele.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.helpers.Color;
import com.javachess.helpers.Utils;
import com.javachess.modele.plateau.Board;
import com.javachess.modele.plateau.Square;

public class Cavalier extends Piece {

	public Cavalier(Color couleur, Square position) {
		super(couleur, position);
	}

	@Override
	public List<Square> availableMoves(final Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 1, 2));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 1, -2));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, -1, 2));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, -1, -2));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 2, 1));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 2, -1));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, -2, 1));
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, -2, -1));

		filterSameColorSquares(availableMoves, board);

		return availableMoves;
	}
}
