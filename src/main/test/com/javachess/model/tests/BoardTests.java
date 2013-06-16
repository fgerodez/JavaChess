package com.javachess.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.javachess.boards.Board;
import com.javachess.boards.Color;
import com.javachess.boards.Square;
import com.javachess.converters.StandardConverter;
import com.javachess.converters.NotationConverter;
import com.javachess.exceptions.ConversionException;
import com.javachess.exceptions.GameException;
import com.javachess.pieces.King;
import com.javachess.pieces.Piece;
import com.javachess.pieces.Queen;
import com.javachess.pieces.Rook;

public class BoardTests {

	NotationConverter converter = new StandardConverter();
	
	@Test
	public void retrieveSquare() throws ConversionException {
		Square initSquare = converter.toSquare("E5");

		assertEquals(converter.toSquare("E5"), new Square(initSquare, 0, 0));
		assertEquals(converter.toSquare("F6"), new Square(initSquare, 1, 1));
		assertEquals(converter.toSquare("E4"), new Square(initSquare, 0, -1));
		assertEquals(converter.toSquare("A7"), new Square(initSquare, -4, 2));
	}

	@Test
	public void retrievePiece() throws ConversionException {
		Board board = new Board();

		assertTrue(board.getPiece(converter.toSquare("A1")) instanceof Rook);
		assertTrue(board.getPiece(converter.toSquare("D1")) instanceof Queen);
		assertTrue(board.getPiece(converter.toSquare("E8")) instanceof King);
		assertNull(board.getPiece(converter.toSquare("E4")));
	}

	@Test
	public void movePiece() throws GameException {
		Board board = new Board();

		Piece pieceSrc = board.getPiece(converter.toSquare("B1"));
		board.move(converter.toSquare("B1"), converter.toSquare("C3"));

		Piece pieceDst = board.getPiece(converter.toSquare("C3"));
		Piece empty = board.getPiece(converter.toSquare("B1"));

		assertEquals(pieceSrc, pieceDst);
		assertEquals(null, empty);

		try {
			board.move(converter.toSquare("Z1"), converter.toSquare("K8"));
			fail("Incorrect move did not fail");
		} catch (GameException e) {

		}
	}

	@Test
	public void availableMoves() {
		Board board = new Board();

		assertEquals(20, board.moveCount(Color.WHITE));
		assertEquals(20, board.moveCount(Color.BLACK));
	}
	
	@Test
	public void checkMate() {
		Board board = new CheckMateBoard();
		
		assertTrue(board.checkMate(Color.BLACK));
		assertFalse(board.checkMate(Color.WHITE));
	}
}
