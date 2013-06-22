package com.javachess.state;

import static com.javachess.evaluator.BoardEvaluator.findKing;
import static com.javachess.evaluator.BoardEvaluator.isCheck;
import static com.javachess.evaluator.BoardEvaluator.isThreatenedBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javachess.board.Board;
import com.javachess.board.Square;
import com.javachess.evaluator.BoardEvaluator;
import com.javachess.move.Move;
import com.javachess.move.castling.CastlingKingSide;
import com.javachess.move.castling.CastlingQueenSide;
import com.javachess.piece.Color;
import com.javachess.piece.Piece;
import com.javachess.piece.PieceType;

public class CastlingState {

	private enum CastlingType {
		CASTLING_KING_SIDE, CASTLING_QUEEN_SIDE
	};

	private Map<Color, List<CastlingType>> generalCastlings;
	private List<Move> semiLegalCastlings;

	public CastlingState() {
		generalCastlings = new HashMap<Color, List<CastlingType>>();
		semiLegalCastlings = new ArrayList<Move>();

		CastlingType[] initialCastlings = { CastlingType.CASTLING_KING_SIDE, CastlingType.CASTLING_QUEEN_SIDE };
		
		generalCastlings.put(Color.WHITE, new ArrayList<CastlingType>(Arrays.asList(initialCastlings)));
		generalCastlings.put(Color.BLACK, new ArrayList<CastlingType>(Arrays.asList(initialCastlings)));
	}

	public void notifyMove(Move move, Board board) {
		semiLegalCastlings = new ArrayList<Move>();

		updateGeneralCastlings(move, board);

		Color opponent = board.at(move.getDst()).color().opponent();
		
		if (isCheck(opponent, board))
			return;

		if (generalCastlings.get(opponent).contains(CastlingType.CASTLING_KING_SIDE))
			kingSideCastling(semiLegalCastlings, opponent, board);

		if (generalCastlings.get(opponent).contains(CastlingType.CASTLING_QUEEN_SIDE))
			queenSideCastling(semiLegalCastlings, opponent, board);
	}

	private void updateGeneralCastlings(Move move, Board board) {
		Piece srcPiece = board.at(move.getDst());

		if (srcPiece.isType(PieceType.KING)) {
			List<CastlingType> noCaslings = Collections.emptyList();
			generalCastlings.put(srcPiece.color(), noCaslings);
		}

		if (srcPiece.isType(PieceType.ROOK) && move.getSource().getCol() == 0) {
			generalCastlings.get(srcPiece.color()).remove(CastlingType.CASTLING_QUEEN_SIDE);
		}

		if (srcPiece.isType(PieceType.ROOK) && move.getSource().getCol() == 7) {
			generalCastlings.get(srcPiece.color()).remove(CastlingType.CASTLING_KING_SIDE);
		}
	}

	public List<Move> getCtxMoves() {
		return semiLegalCastlings;
	}

	private void kingSideCastling(List<Move> moves, Color color, Board board) {
		Square kingSquare = BoardEvaluator.findKing(color, board);

		Square fstInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() + 1);
		Square sndInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() + 2);

		if (!board.isFree(fstInterSquare) || !board.isFree(sndInterSquare))
			return;

		if (isThreatenedBy(color.opponent(), fstInterSquare, board))
			return;

		moves.add(new CastlingKingSide(color, board));
	}

	private void queenSideCastling(List<Move> moves, Color color, Board board) {
		Square kingSquare = findKing(color, board);

		Square fstInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() - 1);
		Square sndInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() - 2);
		Square thdInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() - 3);

		if (!board.isFree(fstInterSquare) || !board.isFree(sndInterSquare) || !board.isFree(thdInterSquare))
			return;

		if (isThreatenedBy(color.opponent(), fstInterSquare, board)
				|| isThreatenedBy(color.opponent(), sndInterSquare, board))
			return;

		moves.add(new CastlingQueenSide(color, board));
	}
}
