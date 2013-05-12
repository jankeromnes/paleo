/*
 * PALEO: Petite Application Logicielle d'Etude Objet
 *
 * <p>PALEO est un pseudo-compilateur generant des schemas memoires en fonction d'instructions Java.</p>
 * 
 * Projet de Synthese (LCIN4U51)
 * Licence Informatique Semestre 4
 * Universite Henri Poincare (UHP Nancy)
 * 
 * @author: Jan KEROMNES
 * @version: 1.0
 * 
 */
package paleo.graphe;

// TODO: Auto-generated Javadoc
/**
 * La Ligne d'un Tableau contenant une valeur.
 */
public class LigneValeur extends Ligne {

	/**
	 * Instantiates a new ligne valeur.
	 * 
	 * @param nom
	 *            the nom
	 * @param valeur
	 *            the valeur
	 * @param tab
	 *            the tab
	 */
	public LigneValeur(String nom, String valeur, Tableau tab) {
		super(nom, valeur, tab);
	}

	/* (non-Javadoc)
	 * @see paleo.graphe.Ligne#generer()
	 */
	@Override
	public void generer() {
		GenerateurDot.getInstance().printLigneValeur(nom, valeur);
	}
}
