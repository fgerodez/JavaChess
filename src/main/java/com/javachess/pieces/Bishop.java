package com.javachess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.boards.Board;
import com.javachess.boards.Color;
import com.javachess.boards.Square;
import com.javachess.moves.Move;

public class Bishop extends Piece {

	public Bishop(Color couleur) {
		super(couleur);
	}

	@Override
	public List<Move> availableMoves(Square src, Board board) {
		List<Move> availableMoves = new ArrayList<Move>();

		//Diagonal movements
		availableMoves.addAll(iterateDirection(src, -1, -1, board));
		availableMoves.addAll(iterateDirection(src, 1, -1, board));
		availableMoves.addAll(iterateDirection(src, -1, 1, board));
		availableMoves.addAll(iterateDirection(src, 1, 1, board));
		
		return availableMoves;
	}
}
