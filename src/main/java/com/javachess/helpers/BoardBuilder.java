package com.javachess.helpers;

import com.javachess.board.Color;
import com.javachess.pieces.Bishop;
import com.javachess.pieces.King;
import com.javachess.pieces.Knight;
import com.javachess.pieces.Pawn;
import com.javachess.pieces.Piece;
import com.javachess.pieces.Queen;
import com.javachess.pieces.Rook;

public class BoardBuilder {
	public static Piece[][] CLASSIC = {
			{ new Rook(Color.WHITE), new Knight(Color.WHITE),
					new Bishop(Color.WHITE), new Queen(Color.WHITE),
					new King(Color.WHITE), new Bishop(Color.WHITE),
					new Knight(Color.WHITE), new Rook(Color.WHITE) },
			{ new Pawn(Color.WHITE), new Pawn(Color.WHITE),
					new Pawn(Color.WHITE), new Pawn(Color.WHITE),
					new Pawn(Color.WHITE), new Pawn(Color.WHITE),
					new Pawn(Color.WHITE), new Pawn(Color.WHITE) },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ new Pawn(Color.BLACK), new Pawn(Color.BLACK),
					new Pawn(Color.BLACK), new Pawn(Color.BLACK),
					new Pawn(Color.BLACK), new Pawn(Color.BLACK),
					new Pawn(Color.BLACK), new Pawn(Color.BLACK) },
			{ new Rook(Color.BLACK), new Knight(Color.BLACK),
					new Bishop(Color.BLACK), new King(Color.BLACK),
					new Queen(Color.BLACK), new Bishop(Color.BLACK),
					new Knight(Color.BLACK), new Rook(Color.BLACK) } };

	public static Piece[][] CHECKMATE = {
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ new Knight(Color.WHITE), new King(Color.WHITE), new Bishop(Color.WHITE), null, null,
					null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ new King(Color.BLACK), null, null, null, null, null, null, null } };
}
