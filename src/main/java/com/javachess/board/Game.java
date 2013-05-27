package com.javachess.board;

import static com.javachess.helpers.Utils.toSquare;

import com.javachess.exceptions.GameException;
import com.javachess.players.Player;

public class Game {
	private Board board;
	private Player player1;
	private Player player2;
	private Player currentPlayer;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;

		this.currentPlayer = player1;

		this.board = new Board();
	}

	public void move(String src, String dst) throws GameException {
		Square srcSquare = toSquare(src);
		Square dstSquare = toSquare(dst);
		
		board.move(srcSquare, dstSquare);

		switchPlayers();

		if (board.checkMate(currentPlayer.getColor()))
			throw new GameException("checkmate");
	}

	private void switchPlayers() {
		if (currentPlayer.equals(player1))
			currentPlayer = player2;
		else
			currentPlayer = player1;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}