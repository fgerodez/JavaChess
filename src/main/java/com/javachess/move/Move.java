package com.javachess.move;

import com.javachess.board.Square;
import com.javachess.piece.Piece;

public interface Move {
	public void execute();
	public void undo();
	
	public Piece getCapturedPiece();
	
	public Square getSource();
	public Square getDst();
	
	public boolean equals(Square src, Square dst);
}
