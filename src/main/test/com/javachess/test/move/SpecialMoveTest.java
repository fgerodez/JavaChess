package com.javachess.test.move;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.move.Move;
import com.javachess.move.PawnPush;
import com.javachess.move.castling.CastlingKingSide;
import com.javachess.move.castling.CastlingQueenSide;
import com.javachess.move.enpassant.LeftEnPassant;
import com.javachess.move.enpassant.RightEnPassant;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;
import com.javachess.test.initializer.EnPassantInitializer;

public class SpecialMoveTest {

	private Board board;

	@Before
	public void setUp() {
		board = new Board();
	}

	@Test
	public void castling() {
		new BoardInitializer() {
			@Override
			public void init(Board board) {
				board.setPieceAt(Square.at(0, 0), Piece.WHITE_ROOK);
				board.setPieceAt(Square.at(0, 4), Piece.WHITE_KING);
				board.setPieceAt(Square.at(0, 7), Piece.WHITE_ROOK);
			}
		}.init(board);

		Move move = new CastlingQueenSide(Color.WHITE, board);
		move.execute();

		assertEquals(Piece.WHITE_KING, board.at(Square.at(0, 2)));
		assertEquals(Piece.WHITE_ROOK, board.at(Square.at(0, 3)));

		move.undo();

		move = new CastlingKingSide(Color.WHITE, board);
		move.execute();

		assertEquals(Piece.WHITE_KING, board.at(Square.at(0, 6)));
		assertEquals(Piece.WHITE_ROOK, board.at(Square.at(0, 5)));

		assertNull(move.getCapturedPiece());
		assertEquals(Square.at(0, 4), move.getSource());
	}

	@Test
	public void enPassant() {
		new EnPassantInitializer().init(board);
		new PawnPush(Square.at(1, 4), board).execute();
		
		Move enPassant = new RightEnPassant(Square.at(3, 3), board);
		enPassant.execute();

		assertNull(board.at(Square.at(3, 3)));
		assertNull(board.at(Square.at(3, 4)));
		assertEquals(Piece.BLACK_PAWN, board.at(Square.at(2, 4)));

		enPassant.undo();

		enPassant = new LeftEnPassant(Square.at(3, 5), board);
		enPassant.execute();

		assertNull(board.at(Square.at(3, 5)));
		assertNull(board.at(Square.at(3, 4)));
		assertEquals(Piece.BLACK_PAWN, board.at(Square.at(2, 4)));
	}
}
