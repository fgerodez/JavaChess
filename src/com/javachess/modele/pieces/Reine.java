package com.javachess.modele.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.helpers.Color;
import com.javachess.modele.plateau.Board;
import com.javachess.modele.plateau.Square;

public class Reine extends Piece {
	
	public Reine(Color couleur, Square position) {
		super(couleur, position);
	}

	@Override
	public List<Square> availableMoves(Board board) {
		List<Square> availableMoves = new ArrayList<Square>();

		//Diagonal movements
		iterateDirection(availableMoves, -1, -1, board);
		iterateDirection(availableMoves, 1, -1, board);
		iterateDirection(availableMoves, -1, 1, board);
		iterateDirection(availableMoves, 1, 1, board);
		
		//Vertical movements
		iterateDirection(availableMoves, 0, 1, board);
		iterateDirection(availableMoves, 0, -1, board);
		
		//Horizontal movements
		iterateDirection(availableMoves, 1, 0, board);
		iterateDirection(availableMoves, -1, 0, board);
		
		return availableMoves;
	}
}
