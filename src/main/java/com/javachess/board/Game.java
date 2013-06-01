package com.javachess.board;

import com.javachess.converters.NotationConverter;
import com.javachess.exceptions.GameException;
import com.javachess.moves.Move;
import com.javachess.pieces.Piece;
import com.javachess.players.Player;


public class Game {
	private Board board;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private NotationConverter converter;
	private GameEventListener listener;

	public Game(Player player1, Player player2, NotationConverter converter, GameEventListener listener,
			PromotionDelegate delegate) {
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = player1;

		this.board = new Board(delegate);

		this.converter = converter;
		this.listener = listener;
	}

	public void move(String src, String dst) throws GameException {
		Square srcSquare = converter.toSquare(src);
		Square dstSquare = converter.toSquare(dst);

		Piece piece = board.getPiece(srcSquare);

		for (Move move : piece.availableMoves(srcSquare, board)) {
			if (move.getDst().equals(dstSquare)) {
				executeMove(move);
				break;
			}
		}
	}

	private void executeMove(Move move) throws GameException {
		board.move(move);
		switchPlayers();

		if (board.check(currentPlayer.getColor()))
			listener.fireEvent(GameEvent.CHECK);

		if (board.checkMate(currentPlayer.getColor()))
			listener.fireEvent(GameEvent.CHECKMATE);

		if (board.staleMate())
			listener.fireEvent(GameEvent.STALEMATE);
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
}