package com.javachess.move.enpassant;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.move.StandardMove;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;

class EnPassant implements Move {

    private final StandardMove horizontalMove;
    private final StandardMove forwardMove;

    public EnPassant(Square srcSquare, Square opponentSquare, Board board) {
        Color color = board.at(srcSquare).color();
        Square dstSquare = opponentSquare.forward(1 * color.dir());

        this.horizontalMove = new StandardMove(srcSquare, opponentSquare, board);
        this.forwardMove = new StandardMove(opponentSquare, dstSquare, board);
    }

    @Override
    public void execute() {
        horizontalMove.execute();
        forwardMove.execute();
    }

    @Override
    public void undo() {
        forwardMove.undo();
        horizontalMove.undo();
    }

    @Override
    public Square getSource() {
        return horizontalMove.getSource();
    }

    @Override
    public Square getDst() {
        return forwardMove.getDst();
    }

    @Override
    public Piece getCapturedPiece() {
        return horizontalMove.getCapturedPiece();
    }
}
