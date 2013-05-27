package com.javachess.board;

public class Square {
	private final int col;
	private final int row;

	public Square(int column, int row) {
		this.col = column;
		this.row = row;
	}

	public Square(Square src, int colOffset, int rowOffset) {
		this(src.getCol() + colOffset, src.getRow() + rowOffset);
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
}
