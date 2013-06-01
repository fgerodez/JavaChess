//package com.javachess.model.tests;
//
//import com.javachess.board.Game;
//
//public class GameTests {
//	private Game game;
//
//	@Before
//	public void setUp() {
//		game = new Game(new HumanPlayer("Ouzned", Color.WHITE),
//				new HumanPlayer("player2", Color.BLACK));
//	}
//
//	@Test
//	public void simpleMove() throws GameException {
//		// Regular white move
//		game.move("B1", "C3");
//		assertEquals(game.getCurrentPlayer().getColor(), Color.BLACK);
//
//		// Regular black move
//		game.move("A7", "A5");
//		assertEquals(game.getCurrentPlayer().getColor(), Color.WHITE);
//
//		try {
//			// White player trying to move a black piece
//			game.move("E7", "E6");
//			fail("A player managed to move one of the other player's pieces");
//		} catch (GameException e) {
//			assertEquals(game.getCurrentPlayer().getColor(), Color.WHITE);
//		}
//	}
//
//	@Test
//	public void kingThreatenedMove() throws GameException {
//		// The king takes the threatening queen
//		game.setBoard(new Board(BoardBuilder.KING_THREATENED));
//		game.move("E1", "E2");
//
//		try {
//			// This move doesn't put the king out of harm's way
//			game.setBoard(new Board(BoardBuilder.KING_THREATENED));
//			game.move("A2", "A3");
//			fail("Forbidden move, the king is threatened");
//		} catch (GameException e) {
//
//		}
//
//	}
//
//	@Test(expected=GameException.class)
//	public void checkMate() throws GameException {
//		// Bishop and knight checkmate!
//		game.setBoard(new Board(BoardBuilder.CHECKMATE));
//		game.move("C5", "A6");
//	}
//}
