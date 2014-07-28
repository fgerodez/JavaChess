package com.javachess.converter;

import com.javachess.board.Square;

public class StandardConverter implements NotationConverter {

    private enum Letter {

        A, B, C, D, E, F, G, H
    }

    @Override
    public Square getSrc(String notation) {
        return toSquare(notation.substring(0, 2));
    }

    @Override
    public Square getDst(String notation) {
        return toSquare(notation.substring(2, 4));
    }

    private Square toSquare(String code) {
        checkLength(code);

        Square square = null;

        try {
            Letter letter = Letter.valueOf(code.substring(0, 1));
            int row = Integer.parseInt(code.substring(1, 2));

            checkRow(row);

            square = Square.at(row - 1, letter.ordinal());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("An error occured while converting " + code + " " + e.getMessage());
        }

        return square;
    }

    private void checkRow(int row) {
        if (row < 1 || row > 8) {
            throw new IllegalArgumentException("Row value must be between 1 and 8");
        }
    }

    private void checkLength(String code) {
        if (code == null || code.length() != 2) {
            throw new IllegalArgumentException("Code length must be exactly 2 characters");
        }
    }
}