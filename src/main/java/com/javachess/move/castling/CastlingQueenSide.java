package com.javachess.move.castling;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.evaluator.BoardEvaluator;
import com.javachess.move.Move;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;

public class CastlingQueenSide implements Move {

    private final Castling castlingQueenSide;

    public CastlingQueenSide(Color color, Board board) {
        Square kingSquare = BoardEvaluator.findKing(color, board);
        Square kingDestination = kingSquare.left(2);
        Square rookSrc = kingSquare.left(4);
        Square rookDst = kingSquare.left(1);

        castlingQueenSide = new Castling(kingSquare, kingDestination, rookSrc, rookDst, board);
    }

    @Override
    public void execute() {
        castlingQueenSide.execute();
    }

    @Override
    public void undo() {
        castlingQueenSide.undo();
    }

    @Override
    public Piece getCapturedPiece() {
        return castlingQueenSide.getCapturedPiece();
    }

    @Override
    public Square getSource() {
        return castlingQueenSide.getSource();
    }

    @Override
    public Square getDst() {
        return castlingQueenSide.getDst();
    }
}
