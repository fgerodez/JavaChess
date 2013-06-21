package com.javachess.generator;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.move.StandardMove;
import com.javachess.piece.Color;
import com.javachess.state.State;

public class KnightMoveGenerator implements MoveGeneratorOld {

	@Override
	public List<Move> generate(Square position, Color color, Board board, State state) {
		List<Move> moves = new ArrayList<Move>();

		moves.add(new StandardMove(position, new Square(position, 1, 2), board));
		moves.add(new StandardMove(position, new Square(position, 1, -2), board));
		moves.add(new StandardMove(position, new Square(position, -1, 2), board));
		moves.add(new StandardMove(position, new Square(position, -1, -2), board));
		moves.add(new StandardMove(position, new Square(position, 2, 1), board));
		moves.add(new StandardMove(position, new Square(position, 2, -1), board));
		moves.add(new StandardMove(position, new Square(position, -2, 1), board));
		moves.add(new StandardMove(position, new Square(position, -2, -1), board));
		
		return moves;
	}
}
