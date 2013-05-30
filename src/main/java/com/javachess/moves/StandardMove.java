package com.javachess.moves;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.pieces.Piece;

/**
 * Modélisation d'un coup d'une case source vers une case cible
 * 
 * @author Ouzned
 * 
 */
public class StandardMove implements Move {
	private final Square src;
	private final Square dst;
	private final Piece srcPiece;
	private final Piece dstPiece;

	public StandardMove(Square srcSquare, Square dstSquare, Board board) {
		this.src = srcSquare;
		this.dst = dstSquare;
		this.srcPiece = board.getPiece(srcSquare);
		this.dstPiece = board.getPiece(dstSquare);
	}

	@Override
	public void execute(Board board) {
		board.move(src, dst);
	}

	@Override
	public void undo(Board board) {
		board.move(dst, src);
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
}
