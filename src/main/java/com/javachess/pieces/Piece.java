package com.javachess.pieces;

import static com.javachess.helpers.Utils.nullSafeAdd;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;

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

	public abstract List<Square> availableMoves(Square src, final Board board);

	public boolean canGoTo(Square src, Square dst, Board board) {
		return availableMoves(src, board).contains(dst);
	}

	public boolean isSameColor(final Piece piece) {
		if (piece == null)
			return false;

		return isColor(piece.color);
	}

	protected List<Square> filterSameColor(List<Square> squares,
			final Board board) {
		List<Square> filteredSquares = new ArrayList<Square>();

		for (Square square : squares) {
			if (this.isSameColor(board.getPiece(square)))
				continue;

			filteredSquares.add(square);
		}

		return filteredSquares;
	}

	protected List<Square> iterateDirection(final Square src, final int colOff,
			final int rowOff, final Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		for (int i = 1;; i++) {
			Square square = board.atOffset(src, colOff * i, rowOff * i);
			Piece piece = board.getPiece(square);

			if (square != null && piece == null) {
				nullSafeAdd(availableMoves, square);
				continue;
			}

			if (!this.isSameColor(piece))
				nullSafeAdd(availableMoves, square);

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

	public Square getInitialPosition() {
		return initialPosition;
	}
}
