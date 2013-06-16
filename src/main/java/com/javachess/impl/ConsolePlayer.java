package com.javachess.impl;

import java.util.Scanner;

import com.javachess.boards.Color;
import com.javachess.boards.Game;
import com.javachess.players.Player;

public class ConsolePlayer implements Player {
	
	private Color color;
	
	public ConsolePlayer(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public String getNextMove(Game game) {
		game.display();
		
		Scanner s = new Scanner(System.in);
		String choice;
		
		System.out.println("Joueur " + color);
		System.out.println("Choisir mouvement: ");
		choice = s.nextLine();
		
		//s.close();
		
		return choice;
	}
}
