package com.javachess.helpers;

import com.javachess.modele.plateau.Case;

public class PositionConverter {
	public static String[] lettres = { "A", "B", "C", "D", "E", "F", "G", "H" };

	public static int convertCaseEnIndex(Case nCase) {
		return (nCase.getColonne() - 1) + (nCase.getLigne() - 1)
				* lettres.length;
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

	public static Case getCaseBas(Case nCase) {
		int nvlLigne = nCase.getLigne() - 1;
		int nvlColonne = nCase.getColonne();

		return new Case(nvlColonne, nvlLigne);
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

	public static Direction getDirection(Case caseSource, Case caseDestination) {
		boolean colonne = false;
		boolean ligne = false;

		if (caseDestination.getLigne() != caseSource.getLigne())
			colonne = true;

		if (caseDestination.getColonne() != caseSource.getColonne())
			ligne = true;

		if (ligne && colonne)
			return Direction.Diagonale;

		if (ligne)
			return Direction.Ligne;

		if (colonne)
			return Direction.Colonne;

		assert (false);

		return null;
	}

	public static Sens getSensCoup(Coup coup) {
		Case caseSource = coup.getCaseSource();
		Case caseDest = coup.getCaseDestination();

		Direction direction = getDirection(caseSource, caseDest);

		switch (direction) {
		case Colonne:
			return getSensPourColonne(coup);
		case Ligne:
			return getSensPourLigne(coup);
		case Diagonale:
			return getSensPourDiagonale(coup);
		default:
			assert (false);
		}

		return null;
	}

	private static Sens getSensPourLigne(Coup coup) {
		Case caseSource = coup.getCaseSource();
		Case caseDest = coup.getCaseDestination();

		if (caseDest.getColonne() - caseSource.getColonne() > 0)
			return Sens.Droite;
		else
			return Sens.Gauche;
	}

	private static Sens getSensPourColonne(Coup coup) {
		Case caseSource = coup.getCaseSource();
		Case caseDest = coup.getCaseDestination();

		if (caseDest.getLigne() - caseSource.getLigne() > 0)
			return Sens.Haut;
		else
			return Sens.Bas;
	}

	private static Sens getSensPourDiagonale(Coup coup) {
		Case caseSource = coup.getCaseSource();
		Case caseDest = coup.getCaseDestination();

		if ((caseDest.getLigne() - caseSource.getLigne() > 0)
				&& (caseDest.getColonne() - caseSource.getColonne() > 0))
			return Sens.DiagHautDroite;
		
		if ((caseDest.getLigne() - caseSource.getLigne() > 0)
				&& (caseDest.getColonne() - caseSource.getColonne() < 0))
			return Sens.DiagHautGauche;
		
		if ((caseDest.getLigne() - caseSource.getLigne() < 0)
				&& (caseDest.getColonne() - caseSource.getColonne() > 0))
			return Sens.DiagBasDroite;
		
		if ((caseDest.getLigne() - caseSource.getLigne() < 0)
				&& (caseDest.getColonne() - caseSource.getColonne() < 0))
			return Sens.DiagBasGauche;
		
		assert(false);
		return null;
	}
}
