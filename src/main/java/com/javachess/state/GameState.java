package com.javachess.state;

import static com.javachess.evaluator.BoardEvaluator.isCheck;
import static com.javachess.evaluator.BoardEvaluator.isCheckMate;
import static com.javachess.evaluator.BoardEvaluator.isStaleMate;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.move.Move;
import com.javachess.piece.Color;

public class GameState {

    private Color playerColor;

    private BoardState boardState;
    private final EnPassantState enPassantState;
    private final CastlingState castlingState;

    public GameState() {
        boardState = BoardState.STANDARD;
        enPassantState = new EnPassantState();
        castlingState = new CastlingState();
    }

    private GameState(Color playerColor, BoardState state, EnPassantState eps, CastlingState cs) {
        this.playerColor = playerColor;
        this.boardState = state;
        this.enPassantState = eps.copy();
        this.castlingState = cs.copy();
    }

    public GameState copy() {
        return new GameState(playerColor, boardState, enPassantState, castlingState);
    }

    public boolean isEnded() {
        return boardState == BoardState.CHECKMATE || boardState == BoardState.STALEMATE;
    }

    public void notifyMove(Move move, Board board) {
        playerColor = playerColor.opponent();

        enPassantState.notifyMove(move, board);
        castlingState.notifyMove(move, board);

        evaluateBoardState(board);
    }

    public List<Move> specialMoves() {
        List<Move> moves = new ArrayList<>();

        moves.addAll(enPassantState.getSpecialMoves());
        moves.addAll(castlingState.getSpecialMoves());

        return moves;
    }

    public Color currentPlayerColor() {
        return playerColor;
    }

    private void evaluateBoardState(Board board) {
        if (isCheck(playerColor, board)) {
            boardState = BoardState.CHECK;
        }

        if (isCheckMate(playerColor, specialMoves(), board)) {
            boardState = BoardState.CHECKMATE;
        }

        if (isStaleMate(playerColor, specialMoves(), board)) {
            boardState = BoardState.STALEMATE;
        }

        boardState = BoardState.STANDARD;
    }
}