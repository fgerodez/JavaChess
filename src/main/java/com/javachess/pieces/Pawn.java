package com.javachess.pieces;

import static com.javachess.helpers.Utils.nullSafeAdd;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;

public class Pawn extends Piece {

	public Pawn(Color color, Square position) {
		super(color, position);
	}

	@Override
	public List<Square> availableMoves(Square src, final Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		availableMoves.addAll(forward(src, board));
		availableMoves.addAll(diagonal(src, board));

		return filterSameColor(availableMoves, board);
	}

	private List<Square> diagonal(Square src, Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		Square diagLeft = board.atOffset(src, -1, 1 * color.dir());
		Square diagRight = board.atOffset(src, 1, 1 * color.dir());

		Piece diagLeftPiece = board.getPiece(diagLeft);
		Piece diagRightPiece = board.getPiece(diagRight);

		if (diagLeftPiece != null && !this.isSameColor(diagLeftPiece))
			nullSafeAdd(availableMoves, diagLeft);

		if (diagRightPiece != null && !this.isSameColor(diagRightPiece))
			nullSafeAdd(availableMoves, diagRight);

		return availableMoves;
	}

	private List<Square> forward(Square src, final Board board) {
		List<Square> availableMoves = new ArrayList<Square>();
		Square fwdSquare = board.atOffset(src, 0, 1 * color.dir());
		Square fwd2Square = board.atOffset(src, 0, 2 * color.dir());

		if (board.getPiece(fwdSquare) != null)
			return availableMoves;

		nullSafeAdd(availableMoves, fwdSquare);

		if (src.equals(initialPosition) && board.getPiece(fwd2Square) == null)
			nullSafeAdd(availableMoves, fwd2Square);

		return availableMoves;
	}
}
