package com.javachess.impl;

import com.javachess.boards.BoardDisplayer;
import com.javachess.boards.Color;
import com.javachess.boards.Game;
import com.javachess.converters.NotationConverter;
import com.javachess.converters.StandardConverter;
import com.javachess.players.Player;

public class ConsoleGameManager {
	public static void main(String[] args) {
		Player player1 = new ConsolePlayer(Color.WHITE);
		Player player2 = new ConsolePlayer(Color.BLACK);

		NotationConverter converter = new StandardConverter();
		BoardDisplayer displayer = new ConsoleBoardDisplayer();

		Game game = new Game(player1, player2, displayer, converter);
		
		game.start();
	}
}
