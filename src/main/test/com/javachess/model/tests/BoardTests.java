package com.javachess.model.tests;

import static com.javachess.helpers.Utils.toSquare;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.exceptions.ConversionException;
import com.javachess.pieces.Queen;
import com.javachess.pieces.Tower;

public class BoardTests {

	@Test
	public void squareRetrieval() throws ConversionException {
		Board board = new Board();
		Square initSquare = toSquare("E5");

		assertEquals(toSquare("E5"), board.atOffset(initSquare, 0, 0));
		assertEquals(toSquare("F6"), board.atOffset(initSquare, 1, 1));
		assertEquals(toSquare("E4"), board.atOffset(initSquare, 0, -1));
		assertEquals(toSquare("A7"), board.atOffset(initSquare, -4, 2));
		assertNull(board.atOffset(initSquare, 10, 10));
	}

	@Test
	public void pieceRetrieval() throws ConversionException {
		Board board = new Board();

		assertTrue(board.getPiece(toSquare("A1")) instanceof Tower);
		assertTrue(board.getPiece(toSquare("D1")) instanceof Queen);
		assertTrue(board.getPiece(toSquare("E8")) instanceof Queen);
		assertNull(board.getPiece(toSquare("E4")));
	}
}
