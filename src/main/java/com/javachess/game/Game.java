package com.javachess.game;

import static com.javachess.evaluator.BoardEvaluator.legalMoves;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.board.initializer.BoardInitializer;
import com.javachess.converter.NotationConverter;
import com.javachess.move.Move;
import com.javachess.piece.Color;
import com.javachess.player.Player;
import com.javachess.state.GameState;

public class Game {

    private GameState state;
    private final Board board;
    private final NotationConverter converter;

    private final Stack<Move> moveHistory;
    private final Stack<GameState> stateHistory;
    private final Map<Color, Player> players;

    public Game(Player player1, Player player2, NotationConverter converter, BoardInitializer initializer) {
        this.board = new Board();
        this.converter = converter;
        this.state = new GameState();

        moveHistory = new Stack<>();
        stateHistory = new Stack<>();

        players = new HashMap<>();
        players.put(Color.WHITE, player1);
        players.put(Color.BLACK, player2);

        initializer.init(board);
    }

    public void start() {
        while (!state.isEnded()) {
            String notation = players.get(state.currentPlayerColor()).takeMove(this);

            Square srcSquare = converter.getSrc(notation);
            Square dstSquare = converter.getDst(notation);

            List<Move> legalMoves = legalMoves(state.currentPlayerColor(), state.specialMoves(), board);
            
            for (Move move : legalMoves) {
                if (move.equals(srcSquare, dstSquare)) {
                    executeMove(move);
                    break;
                }
            }
        }
    }

    public void undo() {
        moveHistory.peek().undo();
        moveHistory.pop();

        state = stateHistory.pop();
    }

    private void executeMove(Move move) {
        move.execute();

        moveHistory.add(move);
        stateHistory.add(state.copy());

        state.notifyMove(move, board);
    }
}
