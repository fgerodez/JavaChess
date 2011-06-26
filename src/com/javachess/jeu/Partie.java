package com.javachess.jeu;

import com.javachess.exceptions.GameException;
import com.javachess.helpers.Coup;
import com.javachess.helpers.PositionConverter;
import com.javachess.joueurs.Joueur;
import com.javachess.modele.pieces.Piece;
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

		joueurSuivant();
	}

	/**
	 * Sélectionne le joueur suivant
	 */
	private void joueurSuivant() {
		if (joueurCourant == null) {
			joueurCourant = joueur1;
			return;
		}

		if (joueurCourant == joueur1)
			joueurCourant = joueur2;
		else
			joueurCourant = joueur1;
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
			if (isCoupValide(coup) && plateau.jouerCoup(coup, isAttaque(coup))) {
				// etatPartie.verifierEtat();
				joueurSuivant();
			}
		}
	}

	// TODO: Gestion de l'echec, de l'echec et mat

	/**
	 * Vérifie que l'action en cours est valide : pas de déplacement sur une
	 * case contrôlée par le même joueur, déplacement sur une case existante,
	 * déplacement d'une pièce de la même couleur que le joueur courant.
	 * 
	 * @param coup
	 * @return
	 */
	public boolean isCoupValide(Coup coup) {
		int indexSource = PositionConverter.convertCaseEnIndex(coup
				.getCaseSource());
		int indexDestination = PositionConverter.convertCaseEnIndex(coup
				.getCaseDestination());

		if (indexSource < 0 || indexSource >= plateau.getEchiquier().length)
			return false;

		if (indexDestination < 0
				|| indexDestination >= plateau.getEchiquier().length)
			return false;

		if (!plateau.getEchiquier()[indexSource].getColor().equals(
				joueurCourant.getCouleur()))
			return false;

		if (plateau.getEchiquier()[indexDestination] != null
				&& plateau.getEchiquier()[indexDestination].getColor().equals(
						joueurCourant.getCouleur()))
			return false;

		if (coup.getCaseDestination().equals(coup.getCaseSource()))
			return false;

		return true;
	}

	/**
	 * Contrôle si l'action en cours est un mouvement d'attaque vers une case
	 * contrôlée par l'adversaire
	 * 
	 * @param coup
	 * @return True si la case cible est contrôlée par l'adversaire.
	 */
	public boolean isAttaque(Coup coup) {
		int indexDestination = PositionConverter.convertCaseEnIndex(coup
				.getCaseDestination());

		Piece piece = plateau.getEchiquier()[indexDestination];

		if (piece != null
				&& !piece.getColor().equals(joueurCourant.getCouleur()))
			return true;

		return false;
	}

	public boolean isFinished() {
		return false;
	}
}
