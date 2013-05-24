package com.javachess.pieces;

import static com.javachess.helpers.Utils.nullSafeAdd;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;

public class Knight extends Piece {

	public Knight(Color couleur, Square position) {
		super(couleur, position);
	}

	@Override
	public List<Square> availableMoves(final Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		nullSafeAdd(availableMoves, board.atOffset(position, 1, 2));
		nullSafeAdd(availableMoves, board.atOffset(position, 1, -2));
		nullSafeAdd(availableMoves, board.atOffset(position, -1, 2));
		nullSafeAdd(availableMoves, board.atOffset(position, -1, -2));
		nullSafeAdd(availableMoves, board.atOffset(position, 2, 1));
		nullSafeAdd(availableMoves, board.atOffset(position, 2, -1));
		nullSafeAdd(availableMoves, board.atOffset(position, -2, 1));
		nullSafeAdd(availableMoves, board.atOffset(position, -2, -1));

		return filterSameColor(availableMoves, board);
	}
}
