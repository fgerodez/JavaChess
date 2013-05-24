package com.javachess.board;

import com.javachess.pieces.Bishop;
import com.javachess.pieces.King;
import com.javachess.pieces.Knight;
import com.javachess.pieces.Pawn;
import com.javachess.pieces.Piece;
import com.javachess.pieces.Queen;
import com.javachess.pieces.Tower;

/**
 * Classe qui modélise un plateau d'echec
 * 
 * @author Ouzned
 * 
 */
public class Board {

	private Piece[][] board;

	public Board() {
		super();

		Piece[][] board = {
				{ new Tower(Color.WHITE, new Square(1, 1)),
						new Knight(Color.WHITE, new Square(2, 1)),
						new Bishop(Color.WHITE, new Square(3, 1)),
						new Queen(Color.WHITE, new Square(4, 1)),
						new King(Color.WHITE, new Square(5, 1)),
						new Bishop(Color.WHITE, new Square(6, 1)),
						new Knight(Color.WHITE, new Square(7, 1)),
						new Tower(Color.WHITE, new Square(8, 1)) },
				{ new Pawn(Color.WHITE, new Square(1, 2)),
						new Pawn(Color.WHITE, new Square(2, 2)),
						new Pawn(Color.WHITE, new Square(3, 2)),
						new Pawn(Color.WHITE, new Square(4, 2)),
						new Pawn(Color.WHITE, new Square(5, 2)),
						new Pawn(Color.WHITE, new Square(6, 2)),
						new Pawn(Color.WHITE, new Square(7, 2)),
						new Pawn(Color.WHITE, new Square(8, 2)) },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ new Pawn(Color.BLACK, new Square(1, 7)),
						new Pawn(Color.BLACK, new Square(2, 7)),
						new Pawn(Color.BLACK, new Square(3, 7)),
						new Pawn(Color.BLACK, new Square(4, 7)),
						new Pawn(Color.BLACK, new Square(5, 7)),
						new Pawn(Color.BLACK, new Square(6, 7)),
						new Pawn(Color.BLACK, new Square(7, 7)),
						new Pawn(Color.BLACK, new Square(8, 7)) },
				{ new Tower(Color.BLACK, new Square(1, 8)),
						new Knight(Color.BLACK, new Square(2, 8)),
						new Bishop(Color.BLACK, new Square(3, 8)),
						new King(Color.BLACK, new Square(4, 8)),
						new Queen(Color.BLACK, new Square(5, 8)),
						new Bishop(Color.BLACK, new Square(6, 8)),
						new Knight(Color.BLACK, new Square(7, 8)),
						new Tower(Color.BLACK, new Square(8, 8)) } };

		this.board = board;
	}

	public Square atOffset(Square square, int colOffset, int rowOffset) {
		Square newSquare = new Square(square.getColumn() + colOffset, square.getRow()
				+ rowOffset);

		if (newSquare.isValid())
			return newSquare;

		return null;
	}
	
	public Piece getPiece(Square square) {
		if (square == null)
			return null;
		
		return board[square.getRow()-1][square.getColumn()-1];
	}
}
