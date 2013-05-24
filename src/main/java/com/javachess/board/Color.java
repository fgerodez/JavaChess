package com.javachess.board;


/**
 * Enumération qui définit la couleur d'une pièce
 * 
 * @author Ouzned
 */
public enum Color {
	BLACK(-1), WHITE(1);
	
	private final int value;
	
	private Color(int value) {
		this.value = value;
	}
	
	public int dir() {
		return value;
	}
}
