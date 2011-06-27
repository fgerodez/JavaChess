package com.javachess.helpers;

import com.javachess.jeu.Case;
import com.javachess.pieces.Piece;

/**
 * Modélisation d'un coup d'une case source vers une case cible
 * 
 * @author Ouzned
 * 
 */
public class Coup {
	private Case caseSource;
	private Case caseDestination;
	private Piece pieceSource;
	private Piece pieceDestination;

	public Coup(Case caseSource, Case caseDestination) {
		this.caseSource = caseSource;
		this.caseDestination = caseDestination;
	}

	public Case getCaseSource() {
		return caseSource;
	}

	public void setCaseSource(Case caseSource) {
		this.caseSource = caseSource;
	}

	public Case getCaseDestination() {
		return caseDestination;
	}

	public void setCaseDestination(Case caseDestination) {
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
