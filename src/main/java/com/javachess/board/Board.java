package com.javachess.board;

import java.util.ArrayList;
import java.util.List;

import com.javachess.moves.Move;
import com.javachess.pieces.Bishop;
import com.javachess.pieces.King;
import com.javachess.pieces.Knight;
import com.javachess.pieces.Pawn;
import com.javachess.pieces.Piece;
import com.javachess.pieces.Queen;
import com.javachess.pieces.Rook;

/**
 * Chess board representation
 * 
 * @author Ouzned
 * 
 */
public class Board {

	private final int rows = 8;
	private final int cols = 8;

	protected Piece[][] board;
	protected List<Move> moveList;

	private PromotionDelegate promotionDelegate;
	
	public Board(PromotionDelegate delegate) {
		promotionDelegate = delegate;
		moveList = new ArrayList<Move>();
		init();
	}
	
	public Board() {
		this(null);
	}

	// only used for copying boards
	private Board(Piece[][] board, List<Move> moveList) {
		this.moveList = moveList;
		this.board = new Piece[board.length][board.length];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				this.board[row][col] = board[row][col];
			}
		}
	}

	// this method can be overridden for testing purposes
	protected void init() {
		board = new Piece[rows][cols];

		for (int col = 0; col < cols; col++) {
			board[1][col] = new Pawn(Color.WHITE);
			board[6][col] = new Pawn(Color.BLACK);
		}

		setUpKingRow(0, Color.WHITE);
		setUpKingRow(7, Color.BLACK);
	}

	private void setUpKingRow(int row, Color color) {
		board[row][0] = new Rook(color);
		board[row][1] = new Knight(color);
		board[row][2] = new Bishop(color);
		board[row][3] = new Queen(color);
		board[row][4] = new King(color);
		board[row][5] = new Bishop(color);
		board[row][6] = new Knight(color);
		board[row][7] = new Rook(color);
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
		return new Board(board, moveList);
	}

	private Square findKing(Color color) {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (board[row][col] instanceof King && board[row][col].isColor(color))
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
	
	public PromotionDelegate getPromotionDelegate() {
		return this.promotionDelegate;
	}

	public boolean hasMoved(Piece piece) {
		for (Move move : moveList) {
			if (move.getSrcPiece() == piece)
				return true;
		}

		return false;
	}

	private boolean inRange(Square s) {
		return s.getCol() >= 0 && s.getCol() < cols && s.getRow() >= 0 && s.getRow() < rows;
	}

	public boolean isFree(final Square square) {
		return inRange(square) && getPiece(square) == null;
	}

	public boolean isLegal(Move move) {
		Board copy = this.copy();
		copy.move(move);

		return !copy.check(move.getSrcPiece().getColor());
	}

	public boolean isRowFree(final Square row1, final Square row2, Color opponent) {
		if (row1.getRow() != row2.getRow())
			return false;

		Square start, end;

		if (row2.getCol() > row1.getCol()) {
			start = row1;
			end = row2;
		} else {
			start = row2;
			end = row1;
		}

		for (int i = 1; i < end.getCol() - start.getCol(); i++) {
			Square dst = new Square(start, i, 0);
			if (!isFree(dst) || isThreatened(dst, opponent))
				return false;
		}

		return true;
	}

	private boolean isThreatened(Square s, Color color) {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
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

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
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
	
	public void setPromotionDelegate(PromotionDelegate promotionDelegate) {
		this.promotionDelegate = promotionDelegate;
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
		lastMove().undo(this);
		moveList.remove(lastMove());
	}
}