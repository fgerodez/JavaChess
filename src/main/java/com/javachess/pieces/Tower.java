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
	public List<Square> availableMoves(Square src, Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		//Vertical movements
		availableMoves.addAll(iterateDirection(src, 0, -1, board));
		availableMoves.addAll(iterateDirection(src, 0, -1, board));
		
		//Horizontal movements
		availableMoves.addAll(iterateDirection(src, 1, 0, board));
		availableMoves.addAll(iterateDirection(src, -1, 0, board));
		
		return availableMoves;
	}
}
