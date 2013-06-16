package com.javachess.boards;

import com.javachess.converters.NotationConverter;
import com.javachess.exceptions.GameException;
import com.javachess.moves.Move;
import com.javachess.players.Player;

public class Game {
	private Board board;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	
	private BoardDisplayer displayer;
	private NotationConverter converter;
	
	private boolean ended = false;

	public Game(Player player1, Player player2, BoardDisplayer displayer, NotationConverter converter) {
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = player1;

		this.board = new Board();

		this.displayer = displayer;
		this.converter = converter;
	}
	
	public void start() {
		while (!isEnded()) {
			try {
				String notation = getCurrentPlayer().getNextMove(this);
				move(notation);
			} catch (GameException e) {

			}
		}
	}

	public void display() {
		displayer.displayBoard(board);
	}
	
	public void move(String notation) throws GameException {
		Square srcSquare = converter.getSrc(notation);
		Square dstSquare = converter.getDst(notation);

		for (Move move : board.legalMoves(currentPlayer.getColor())) {
			if (move.equals(srcSquare, dstSquare)) {
				executeMove(move);
				break;
			}
		}
	}

	private void executeMove(Move move) throws GameException {
		board.move(move);
		switchPlayers();

		if (board.checkMate(currentPlayer.getColor()) || board.staleMate())
			ended = true;
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

	public boolean isEnded() {
		return ended;
	}	
}