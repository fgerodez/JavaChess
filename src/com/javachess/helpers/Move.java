package com.javachess.helpers;

import com.javachess.modele.pieces.Piece;
import com.javachess.modele.plateau.Tile;

/**
 * Mod�lisation d'un coup d'une case source vers une case cible
 * 
 * @author Ouzned
 * 
 */
public class Move {
	private Tile caseSource;
	private Tile caseDestination;
	private Piece pieceSource;
	private Piece pieceDestination;

	public Move(Tile caseSource, Tile caseDestination) {
		this.caseSource = caseSource;
		this.caseDestination = caseDestination;
	}

	public Tile getCaseSource() {
		return caseSource;
	}

	public void setCaseSource(Tile caseSource) {
		this.caseSource = caseSource;
	}

	public Tile getCaseDestination() {
		return caseDestination;
	}

	public void setCaseDestination(Tile caseDestination) {
		this.caseDestination = caseDestination;
	}

	public Piece getPieceSource() {
		return pieceSource;
	}

	public void setPieceSource(Piece pieceSource) {
		this.pieceSource = pieceSource;
	}

	public Piece getPieceDestination() {
		return pieceDestination;
	}

	public void setPieceDestination(Piece pieceDestination) {
		this.pieceDestination = pieceDestination;
	}
}
