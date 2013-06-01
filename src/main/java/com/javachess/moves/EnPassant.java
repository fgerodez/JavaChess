package com.javachess.moves;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.pieces.Piece;

public class EnPassant extends StandardMove {

	private final Piece capturedPiece;
	private final Square capturedSquare;

	public EnPassant(Square srcSquare, Square dstSquare, Square targetSquare, Board board) {
		super(srcSquare, dstSquare, board);

		this.capturedPiece = board.getPiece(targetSquare);
		this.capturedSquare = targetSquare;
	}

	@Override
	public void execute(Board board) {
		super.execute(board);
		board.setPiece(capturedSquare, null);
	}

	@Override
	public void undo(Board board) {
		super.undo(board);
		board.setPiece(capturedSquare, capturedPiece);
	}

	@Override
	public boolean isAttack() {
		return true;
	}

	@Override
	public Piece getCapturedPiece() {
		return capturedPiece;
	}
}
