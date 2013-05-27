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
	
	private StandardMove next;
	
	public StandardMove(Square srcSquare, Square dstSquare, Board board) {
		this.src = srcSquare;
		this.dst = dstSquare;
		this.srcPiece = board.getPiece(srcSquare);
		this.dstPiece = board.getPiece(dstSquare);
	}
	
	@Override
	public void execute(Board board) {
		
	}
	
	@Override
	public void undo(Board board) {
		
	}

	public void setNext(StandardMove next) {
		this.next = next;
	}
	
	public StandardMove getNext() {
		return next;
	}
	
	public Square getSrc() {
		return src;
	}

	public Square getDst() {
		return dst;
	}

	public Piece getSrcPiece() {
		return srcPiece;
	}

	public Piece getDstPiece() {
		return dstPiece;
	}
}
