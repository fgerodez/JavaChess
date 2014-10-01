package com.javachess.move;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.piece.Piece;

public class StandardMove implements Move {

    protected Board board;
    protected Square srcSquare;
    protected Square dstSquare;
    protected Piece capturedPiece;

    public StandardMove(Square sourceSquare, Square targetSquare, Board board) {
        this.srcSquare = sourceSquare;
        this.board = board;
        this.dstSquare = targetSquare;
    }

    @Override
    public void execute() {
        Piece sourcePiece = board.at(srcSquare);
        Piece targetPiece = board.at(dstSquare);

        board.removePieceAt(srcSquare);
        board.setPieceAt(dstSquare, sourcePiece);

        capturedPiece = targetPiece;
    }

    @Override
    public void undo() {
        Piece movedPiece = board.at(dstSquare);

        board.setPieceAt(dstSquare, capturedPiece);
        board.setPieceAt(srcSquare, movedPiece);

        capturedPiece = null;
    }

    @Override
    public Square getSource() {
        return srcSquare;
    }

    @Override
    public Square getDst() {
        return dstSquare;
    }

    @Override
    public Piece getCapturedPiece() {
        return capturedPiece;
    }
}
