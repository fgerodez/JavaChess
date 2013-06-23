package com.javachess.test.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.StandardInitializer;
import com.javachess.game.StateContext;
import com.javachess.move.Move;
import com.javachess.move.StandardMove;
import com.javachess.piece.Color;

public class StateContextTest {

	private Board board;
	private StateContext ctx;

	@Before
	public void setUp() {
		board = new Board();
		ctx = new StateContext();
		
		new StandardInitializer().init(board);
	}

	@Test
	public void stateContextOperations() {
		assertEquals(0, ctx.getCtxMoves().size());
		assertEquals(Color.WHITE, ctx.currentColor());

		try {
			ctx.lastMove();
			fail("Last move should raise EmptyStackException");
		} catch (EmptyStackException e) {}
		
		Move move = new StandardMove(Square.at(1, 0), Square.at(2, 0), board);
		move.execute();
		
		ctx.notifyMove(move, board);
		
		assertEquals(move, ctx.lastMove());
		
		ctx.undo();
		
		try {
			ctx.lastMove();
			fail("Last move should raise EmptyStackException");
		} catch (EmptyStackException e) {}
	}
}
