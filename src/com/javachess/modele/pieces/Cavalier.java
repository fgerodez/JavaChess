package com.javachess.modele.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.helpers.Color;
import com.javachess.jeu.Board;
import com.javachess.modele.plateau.Tile;

public class Cavalier extends Piece {

	public Cavalier(Color couleur, Tile position) {
		super(couleur, position);
	}

	@Override
	protected List<Tile> availableMoves(final Board board) {
		List<Tile> availableMoves = new ArrayList<Tile>();
		
		availableMoves.add(board.getTileAtOffset(getPosition(), 1, 2));
		availableMoves.add(board.getTileAtOffset(getPosition(), 1, -2));
		availableMoves.add(board.getTileAtOffset(getPosition(), -1, 2));
		availableMoves.add(board.getTileAtOffset(getPosition(), -1, -2));
		availableMoves.add(board.getTileAtOffset(getPosition(), 2, 1));
		availableMoves.add(board.getTileAtOffset(getPosition(), 2, -1));
		availableMoves.add(board.getTileAtOffset(getPosition(), -2, 1));
		availableMoves.add(board.getTileAtOffset(getPosition(), -2, -1));
		
		filterSameColorTiles(availableMoves, board);
		
		return availableMoves;
	}
}
