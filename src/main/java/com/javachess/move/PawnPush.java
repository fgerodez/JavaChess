package com.javachess.move;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;

public class PawnPush implements Move {

    private final StandardMove pawnPush;
    
    public PawnPush(Square srcSquare, Board board) {
        Color color = board.at(srcSquare).color();
        Square dstSquare = srcSquare.forward(2 * color.dir());
        
        this.pawnPush = new StandardMove(srcSquare, dstSquare, board);
    }

    @Override
    public void execute() {
        pawnPush.execute();
    }

    @Override
    public void undo() {
        pawnPush.undo();
    }

    @Override
    public Piece getCapturedPiece() {
        return pawnPush.getCapturedPiece();
    }

    @Override
    public Square getSource() {
        return pawnPush.getSource();
    }

    @Override
    public Square getDst() {
        return pawnPush.getDst();
    }
}
