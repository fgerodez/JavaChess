package com.javachess.move.castling;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.move.StandardMove;
import com.javachess.piece.Piece;

class Castling implements Move {

    private final StandardMove kingMove;
    private final StandardMove rookMove;

    public Castling(Square kingSrc, Square kingDst, Square rookSrc, Square rookDst, Board board) {
        kingMove = new StandardMove(kingSrc, kingDst, board);
        rookMove = new StandardMove(rookSrc, rookDst, board);
    }

    @Override
    public void execute() {
        kingMove.execute();
        rookMove.execute();
    }

    @Override
    public void undo() {
        rookMove.undo();
        kingMove.undo();
    }

    @Override
    public Piece getCapturedPiece() {
        return null;
    }

    @Override
    public Square getSource() {
        return kingMove.getSource();
    }

    @Override
    public Square getDst() {
        return kingMove.getDst();
    }
}
