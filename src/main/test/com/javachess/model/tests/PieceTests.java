package com.javachess.model.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.javachess.boards.Board;
import com.javachess.converters.StandardConverter;
import com.javachess.converters.NotationConverter;
import com.javachess.exceptions.ConversionException;
import com.javachess.exceptions.GameException;
import com.javachess.moves.Move;
import com.javachess.moves.PawnTwoFwd;
import com.javachess.moves.StandardMove;
import com.javachess.pieces.Piece;

public class PieceTests {

	Board board;
	
	NotationConverter converter = new StandardConverter();

	@Before
	public void setUp() {
		board = new Board();
	}

	@Test
	public void initialMoves() throws ConversionException {
		Piece piece;
		List<Move> availableMoves;

		// Knight
		piece = board.getPiece(converter.toSquare("B1"));
		availableMoves = piece.availableMoves(converter.toSquare("B1"), board);

		assertEquals(2, availableMoves.size());

		// King
		piece = board.getPiece(converter.toSquare("E1"));
		availableMoves = piece.availableMoves(converter.toSquare("E1"), board);

		assertEquals(0, availableMoves.size());

		// Pawn
		piece = board.getPiece(converter.toSquare("D2"));
		availableMoves = piece.availableMoves(converter.toSquare("D2"), board);

		assertEquals(2, availableMoves.size());
	}

	@Test
	public void freeMoves() throws ConversionException {
		Piece piece;
		List<Move> availableMoves;

		// Knight
		piece = board.getPiece(converter.toSquare("B1"));
		availableMoves = piece.availableMoves(converter.toSquare("E5"), board);

		assertEquals(8, availableMoves.size());

		// Queen
		piece = board.getPiece(converter.toSquare("D1"));
		availableMoves = piece.availableMoves(converter.toSquare("E5"), board);

		assertEquals(19, availableMoves.size());

		// King
		piece = board.getPiece(converter.toSquare("E1"));
		availableMoves = piece.availableMoves(converter.toSquare("E5"), board);

		assertEquals(8, availableMoves.size());
	}
	
	@Test
	public void pawnAttack() throws GameException {
		board.move(converter.toSquare("D7"), converter.toSquare("D3"));
		
		Piece pawn;
		List<Move> availableMoves;

		// Pawn right attack
		pawn = board.getPiece(converter.toSquare("C2"));
		availableMoves = pawn.availableMoves(converter.toSquare("C2"), board);
		
		assertEquals(3, availableMoves.size());
		
		// Pawn left attack
		pawn = board.getPiece(converter.toSquare("E2"));
		availableMoves = pawn.availableMoves(converter.toSquare("E2"), board);
		
		assertEquals(3, availableMoves.size());
	}
	
	@Test
	public void pawnEnPassant() throws GameException {
		// Move black pawn to B4
		board.move(converter.toSquare("B7"), converter.toSquare("B4"));
		
		// 2-square move white pawn to A4 (enabling EnPassant for black pawn)
		Move whitePawnFwd = new PawnTwoFwd(converter.toSquare("A2"), converter.toSquare("A4"), board);		
		board.move(whitePawnFwd);
		
		Piece blackPawn = board.getPiece(converter.toSquare("B4"));
		List<Move> availableMoves = blackPawn.availableMoves(converter.toSquare("B4"), board); 
		
		assertEquals(2, availableMoves.size());
	}
	
	@Test 
	public void kingCastling() throws GameException {
		board.setPiece(converter.toSquare("F1"), null);
		board.setPiece(converter.toSquare("G1"), null);
		
		Piece king = board.getPiece(converter.toSquare("E1"));
		List<Move> availableMoves = king.availableMoves(converter.toSquare("E1"), board);
		
		// F1 + Castle G2
		assertEquals(2, availableMoves.size());
		
		// black rook threatens one of the squares
		board.move(converter.toSquare("H8"), converter.toSquare("G2"));
		availableMoves = king.availableMoves(converter.toSquare("E1"), board);
		board.move(converter.toSquare("G2"), converter.toSquare("H8"));
		
		// only F1, no castling
		assertEquals(1, availableMoves.size());
		
		// Rook has moved
		Move move1 = new StandardMove(converter.toSquare("H1"),converter.toSquare("G1"),board);
		Move move2 = new StandardMove(converter.toSquare("G1"),converter.toSquare("H1"),board);
		board.move(move1);
		board.move(move2);
		
		availableMoves = king.availableMoves(converter.toSquare("E1"), board);
		
		// only F1, no castling
		assertEquals(1, availableMoves.size());
	}
}
