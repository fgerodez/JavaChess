package com.javachess.board;

import com.javachess.board.initializer.BoardInitializer;
import com.javachess.converter.NotationConverter;
import com.javachess.state.StateContext;

public class Game {
	private Board board;
	private StateContext context;
	private NotationConverter converter;

	public Game(NotationConverter converter, BoardInitializer initializer) {
		this.board = new Board(initializer);
		this.converter = converter;
		this.context = new StateContext();
	}

	public void start() {
		while (true) {
			String notation = null;//currentPlayer.getNextMove(this);
			
			Square srcSquare = converter.getSrc(notation);
			Square dstSquare = converter.getDst(notation);
			
			srcSquare.equals(dstSquare);
			
			// TODO: execute move
			context.notifyMove(null, board);
		}
	}

	public void undo() {
		context.lastMove().undo();
		context.undo();
	}
}