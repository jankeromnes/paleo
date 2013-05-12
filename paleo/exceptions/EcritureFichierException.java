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
 * The Class EcritureFichierException.
 */
public class EcritureFichierException extends ESException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ecriture fichier exception.
	 * 
	 * @param str
	 *            the str
	 */
	public EcritureFichierException(String str) {
		super("Paleo a rencontre un probleme d'ecriture de fichier : " + str);
	}

}
