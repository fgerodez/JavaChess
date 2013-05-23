package com.javachess.helpers;

import com.javachess.modele.pieces.Piece;
import com.javachess.modele.plateau.Square;

/**
 * Mod�lisation d'un coup d'une case source vers une case cible
 * 
 * @author Ouzned
 * 
 */
public class Move {
	private Square caseSource;
	private Square caseDestination;
	private Piece pieceSource;
	private Piece pieceDestination;

	public Move(Square caseSource, Square caseDestination) {
		this.caseSource = caseSource;
		this.caseDestination = caseDestination;
	}

	public Square getCaseSource() {
		return caseSource;
	}

	public void setCaseSource(Square caseSource) {
		this.caseSource = caseSource;
	}

	public Square getCaseDestination() {
		return caseDestination;
	}

	public void setCaseDestination(Square caseDestination) {
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
