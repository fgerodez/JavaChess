package com.javachess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.boards.Board;
import com.javachess.boards.Color;
import com.javachess.boards.Square;
import com.javachess.moves.Move;
import com.javachess.moves.StandardMove;

public class Knight extends Piece {

	public Knight(Color couleur) {
		super(couleur);
	}

	@Override
	public List<Move> availableMoves(Square src, final Board board) {
		List<Move> moves = new ArrayList<Move>();

		moves.add(new StandardMove(src, new Square(src, 1, 2), board));
		moves.add(new StandardMove(src, new Square(src, 1, -2), board));
		moves.add(new StandardMove(src, new Square(src, -1, 2), board));
		moves.add(new StandardMove(src, new Square(src, -1, -2), board));
		moves.add(new StandardMove(src, new Square(src, 2, 1), board));
		moves.add(new StandardMove(src, new Square(src, 2, -1), board));
		moves.add(new StandardMove(src, new Square(src, -2, 1), board));
		moves.add(new StandardMove(src, new Square(src, -2, -1), board));

		return filterValid(moves, board);
	}
}
