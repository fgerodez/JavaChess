package com.javachess.joueurs;

import com.javachess.board.Color;
import com.javachess.helpers.Move;

public interface Joueur {
	public Move jouer();
	public Color getCouleur();
}
