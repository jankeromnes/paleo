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
 * Modelise une variable declaree.
 */
public abstract class Declaration extends Noeuds {

	/**
	 * Instantiates a new declaration.
	 * 
	 * @param nom
	 *            le nom de declaration
	 * @param primitif
	 *            la nature primitive ou non de la variable declaree
	 */
	public Declaration(String nom, boolean primitif) {
		super(nom, primitif);
	}

}
