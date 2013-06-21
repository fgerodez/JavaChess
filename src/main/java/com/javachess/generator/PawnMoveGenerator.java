package com.javachess.generator;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.EnPassant;
import com.javachess.move.Move;
import com.javachess.piece.Color;
import com.javachess.state.State;

public class PawnMoveGenerator implements MoveGeneratorOld {

	@Override
	public List<Move> generate(Square position, Color color, Board board, State state) {
		List<Move> moves = new ArrayList<Move>();
	
		// TODO: port diag + fwd
		
		if (state.canEnpassant(position))
			moves.add(new EnPassant(null, null, null, null));
		
		return moves;
	}
}
