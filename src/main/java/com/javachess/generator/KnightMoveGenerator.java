package com.javachess.generator;

import static com.javachess.generator.MoveGeneratorHelper.addMoveIfEmptyOrOpponent;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.piece.Color;

public class KnightMoveGenerator implements MoveGenerator {

    @Override
    public List<Move> generateMoves(Square square, Color color, Board board) {
        List<Move> moves = new ArrayList<>();

        addMoveIfEmptyOrOpponent(square, Square.atOffset(square, 2, 1), color, moves, board);
        addMoveIfEmptyOrOpponent(square, Square.atOffset(square, 2, -1), color, moves, board);
        addMoveIfEmptyOrOpponent(square, Square.atOffset(square, -2, 1), color, moves, board);
        addMoveIfEmptyOrOpponent(square, Square.atOffset(square, -2, -1), color, moves, board);
        addMoveIfEmptyOrOpponent(square, Square.atOffset(square, 1, 2), color, moves, board);
        addMoveIfEmptyOrOpponent(square, Square.atOffset(square, 1, -2), color, moves, board);
        addMoveIfEmptyOrOpponent(square, Square.atOffset(square, -1, 2), color, moves, board);
        addMoveIfEmptyOrOpponent(square, Square.atOffset(square, -1, -2), color, moves, board);

        return moves;
    }
}
