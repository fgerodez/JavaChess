package com.javachess.helpers;

import com.javachess.jeu.Case;

public class PositionConverter {
	private static String[] LETTRES = { "A", "B", "C", "D", "E", "F", "G", "H" };

	/**
	 * Calcule l'index interne qui reprŽsente une case
	 * 
	 * @param nCase
	 *            La case pour laquelle calculer l'index
	 * @return L'index qui reprŽsente la case
	 */
	public static int convertCaseEnIndex(Case nCase) {
		return (nCase.getColonne() - 1) + (nCase.getLigne() - 1)
				* LETTRES.length;
	}

	/**
	 * Calcule la case qui correspond ˆ l'index interne
	 * 
	 * @param index
	 *            L'index pour lequel calculer la case
	 * @return La case reprŽsentŽe par l'index
	 */
	public static Case convertIndexEnCase(int index) {
		int colonne = index - (LETTRES.length * (index / LETTRES.length)) + 1;
		int ligne = index / LETTRES.length + 1;

		return new Case(colonne, ligne);
	}

	/**
	 * Renvoie toutes les cases adjacentes d'une case. Ne fais aucun contr™le de
	 * validitŽ
	 * 
	 * @param nCase
	 *            La case pour laquelle calculer les cases adjacentes
	 * @return Le tableau des cases adjacentes
	 */
	public static Case[] getCasesAdjacentes(Case nCase) {
		return new Case[] { getCaseDiagHautGauche(nCase),
				getCaseDiagHautDroite(nCase), getCaseDiagBasGauche(nCase),
				getCaseDiagBasDroite(nCase), getCaseHaut(nCase),
				getCaseBas(nCase), getCaseDroite(nCase), getCaseGauche(nCase) };
	}

	public static Case getCaseDiagHautGauche(Case nCase) {
		int nvlLigne = nCase.getLigne() + 1;
		int nvlColonne = nCase.getColonne() - 1;

		return new Case(nvlColonne, nvlLigne);
	}

	public static Case getCaseDiagHautDroite(Case nCase) {
		int nvlLigne = nCase.getLigne() + 1;
		int nvlColonne = nCase.getColonne() + 1;

		return new Case(nvlColonne, nvlLigne);
	}

	public static Case getCaseDiagBasGauche(Case nCase) {
		int nvlLigne = nCase.getLigne() - 1;
		int nvlColonne = nCase.getColonne() - 1;

		return new Case(nvlColonne, nvlLigne);
	}

	public static Case getCaseDiagBasDroite(Case nCase) {
		int nvlLigne = nCase.getLigne() - 1;
		int nvlColonne = nCase.getColonne() + 1;

		return new Case(nvlColonne, nvlLigne);
	}

	public static Case getCaseHaut(Case nCase) {
		int nvlLigne = nCase.getLigne() + 1;
		int nvlColonne = nCase.getColonne();

		return new Case(nvlColonne, nvlLigne);
	}

	public static Case getCaseHaut(Case nCase, int nombre) {
		Case caseRes = new Case(nCase.getColonne(), nCase.getLigne());

		while (nombre != 0) {
			caseRes = getCaseHaut(caseRes);
			nombre--;
		}

		return caseRes;
	}

	public static Case getCaseBas(Case nCase) {
		int nvlLigne = nCase.getLigne() - 1;
		int nvlColonne = nCase.getColonne();

		return new Case(nvlColonne, nvlLigne);
	}

	public static Case getCaseBas(Case nCase, int nombre) {
		Case caseRes = new Case(nCase.getColonne(), nCase.getLigne());

		while (nombre != 0) {
			caseRes = getCaseBas(caseRes);
			nombre--;
		}

		return caseRes;
	}

	public static Case getCaseGauche(Case nCase) {
		int nvlLigne = nCase.getLigne();
		int nvlColonne = nCase.getColonne() - 1;

		return new Case(nvlColonne, nvlLigne);
	}

