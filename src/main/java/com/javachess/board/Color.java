package com.javachess.board;

public enum Color {
	BLACK(-1), WHITE(1);
	
	private final int value;
	
	private Color(int value) {
		this.value = value;
	}
	
	public int dir() {
		return value;
	}
	
	public Color opponent() {
		if (this == Color.BLACK)
			return Color.WHITE;
		else
			return Color.BLACK;
	}
}
