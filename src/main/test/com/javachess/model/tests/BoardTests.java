package com.javachess.model.tests;

import static com.javachess.helpers.Utils.toSquare;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.exceptions.ConversionException;
import com.javachess.exceptions.GameException;
import com.javachess.helpers.BoardBuilder;
import com.javachess.pieces.Piece;
import com.javachess.pieces.Queen;
import com.javachess.pieces.Rook;

public class BoardTests {

	@Test
	public void retrieveSquare() throws ConversionException {
		Square initSquare = toSquare("E5");

		assertEquals(toSquare("E5"), new Square(initSquare, 0, 0));
		assertEquals(toSquare("F6"), new Square(initSquare, 1, 1));
		assertEquals(toSquare("E4"), new Square(initSquare, 0, -1));
		assertEquals(toSquare("A7"), new Square(initSquare, -4, 2));
		assertNull(new Square(initSquare, 10, 10));
	}

	@Test
	public void retrievePiece() throws ConversionException {
		Board board = new Board();

		assertTrue(board.getPiece(toSquare("A1")) instanceof Rook);
		assertTrue(board.getPiece(toSquare("D1")) instanceof Queen);
		assertTrue(board.getPiece(toSquare("E8")) instanceof Queen);
		assertNull(board.getPiece(toSquare("E4")));
	}

	@Test
	public void movePiece() throws GameException {
		Board board = new Board();

		Piece pieceSrc = board.getPiece(toSquare("B1"));
		board.move(toSquare("B1"), toSquare("C3"));

		Piece pieceDst = board.getPiece(toSquare("C3"));
		Piece empty = board.getPiece(toSquare("B1"));

		assertEquals(pieceSrc, pieceDst);
		assertEquals(null, empty);

		try {
			board.move(toSquare("Z1"), toSquare("K8"));
			fail("Incorrect move did not fail");
		} catch (GameException e) {

		}
	}

	@Test
	public void findKing() throws GameException {
		Board board = new Board();

		Square whiteKing = board.findKing(Color.WHITE);
		Square blackKing = board.findKing(Color.BLACK);

		assertNotNull(whiteKing);
		assertNotNull(blackKing);

		board = new Board(BoardBuilder.NO_KING);

		try {
			board.findKing(Color.WHITE);
			fail("The white king should not be found");
		} catch (GameException e) {

		}
	}
}
