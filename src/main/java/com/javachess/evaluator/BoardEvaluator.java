package com.javachess.evaluator;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.Move;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;
import com.javachess.piece.PieceType;
import com.javachess.state.State;

public class BoardEvaluator {

	private Board board;
	private State state;

	public BoardEvaluator(Board board, State state) {
		this.board = board;
		this.state = state;
	}

	public boolean isCheck(Color color) {
		Square kingSquare = findKing(color, board);

		List<Move> availableMoves = semiLegalMoves(color.opponent());

		for (Move move : availableMoves) {
			if (move.getDestination().equals(kingSquare))
				return true;
		}

		return false;
	}

	public boolean isCheckMate(Color color) {	
		return isCheck(color) && legalMoves(color).size() == 0;
	}

	public boolean isStaleMate(Color color) {
		return !isCheck(color) && legalMoves(color).size() == 0;
	}

	public List<Move> legalMoves(Color color) {
		List<Move> legalMoves = new ArrayList<Move>();

		for (Move move : semiLegalMoves(color)) {
			move.execute();

			if (!isCheck(color))
				legalMoves.add(move);

			move.undo();
		}

		return legalMoves;
	}

	private List<Move> semiLegalMoves(Color color) {
		List<Move> moveList = new ArrayList<Move>();

		for (Square square : board.allSquares()) {
			Piece piece = board.at(square);

			if (piece.isColor(color))
				moveList.addAll(piece.availableMoves(square, board, state));
		}

		return moveList;
	}

	private Square findKing(Color color, Board board) {
		for (Square square : board.allSquares()) {
			Piece piece = board.at(square);

			if (piece.isType(PieceType.KING) && piece.isColor(color)) {
				return square;
			}
		}

		throw new IllegalStateException("The king is missing!");
	}
}
