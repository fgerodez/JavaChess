package com.javachess.test.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.move.Move;
import com.javachess.move.PawnPush;
import com.javachess.move.enpassant.LeftEnPassant;
import com.javachess.move.enpassant.RightEnPassant;
import com.javachess.state.EnPassantState;
import com.javachess.test.initializer.EnPassantInitializer;

public class EnPassantStateTest {

	@Test
	public void noEnPassantInitial() {	
		EnPassantState epState = new EnPassantState();
		
		assertEquals(0, epState.getCtxMoves().size());
	}
	
	@Test
	public void leftEnPassant() {	
		BoardInitializer initializer = new EnPassantInitializer(true, false);
		Board board = new Board(initializer);
		EnPassantState epState = new EnPassantState();
		
		Move pawnPush = new PawnPush(Square.at(1, 4), Square.at(3, 4), board);
		
		epState.executeTransition(pawnPush, board);
		
		assertEquals(1, epState.getCtxMoves().size());
		assertTrue(epState.getCtxMoves().get(0) instanceof LeftEnPassant);
	}
	
	@Test
	public void rightEnPassant() {	
		BoardInitializer initializer = new EnPassantInitializer(false, true);
		Board board = new Board(initializer);
		EnPassantState epState = new EnPassantState();
		
		Move pawnPush = new PawnPush(Square.at(1, 4), Square.at(3, 4), board);
		
		epState.executeTransition(pawnPush, board);
		
		assertEquals(1, epState.getCtxMoves().size());
		assertTrue(epState.getCtxMoves().get(0) instanceof RightEnPassant);
	}
	
	@Test
	public void leftAndRightEnPassant() {	
		BoardInitializer initializer = new EnPassantInitializer(true, true);
		Board board = new Board(initializer);
		EnPassantState epState = new EnPassantState();
		
		Move pawnPush = new PawnPush(Square.at(1, 4), Square.at(3, 4), board);
		
		epState.executeTransition(pawnPush, board);
		
		assertEquals(2, epState.getCtxMoves().size());
	}
}
