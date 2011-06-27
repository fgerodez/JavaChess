package com.javachess.jeu;

import com.javachess.exceptions.GameException;
import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.joueurs.Joueur;
import com.javachess.pieces.Piece;
import com.javachess.pieces.Roi;

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
	private boolean echecEtMat = false;

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

		joueurCourant = joueurSuivant();
	}

	/**
	 * Sélectionne le joueur suivant
	 */
	private Joueur joueurSuivant() {
		Joueur joueurSuivant = null;

		if (joueurCourant == null) {
			joueurSuivant = joueur1;
		} else {
			if (joueurCourant == joueur1)
				joueurSuivant = joueur2;
			else
				joueurSuivant = joueur1;
		}

		return joueurSuivant;
	}

	/**
	 * Vérifie que l'action en cours est valide : pas de déplacement sur une
	 * case contrôlée par le même joueur, déplacement sur une case existante,
	 * déplacement d'une pièce de la même couleur que le joueur courant.
	 * 
	 * @param coup
	 * @return
	 */
	private boolean isCoupValide(Coup coup, Couleur joueur)
			throws GameException {
		if (!plateau.isCasesValides(coup.getCaseSource(),
				coup.getCaseDestination()))
			return false;

		if (!plateau.isPieceDuJoueur(coup.getCaseSource(), joueur))
			return false;

		if (plateau.isDestOccupee(coup.getCaseDestination(), joueur))
			return false;

		if (coup.getCaseDestination().equals(coup.getCaseSource()))
			return false;

		if (coupMetEnEchec(coup, joueur))
			return false;

		return true;
	}

	/**
	 * Vérifie que le joueur qui joue un coup ne met pas son propre roi en
	 * situation d'echec
	 * 
	 * @param coup
	 * @return
	 * @throws GameException
	 */
	private boolean coupMetEnEchec(Coup coup, Couleur couleurJoueur)
			throws GameException {
		Roi roi = (Roi) plateau.getRoi(couleurJoueur);
		boolean result = false;

		plateau.jouerCoup(coup);

		if (plateau.caseMenacee(roi.getPosition()))
			result = true;

		plateau.reculerUnCoup();

		return result;
	}

	/**
	 * Après un coup du joueur1, vérifie si le joueur2 est en echec ou echec et
	 * mat.
	 * 
	 */
	private void controlerEtat() throws GameException {
		Roi roi = (Roi) plateau.getRoi(joueurSuivant().getCouleur());

		if (plateau.caseMenacee(roi.getPosition())) {
			roi.setEchec(true);
			System.out.println("Echec");
		} else {
			roi.setEchec(false);
		}

		if (roi.isEchec() && echecEtMat())
			echecEtMat = true;
	}

	/**
	 * Vérifie s'il existe une solution pour laquelle le roi adverse n'est plus
	 * en situation d'echec
	 * 
	 * @return
	 * @throws GameException
	 */
	private boolean echecEtMat() throws GameException {
		Piece[] pieces = plateau.getPions(joueurSuivant().getCouleur());
		Case[] cases = plateau.getCases();

		for (int iPiece = 0; iPiece < pieces.length; iPiece++) {
			for (int iCase = 0; iCase < cases.length; iCase++) {
				Coup coup = new Coup(pieces[iPiece].getPosition(), cases[iCase]);

				if (isCoupValide(coup, joueurSuivant().getCouleur())
						&& pieces[iPiece].deplacementPossible(coup, plateau,
								plateau.isAttaque(coup.getCaseDestination(),
										joueurSuivant().getCouleur())))
					return false;
			}
		}

		return true;
	}

	/**
	 * Joue un coup sur l'échiquier. Renvoie un objet de type Echiquier pour
	 * renseigner sur le nouvel état de l'échiquier.
	 * 
	 * @throws GameException
	 */
	public boolean jouerCoup(Coup coup) throws GameException {

		Piece piece = plateau.getPiece(coup.getCaseSource());

		if (isCoupValide(coup, joueurCourant.getCouleur())
				&& piece.deplacementPossible(coup, plateau, plateau.isAttaque(
						coup.getCaseDestination(), joueurCourant.getCouleur()))) {

			plateau.jouerCoup(coup);

			controlerEtat();
			joueurCourant = joueurSuivant();

			return true;
		}

		return false;
	}

	/**
	 * Renvoie le joueur courant
	 * 
	 * @return le joueur courant
	 */
	public Joueur getJoueurCourant() {
		return this.joueurCourant;
	}

	/**
	 * Vérifie si la partie est en echec et mat
	 * 
	 * @return True si la partie est en echec et mat
	 */
	public boolean isEchecEtMat() {
		return this.echecEtMat;
	}

	/**
	 * Vérifie si le joueur est en echec
	 * 
	 * @param joueur
	 *            Le joueur à contrôler
	 * @return
	 */
	public boolean isEchec(Joueur joueur) {
		Roi roi = (Roi) plateau.getRoi(joueur.getCouleur());
		return roi.isEchec();
	}

	/**
	 * Renvoie l'état de l'échiquier
	 * 
	 * @return
	 */
	public Echiquier getEtat() {
		return plateau;
	}
}
