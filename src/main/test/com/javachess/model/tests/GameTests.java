package com.javachess.model.tests;

import org.junit.Before;
import org.junit.Test;

import com.javachess.board.Color;
import com.javachess.game.Game;
import com.javachess.joueurs.JoueurHumain;

public class GameTests {
	private Game game;

	@Before
	public void setUp() {
		game = new Game(new JoueurHumain("Ouzned", Color.WHITE),
				new JoueurHumain("player2", Color.BLACK));
	}

}
