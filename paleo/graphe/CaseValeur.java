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
 * Une Case de schema memoire contenant une valeur.
 */
public class CaseValeur extends Case {

	/**
	 * Instantiates a new case valeur.
	 * 
	 * @param nom
	 *            the nom
	 * @param valeur
	 *            the valeur
	 */
	public CaseValeur(String nom, String valeur) {
		super(nom, valeur);
	}

	/* (non-Javadoc)
	 * @see paleo.graphe.Case#generer()
	 */
	@Override
	public void generer() {
		GenerateurDot.getInstance().printCaseValeur(nom, valeur);
	}
}