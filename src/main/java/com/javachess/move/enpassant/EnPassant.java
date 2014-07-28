package com.javachess.move.enpassant;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.StandardMove;

public abstract class EnPassant extends StandardMove {

    protected Square capturedPieceSquare;

    public EnPassant(Square srcSquare, Board board) {
        super(srcSquare, null, board);

        dstSquare = getDstSquare(srcSquare);
        capturedPieceSquare = getCapturedSquare(srcSquare);
        capturedPiece = board.at(capturedPieceSquare);
    }

    @Override
    public void execute() {
        board.movePiece(srcSquare, dstSquare);
        board.removePieceAt(capturedPieceSquare);
    }

    @Override
    public void undo() {
        board.movePiece(dstSquare, srcSquare);
        board.setPieceAt(capturedPieceSquare, capturedPiece);
    }

    protected abstract Square getDstSquare(Square srcSquare);

    protected abstract Square getCapturedSquare(Square srcSquare);
}
