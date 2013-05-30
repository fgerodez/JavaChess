package com.javachess.board;

import java.util.ArrayList;
import java.util.List;

import com.javachess.helpers.BoardBuilder;
import com.javachess.moves.Move;
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
	private List<Move> moveList;

	public Board(Piece[][] board) {
		this.moveList = new ArrayList<Move>();
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

	public boolean check(Color color) {
		Square kingPos = findKing(color);

		for (Move move : moves(color.opponent())) {
			if (move.getDst().equals(kingPos))
				return true;
		}

		return false;
	}

	public boolean checkMate(Color color) {
		return check(color) && moveCount(color) == 0;
	}

	public Board copy() {
		return new Board(this.board);
	}

	private Square findKing(Color color) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (board[row][col] instanceof King
						&& board[row][col].isColor(color))
					return new Square(col, row);
			}
		}

		return null;
	}

	public Piece getPiece(Square square) {
		if (square == null || !square.isValid())
			return null;

		return board[square.getRow()][square.getCol()];
	}

	public boolean hasMoved(Piece piece) {
		for (Move move : moveList) {
			if (move.getSrcPiece().equals(piece))
				return true;
		}

		return false;
	}

	private boolean inRange(Square s) {
		return s.getCol() >= 0 && s.getCol() < 8 && s.getRow() >= 0
				&& s.getRow() < 8;
	}

	public boolean isFree(final Square square) {
		return inRange(square) && getPiece(square) == null;
	}

	public boolean isLegal(Move move) {
		Board copy = this.copy();
		copy.move(move);

		return !copy.check(move.getSrcPiece().getColor());
	}

	public boolean isRowFree(final Square row1, final Square row2, Color color) {
		if (row1.getRow() != row2.getRow())
			return false;

		Square start, end;

		if (row2.getCol() > row1.getCol()) {
			start = row2;
			end = row1;
		} else {
			start = row1;
			end = row2;
		}

		for (int i = 1; i < end.getCol() - start.getCol(); i++) {
			Square dst = new Square(start, i, 0);
			if (!isFree(dst) || isThreatened(dst, color))
				return false;
		}

		return true;
	}

	private boolean isThreatened(Square s, Color color) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Piece p = board[row][col];
				Square src = new Square(col, row);

				if (p != null && p.isColor(color) && p.canGoTo(src, s, this))
					return true;
			}
		}

		return false;
	}

	public Move lastMove() {
		if (moveList.size() == 0)
			return null;

		return moveList.get(moveList.size() - 1);
	}

	public void move(Move move) {
		moveList.add(move);
		move.execute(this);
	}

	public void move(Square src, Square dst) {
		Piece piece = getPiece(src);

		board[src.getRow()][src.getCol()] = null;
		board[dst.getRow()][dst.getCol()] = piece;
	}

	private List<Move> moves(Color color) {
		List<Move> allMoves = new ArrayList<Move>();

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Square src = new Square(col, row);
				Piece piece = getPiece(src);

				if (piece == null || !piece.isColor(color))
					continue;

				allMoves.addAll(piece.availableMoves(src, this));
			}
		}

		return allMoves;
	}

	public int moveCount(Color color) {
		return legalMoves(color).size();
	}

	public void setPiece(Square s, Piece p) {
		board[s.getRow()][s.getCol()] = p;
	}

	public boolean staleMate() {
		return moveCount(Color.WHITE) == 0 && moveCount(Color.BLACK) == 0;
	}

	public List<Move> legalMoves(Color color) {
		List<Move> allLegalMoves = new ArrayList<Move>();

		for (Move move : moves(color)) {
			if (isLegal(move))
				allLegalMoves.add(move);
		}

		return allLegalMoves;
	}

	public void undo() {
		moveList.remove(lastMove());
		lastMove().undo(this);
	}
}