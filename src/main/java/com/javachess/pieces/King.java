package com.javachess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.moves.Castling;
import com.javachess.moves.Move;
import com.javachess.moves.StandardMove;

public class King extends Piece {

	public King(Color couleur) {
		super(couleur);
	}

	@Override
	public List<Move> availableMoves(final Square src, final Board board) {
		List<Move> moves = new ArrayList<Move>();

		moves.add(new StandardMove(src, new Square(src, 0, 1), board));
		moves.add(new StandardMove(src, new Square(src, 0, -1), board));
		moves.add(new StandardMove(src, new Square(src, 1, 0), board));
		moves.add(new StandardMove(src, new Square(src, -1, 0), board));
		moves.add(new StandardMove(src, new Square(src, -1, 1), board));
		moves.add(new StandardMove(src, new Square(src, -1, -1), board));
		moves.add(new StandardMove(src, new Square(src, 1, 1), board));
		moves.add(new StandardMove(src, new Square(src, 1, -1), board));

		castling(src, moves, board);

		return filterValid(moves, board);
	}

	private void castling(Square src, List<Move> moves, Board board) {
		Square rookQSrc = new Square(src, -4, 0);
		Square rookQDst = new Square(src, 3, 0);
		Square kingQDst = new Square(src, -2, 0);

		castling(src, rookQSrc, kingQDst, rookQDst, moves, board);

		Square rookKSrc = new Square(src, 3, 0);
		Square rookKDst = new Square(src, -1, 0);
		Square kingKDst = new Square(src, 2, 0);

		castling(src, rookKSrc, kingKDst, rookKDst, moves, board);
	}

	private void castling(Square kingSrc, Square rookSrc, Square kingDst,
			Square rookDst, List<Move> moves, Board board) {

		Piece rook = board.getPiece(rookSrc);

		if (rook instanceof Rook
				&& board.isRowFree(rookSrc, kingSrc, color.opponent())
				&& !board.hasMoved(this) && !board.hasMoved(rook)) {
			moves.add(new Castling(kingSrc, rookSrc, kingDst, rookDst, board));
		}
	}
}
