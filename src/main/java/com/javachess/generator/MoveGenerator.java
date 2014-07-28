package com.javachess.generator;

import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.piece.Color;

public interface MoveGenerator {

    public List<Move> generateMoves(Square square, Color color, Board board);
}
