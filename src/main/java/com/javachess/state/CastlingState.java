package com.javachess.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.javachess.board.Board;
import com.javachess.evaluator.MoveEvaluator;
import com.javachess.move.Move;
import com.javachess.move.castling.CastlingKingSide;
import com.javachess.move.castling.CastlingQueenSide;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;
import com.javachess.piece.PieceType;
import java.util.EnumMap;

public class CastlingState {

    private enum CastlingType {

        CASTLING_KING_SIDE, CASTLING_QUEEN_SIDE
    };

    private final Map<Color, List<CastlingType>> availableCastlings;
    private final List<Move> castlings;

    public CastlingState() {
        CastlingType[] initialCastlings = {CastlingType.CASTLING_KING_SIDE, CastlingType.CASTLING_QUEEN_SIDE};

        castlings = new ArrayList<>();

        availableCastlings = new EnumMap<>(Color.class);
        availableCastlings.put(Color.WHITE, Arrays.asList(initialCastlings));
        availableCastlings.put(Color.BLACK, Arrays.asList(initialCastlings));
    }

    private CastlingState(Map<Color, List<CastlingType>> generalCastlings, List<Move> semiLegalCastlings) {
        this.availableCastlings = new EnumMap<>(generalCastlings);
        this.castlings = new ArrayList<>(semiLegalCastlings);
    }

    public CastlingState copy() {
        return new CastlingState(availableCastlings, castlings);
    }

    public void notifyMove(Move move, Board board) {
        updateAvailableCastlings(move, board);

        Color opponent = board.at(move.getDst()).color().opponent();

        if (availableCastlings.get(opponent).contains(CastlingType.CASTLING_KING_SIDE) && MoveEvaluator.canKingSideCastling(opponent, board)) {
            castlings.add(new CastlingKingSide(opponent, board));
        }

        if (availableCastlings.get(opponent).contains(CastlingType.CASTLING_QUEEN_SIDE) && MoveEvaluator.canQueenSideCastling(opponent, board)) {
            castlings.add(new CastlingQueenSide(opponent, board));
        }
    }

    private void updateAvailableCastlings(Move move, Board board) {
        Piece srcPiece = board.at(move.getDst());

        if (srcPiece.isType(PieceType.KING)) {
            availableCastlings.get(srcPiece.color()).clear();
        }

        if (srcPiece.isType(PieceType.ROOK) && move.getSource().getCol() == 0) {
            availableCastlings.get(srcPiece.color()).remove(CastlingType.CASTLING_QUEEN_SIDE);
        }

        if (srcPiece.isType(PieceType.ROOK) && move.getSource().getCol() == 7) {
            availableCastlings.get(srcPiece.color()).remove(CastlingType.CASTLING_KING_SIDE);
        }
    }

    public List<Move> getSpecialMoves() {
        return castlings;
    }
}
