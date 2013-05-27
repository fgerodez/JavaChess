package com.javachess.players;

import com.javachess.board.Color;
import com.javachess.moves.StandardMove;

public interface Player {
	public StandardMove jouer();
	public Color getColor();
}
