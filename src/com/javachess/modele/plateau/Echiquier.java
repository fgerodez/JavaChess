package com.javachess.modele.plateau;

import java.util.ArrayList;

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
	private Coup dernierCoup = null;

	public Echiquier() {
		super();
		initialisePlateau();
	}

	/**
	 * Crée les 64 cases du plateau
	 */
	public void initialisePlateau() {
		preparePionsBlancs();
		preparePionsNoirs();
	}

	private void preparePionsBlancs() {
		for (int i = 0; i <= 7; i += 7) {
			Piece tourBlanche = new Tour(Couleur.BLANC,
					PositionConverter.convertIndexEnCase(i));
			echiquier[i] = tourBlanche;
		}

		for (int i = 1; i <= 6; i += 5) {
			Piece cavalierBlanc = new Cavalier(Couleur.BLANC,
					PositionConverter.convertIndexEnCase(i));
			echiquier[i] = cavalierBlanc;
		}

		for (int i = 2; i <= 5; i += 3) {
			Piece fouBlanc = new Fou(Couleur.BLANC,
					PositionConverter.convertIndexEnCase(i));
			echiquier[i] = fouBlanc;
		}

		Piece reineBlanche = new Reine(Couleur.BLANC,
				PositionConverter.convertIndexEnCase(4));
		echiquier[4] = reineBlanche;

		Piece roiBlanc = new Roi(Couleur.BLANC,
				PositionConverter.convertIndexEnCase(3));
		echiquier[3] = roiBlanc;

		for (int i = 8; i <= 15; i++) {
			Piece pionBlanc = new Pion(Couleur.BLANC,
					PositionConverter.convertIndexEnCase(i));
			echiquier[i] = pionBlanc;
		}
	}

	private void preparePionsNoirs() {
		for (int i = 56; i <= 63; i += 7) {
			Piece tour = new Tour(Couleur.NOIR,
					PositionConverter.convertIndexEnCase(i));
			echiquier[i] = tour;
		}

		for (int i = 57; i <= 62; i += 5) {
			Piece cavalier = new Cavalier(Couleur.NOIR,
					PositionConverter.convertIndexEnCase(i));
			echiquier[i] = cavalier;
		}

		for (int i = 58; i <= 61; i += 3) {
			Piece fou = new Fou(Couleur.NOIR,
					PositionConverter.convertIndexEnCase(i));
			echiquier[i] = fou;
		}

		Piece reine = new Reine(Couleur.NOIR,
				PositionConverter.convertIndexEnCase(60));
		echiquier[60] = reine;

		Piece roi = new Roi(Couleur.NOIR,
				PositionConverter.convertIndexEnCase(59));
		echiquier[59] = roi;

		for (int i = 48; i <= 55; i++) {
			Piece pion = new Pion(Couleur.NOIR,
					PositionConverter.convertIndexEnCase(i));
			echiquier[i] = pion;
		}
	}

	/**
	 * Avance un pion sur l'échiquier
	 * 
	 * @param coup
	 * @throws GameException
	 */
	public void jouerCoup(Coup coup) throws GameException {
		int indexSource = PositionConverter.convertCaseEnIndex(coup
				.getCaseSource());
		int indexDest = PositionConverter.convertCaseEnIndex(coup
				.getCaseDestination());

		Piece piece = this.getEchiquier()[indexSource];

		if (piece == null)
			throw new GameException("Etat de l'échiquier incohérent");

		coup.setPieceSource(this.getEchiquier()[indexSource]);
		coup.setPieceDestination(this.getEchiquier()[indexDest]);

		this.getEchiquier()[indexSource] = null;
		this.getEchiquier()[indexDest] = piece;
		this.setDernierCoup(coup);

		piece.setPosition(coup.getCaseDestination());
	}

	/**
	 * Annule le coup précédent
	 */
	public void reculerUnCoup() {
		if (dernierCoup != null) {

			this.getEchiquier()[PositionConverter
					.convertCaseEnIndex(dernierCoup.getCaseSource())] = dernierCoup
					.getPieceSource();

			this.getEchiquier()[PositionConverter
					.convertCaseEnIndex(dernierCoup.getCaseDestination())] = dernierCoup
					.getPieceDestination();

			dernierCoup.getPieceSource().setPosition(
					dernierCoup.getCaseSource());

			if (dernierCoup.getPieceDestination() != null)
				dernierCoup.getPieceDestination().setPosition(
						dernierCoup.getCaseDestination());

			dernierCoup = null;
		}
	}

	/**
	 * Vérifie si la case passée en paramètre est vide ou non
	 * 
	 * @param nCase
	 * @return true si la case est vide false sinon
	 */
	public boolean isCaseVide(Case nCase) {
		return this.getPiece(nCase) == null ? true : false;
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

	/**
	 * Retourne la pièce situé sur la case passée en paramètre
	 * 
	 * @param nCase
	 *            La case à véifier
	 * @return La pièce située sur la case ou null si la case est vide
	 */
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
	 * Vérifie que la case caseDest contient une pièce de la même couleur que le
	 * paramètre joueurCouleur
	 * 
	 * @param indexDestination
	 * @return
	 */
	public boolean isDestOccupee(Case caseDest, Couleur joueurCouleur) {
		int indexDestination = PositionConverter.convertCaseEnIndex(caseDest);

		if (getEchiquier()[indexDestination] != null
				&& getEchiquier()[indexDestination].getColor().equals(
						joueurCouleur))
			return true;

		return false;
	}

	/**
	 * Vérifie que la pièce sélectionné par le joueur lui appartient.
	 * 
	 * @param indexSource
	 * @return
	 */
	public boolean isPieceDuJoueur(Case caseSrc, Couleur joueurCouleur) {
		int indexSource = PositionConverter.convertCaseEnIndex(caseSrc);

		if (getEchiquier()[indexSource] == null)
			return false;

		if (getEchiquier()[indexSource].getColor().equals(joueurCouleur))
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

		if (piece != null && !piece.getColor().equals(joueurCouleur))
			return true;

		return false;
	}

	/**
	 * Renvoie vraie si la case ciblée peut-être attaquée au tour suivant par
	 * l'adversaire
	 * 
	 * @param nCase
	 * @return
	 */
	public boolean caseMenacee(Case nCase) {
		for (int index = 0; index < echiquier.length; index++) {
			Piece piece = echiquier[index];

			if (piece != null && piece.getColor() != getCouleurPiece(nCase)) {
				if (piece.deplacementPossible(
						new Coup(PositionConverter.convertIndexEnCase(index),
								nCase), this, true))
					return true;
			}
		}

		return false;
	}

	/**
	 * Retourne le roi de la couleur spécifiée
	 * 
	 * @param couleur
	 * @return
	 */
	public Piece getRoi(Couleur couleur) {
		for (int index = 0; index < echiquier.length; index++) {
			Piece piece = echiquier[index];

			if (piece != null && piece instanceof Roi
					&& piece.getColor() == couleur)
				return piece;
		}

		return null;
	}

	/**
	 * Renvoie un tableau contenant l'ensemble des pions d'une couleur présents
	 * sur l'échiquier
	 * 
	 * @param couleur
	 * @return
	 */
	public Piece[] getPions(Couleur couleur) {
		ArrayList<Piece> pieces = new ArrayList<Piece>();

		for (int index = 0; index < echiquier.length; index++) {
			Piece piece = echiquier[index];

			if (piece != null && piece.getColor() == couleur)
				pieces.add(piece);
		}

		return pieces.toArray(new Piece[pieces.size()]);
	}

	/**
	 * Renvoie toutes les cases de l'échiquier
	 * 
	 * @return
	 */
	public Case[] getCases() {
		ArrayList<Case> cases = new ArrayList<Case>();

		for (int index = 0; index < echiquier.length; index++) {
			cases.add(PositionConverter.convertIndexEnCase(index));
		}

		return cases.toArray(new Case[cases.size()]);
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

	public Coup getDernierCoup() {
		return dernierCoup;
	}

	public void setDernierCoup(Coup dernierCoup) {
		this.dernierCoup = dernierCoup;
	}
}
