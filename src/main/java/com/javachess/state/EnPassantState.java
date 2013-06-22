package com.javachess.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.move.LeftEnPassant;
import com.javachess.move.Move;
import com.javachess.move.PawnTwoFwd;
import com.javachess.move.RightEnPassant;
import com.javachess.piece.Piece;
import com.javachess.piece.PieceType;

public class EnPassantState implements State {

	private List<Move> enPassant;

	public EnPassantState() {
		enPassant = new ArrayList<Move>();
	}

	@Override
	public void executeTransition(Move move, Board board, StateContext context) {
		if (!(move instanceof PawnTwoFwd)) {
			enPassant = Collections.emptyList();
			return;
		}
			
		Square leftSrcSquare = Square.getSquare(move.getDst().getRow(), move.getDst().getCol() - 1);
		Square rightSrcSquare = Square.getSquare(move.getDst().getRow(), move.getDst().getCol() + 1);
		
		Piece srcPiece = board.at(move.getDst());
		Piece leftSrcPiece = board.at(leftSrcSquare);
		Piece rightSrcPiece = board.at(rightSrcSquare);
		
		if (leftSrcPiece.isColor(srcPiece.color().opponent()) && leftSrcPiece.isType(PieceType.PAWN))
			enPassant.add(new LeftEnPassant(leftSrcSquare, board));
		
		if (rightSrcPiece.isColor(srcPiece.color().opponent()) && rightSrcPiece.isType(PieceType.PAWN))
			enPassant.add(new RightEnPassant(rightSrcSquare, board));
	}
	
	public List<Move> getCtxMoves() {
		return enPassant;
	}
}
