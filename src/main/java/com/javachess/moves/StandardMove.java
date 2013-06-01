package com.javachess.moves;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.pieces.Pawn;
import com.javachess.pieces.Piece;

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

		if (isPromotion() && board.getPromotionDelegate() != null) {
			Piece promoted = board.getPromotionDelegate().getPromotion().create(srcPiece.getColor());
			board.setPiece(dst, promoted);
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
		return srcPiece instanceof Pawn && (dst.getCol() == 7 || dst.getCol() == 0);
	}
}
