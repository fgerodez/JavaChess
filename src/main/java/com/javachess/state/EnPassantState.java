package com.javachess.state;

import static com.javachess.evaluator.MoveEvaluator.canLeftEnPassant;
import static com.javachess.evaluator.MoveEvaluator.canRightEnPassant;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.move.Move;
import com.javachess.move.enpassant.LeftEnPassant;
import com.javachess.move.enpassant.RightEnPassant;

public class EnPassantState {

    private final List<Move> enPassant;

    public EnPassantState() {
        enPassant = new ArrayList<>();
    }

    private EnPassantState(List<Move> enPassant) {
        this.enPassant = new ArrayList<>(enPassant);
    }

    public EnPassantState copy() {
        return new EnPassantState(enPassant);
    }

    public void notifyMove(Move move, Board board) {
        enPassant.clear();
        
        if (canLeftEnPassant(move, board)) {
            enPassant.add(new LeftEnPassant(move.getDst(), board));
        }

        if (canRightEnPassant(move, board)) {
            enPassant.add(new RightEnPassant(move.getDst(), board));
        }
    }

    public List<Move> getSpecialMoves() {
        return enPassant;
    }
}
