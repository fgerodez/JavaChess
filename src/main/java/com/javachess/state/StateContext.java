package com.javachess.state;

import java.util.Stack;

import com.javachess.board.Board;
import com.javachess.move.Move;
import com.javachess.piece.Color;

public class StateContext {
	private State currentState;
	private Color currentPlayerColor;
	
	private Stack<Move> moveHistory;
	private Stack<Color> colorHistory;
	private Stack<State> stateHistory;
	
	public StateContext() {
		this.currentPlayerColor = Color.WHITE;
		this.currentState = new StandardState();
		
		this.moveHistory = new Stack<Move>();
		this.colorHistory = new Stack<Color>();
		this.stateHistory = new Stack<State>();
	}
	
	public boolean execute(Move move, Board board) {
		if (!currentState.isValidMove(move, board, this))
			return false;
		
		registerMove(move);
		move.execute();
		
		currentPlayerColor = currentPlayerColor.opponent();
		currentState.executeTransition(this, board);
		
		return true;
	}
	
	public void undo() {
		lastMove().undo();
		
		moveHistory.pop();
		colorHistory.pop();
		stateHistory.pop();
	}
	
	public boolean isEnded() {
		return currentState.isEnded();
	}
	
	public Color currentColor() {
		return currentPlayerColor;
	}
	
	private void registerMove(Move move) {
		colorHistory.add(currentPlayerColor);
		stateHistory.add(currentState);
		moveHistory.add(move);
	}
	
	void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
	Move lastMove() {
		return moveHistory.peek();
	}
}