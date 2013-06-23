package com.javachess.test.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.move.StandardMove;
import com.javachess.move.castling.CastlingKingSide;
import com.javachess.move.castling.CastlingQueenSide;
import com.javachess.state.CastlingState;
import com.javachess.test.initializer.CastlingInitializer;

public class CastlingStateTest {

	private Board board;
	private CastlingState cs;
	
	@Before
	public void setUp() {
		board = new Board();
		cs = new CastlingState();
		
		new CastlingInitializer().init(board);
	}
	
	@Test
	public void initialNoCastling() {
		CastlingState cs = new CastlingState();				
		assertEquals(0, cs.getSpecialMoves().size());
	}
	
	@Test
	public void regularCastlings() {	
		Move move = new StandardMove(Square.at(1, 0), Square.at(2, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		assertEquals(2, cs.getSpecialMoves().size());
	}
	
	@Test
	public void QueenRookMoveNoQueenSideCastling() {			
		Move move = new StandardMove(Square.at(7, 0), Square.at(6, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		move = new StandardMove(Square.at(1, 0), Square.at(2, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		assertEquals(1, cs.getSpecialMoves().size());
		assertTrue(cs.getSpecialMoves().get(0) instanceof CastlingKingSide);
	}
	
	@Test
	public void KingRookMoveNoKingSideCastling() {	
		Move move = new StandardMove(Square.at(7, 7), Square.at(6, 7), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		move = new StandardMove(Square.at(1, 0), Square.at(2, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		assertEquals(1, cs.getSpecialMoves().size());
		assertTrue(cs.getSpecialMoves().get(0) instanceof CastlingQueenSide);
	}
	
	@Test
	public void KingMoveNoCastling() {				
		Move move = new StandardMove(Square.at(7, 4), Square.at(6, 4), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		move = new StandardMove(Square.at(1, 0), Square.at(2, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		assertEquals(0, cs.getSpecialMoves().size());
	}
}
