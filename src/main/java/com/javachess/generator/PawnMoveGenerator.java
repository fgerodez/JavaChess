package com.javachess.generator;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.move.PawnPush;
import com.javachess.move.StandardMove;
import com.javachess.piece.Color;

public class PawnMoveGenerator implements MoveGenerator {

    @Override
    public List<Move> generateMoves(Square square, Color color, Board board) {
        List<Move> moves = new ArrayList<>();

        Square fwd = Square.atOffset(square, 1 * color.dir(), 0);
        Square push = Square.atOffset(square, 2 * color.dir(), 0);
        Square leftDiag = Square.atOffset(square, color.dir(), -1);
        Square rightDiag = Square.atOffset(square, color.dir(), 1);

        if (board.isFree(fwd)) {
            moves.add(new StandardMove(square, fwd, board));
        }

        if (square.getRow() == color.pawnRow() && board.isFree(fwd) && board.isFree(push)) {
            moves.add(new PawnPush(square, board));
        }

        if (board.isColor(rightDiag, color.opponent())) {
            moves.add(new StandardMove(square, rightDiag, board));
        }

        if (board.isColor(leftDiag, color.opponent())) {
            moves.add(new StandardMove(square, leftDiag, board));
        }

        return moves;
    }
}
