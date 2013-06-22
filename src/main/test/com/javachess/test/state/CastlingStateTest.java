package com.javachess.test.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.move.Move;
import com.javachess.move.StandardMove;
import com.javachess.move.castling.CastlingKingSide;
import com.javachess.move.castling.CastlingQueenSide;
import com.javachess.state.CastlingState;
import com.javachess.test.initializer.CastlingInitializer;

public class CastlingStateTest {

	@Test
	public void initialNoCastling() {
		CastlingState cs = new CastlingState();
				
		assertEquals(0, cs.getCtxMoves().size());
	}
	
	@Test
	public void regularCastlings() {
		BoardInitializer castlingInit = new CastlingInitializer();
		Board board = new Board(castlingInit);
		
		CastlingState cs = new CastlingState();
		
		Move move = new StandardMove(Square.at(1, 0), Square.at(2, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		assertEquals(2, cs.getCtxMoves().size());
	}
	
	@Test
	public void QueenRookMoveNoQueenSideCastling() {
		BoardInitializer castlingInit = new CastlingInitializer();
		Board board = new Board(castlingInit);
		
		CastlingState cs = new CastlingState();
		
		Move move = new StandardMove(Square.at(7, 0), Square.at(6, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		move = new StandardMove(Square.at(1, 0), Square.at(2, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		assertEquals(1, cs.getCtxMoves().size());
		assertTrue(cs.getCtxMoves().get(0) instanceof CastlingKingSide);
	}
	
	@Test
	public void KingRookMoveNoKingSideCastling() {
		BoardInitializer castlingInit = new CastlingInitializer();
		Board board = new Board(castlingInit);
		
		CastlingState cs = new CastlingState();
		
		Move move = new StandardMove(Square.at(7, 7), Square.at(6, 7), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		move = new StandardMove(Square.at(1, 0), Square.at(2, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		assertEquals(1, cs.getCtxMoves().size());
		assertTrue(cs.getCtxMoves().get(0) instanceof CastlingQueenSide);
	}
	
	@Test
	public void KingMoveNoCastling() {
		BoardInitializer castlingInit = new CastlingInitializer();
		Board board = new Board(castlingInit);
		
		CastlingState cs = new CastlingState();
		
		Move move = new StandardMove(Square.at(7, 4), Square.at(6, 4), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		move = new StandardMove(Square.at(1, 0), Square.at(2, 0), board);
		move.execute();
		
		cs.notifyMove(move, board);
		
		assertEquals(0, cs.getCtxMoves().size());
	}
}
