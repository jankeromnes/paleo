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
 * Une Case de schema memoire.
 */
public abstract class Case extends Noeuds {

	/**
	 * Instantiates a new case.
	 * 
	 * @param nom
	 *            the nom
	 * @param valeur
	 *            the valeur
	 */
	protected Case(String nom, String valeur) {
		super(nom, valeur);
	}

	/* (non-Javadoc)
	 * @see paleo.graphe.Noeuds#generer()
	 */
	@Override
	public abstract void generer();

	/**
	 * Generer arc.
	 */
	public void genererArc() {
	}
}
