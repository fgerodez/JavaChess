package com.javachess.test.initializer;

import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.move.PawnPush;
import com.javachess.state.EnPassantState;
import com.javachess.state.StateContext;


public class BoardTests {

	@Test
	public void initTest() {
		BoardInitializer standardInitializer = new EnPassantInitializer(true, true);
		Board board = new Board(standardInitializer);
		
		StateContext ctx = new StateContext();
		
		PawnPush push = new PawnPush(Square.at(1, 4), Square.at(3, 4), board);
		
		EnPassantState state = new EnPassantState();

		state.executeTransition(push, board);
	
		System.out.println("test");
	}
}
