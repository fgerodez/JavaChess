package com.javachess.state;

import java.util.List;
import java.util.Stack;

import com.javachess.board.Board;
import com.javachess.move.Move;
import com.javachess.piece.Color;

public class StateContext {
	private GameState currentState;
	private Color currentPlayerColor;
	
	private Stack<Move> moveHistory;
	private Stack<Color> colorHistory;
	private Stack<State> stateHistory;
	
	public StateContext() {
		this.currentPlayerColor = Color.WHITE;
		this.currentState = new GameState();
		
		this.moveHistory = new Stack<Move>();
		this.colorHistory = new Stack<Color>();
		this.stateHistory = new Stack<State>();
	}
	
	public void notifyMove(Move move, Board board) {		
		registerMove(move);
		
		currentPlayerColor = currentPlayerColor.opponent();
		currentState.executeTransition(move, board, this);
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
		return currentState.getCtxMoves(currentPlayerColor);
	}
	
	private void registerMove(Move move) {
		colorHistory.add(currentPlayerColor);
		stateHistory.add(currentState);
		moveHistory.add(move);
	}
	
	void setCurrentState(State newBoardState) {
		currentState.setBoardState(newBoardState);
	}
}