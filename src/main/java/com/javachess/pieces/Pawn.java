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
	public List<Square> availableMoves(Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		initialForward(board, availableMoves);

		forward(board, availableMoves);

		diagonal(board, availableMoves);

		return filterSameColor(availableMoves, board);
	}

	private void diagonal(Board board, List<Square> availableMoves) {
		Square diagLeft = board.atOffset(position, -1, 1 * color.dir());
		Square diagRight = board.atOffset(position, 1, 1 * color.dir());

		Piece diagLeftPiece = board.getPiece(diagLeft);
		Piece diagRightPiece = board.getPiece(diagRight);

		if (diagLeftPiece != null && !this.isSameColor(diagLeftPiece))
			nullSafeAdd(availableMoves, diagLeft);

		if (diagRightPiece != null && !this.isSameColor(diagRightPiece))
			nullSafeAdd(availableMoves, diagRight);
	}

	private void forward(Board board, List<Square> availableMoves) {
		nullSafeAdd(availableMoves,
				board.atOffset(position, 0, 1 * color.dir()));
	}

	private void initialForward(Board board, List<Square> availableMoves) {
		Square fwdSquare = board.atOffset(position, 0, 1 * color.dir());

		if (board.getPiece(fwdSquare) != null)
			return;

		if (position.equals(initialPosition))
			nullSafeAdd(availableMoves,
					board.atOffset(position, 0, 2 * color.dir()));
	}
}
