package com.javachess.modele.plateau;

public class Tile {
	private int column;
	private int row;
		
	public Tile(int column, int row) {
		super();
		this.column = column;
		this.row = row;
	}
	
	public boolean isValid() {
		return column > 1 && column < 9 && row > 1 && row < 9;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
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
		Tile other = (Tile) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
}
