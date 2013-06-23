package com.javachess.test.generator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.StandardInitializer;
import com.javachess.generator.KnightMoveGenerator;
import com.javachess.move.Move;
import com.javachess.move.StandardMove;
import com.javachess.piece.Color;

public class KnightGeneratorTest {

	private Board board;
	private KnightMoveGenerator kmg = new KnightMoveGenerator();
	
	@Before
	public void setUp() {
		board = new Board();
		kmg = new KnightMoveGenerator();
		
		new StandardInitializer().init(board);
	}
	
	@Test
	public void knightInitMove() {
		List<Move> moves = kmg.generateMoves(Square.at(0, 1), Color.WHITE, board);
		assertEquals(2, moves.size());
	}
	
	@Test
	public void knightAttackMove() {
		new StandardMove(Square.at(6, 0), Square.at(2, 0), board).execute();
		
		List<Move> moves = kmg.generateMoves(Square.at(0, 1), Color.WHITE, board);
		
		assertEquals(2, moves.size());
	}
	
	@Test
	public void knightAllMoves() {
		new StandardMove(Square.at(0, 1), Square.at(4, 4), board).execute();
		
		List<Move> moves = kmg.generateMoves(Square.at(4, 4), Color.WHITE, board);

		assertEquals(8, moves.size());
	}
}
