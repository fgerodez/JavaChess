package com.javachess.board;

import com.javachess.exceptions.GameException;
import com.javachess.helpers.BoardBuilder;
import com.javachess.pieces.King;
import com.javachess.pieces.Piece;

/**
 * Classe qui modélise un plateau d'echec
 * 
 * @author Ouzned
 * 
 */
public class Board {

	private Piece[][] board;

	public Board(Piece[][] board) {
		this.board = new Piece[board.length][board.length];
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				this.board[row][col] = board[row][col];
			}
		}
	}

	public Board() {
		this(BoardBuilder.CLASSIC);
	}
	
	public Board copy() {
		return new Board(this.board);
	}

	public Square atOffset(Square square, int colOffset, int rowOffset) {
		Square newSquare = new Square(square.getColumn() + colOffset,
				square.getRow() + rowOffset);

		if (newSquare.isValid())
			return newSquare;

		return null;
	}

	public Piece getPiece(Square square) {
		if (square == null || !square.isValid())
			return null;

		return board[square.getRow() - 1][square.getColumn() - 1];
	}

	public Square findKing(Color color) throws GameException {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] != null && board[row][col].isColor(color)
						&& board[row][col] instanceof King)
					return new Square(col + 1, row + 1);
			}
		}

		throw new GameException("Can't find king for color " + color);
	}

	public void move(Square src, Square dst) throws GameException {
		checkSquare(src);
		checkSquare(dst);

		Piece piece = getPiece(src);

		board[src.getRow() - 1][src.getColumn() - 1] = null;
		board[dst.getRow() - 1][dst.getColumn() - 1] = piece;
	}

	private void checkSquare(Square square) throws GameException {
		if (square == null || !square.isValid())
			throw new GameException("Incorrect square provided : " + square);
	}
}
