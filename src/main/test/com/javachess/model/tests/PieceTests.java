package com.javachess.model.tests;

import static com.javachess.helpers.Utils.toSquare;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.exceptions.ConversionException;
import com.javachess.pieces.Piece;

public class PieceTests {

	@Test
	public void initialMoves() throws ConversionException {
		Board board = new Board();
		Piece piece;
		List<Square> availableMoves;

		// Knight
		piece = board.getPiece(toSquare("B1"));
		availableMoves = piece.availableMoves(board);

		assertEquals(2, availableMoves.size());
		assertTrue(availableMoves.contains(toSquare("A3")));
		assertTrue(availableMoves.contains(toSquare("C3")));

		// King
		piece = board.getPiece(toSquare("E1"));
		availableMoves = piece.availableMoves(board);

		assertEquals(0, availableMoves.size());

		// Pawn
		piece = board.getPiece(toSquare("D2"));
		availableMoves = piece.availableMoves(board);

		assertEquals(2, availableMoves.size());
		assertTrue(availableMoves.contains(toSquare("D3")));
		assertTrue(availableMoves.contains(toSquare("D4")));
	}
	
	@Test
	public void freeMoves() {
		
	}
}
