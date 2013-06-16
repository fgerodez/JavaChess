package com.javachess.model.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.javachess.boards.Board;
import com.javachess.boards.Color;
import com.javachess.converters.StandardConverter;
import com.javachess.converters.NotationConverter;
import com.javachess.exceptions.GameException;
import com.javachess.moves.EnPassant;
import com.javachess.moves.Move;
import com.javachess.moves.StandardMove;

public class MoveTests {

	NotationConverter converter = new StandardConverter();
	
	@Test
	public void standardMove() throws GameException {
		Board board = new Board();
		Move move = new StandardMove(converter.toSquare("A2"), converter.toSquare("A3"), board);
		
		board.move(move);

		assertNotNull(board.getPiece(converter.toSquare("A3")));
		assertNull(board.getPiece(converter.toSquare("A2")));
		
		board.undo();
		
		assertNull(board.getPiece(converter.toSquare("A3")));
		assertNotNull(board.getPiece(converter.toSquare("A2")));
	}

	@Test
	public void enPassant() throws GameException {
		Board board = new Board();
		board.move(converter.toSquare("F7"), converter.toSquare("F4"));
		board.move(converter.toSquare("E2"), converter.toSquare("E4"));
		
		Move move = new EnPassant(converter.toSquare("F4"), converter.toSquare("E3"), converter.toSquare("E4"), board);
		
		board.move(move);
		
		assertNull(board.getPiece(converter.toSquare("E4")));
		assertNull(board.getPiece(converter.toSquare("F4")));
		assertNotNull(board.getPiece(converter.toSquare("E3")));
		assertTrue(board.getPiece(converter.toSquare("E3")).isColor(Color.BLACK));
		
		board.undo();
		
		assertNotNull(board.getPiece(converter.toSquare("F4")));
		assertNotNull(board.getPiece(converter.toSquare("E4")));
		assertTrue(board.getPiece(converter.toSquare("F4")).isColor(Color.BLACK));
		assertTrue(board.getPiece(converter.toSquare("E4")).isColor(Color.WHITE));
	}
}
