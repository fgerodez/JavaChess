package com.javachess.test.evaluator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.board.initializer.StandardInitializer;
import com.javachess.evaluator.BoardEvaluator;
import com.javachess.piece.Color;
import com.javachess.test.initializer.CheckMateInitializer;
import com.javachess.test.initializer.StaleMateInitializer;

public class BoardEvaluatorTest {

	@Test
	public void staleMate() {
		BoardInitializer staleMateInit = new StaleMateInitializer();
		Board board = new Board(staleMateInit);

		assertTrue(BoardEvaluator.isStaleMate(Color.BLACK, null, board));
	}

	@Test
	public void checkMate() {
		BoardInitializer checkMateInit = new CheckMateInitializer();
		Board board = new Board(checkMateInit);

		assertTrue(BoardEvaluator.isCheckMate(Color.BLACK, null, board));
	}

	@Test(expected = IllegalStateException.class)
	public void kingLessBoard() {
		BoardInitializer noOpInit = new BoardInitializer() {
			@Override
			public void init(Board board) {}
		};
		Board board = new Board(noOpInit);
		
		BoardEvaluator.findKing(Color.WHITE, board);
	}
	
	@Test()
	public void initialLegalMoves() {
		BoardInitializer standardInit = new StandardInitializer();
		Board board = new Board(standardInit);
			
		assertEquals(20, BoardEvaluator.legalMoves(Color.WHITE, null, board).size());
	}
}
