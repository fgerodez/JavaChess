package com.javachess.players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.javachess.board.Color;
import com.javachess.board.Square;
import com.javachess.moves.StandardMove;

/**
 * Joueur en mode manuel (humain). Les d�placements sont r�cup�r�s directement
 * depuis la console (interface ultra basique).
 * 
 * Cette classe n'est pas cens�e faire partie du moteur. C'est au programme
 * appelant d'instancier ses proposes joueurs en se basant sur l'interface
 * Joueur.
 * 
 * @author Ouzned
 * 
 */
public class HumanPlayer implements Player {

	private String name;
	private Color color;

	public HumanPlayer(String nom, Color couleur) {
		this.name = nom;
		this.color = couleur;
	}

	/**
	 * Demande au joueur quel coup effectuer
	 */
	@Override
	public StandardMove jouer() {

		System.out.println(name + ", colonne-ligne source :");
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

		Square caseSource = new Square(colonne, ligne);

		System.out.println(name + ", colonne-ligne destination :");

		try {
			caseInfo = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		colonne = Integer.parseInt(caseInfo.substring(0, 1));
		ligne = Integer.parseInt(caseInfo.substring(2, 3));

		Square caseDestination = new Square(colonne, ligne);

		return new StandardMove(caseSource, caseDestination);
	}

	/**
	 * Renvoie la couleur du joueur.
	 */
	@Override
	public Color getColor() {
		return color;
	}

}
