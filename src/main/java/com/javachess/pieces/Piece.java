package com.javachess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.moves.Move;
import com.javachess.moves.StandardMove;

/**
 * Cette classe représente le template d'une pièce d'échec. Chaque pièce est
 * représentée par une couleur et une position.
 * 
 * @author Ouzned
 */
public abstract class Piece {

	protected final Color color;
	protected final Square initialPosition;

	public Piece(final Color color, final Square position) {
		assert color != null;
		assert position != null;

		this.color = color;
		this.initialPosition = position;
	}

	protected abstract List<Move> availableMoves(Square src, final Board board);

	public boolean canGoTo(Square src, Square dst, Board board) {
		return availableMoves(src, board).contains(dst);
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

			if (square != null && piece == null) {
				availableMoves.add(new StandardMove(src, square, board));
				continue;
			}

			if (!this.isSameColor(piece))
				availableMoves.add(new StandardMove(src, square, board));

			break;
		}

		return availableMoves;
	}

	/*
	 * Getters & Setters
	 */

	public boolean isColor(final Color color) {
		return this.color.equals(color);
	}
	
	public Color getColor() {
		return this.color;
	}

	public Square getInitialPosition() {
		return initialPosition;
	}
}
