package com.javachess.board;

public class Square {

    private final int col;
    private final int row;

    public static Square at(int row, int col) {
        Square newSquare = new Square(row, col);

        if (!newSquare.isValid()) {
            return null;
        }

        return newSquare;
    }

    public static Square atOffset(Square square, int row, int col) {
        Square newSquare = new Square(square.getRow() + row, square.getCol() + col);

        if (!newSquare.isValid()) {
            return null;
        }

        return newSquare;
    }

    private Square(int row, int column) {
        this.col = column;
        this.row = row;
    }

    public Square right(int offset) {
        Square newSquare = new Square(this.getRow(), this.getCol() + offset);

        if (!newSquare.isValid()) {
            return null;
        }

        return newSquare;
    }

    public Square left(int offset) {
        Square newSquare = new Square(this.getRow(), this.getCol() - offset);

        if (!newSquare.isValid()) {
            return null;
        }

        return newSquare;
    }

    public Square forward(int offset) {
        Square newSquare = new Square(this.getRow() + offset, this.getCol());

        if (!newSquare.isValid()) {
            return null;
        }

        return newSquare;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean isValid() {
        return col >= 0 && col < 8 && row >= 0 && row < 8;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + col;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Square other = (Square) obj;
        if (col != other.col) {
            return false;
        }
        if (row != other.row) {
            return false;
        }
        return true;
    }
}
