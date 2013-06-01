package com.javachess.board;

import com.javachess.pieces.Bishop;
import com.javachess.pieces.Knight;
import com.javachess.pieces.Piece;
import com.javachess.pieces.Queen;
import com.javachess.pieces.Rook;

public enum PromotionPiece {
	KNIGHT, BISHOP, QUEEN, ROOK;
	
	public Piece create(Color color) {
		switch(this) {
		case KNIGHT:
			return new Knight(color);
		case BISHOP:
			return new Bishop(color);
		case QUEEN:
			return new Queen(color);
		case ROOK:
			return new Rook(color);
		default:
			return new Queen(color);
		}	
	}
}
