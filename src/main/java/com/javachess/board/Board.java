package com.javachess.board;

import java.util.ArrayList;
import java.util.List;

import com.javachess.helpers.BoardBuilder;
import com.javachess.moves.StandardMove;
import com.javachess.pieces.King;
import com.javachess.pieces.Piece;

/**
 * Classe qui modélise un plateau d'echec
 * 
 * @author Ouzned
 * 
 */
public class Board {

	private final int width = 8;
	private final int height = 8;

	private Piece[][] board;
	private List<StandardMove> moveList;

	public Board(Piece[][] board) {
		this.moveList = new ArrayList<StandardMove>();
		this.board = new Piece[board.length][board.length];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
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

	public Piece getPiece(Square square) {
		if (square == null || !square.isValid())
			return null;

		return board[square.getRow()][square.getCol()];
	}

	public boolean check(Color color) {
		Square kingPos = findKing(color);

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Square src = new Square(col, row);
				Piece piece = getPiece(src);

				if (piece != null && piece.canGoTo(src, kingPos, this))
					return true;
			}
		}
		return false;
	}

	public StandardMove lastMove() {
		if (moveList.size() == 0)
			return null;

		return moveList.get(moveList.size() - 1);
	}

	public boolean isFree(final Square square) {
		return inRange(square) && getPiece(square) == null;
	}
	
	public boolean hasMoved(Piece piece) {
		for (StandardMove move : moveList) {
			if (move.getSrcPiece().equals(piece))
				return true;
		}
		
		return false;
	}

	public boolean isRowFree(final Square start, final Square end) {
		if (start.getRow() != end.getRow() || start.getCol() >= end.getCol())
			return false;

		for (int i = 1; i < end.getCol() - start.getCol(); i++) {
			if (!isFree(new Square(start,i,0)))
					return false;
		}

		return true;
	}

	public boolean checkMate(Color color) {
		return check(color) && moveCount(color) == 0;
	}

	public boolean staleMate() {
		return moveCount(Color.WHITE) == 0 && moveCount(Color.BLACK) == 0;
	}

	public int moveCount(Color color) {
		List<Square> allMoves = new ArrayList<Square>();

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Square src = new Square(col, row);
				Piece piece = getPiece(src);

				if (piece == null || !piece.isColor(color))
					continue;

				// allMoves.addAll(piece.(src, this));
			}
		}

		return allMoves.size();
	}

	public boolean isLegal(StandardMove move) {
		Board copy = this.copy();
		copy.move(move);

		return !copy.check(move.getSrcPiece().getColor());
	}

	public boolean inRange(Square s) {
		return s.getCol() >= 0 && s.getCol() < 8 && s.getRow() >= 0
				&& s.getRow() < 8;
	}

	public Square findKing(Color color) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (board[row][col] instanceof King
						&& board[row][col].isColor(color))
					return new Square(col, row);
			}
		}

		return null;
	}

	public void move(StandardMove move) {
		moveList.add(move);
		move.execute(this);
	}

	public void move(Square src, Square dst) {
		Piece piece = getPiece(src);

		board[src.getRow()][src.getCol()] = null;
		board[dst.getRow()][dst.getCol()] = piece;
	}
}
