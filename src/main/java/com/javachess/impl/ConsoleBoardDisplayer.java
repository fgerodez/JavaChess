package com.javachess.impl;

import com.javachess.boards.Board;
import com.javachess.boards.BoardDisplayer;
import com.javachess.boards.Square;
import com.javachess.pieces.Piece;

public class ConsoleBoardDisplayer implements BoardDisplayer {

	@Override
	public void displayBoard(Board board) {
		System.out.println();
		
		System.out.println("  A B C D E F G H");

		for (int row = 0; row < board.getRows(); row++) {
			System.out.print(row + 1 + " ");

			for (int col = 0; col < board.getCols(); col++) {
				Square square = new Square(col, row);
				Piece piece = board.getPiece(square);

				if (piece != null)
					System.out.print(piece.getClass().getSimpleName().substring(0, 1) + " ");
				else
					System.out.print("  ");
			}
			
			System.out.println();
		}
		
		System.out.println("  A B C D E F G H");
		System.out.println();
	}
}
