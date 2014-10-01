package com.javachess.piece;

public enum Color {
    
    BLACK(-1, 6), WHITE(1, 1);
    
    private final int direction;
    private final int pawnRow;

    private Color(int value, int pawnRow) {
        this.direction = value;
        this.pawnRow = pawnRow;
    }

    public int dir() {
        return direction;
    }

    public int pawnRow() {
        return pawnRow;
    }

    public Color opponent() {
        if (this == Color.BLACK) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
}
