package com.javachess.piece;

import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;

public enum Piece {

    WHITE_KING(PieceType.KING, Color.WHITE),
    WHITE_QUEEN(PieceType.QUEEN, Color.WHITE),
    WHITE_BISHOP(PieceType.BISHOP, Color.WHITE),
    WHITE_KNIGHT(PieceType.KNIGHT, Color.WHITE),
    WHITE_ROOK(PieceType.ROOK, Color.WHITE),
    WHITE_PAWN(PieceType.PAWN, Color.WHITE),
    BLACK_KING(PieceType.KING, Color.BLACK),
    BLACK_QUEEN(PieceType.QUEEN, Color.BLACK),
    BLACK_BISHOP(PieceType.BISHOP, Color.BLACK),
    BLACK_KNIGHT(PieceType.KNIGHT, Color.BLACK),
    BLACK_ROOK(PieceType.ROOK, Color.BLACK),
    BLACK_PAWN(PieceType.PAWN, Color.BLACK);

    private final PieceType type;
    private final Color color;

    Piece(PieceType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public Color color() {
        return color;
    }

    public boolean isColor(Color otherColor) {
        return color.equals(otherColor);
    }

    public boolean isType(PieceType otherType) {
        return type.equals(otherType);
    }

    public List<Move> availableMoves(Square position, Board board) {
        return type.generator.generateMoves(position, color, board);
    }
}