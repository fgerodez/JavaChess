package com.javachess.helpers;

import com.javachess.modele.plateau.Square;

public class PositionConverter {
	private static String[] LETTRES = { "A", "B", "C", "D", "E", "F", "G", "H" };

	/**
	 * Calcule l'index interne qui représente une case
	 * 
	 * @param nCase
	 *            La case pour laquelle calculer l'index
	 * @return L'index qui représente la case
	 */
	public static int convertCaseEnIndex(Square nCase) {
		return (nCase.getColumn() - 1) + (nCase.getRow() - 1)
				* LETTRES.length;
	}

	/**
	 * Calcule la case qui correspond à l'index interne
	 * 
	 * @param index
	 *            L'index pour lequel calculer la case
	 * @return La case représentée par l'index
	 */
	public static Square convertIndexEnCase(int index) {
		int colonne = index - (LETTRES.length * (index / LETTRES.length)) + 1;
		int ligne = index / LETTRES.length + 1;

		return new Square(colonne, ligne);
	}
}
