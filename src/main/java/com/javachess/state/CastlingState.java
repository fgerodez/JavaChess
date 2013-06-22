package com.javachess.state;

import static com.javachess.evaluator.BoardEvaluator.findKing;
import static com.javachess.evaluator.BoardEvaluator.isThreatened;

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

	private Map<Color, List<CastlingType>> semiLegalCastlings;

	public CastlingState() {
		semiLegalCastlings = new HashMap<Color, List<CastlingType>>();

		CastlingType[] initialCastlings = { CastlingType.CASTLING_KING_SIDE, CastlingType.CASTLING_QUEEN_SIDE };

		semiLegalCastlings.put(Color.WHITE, Arrays.asList(initialCastlings));
		semiLegalCastlings.put(Color.BLACK, Arrays.asList(initialCastlings));
	}

	public void executeTransition(Move move, Board board) {
		Piece srcPiece = board.at(move.getDst());
		
		if (srcPiece.isType(PieceType.KING)) {
			List<CastlingType> noCaslings = Collections.emptyList();
			semiLegalCastlings.put(srcPiece.color(), noCaslings);
		}

		if (srcPiece.isType(PieceType.ROOK) && move.getSource().getCol() == 0) {
			semiLegalCastlings.get(srcPiece.color()).remove(CastlingType.CASTLING_QUEEN_SIDE);
		}

		if (srcPiece.isType(PieceType.ROOK) && move.getSource().getCol() == 7) {
			semiLegalCastlings.get(srcPiece.color()).remove(CastlingType.CASTLING_KING_SIDE);
		}
	}

	public List<Move> getCtxMoves(Color color, Board board) {
		List<CastlingType> legalCastlings = semiLegalCastlings.get(color);
		List<Move> moves = new ArrayList<Move>();

		for (CastlingType type : legalCastlings) {
			switch (type) {
			case CASTLING_KING_SIDE:
				kingSideCastling(moves, color, board);
			case CASTLING_QUEEN_SIDE:
				queenSideCastling(moves, color, board);
			}
		}

		return moves;
	}

	private void kingSideCastling(List<Move> moves, Color color, Board board) {
		Square kingSquare = BoardEvaluator.findKing(color, board);

		Square fstInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() + 1);
		Square sndInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() + 2);

		if (!board.isFree(fstInterSquare) || !board.isFree(sndInterSquare))
			return;

		if (isThreatened(fstInterSquare, board))
			return;

		moves.add(new CastlingKingSide(color));
	}

	private void queenSideCastling(List<Move> moves, Color color, Board board) {
		Square kingSquare = findKing(color, board);

		Square fstInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() - 1);
		Square sndInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() - 2);
		Square thdInterSquare = Square.at(kingSquare.getRow(), kingSquare.getCol() - 3);

		if (!board.isFree(fstInterSquare) || !board.isFree(sndInterSquare) || !board.isFree(thdInterSquare))
			return;

		if (isThreatened(fstInterSquare, board) || isThreatened(sndInterSquare, board))
			return;

		moves.add(new CastlingQueenSide(color));
	}
}
