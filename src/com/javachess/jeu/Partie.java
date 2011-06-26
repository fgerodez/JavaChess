package com.javachess.jeu;

import com.javachess.exceptions.GameException;
import com.javachess.helpers.Coup;
import com.javachess.joueurs.Joueur;
import com.javachess.modele.pieces.Piece;
import com.javachess.modele.pieces.Roi;
import com.javachess.modele.plateau.Echiquier;

/**
 * Modélise une partie d'echec. Chaque partie correspond à un plateau et à deux
 * joueurs. Chaque joueur effectue un coup à tour de rôle jusqu'à ce que l'un
 * d'entre eux abandonne ou jusqu'à l'echec et mat.
 * 
 * @author Ouzned
 * 
 */
public class Partie {
	private Echiquier plateau;
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurCourant;

	/**
	 * Initialisation de la partie
	 * 
	 * @param joueur1
	 * @param joueur2
	 */
	public Partie(Joueur joueur1, Joueur joueur2) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;

		this.plateau = new Echiquier();

		setJoueurCourant(joueurSuivant());
	}

	/**
	 * Sélectionne le joueur suivant
	 */
	private Joueur joueurSuivant() {
		Joueur joueurSuivant = null;
		
		if (joueurCourant == null) {
			joueurSuivant = joueur1;
		}
		else {
			if (joueurCourant == joueur1)
				joueurSuivant = joueur2;
			else
				joueurSuivant = joueur1;
		}

		return joueurSuivant;
	}

	/**
	 * Démarre la partie
	 * 
	 * @throws GameException
	 */
	public void start() throws GameException {
		while (!isFinished()) {
			System.out.println(plateau);
			Coup coup = joueurCourant.jouer();
			Piece piece = plateau.getPiece(coup.getCaseSource());

			if (isCoupValide(coup)
					&& piece.deplacementPossible(coup, plateau, plateau
							.isAttaque(coup.getCaseDestination(),
									joueurCourant.getCouleur()))) {
				
				plateau.jouerCoup(coup);
				
				controlerEtat();
				setJoueurCourant(joueurSuivant());
			}
		}
	}

	// TODO: Gestion de l'echec et mat

	/**
	 * Vérifie que l'action en cours est valide : pas de déplacement sur une
	 * case contrôlée par le même joueur, déplacement sur une case existante,
	 * déplacement d'une pièce de la même couleur que le joueur courant.
	 * 
	 * @param coup
	 * @return
	 */
	public boolean isCoupValide(Coup coup) throws GameException {
		if (!plateau.isCasesValides(coup.getCaseSource(),
				coup.getCaseDestination()))
			return false;

		if (!plateau.isPieceDuJoueur(coup.getCaseSource(),
				joueurCourant.getCouleur()))
			return false;

		if (!plateau.isDestOccupee(coup.getCaseDestination(),
				joueurCourant.getCouleur()))
			return false;

		if (coup.getCaseDestination().equals(coup.getCaseSource()))
			return false;

		if (!verifierEchec(coup))
			return false;

		return true;
	}

	public boolean verifierEchec(Coup coup) throws GameException {
		Roi roi = (Roi) plateau.getRoi(joueurCourant.getCouleur());
		boolean result = true;

		plateau.jouerCoup(coup);
		if (plateau
				.caseMenacee(roi.getPosition(), joueurSuivant().getCouleur()))
			result = false;
		plateau.reculerUnCoup();

		return result;
	}
	
	public void controlerEtat() {
		Roi roi = (Roi) plateau.getRoi(joueurCourant.getCouleur());
		
		if (plateau
				.caseMenacee(roi.getPosition(), joueurSuivant().getCouleur()))
			roi.setEchec(true);
		else
			roi.setEchec(false);
		
		//TODO : if(!hasSolution) return echecEtMat!!
	}

	public boolean isFinished() {
		return false;
	}

	public void setJoueurCourant(Joueur joueur) {
		this.joueurCourant = joueur;
	}
}
