package com.javachess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.moves.Move;
import com.javachess.moves.StandardMove;

public abstract class Piece {

	protected final Color color;

	public Piece(final Color color) {
		assert color != null;

		this.color = color;
	}

	public abstract List<Move> availableMoves(Square src, final Board board);

	public boolean canGoTo(Square src, Square dst, Board board) {
		for (Move move : availableMoves(src, board)) {
			if (move.getDst().equals(dst))
				return true;
		}
		
		return false;
	}

	protected List<Move> filterValid(List<Move> moves, Board board) {
		List<Move> filteredMoves = new ArrayList<Move>();

		for (Move move : moves) {
			if (move.getDst().isValid()
					&& !this.isSameColor(board.getPiece(move.getDst())))
				filteredMoves.add(move);
		}

		return filteredMoves;
	}

	public Color getColor() {
		return this.color;
	}

	public boolean isColor(final Color color) {
		return this.color.equals(color);
	}

	public boolean isSameColor(final Piece piece) {
		if (piece == null)
			return false;

		return isColor(piece.color);
	}

	protected List<Move> iterateDirection(final Square src, final int colOff,
			final int rowOff, final Board board) {
		List<Move> availableMoves = new ArrayList<Move>();

		for (int i = 1;; i++) {
			Square square = new Square(src, colOff * i, rowOff * i);
			Piece piece = board.getPiece(square);

			if (square.isValid() && piece == null) {
				availableMoves.add(new StandardMove(src, square, board));
				continue;
			}

			if (square.isValid() && !this.isSameColor(piece))
				availableMoves.add(new StandardMove(src, square, board));

			break;
		}

		return availableMoves;
	}
}
