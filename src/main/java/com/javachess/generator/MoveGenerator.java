package com.javachess.generator;

import java.util.List;

import com.javachess.board.Board;
import com.javachess.move.Move;
import com.javachess.piece.Color;

public interface MoveGenerator {

	public List<Move> generateMoves(Color color, Move lastMove, Board board);
}
