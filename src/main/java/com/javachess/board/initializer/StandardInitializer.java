package com.javachess.board.initializer;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;

public class StandardInitializer implements BoardInitializer {

    private static final int COLS = 8;

    @Override
    public void init(Board board) {
        board.setPieceAt(Square.at(0, 0), Piece.WHITE_ROOK);
        board.setPieceAt(Square.at(0, 1), Piece.WHITE_KNIGHT);
        board.setPieceAt(Square.at(0, 2), Piece.WHITE_BISHOP);
        board.setPieceAt(Square.at(0, 3), Piece.WHITE_QUEEN);
        board.setPieceAt(Square.at(0, 4), Piece.WHITE_KING);
        board.setPieceAt(Square.at(0, 5), Piece.WHITE_BISHOP);
        board.setPieceAt(Square.at(0, 6), Piece.WHITE_KNIGHT);
        board.setPieceAt(Square.at(0, 7), Piece.WHITE_ROOK);

        board.setPieceAt(Square.at(7, 0), Piece.BLACK_ROOK);
        board.setPieceAt(Square.at(7, 1), Piece.BLACK_KNIGHT);
        board.setPieceAt(Square.at(7, 2), Piece.BLACK_BISHOP);
        board.setPieceAt(Square.at(7, 3), Piece.BLACK_QUEEN);
        board.setPieceAt(Square.at(7, 4), Piece.BLACK_KING);
        board.setPieceAt(Square.at(7, 5), Piece.BLACK_BISHOP);
        board.setPieceAt(Square.at(7, 6), Piece.BLACK_KNIGHT);
        board.setPieceAt(Square.at(7, 7), Piece.BLACK_ROOK);

        for (int col = 0; col < COLS; col++) {
            board.setPieceAt(Square.at(Color.WHITE.pawnRow(), col), Piece.WHITE_PAWN);
        }

        for (int col = 0; col < COLS; col++) {
            board.setPieceAt(Square.at(Color.BLACK.pawnRow(), col), Piece.BLACK_PAWN);
        }
    }
}