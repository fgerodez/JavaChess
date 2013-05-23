package com.javachess.jeu;

import com.javachess.exceptions.GameException;
import com.javachess.helpers.Color;
import com.javachess.helpers.Move;
import com.javachess.joueurs.Joueur;
import com.javachess.modele.pieces.Piece;
import com.javachess.modele.pieces.Roi;
import com.javachess.modele.plateau.Tile;

/**
 * Mod�lise une partie d'echec. Chaque partie correspond � un plateau et � deux
 * joueurs. Chaque joueur effectue un coup � tour de r�le jusqu'� ce que l'un
 * d'entre eux abandonne ou jusqu'� l'echec et mat.
 * 
 * @author Ouzned
 * 
 */
public class Partie {
	private Board plateau;
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

		this.plateau = new Board();

		joueurCourant = joueurSuivant();
	}

	/**
	 * S�lectionne le joueur suivant
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
	 * V�rifie que l'action en cours est valide : pas de d�placement sur une
	 * case contr�l�e par le m�me joueur, d�placement sur une case existante,
	 * d�placement d'une pi�ce de la m�me couleur que le joueur courant.
	 * 
	 * @param coup
	 * @return
	 */
	private boolean isCoupValide(Move coup, Color joueur)
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
	 * V�rifie que le joueur qui joue un coup ne met pas son propre roi en
	 * situation d'echec
	 * 
	 * @param coup
	 * @return
	 * @throws GameException
	 */
	private boolean coupMetEnEchec(Move coup, Color couleurJoueur)
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
	 * Apr�s un coup du joueur1, v�rifie si le joueur2 est en echec ou echec et
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
	 * V�rifie s'il existe une solution pour laquelle le roi adverse n'est plus
	 * en situation d'echec
	 * 
	 * @return
	 * @throws GameException
	 */
	private boolean echecEtMat() throws GameException {
		Piece[] pieces = plateau.getPions(joueurSuivant().getCouleur());
		Tile[] cases = plateau.getCases();

		for (int iPiece = 0; iPiece < pieces.length; iPiece++) {
			for (int iCase = 0; iCase < cases.length; iCase++) {
				Move coup = new Move(pieces[iPiece].getPosition(), cases[iCase]);

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
	 * Joue un coup sur l'�chiquier. Renvoie un objet de type Echiquier pour
	 * renseigner sur le nouvel �tat de l'�chiquier.
	 * 
	 * @throws GameException
	 */
	public boolean jouerCoup(Move coup) throws GameException {

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
	 * V�rifie si la partie est en echec et mat
	 * 
	 * @return True si la partie est en echec et mat
	 */
	public boolean isEchecEtMat() {
		return this.echecEtMat;
	}

	/**
	 * V�rifie si le joueur est en echec
	 * 
	 * @param joueur
	 *            Le joueur � contr�ler
	 * @return
	 */
	public boolean isEchec(Joueur joueur) {
		Roi roi = (Roi) plateau.getRoi(joueur.getCouleur());
		return roi.isEchec();
	}

	/**
	 * Renvoie l'�tat de l'�chiquier
	 * 
	 * @return
	 */
	public Board getEtat() {
		return plateau;
	}
}
