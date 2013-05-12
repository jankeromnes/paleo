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
 * The Class JavacException.
 */
public class JavacException extends PaleoException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new javac exception.
	 * 
	 * @param chaine
	 *            the chaine
	 */
	public JavacException(String chaine) {
		super("Probleme de compilation Java !\n"
				+ GestionnaireErreurs.getInstance().gestionErreursCompilation(
						chaine));
	}
}
