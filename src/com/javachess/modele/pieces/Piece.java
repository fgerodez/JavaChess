package com.javachess.modele.pieces;

import java.util.List;

import com.javachess.helpers.Color;
import com.javachess.jeu.Board;
import com.javachess.modele.plateau.Tile;

/**
 * Cette classe représente le template d'une pièce d'échec. Chaque pièce est
 * représentée par une couleur et une position.
 * 
 * @author Ouzned
 */
public abstract class Piece {
	private Tile position;
	
	private final Color color;
	private final Tile initialPosition;
	
	public Piece(Color couleur, Tile position) {
		this.color = couleur;
		this.position = position;
		this.initialPosition = position;
	}
	
	public boolean isSameColor(Piece piece) {
		return color.equals(piece.color);
	}
	
	protected abstract List<Tile> availableMoves(final Board board);
	
	protected void filterSameColorTiles(List<Tile> tiles, final Board board) {
		for(Tile tile : tiles) {
			if (isSameColor(board.getPiece(tile)))
				tiles.remove(tile);
		}
	}

	public Tile getPosition() {
		return position;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}

	public Tile getInitialPosition() {
		return initialPosition;
	}
}
