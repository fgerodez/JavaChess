package com.javachess.helpers;


/**
 * Enumération qui définit la couleur d'une pièce
 * 
 * @author Ouzned
 */
public enum Color {
	BLACK(1), WHITE(-1);
	
	private final int fwdDirection;
	
	private Color(int fwdDirection) {
		this.fwdDirection = fwdDirection;
	}
	
	public int getFwdModifier() {
		return fwdDirection;
	}
}
