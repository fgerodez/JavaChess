package com.javachess.move.enpassant;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.piece.Piece;

public class LeftEnPassant implements Move {

    private final EnPassant enPassant;

    public LeftEnPassant(Square targetSquare, Board board) {
        Square srcSquare = targetSquare.right(1);

        enPassant = new EnPassant(srcSquare, targetSquare, board);
    }

    @Override
    public void execute() {
        enPassant.execute();
    }

    @Override
    public void undo() {
        enPassant.undo();
    }

    @Override
    public Piece getCapturedPiece() {
        return enPassant.getCapturedPiece();
    }

    @Override
    public Square getSource() {
        return enPassant.getSource();
    }

    @Override
    public Square getDst() {
        return enPassant.getDst();
    }
}
