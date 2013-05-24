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
	protected Square position;

	protected final Color color;
	protected final Square initialPosition;

	public Piece(Color color, Square position) {
		assert color != null;
		assert position != null;

		this.color = color;
		this.position = position;
		this.initialPosition = position;
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

	protected void iterateDirection(List<Square> availableMoves, int colOffset,
			int rowOffset, Board board) {

		for (int i = 1;; i++) {
			Square square = board.atOffset(position, colOffset * i, rowOffset
					* i);
			Piece piece = board.getPiece(square);

			if (square != null && piece == null) {
				nullSafeAdd(availableMoves, square);
				continue;
			}

			if (!this.isSameColor(piece))
				nullSafeAdd(availableMoves, square);

			break;
		}
	}

	public abstract List<Square> availableMoves(final Board board);

	public boolean isSameColor(Piece piece) {
		if (piece == null)
			return false;

		return color.equals(piece.color);
	}

	/*
	 * Getters & Setters
	 */

	public Square getPosition() {
		return position;
	}

	public void setPosition(Square position) {
		this.position = position;
	}

	public Square getInitialPosition() {
		return initialPosition;
	}
}