	public static Case getCaseDroite(Case nCase) {
		int nvlLigne = nCase.getLigne();
		int nvlColonne = nCase.getColonne() + 1;

		return new Case(nvlColonne, nvlLigne);
	}

	/**
	 * Calcule la direction d'un coup
	 * 
	 * @param coup
	 *            Le coup ˆ Žtudier
	 * 
	 * @return La direction du coup
	 */
	public static Direction getDirection(Coup coup) {
		boolean colonne = false;
		boolean ligne = false;

		Case caseSource = coup.getCaseSource();
		Case caseDestination = coup.getCaseDestination();

		int deltaY = Math.abs(caseDestination.getLigne()
				- caseSource.getLigne());
		int deltaX = Math.abs(caseDestination.getColonne()
				- caseSource.getColonne());

		if (caseDestination.getLigne() != caseSource.getLigne())
			colonne = true;

		if (caseDestination.getColonne() != caseSource.getColonne())
			ligne = true;

		if (ligne && colonne && (deltaX == deltaY))
			return Direction.DIAGONALE;

		if (ligne && colonne)
			return Direction.AUTRE;

		if (ligne)
			return Direction.LIGNE;

		if (colonne)
			return Direction.COLONNE;

		return Direction.AUTRE;
	}

	/**
	 * Calcule le sens d'un coup
	 * 
	 * @param coup
	 *            Le coup ˆ Žtudier
	 * @return Le sens du coup
	 */
	public static Sens getSensCoup(Coup coup) {

		Direction direction = getDirection(coup);

		switch (direction) {
		case COLONNE:
			return getSensPourColonne(coup);
		case LIGNE:
			return getSensPourLigne(coup);
		case DIAGONALE:
			return getSensPourDiagonale(coup);
		default:
			return null;
		}
	}

	/**
	 * Calcule le sens pour la direction Ligne
	 * 
	 * @param coup
	 *            Le coup ˆ Žtudier
	 * 
	 * @return Le sens du coup
	 */
	private static Sens getSensPourLigne(Coup coup) {
		Case caseSource = coup.getCaseSource();
		Case caseDest = coup.getCaseDestination();

		if (caseDest.getColonne() - caseSource.getColonne() > 0)
			return Sens.DROITE;
		else
			return Sens.GAUCHE;
	}

	/**
	 * Calcule le sens pour la direction Colonne
	 * 
	 * @param coup
	 *            Le coup ˆ Žtudier
	 * 
	 * @return Le sens du coup
	 */
	private static Sens getSensPourColonne(Coup coup) {
		Case caseSource = coup.getCaseSource();
		Case caseDest = coup.getCaseDestination();

		if (caseDest.getLigne() - caseSource.getLigne() > 0)
			return Sens.HAUT;
		else
			return Sens.BAS;
	}

	/**
	 * Calcule le sens pour la direction DIAGONALE
	 * 
	 * @param coup
	 *            Le coup ˆ Žtudier
	 * 
	 * @return Le sens du coup
	 */
	private static Sens getSensPourDiagonale(Coup coup) {
		Case caseSource = coup.getCaseSource();
		Case caseDest = coup.getCaseDestination();

		if ((caseDest.getLigne() - caseSource.getLigne() > 0)
				&& (caseDest.getColonne() - caseSource.getColonne() > 0))
			return Sens.DIAGHAUTDROITE;

		if ((caseDest.getLigne() - caseSource.getLigne() > 0)
				&& (caseDest.getColonne() - caseSource.getColonne() < 0))
			return Sens.DIAGHAUTGAUCHE;

		if ((caseDest.getLigne() - caseSource.getLigne() < 0)
				&& (caseDest.getColonne() - caseSource.getColonne() > 0))
			return Sens.DIAGBASDROITE;

		if ((caseDest.getLigne() - caseSource.getLigne() < 0)
				&& (caseDest.getColonne() - caseSource.getColonne() < 0))
			return Sens.DIAGBASGAUCHE;

		assert (false);
		return null;
	}
}
