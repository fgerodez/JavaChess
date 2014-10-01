package com.javachess.evaluator;

import com.javachess.board.Board;
import com.javachess.board.Square;
import static com.javachess.evaluator.BoardEvaluator.isCheck;
import static com.javachess.evaluator.BoardEvaluator.isThreatenedBy;
import com.javachess.move.Move;
import com.javachess.move.PawnPush;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;
import com.javachess.piece.PieceType;
import java.util.ArrayList;
import java.util.List;

public class MoveEvaluator {

    public static boolean canLeftEnPassant(Move lastMove, Board board) {
        return canEnPassant(lastMove.getDst().right(1), lastMove, board);
    }

    public static boolean canRightEnPassant(Move lastMove, Board board) {
        return canEnPassant(lastMove.getDst().left(1), lastMove, board);
    }

    public static boolean canKingSideCastling(Color color, Board board) {
        Square kingSquare = BoardEvaluator.findKing(color, board);

        List<Square> freeSquares = new ArrayList<>();
        freeSquares.add(kingSquare.right(1));
        freeSquares.add(kingSquare.right(2));

        return canCastling(color, freeSquares, freeSquares.subList(0, 1), board);
    }

    public static boolean canQueenSideCastling(Color color, Board board) {
        Square kingSquare = BoardEvaluator.findKing(color, board);

        List<Square> freeSquares = new ArrayList<>();
        freeSquares.add(kingSquare.left(1));
        freeSquares.add(kingSquare.left(2));
        freeSquares.add(kingSquare.left(3));

        return canCastling(color, freeSquares, freeSquares.subList(0, 2), board);
    }

    private static boolean canCastling(Color color, List<Square> freeSquares, List<Square> threatenedSquares, Board board) {
        if (isCheck(color, board)) {
            return false;
        }

        boolean areFree = freeSquares.stream().allMatch(square -> {
            return board.isFree(square);
        });

        boolean areNotThreatened = threatenedSquares.stream().noneMatch(square -> {
            return isThreatenedBy(color.opponent(), square, board);
        });

        return areFree && areNotThreatened;
    }

    private static boolean canEnPassant(Square srcSquare, Move lastMove, Board board) {
        if (!(lastMove instanceof PawnPush)) {
            return false;
        }

        Color opponentColor = board.at(lastMove.getDst()).color();
        Piece enPassantCandidate = board.at(srcSquare);

        return enPassantCandidate != null && enPassantCandidate.isColor(opponentColor) && enPassantCandidate.isType(PieceType.PAWN);
    }
}
