package com.javachess.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.move.PawnPush;
import com.javachess.move.enpassant.LeftEnPassant;
import com.javachess.move.enpassant.RightEnPassant;
import com.javachess.piece.Piece;
import com.javachess.piece.PieceType;

public class EnPassantState {

    private List<Move> enPassant;

    public EnPassantState() {
        enPassant = new ArrayList<Move>();
    }

    private EnPassantState(List<Move> enPassant) {
        this.enPassant = new ArrayList<Move>(enPassant);
    }

    public EnPassantState copy() {
        return new EnPassantState(enPassant);
    }

    public void notifyMove(Move move, Board board) {
        if (!(move instanceof PawnPush)) {
            enPassant = Collections.emptyList();
            return;
        }

        Square rightSrcSquare = Square.atOffset(move.getDst(), 0, -1);
        Square leftSrcSquare = Square.atOffset(move.getDst(), 0, 1);

        Piece srcPiece = board.at(move.getDst());
        Piece leftSrcPiece = board.at(leftSrcSquare);
        Piece rightSrcPiece = board.at(rightSrcSquare);

        if (leftSrcPiece != null && leftSrcPiece.isColor(srcPiece.color().opponent())
                && leftSrcPiece.isType(PieceType.PAWN)) {
            enPassant.add(new LeftEnPassant(leftSrcSquare, board));
        }

        if (rightSrcPiece != null && rightSrcPiece.isColor(srcPiece.color().opponent())
                && rightSrcPiece.isType(PieceType.PAWN)) {
            enPassant.add(new RightEnPassant(rightSrcSquare, board));
        }
    }

    public List<Move> getSpecialMoves() {
        return enPassant;
    }
}