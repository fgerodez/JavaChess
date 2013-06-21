package com.javachess.move;

import com.javachess.board.Board;
import com.javachess.board.Square;

public abstract class EnPassant extends StandardMove {

	protected Square capturedPieceSquare;
	
	public EnPassant(Square srcSquare, Board board) {
		super(srcSquare, null, board);

		dstSquare = getDstSquare(srcSquare);
		capturedPieceSquare = getCapturedSquare(srcSquare);
	}
	
	protected abstract Square getDstSquare(Square srcSquare);
	
	protected abstract Square getCapturedSquare(Square srcSquare);
	
	@Override
	public void execute() {
		capturedPiece = board.at(capturedPieceSquare);
		
		board.movePiece(srcSquare, dstSquare);
		board.removePieceAt(capturedPieceSquare);
	}

	@Override
	public void undo() {
		board.movePiece(dstSquare, srcSquare);
		board.setPieceAt(capturedPieceSquare, capturedPiece);
	}
}
