package com.javachess.test.generator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.generator.PawnMoveGenerator;
import com.javachess.move.Move;
import com.javachess.piece.Color;
import com.javachess.test.initializer.WhitePawnAttackInitializer;
import com.javachess.test.initializer.WhitePawnOnlyInitializer;

public class PawnGeneratorTest {

	private Board board;
	private PawnMoveGenerator pmg;
	
	@Before
	public void setUp() {
		pmg = new PawnMoveGenerator();
		board = new Board();
	}
	
	@Test
	public void pawnInitMove() {
		new WhitePawnOnlyInitializer().init(board);
		
		List<Move> moves = pmg.generateMoves(Square.at(Color.WHITE.pawnRow(), 0), Color.WHITE, board);

		assertEquals(2, moves.size());
	}
	
	@Test 
	public void pawnAttack() {
		new WhitePawnAttackInitializer().init(board);

		List<Move> rightAttack = pmg.generateMoves(Square.at(Color.WHITE.pawnRow(), 0), Color.WHITE, board);
		List<Move> leftAttack = pmg.generateMoves(Square.at(Color.WHITE.pawnRow(), 2), Color.WHITE, board);
		List<Move> frontPawn = pmg.generateMoves(Square.at(Color.WHITE.pawnRow(), 1), Color.WHITE, board);
		
		assertEquals(3, rightAttack.size());
		assertEquals(3, leftAttack.size());
		assertEquals(0, frontPawn.size());
	}
}
