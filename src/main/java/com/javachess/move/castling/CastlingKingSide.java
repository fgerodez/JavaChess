package com.javachess.move.castling;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.evaluator.BoardEvaluator;
import com.javachess.move.Move;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;

public class CastlingKingSide implements Move {

    private final Castling castlingKingSide;

    public CastlingKingSide(Color color, Board board) {
        Square kingSquare = BoardEvaluator.findKing(color, board);
        Square kingDestination = kingSquare.right(2);
        Square rookSrc = kingSquare.right(3);
        Square rookDst = kingSquare.right(1);

        castlingKingSide = new Castling(kingSquare, kingDestination, rookSrc, rookDst, board);
    }

    @Override
    public void execute() {
        castlingKingSide.execute();
    }

    @Override
    public void undo() {
        castlingKingSide.undo();
    }

    @Override
    public Piece getCapturedPiece() {
        return castlingKingSide.getCapturedPiece();
    }

    @Override
    public Square getSource() {
        return castlingKingSide.getSource();
    }

    @Override
    public Square getDst() {
        return castlingKingSide.getDst();
    }
}
