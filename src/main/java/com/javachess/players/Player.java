package com.javachess.players;

import com.javachess.board.Color;
import com.javachess.helpers.Move;

public interface Player {
	public Move jouer();
	public Color getColor();
}
