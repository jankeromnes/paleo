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
 * La Ligne d'un Tableau.
 */
public abstract class Ligne extends Noeuds {

	/** The tableau. */
	protected Tableau tableau;

	/**
	 * Instantiates a new ligne.
	 * 
	 * @param nom
	 *            the nom
	 * @param valeur
	 *            the valeur
	 * @param tab
	 *            the tab
	 */
	protected Ligne(String nom, String valeur, Tableau tab) {
		super(nom, valeur);
		tableau = tab;
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
