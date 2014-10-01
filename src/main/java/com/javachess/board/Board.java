package com.javachess.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javachess.piece.Color;
import com.javachess.piece.Piece;

/**
 * Chess board representation
 *
 * @author Ouzned
 *
 */
public class Board {

    private final Map<Square, Piece> positions;

    public Board() {
        positions = new HashMap<>();
    }

    /* Used only for copying */
    private Board(Map<Square, Piece> newBoard) {
        this.positions = newBoard;
    }

    public Board copy() {
        return new Board(new HashMap<>(positions));
    }

    public List<Square> allSquares() {
        return new ArrayList<>(positions.keySet());
    }

    public Piece at(Square square) {
        return positions.get(square);
    }

    public Piece removePieceAt(Square square) {
        return positions.remove(square);
    }

    public void setPieceAt(Square position, Piece piece) {
        if (position != null && piece != null) {
            positions.put(position, piece);
        }
    }

    public void movePiece(Square src, Square dst) {
        Piece piece = at(src);

        removePieceAt(src);
        setPieceAt(dst, piece);
    }

    public boolean isFree(Square square) {
        if (square == null) {
            return false;
        }

        return !positions.containsKey(square);
    }

    public boolean isColor(Square square, Color color) {
        Piece piece = at(square);

        if (piece != null) {
            return piece.color().equals(color);
        }

        return false;
    }
}
