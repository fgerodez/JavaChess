package com.javachess.test.move;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.move.Move;
import com.javachess.move.castling.CastlingKingSide;
import com.javachess.move.castling.CastlingQueenSide;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;

public class SpecialMoveTest {
	
	@Test
	public void castling() {
		BoardInitializer castlingInit = new BoardInitializer() {
			@Override
			public void init(Board board) {
				board.setPieceAt(Square.at(0, 0), Piece.WHITE_ROOK);
				board.setPieceAt(Square.at(0, 4), Piece.WHITE_KING);
				board.setPieceAt(Square.at(0, 7), Piece.WHITE_ROOK);
			}
		};
		Board board = new Board(castlingInit);
		
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
}
