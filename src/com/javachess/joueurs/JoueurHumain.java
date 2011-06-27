package com.javachess.joueurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;
import com.javachess.jeu.Case;

/**
 * Joueur en mode manuel (humain). Les déplacements sont récupérés directement
 * depuis la console (interface ultra basique).
 * 
 * Cette classe n'est pas censée faire partie du moteur. C'est au programme
 * appelant d'instancier ses proposes joueurs en se basant sur l'interface
 * Joueur.
 * 
 * @author Ouzned
 * 
 */
public class JoueurHumain implements Joueur {

	private String nom;
	private Couleur couleur;

	public JoueurHumain(String nom, Couleur couleur) {
		this.nom = nom;
		this.couleur = couleur;
	}

	/**
	 * Demande au joueur quel coup effectuer
	 */
	@Override
	public Coup jouer() {

		System.out.println(nom + ", colonne-ligne source :");
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));

		String caseInfo = null;

		try {
			caseInfo = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int colonne = Integer.parseInt(caseInfo.substring(0, 1));
		int ligne = Integer.parseInt(caseInfo.substring(2, 3));

		Case caseSource = new Case(colonne, ligne);

		System.out.println(nom + ", colonne-ligne destination :");

		try {
			caseInfo = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		colonne = Integer.parseInt(caseInfo.substring(0, 1));
		ligne = Integer.parseInt(caseInfo.substring(2, 3));

		Case caseDestination = new Case(colonne, ligne);

		return new Coup(caseSource, caseDestination);
	}

	/**
	 * Renvoie la couleur du joueur.
	 */
	@Override
	public Couleur getCouleur() {
		return couleur;
	}

}
