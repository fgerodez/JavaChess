package com.javachess.moves;

import com.javachess.boards.Board;
import com.javachess.boards.Square;
import com.javachess.pieces.Piece;

public class Castling implements Move {

	private Piece king;
	private Square kingSrc;
	private Square kingDst;

	private Square rookSrc;
	private Square rookDst;

	public Castling(Square kingSrc, Square rookSrc, Square kingDst, Square rookDst, Board board) {
		this.king = board.getPiece(kingSrc);
		this.kingSrc = kingSrc;
		this.kingDst = kingDst;

		this.rookSrc = rookSrc;
		this.rookDst = rookDst;
	}

	@Override
	public void execute(Board board) {
		board.move(kingSrc, kingDst);
		board.move(rookSrc, rookDst);
	}

	@Override
	public void undo(Board board) {
		board.move(kingDst, kingSrc);
		board.move(rookDst, rookSrc);
	}

	@Override
	public Piece getSrcPiece() {
		return king;
	}

	@Override
	public Piece getCapturedPiece() {
		return null;
	}

	@Override
	public Square getSrc() {
		return kingSrc;
	}

	@Override
	public Square getDst() {
		return kingDst;
	}

	@Override
	public boolean isAttack() {
		return false;
	}

	@Override
	public boolean isPromotion() {
		return false;
	}

	@Override
	public boolean equals(Square src, Square dst) {
		return getSrc().equals(src) && getDst().equals(dst);
	}
}
