package com.javachess.generator;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.CastlingKingSide;
import com.javachess.move.CastlingQueenSide;
import com.javachess.move.Move;
import com.javachess.move.StandardMove;
import com.javachess.piece.Color;
import com.javachess.state.State;

public class KingMoveGenerator implements MoveGeneratorOld {

	@Override
	public List<Move> generate(Square position, Color color, Board board, State state) {
		List<Move> moves = new ArrayList<Move>();

		moves.add(new StandardMove(position, new Square(position, 0, 1), board));
		moves.add(new StandardMove(position, new Square(position, 0, -1), board));
		moves.add(new StandardMove(position, new Square(position, 1, 0), board));
		moves.add(new StandardMove(position, new Square(position, -1, 0), board));
		moves.add(new StandardMove(position, new Square(position, -1, 1), board));
		moves.add(new StandardMove(position, new Square(position, -1, -1), board));
		moves.add(new StandardMove(position, new Square(position, 1, 1), board));
		moves.add(new StandardMove(position, new Square(position, 1, -1), board));
		
		if (state.canCastleKingSide(color))
			moves.add(new CastlingKingSide());
		
		if (state.canCastleQueenSide(color))
			moves.add(new CastlingQueenSide());
		
		return moves;
	}
}
