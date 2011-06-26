package com.javachess.modele.plateau;

import com.javachess.exceptions.GameException;
import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.helpers.PositionConverter;
import com.javachess.helpers.Sens;
import com.javachess.modele.pieces.Cavalier;
import com.javachess.modele.pieces.Fou;
import com.javachess.modele.pieces.Piece;
import com.javachess.modele.pieces.Pion;
import com.javachess.modele.pieces.Reine;
import com.javachess.modele.pieces.Roi;
import com.javachess.modele.pieces.Tour;

/**
 * Classe qui modélise un plateau d'echec
 * 
 * @author Ouzned
 * 
 */
public class Echiquier {

	private Piece[] echiquier = new Piece[64];

	public Echiquier() {
		super();
		initialisePlateau();
	}

	/**
	 * Crée les 64 cases du plateau
	 */
	public void initialisePlateau() {
		Piece roiB = new Roi(Couleur.WHITE);
		Piece reineB = new Reine(Couleur.WHITE);
		Piece fouB = new Fou(Couleur.WHITE);
		Piece cavalierB = new Cavalier(Couleur.WHITE);
		Piece tourB = new Tour(Couleur.WHITE);
		Piece pionB = new Pion(Couleur.WHITE);

		Piece roiN = new Roi(Couleur.BLACK);
		Piece reineN = new Reine(Couleur.BLACK);
		Piece fouN = new Fou(Couleur.BLACK);
		Piece cavalierN = new Cavalier(Couleur.BLACK);
		Piece tourN = new Tour(Couleur.BLACK);
		Piece pionN = new Pion(Couleur.BLACK);

		Piece[] plateau = { tourB, cavalierB, fouB, roiB, reineB, fouB,
				cavalierB, tourB, pionB, pionB, pionB, pionB, pionB, pionB,
				pionB, pionB, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, pionN, pionN, pionN, pionN, pionN,
				pionN, pionN, pionN, tourN, cavalierN, fouN, roiN, reineN,
				fouN, cavalierN, tourN };

		echiquier = plateau;
	}

	/**
	 * Avance un pion sur l'échiquier
	 * 
	 * @param coup
	 * @throws GameException
	 */
	public boolean jouerCoup(Coup coup) throws GameException {
		int indexSource = PositionConverter.convertCaseEnIndex(coup
				.getCaseSource());
		int indexDest = PositionConverter.convertCaseEnIndex(coup
				.getCaseDestination());

		Piece piece = this.getEchiquier()[indexSource];

		if (piece == null)
			throw new GameException("Etat de l'échiquier incohérent");

		this.getEchiquier()[indexSource] = null;
		this.getEchiquier()[indexDest] = piece;

		return true;
	}

	/**
	 * Vérifie si la case passée en paramètre est vide ou non
	 * 
	 * @param nCase
	 * @return true si la case est vide false sinon
	 */
	public boolean isCaseVide(Case nCase) {
		int index = PositionConverter.convertCaseEnIndex(nCase);
		return this.getEchiquier()[index] == null;
	}

	/**
	 * Renvoie la couleur de la pièce se trouvant sur la case passé en paramètre
	 * 
	 * @param nCase
	 * @return La couleur de la pièce, null si la case est vide
	 */
	public Couleur getCouleurPiece(Case nCase) {
		int index = PositionConverter.convertCaseEnIndex(nCase);

		if (this.getEchiquier()[index] == null)
			return null;
		else
			return this.getEchiquier()[index].getColor();
	}

	public Piece getPiece(Case nCase) {
		int index = PositionConverter.convertCaseEnIndex(nCase);

		return this.getEchiquier()[index];
	}

	/**
	 * Contrôle que toutes les cases d'une même colonne entre une case de départ
	 * et une case d'arrivée sont vides
	 * 
	 * @param caseSrc
	 *            La case de départ
	 * @param caseDst
	 *            La case d'arrivée
	 * @param sens
	 *            Le sens de déplacement
	 * @return True si toutes les cases sont vides. False sinon
	 */
	public boolean caseIntermVides(Case caseSrc, Case caseDst, Sens sens) {
		Case caseInter = new Case(caseSrc.getColonne(), caseSrc.getLigne());

		while (!caseInter.equals(caseDst)) {
			caseInter.setLigne(caseInter.getLigne() + sens.getModifLigne());
			caseInter.setColonne(caseInter.getColonne()
					+ sens.getModifColonne());

			if (!caseInter.equals(caseDst)) {
				if (!isCaseVide(caseInter))
					return false;
			}
		}

		return true;
	}

	/**
	 * Vérifie que les cases passées en paramètres sont valides pour l'échiquier
	 * 
	 * @param indexSource
	 * @param indexDestination
	 * @return
	 */
	public boolean isCasesValides(Case caseSrc, Case caseDest) {
		int indexSource = PositionConverter.convertCaseEnIndex(caseSrc);
		int indexDestination = PositionConverter.convertCaseEnIndex(caseDest);

		if (indexSource < 0 || indexSource >= getEchiquier().length)
			return false;

		if (indexDestination < 0 || indexDestination >= getEchiquier().length)
			return false;

		return true;
	}

	/**
	 * Vérifie que la case caseDest contient une pièce de la même
	 * couleur que le paramètre joueurCouleur
	 * 
	 * @param indexDestination
	 * @return
	 */
	public boolean isDestOccupee(Case caseDest, Couleur joueurCouleur) {
		int indexDestination = PositionConverter.convertCaseEnIndex(caseDest);

		if (getEchiquier()[indexDestination] != null
				&& getEchiquier()[indexDestination].getColor().equals(
						joueurCouleur))
			return false;

		return true;
	}
	
	/**
	 * Vérifie que la pièce sélectionné par le joueur lui appartient.
	 * 
	 * @param indexSource
	 * @return
	 */
	public boolean isPieceDuJoueur(Case caseSrc, Couleur joueurCouleur) {
		int indexSource = PositionConverter.convertCaseEnIndex(caseSrc);
		
		if (getEchiquier()[indexSource].getColor().equals(
				joueurCouleur))
			return true;

		return false;
	}

	/**
	 * Contrôle si l'action en cours est un mouvement d'attaque vers une case
	 * contrôlée par l'adversaire
	 * 
	 * @param coup
	 * @return True si la case cible est contrôlée par l'adversaire.
	 */
	public boolean isAttaque(Case caseDest, Couleur joueurCouleur) {
		int indexDestination = PositionConverter.convertCaseEnIndex(caseDest);

		Piece piece = getEchiquier()[indexDestination];

		if (piece != null
				&& !piece.getColor().equals(joueurCouleur))
			return true;

		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder echiquierRep = new StringBuilder("");
		int compteur = 0;

		for (int index = echiquier.length - 1; index >= 0; index--) {

			echiquierRep.append("|");

			if (echiquier[index] != null) {
				echiquierRep.append(echiquier[index].getClass().getSimpleName()
						.substring(0, 2));
				echiquierRep.append(echiquier[index].getColor().toString()
						.substring(0, 1));
			} else
				echiquierRep.append("---");

			echiquierRep.append("|");

			compteur++;

			if (compteur == 8) {
				echiquierRep.append("\n");
				compteur = 0;
			}
		}

		return echiquierRep.toString();
	}

	public Piece[] getEchiquier() {
		return echiquier;
	}

	public void setEchiquier(Piece[] echiquier) {
		this.echiquier = echiquier;
	}
}
