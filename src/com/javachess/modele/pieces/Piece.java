package com.javachess.modele.pieces;

import java.util.List;

import com.javachess.helpers.Color;
import com.javachess.helpers.Utils;
import com.javachess.modele.plateau.Board;
import com.javachess.modele.plateau.Square;

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

	protected void filterSameColorSquares(List<Square> squares,
			final Board board) {
		for (Square square : squares) {
			if (this.isSameColor(board.getPiece(square)))
				squares.remove(square);
		}
	}

	protected void iterateDirection(List<Square> availableMoves, int colOffset,
			int rowOffset, Board board) {

		for (int i = 1;; i++) {
			Square square = board.getSquareAtOffset(position, colOffset * i,
					rowOffset * i);
			Piece piece = board.getPiece(square);

			if (square != null && piece == null) {
				Utils.nullSafeAdd(availableMoves, square);
				continue;
			}

			if (!this.isSameColor(piece))
				Utils.nullSafeAdd(availableMoves, square);

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
