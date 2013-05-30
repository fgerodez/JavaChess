package com.javachess.moves;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.pieces.Piece;

public interface Move {
	public void execute(Board board);
	public void undo(Board board);
	
	public Piece getSrcPiece();
	public Piece getCapturedPiece();
	
	public Square getSrc();
	public Square getDst();
	
	public boolean isAttack();
}
