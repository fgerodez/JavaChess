import com.javachess.exceptions.GameException;
import com.javachess.helpers.Couleur;
import com.javachess.jeu.Partie;
import com.javachess.joueurs.Joueur;
import com.javachess.joueurs.JoueurHumain;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Joueur joueur1 = new JoueurHumain("François", Couleur.BLANC);
		Joueur joueur2 = new JoueurHumain("Player", Couleur.NOIR);
		Partie partie = new Partie(joueur1, joueur2);
		
		try {
			partie.start();
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

}
