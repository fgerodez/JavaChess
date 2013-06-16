package com.javachess.moves;

import com.javachess.boards.Board;
import com.javachess.boards.Square;
import com.javachess.pieces.Pawn;
import com.javachess.pieces.Piece;
import com.javachess.pieces.Queen;

/**
 * Modélisation d'un coup d'une case source vers une case cible
 * 
 * @author Ouzned
 * 
 */
public class StandardMove implements Move {
	protected final Square src;
	protected final Square dst;
	protected final Piece srcPiece;
	protected final Piece dstPiece;

	public StandardMove(Square srcSquare, Square dstSquare, Board board) {
		this.src = srcSquare;
		this.dst = dstSquare;
		this.srcPiece = board.getPiece(srcSquare);
		this.dstPiece = board.getPiece(dstSquare);
	}

	@Override
	public void execute(Board board) {
		board.setPiece(dst, srcPiece);
		board.setPiece(src, null);

		if (isPromotion()) {
			board.setPiece(dst, new Queen(srcPiece.getColor()));
		}
	}

	@Override
	public void undo(Board board) {
		board.setPiece(src, srcPiece);
		board.setPiece(dst, dstPiece);
	}

	@Override
	public Square getSrc() {
		return src;
	}

	@Override
	public Square getDst() {
		return dst;
	}

	@Override
	public Piece getSrcPiece() {
		return srcPiece;
	}

	@Override
	public Piece getCapturedPiece() {
		return dstPiece;
	}

	@Override
	public boolean isAttack() {
		return dstPiece != null;
	}

	@Override
	public boolean isPromotion() {
		return srcPiece instanceof Pawn && (dst.getRow() == 7 || dst.getRow() == 0);
	}

	@Override
	public boolean equals(Square src, Square dst) {
		return getSrc().equals(src) && getDst().equals(dst);
	}
}
