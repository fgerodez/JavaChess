package com.javachess.joueurs;

import com.javachess.helpers.Couleur;
import com.javachess.helpers.Coup;

public interface Joueur {
	public Coup jouer();
	public Couleur getCouleur();
}
