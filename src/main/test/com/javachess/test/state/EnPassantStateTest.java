package com.javachess.test.state;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.move.PawnPush;
import com.javachess.state.EnPassantState;
import com.javachess.test.initializer.EnPassantInitializer;

public class EnPassantStateTest {

	private EnPassantState epState;
	private Board board;
	
	@Before
	public void setUp() {
		board = new Board();
		epState = new EnPassantState();
		
		new EnPassantInitializer().init(board);
	}
	
	@Test
	public void noEnPassantInitial() {	
		EnPassantState epState = new EnPassantState();
		
		assertEquals(0, epState.getSpecialMoves().size());
	}
	
	@Test
	public void leftAndRightEnPassant() {		
		Move pawnPush = new PawnPush(Square.at(1, 4), board);
		pawnPush.execute();
		
		epState.notifyMove(pawnPush, board);
		
		assertEquals(2, epState.getSpecialMoves().size());
	}
}
