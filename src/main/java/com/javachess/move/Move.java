package com.javachess.move;

import com.javachess.board.Square;
import com.javachess.piece.Piece;

public interface Move {

    void execute();

    void undo();

    Piece getCapturedPiece();

    Square getSource();

    Square getDst();

    default boolean equals(Square src, Square dst) {
        return getSource().equals(src) && getDst().equals(dst);
    }
}