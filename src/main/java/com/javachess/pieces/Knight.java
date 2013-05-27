package com.javachess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.moves.Move;
import com.javachess.moves.StandardMove;

public class Knight extends Piece {

	public Knight(Color couleur, Square position) {
		super(couleur, position);
	}

	@Override
	public List<Move> availableMoves(Square src, final Board board) {
		List<Move> availableMoves = new ArrayList<Move>();

		availableMoves.add(new StandardMove(src, new Square(src, 1, 2), board));
		availableMoves.add(new StandardMove(src, new Square(src, 1, -2), board));
		availableMoves.add(new StandardMove(src, new Square(src, -1, 2), board));
		availableMoves.add(new StandardMove(src, new Square(src, -1, -2), board));
		availableMoves.add(new StandardMove(src, new Square(src, 2, 1), board));
		availableMoves.add(new StandardMove(src, new Square(src, 2, -1), board));
		availableMoves.add(new StandardMove(src, new Square(src, -2, 1), board));
		availableMoves.add(new StandardMove(src, new Square(src, -2, -1), board));

		return availableMoves;
	}
}
