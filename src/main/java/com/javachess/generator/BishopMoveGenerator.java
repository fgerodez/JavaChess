package com.javachess.generator;

import static com.javachess.generator.MoveGeneratorHelper.addVectorIfEmptyOrOpponent;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.piece.Color;

public class BishopMoveGenerator implements MoveGenerator {

    @Override
    public List<Move> generateMoves(Square square, Color color, Board board) {
        List<Move> moves = new ArrayList<>();

        moves.addAll(addVectorIfEmptyOrOpponent(square, 1, 1, color, board));
        moves.addAll(addVectorIfEmptyOrOpponent(square, 1, -1, color, board));
        moves.addAll(addVectorIfEmptyOrOpponent(square, -1, 1, color, board));
        moves.addAll(addVectorIfEmptyOrOpponent(square, -1, -1, color, board));

        return moves;
    }

}
