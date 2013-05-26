package com.javachess.game;

import static com.javachess.helpers.Utils.toSquare;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.exceptions.GameException;
import com.javachess.pieces.Piece;
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

		Piece piece = board.getPiece(srcSquare);

		checkValidity(srcSquare, dstSquare, piece);

		board.move(srcSquare, dstSquare);

		switchPlayers();
		
		checkMate();
	}

	private void checkValidity(Square srcSquare, Square dstSquare, Piece piece)
			throws GameException {
		if (!piece.isColor(currentPlayer.getColor()))
			throw new GameException("Wrong color selection");

		if (!piece.canGoTo(srcSquare, dstSquare, board))
			throw new GameException("Impossible move");

		Board simul = board.copy();
		simul.move(srcSquare, dstSquare);
		if (isKingThreatened(simul, currentPlayer.getColor()))
			throw new GameException("Forbidden move (king threatened)");
	}

	private void checkMate() throws GameException {
		for (int row = 1; row < 9; row++) {
			for (int col = 1; col < 9; col++) {
				Square src = new Square(col, row);
				Piece piece = board.getPiece(src);
				
				if (piece == null || !piece.isColor(currentPlayer.getColor()))
					continue;
				
				for (Square dst : piece.availableMoves(src, board)) {
					Board simul = board.copy();
					simul.move(src, dst);
					
					if (!isKingThreatened(simul, currentPlayer.getColor()))
						return;
				}
			}
		}
		
		throw new GameException("Checkmate!");
	}
	
	private boolean isKingThreatened(Board board, Color color)
			throws GameException {
		Square kingPos = board.findKing(color);

		for (int row = 1; row < 9; row++) {
			for (int col = 1; col < 9; col++) {
				Square src = new Square(col, row);
				Piece piece = board.getPiece(src);

				if (piece != null && piece.canGoTo(src, kingPos, board))
					return true;
			}
		}

		return false;
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