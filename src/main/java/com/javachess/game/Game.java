package com.javachess.game;

import static com.javachess.helpers.Utils.toSquare;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.exceptions.GameException;
import com.javachess.joueurs.Joueur;
import com.javachess.pieces.Piece;

public class Game {
	private Board board;
	private Joueur player1;
	private Joueur player2;
	private Joueur currentPlayer;
	boolean chessMate = false;

	public Game(Joueur player1, Joueur player2) {
		this.player1 = player1;
		this.player2 = player2;

		this.currentPlayer = player1;

		this.board = new Board();
	}

	public void move(String src, String dst) throws GameException {
		Square srcSquare = toSquare(src);
		Square dstSquare = toSquare(dst);

		Piece piece = board.getPiece(srcSquare);

		if (!piece.isColor(currentPlayer.getCouleur()))
			return;

		if (!piece.availableMoves(srcSquare, board).contains(dstSquare))
			return;

		Board simul = board.copy();
		simul.move(srcSquare, dstSquare);
		checkChessMate(simul, currentPlayer.getCouleur());

		board.move(srcSquare, dstSquare);

		switchPlayers();
	}

	private void checkChessMate(Board board, Color color) throws GameException {
		Square kingPos = board.findKing(color);

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				Square square = new Square(row, col);
				Piece piece = board.getPiece(square);

				if (piece.availableMoves(square, board).contains(kingPos))
					chessMate = true;
			}
		}

		chessMate = false;
	}

	private void switchPlayers() {
		if (currentPlayer.equals(player1))
			currentPlayer = player2;
		else
			currentPlayer = player1;
	}
}