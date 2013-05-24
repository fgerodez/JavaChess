package com.javachess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;

public class Tower extends Piece {

	public Tower(Color couleur, Square position) {
		super(couleur, position);
	}

	@Override
	public List<Square> availableMoves(Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		//Vertical movements
		iterateDirection(availableMoves, 0, 1, board);
		iterateDirection(availableMoves, 0, -1, board);
		
		//Horizontal movements
		iterateDirection(availableMoves, 1, 0, board);
		iterateDirection(availableMoves, -1, 0, board);
		
		return availableMoves;
	}
}
