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
package paleo.exceptions;

import paleo.outils.GestionnaireErreurs;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaException.
 */
public class JavaException extends PaleoException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new java exception.
	 * 
	 * @param chaine
	 *            the chaine
	 */
	public JavaException(String chaine) {
		super("Probleme d'execution Java !\n"
				+ GestionnaireErreurs.getInstance().gestionErreursExecution(
						chaine));
	}
}
