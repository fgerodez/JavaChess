package com.javachess.test.evaluator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.initializer.StandardInitializer;
import com.javachess.evaluator.BoardEvaluator;
import com.javachess.piece.Color;
import com.javachess.test.initializer.CheckMateInitializer;
import com.javachess.test.initializer.StaleMateInitializer;

public class BoardEvaluatorTest {

	private Board board;
	
	@Before
	public void setUp() {
		board = new Board();
	}
	
	@Test
	public void staleMate() {
		new StaleMateInitializer().init(board);
		assertTrue(BoardEvaluator.isStaleMate(Color.BLACK, null, board));
	}

	@Test
	public void checkMate() {
		new CheckMateInitializer().init(board);
		assertTrue(BoardEvaluator.isCheckMate(Color.BLACK, null, board));
	}

	@Test(expected = IllegalStateException.class)
	public void kingLessBoard() {		
		BoardEvaluator.findKing(Color.WHITE, board);
	}
	
	@Test()
	public void initialLegalMoves() {
		new StandardInitializer().init(board);	
		assertEquals(20, BoardEvaluator.legalMoves(Color.WHITE, null, board).size());
	}
}
