package com.javachess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.moves.EnPassant;
import com.javachess.moves.Move;
import com.javachess.moves.PawnTwoFwd;
import com.javachess.moves.StandardMove;

public class Pawn extends Piece {

	public Pawn(Color color, Square position) {
		super(color, position);
	}

	@Override
	public List<Move> availableMoves(Square src, final Board board) {
		List<Move> moves = new ArrayList<Move>();

		forward(src, moves, board);
		diagonal(src, moves, board);
		enPassant(src, moves, board);

		return moves;
	}

	private void diagonal(Square src, List<Move> moves, Board board) {
		Square diagLeft = new Square(src, -1, 1 * color.dir());
		Square diagRight = new Square(src, 1, 1 * color.dir());

		Piece diagLeftPiece = board.getPiece(diagLeft);
		Piece diagRightPiece = board.getPiece(diagRight);

		if (diagLeftPiece != null && !this.isSameColor(diagLeftPiece))
			moves.add(new StandardMove(src, diagLeft, board));

		if (diagRightPiece != null && !this.isSameColor(diagRightPiece))
			moves.add(new StandardMove(src, diagRight, board));
	}

	private void forward(Square src, List<Move> moves, final Board board) {
		Square fwdSquare = new Square(src, 0, 1 * color.dir());
		Square fwd2Square = new Square(src, 0, 2 * color.dir());

		if (board.getPiece(fwdSquare) != null)
			return;

		moves.add(new StandardMove(src, fwdSquare, board));

		if (src.equals(initialPosition) && board.getPiece(fwd2Square) == null)
			moves.add(new PawnTwoFwd(src, fwd2Square, board));
	}

	protected void enPassant(Square src, List<Move> moves, final Board board) {
		if (board.lastMove() instanceof PawnTwoFwd) {
			PawnTwoFwd lastMove = (PawnTwoFwd) board.lastMove();
			
			Square rightEnPassant = new Square(lastMove.getDst(), -1, 0);
			Square leftEnPassant = new Square(lastMove.getDst(), 1, 0);

			if (rightEnPassant.equals(src))
				moves.add(new EnPassant(src,
						new Square(src, 1, 1 * color.dir()), board));

			if (leftEnPassant.equals(src))
				moves.add(new EnPassant(src, new Square(src, -1, 1 * color
						.dir()), board));
		}
	}
}
