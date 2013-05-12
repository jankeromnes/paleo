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
package paleo.arbre;

// TODO: Auto-generated Javadoc
/**
 * Modelise une Variable affectee.
 */
public abstract class Variable extends Noeuds {

	/**
	 * Instancie une nouvelle variable.
	 * 
	 * @param nom
	 *            le nom
	 * @param primitif
	 *            la nature primitive
	 */
	public Variable(String nom, boolean primitif) {
		super(nom, primitif);
	}

}
