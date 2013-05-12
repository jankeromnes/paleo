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

// TODO: Auto-generated Javadoc
/**
 * The Class LectureFichierException.
 */
public class LectureFichierException extends ESException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new lecture fichier exception.
	 * 
	 * @param str
	 *            the str
	 */
	public LectureFichierException(String str) {
		super("Paleo a rencontre un probleme de lecture de fichier : " + str);
	}

}
