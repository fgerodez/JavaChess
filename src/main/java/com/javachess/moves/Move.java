package com.javachess.moves;

import com.javachess.board.Board;

public interface Move {
	public void execute(Board board);
	public void undo(Board board);
}
