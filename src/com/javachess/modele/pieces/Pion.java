package com.javachess.modele.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.helpers.Color;
import com.javachess.helpers.Utils;
import com.javachess.modele.plateau.Board;
import com.javachess.modele.plateau.Square;

public class Pion extends Piece {

	public Pion(Color color, Square position) {
		super(color, position);
	}

	@Override
	public List<Square> availableMoves(Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		initialForwardMove(availableMoves, board);

		forwardMove(board, availableMoves);

		diagonalMoves(board, availableMoves);

		filterSameColorSquares(availableMoves, board);

		return availableMoves;
	}

	private void diagonalMoves(Board board, List<Square> availableMoves) {
		Square diagonalLeft = board.getSquareAtOffset(position, -1,
				1 * color.getFwdModifier());

		Square diagonalRight = board.getSquareAtOffset(position, 1,
				1 * color.getFwdModifier());

		if (!this.isSameColor(board.getPiece(diagonalLeft)))
			Utils.nullSafeAdd(availableMoves, diagonalLeft);

		if (!this.isSameColor(board.getPiece(diagonalRight)))
			Utils.nullSafeAdd(availableMoves, diagonalRight);
	}

	private void forwardMove(Board board, List<Square> availableMoves) {
		Utils.nullSafeAdd(availableMoves,
				board.getSquareAtOffset(position, 0, 1 * color.getFwdModifier()));
	}

	private void initialForwardMove(List<Square> availableMoves, Board board) {
		if (position.equals(initialPosition))
			Utils.nullSafeAdd(
					availableMoves,
					board.getSquareAtOffset(position, 0,
							2 * color.getFwdModifier()));
	}
}
