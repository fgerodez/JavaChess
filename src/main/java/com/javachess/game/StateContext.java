package com.javachess.game;

import java.util.List;
import java.util.Stack;

import com.javachess.board.Board;
import com.javachess.move.Move;
import com.javachess.piece.Color;
import com.javachess.state.GameState;

public class StateContext {
	private GameState currentState;
	private Color currentPlayerColor;
	
	private Stack<Move> moveHistory;
	private Stack<Color> colorHistory;
	private Stack<GameState> stateHistory;
	
	public StateContext() {
		this.currentPlayerColor = Color.WHITE;
		this.currentState = new GameState();
		
		this.moveHistory = new Stack<Move>();
		this.colorHistory = new Stack<Color>();
		this.stateHistory = new Stack<GameState>();
	}
	
	public void notifyMove(Move move, Board board) {		
		registerMove(move);
		
		currentPlayerColor = currentPlayerColor.opponent();
		currentState.notifyMove(move, board, this);
	}
	
	public void undo() {	
		moveHistory.pop();
		colorHistory.pop();
		stateHistory.pop();
	}
	
	public Color currentColor() {
		return currentPlayerColor;
	}
	
	public Move lastMove() {
		return moveHistory.peek();
	}
	
	public List<Move> getCtxMoves() {
		return currentState.getCtxMoves();
	}
	
	private void registerMove(Move move) {
		colorHistory.add(currentPlayerColor);
		stateHistory.add(currentState);
		moveHistory.add(move);
	}
}