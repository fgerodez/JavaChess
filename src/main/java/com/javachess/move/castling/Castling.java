package com.javachess.move.castling;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.evaluator.BoardEvaluator;
import com.javachess.move.Move;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;

public abstract class Castling implements Move {

    private final Board board;
    protected Square kingSquare;

    public Castling(Color color, Board board) {
        this.board = board;
        this.kingSquare = BoardEvaluator.findKing(color, board);
    }

    @Override
    public void execute() {
        board.movePiece(kingSquare, getDst());
        board.movePiece(getRookSrc(), getRookDst());
    }

    @Override
    public void undo() {
        board.movePiece(getDst(), kingSquare);
        board.movePiece(getRookDst(), getRookSrc());
    }

    @Override
    public Piece getCapturedPiece() {
        return null;
    }

    @Override
    public Square getSource() {
        return kingSquare;
    }

    @Override
    public abstract Square getDst();

    @Override
    public boolean equals(Square src, Square dst) {
        return getSource().equals(src) && getDst().equals(dst);
    }

    protected abstract Square getRookSrc();

    protected abstract Square getRookDst();
}
