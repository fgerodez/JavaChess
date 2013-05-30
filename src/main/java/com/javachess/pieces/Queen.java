package com.javachess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.moves.Move;

public class Queen extends Piece {

	public Queen(Color couleur) {
		super(couleur);
	}

	@Override
	public List<Move> availableMoves(Square src, Board board) {
		List<Move> availableMoves = new ArrayList<Move>();

		// Diagonal movements
		availableMoves.addAll(iterateDirection(src, -1, -1, board));
		availableMoves.addAll(iterateDirection(src, 1, -1, board));
		availableMoves.addAll(iterateDirection(src, -1, 1, board));
		availableMoves.addAll(iterateDirection(src, 1, 1, board));

		// Vertical movements
		availableMoves.addAll(iterateDirection(src, 0, 1, board));
		availableMoves.addAll(iterateDirection(src, 0, -1, board));

		// Horizontal movements
		availableMoves.addAll(iterateDirection(src, 1, 0, board));
		availableMoves.addAll(iterateDirection(src, -1, 0, board));

		return availableMoves;
	}
}
