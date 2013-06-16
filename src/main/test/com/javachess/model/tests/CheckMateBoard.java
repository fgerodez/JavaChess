package com.javachess.model.tests;

import com.javachess.boards.Board;
import com.javachess.boards.Color;
import com.javachess.pieces.Bishop;
import com.javachess.pieces.King;
import com.javachess.pieces.Knight;
import com.javachess.pieces.Piece;

public class CheckMateBoard extends Board {

	@Override
	protected void init() {
		board = new Piece[8][8];

		board[7][0] = new King(Color.BLACK);
		board[5][0] = new Knight(Color.WHITE);
		board[5][1] = new King(Color.WHITE);
		board[5][2] = new Bishop(Color.WHITE);
	}
}
