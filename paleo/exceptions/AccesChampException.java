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
 * The Class AccesChampException.
 */
public class AccesChampException extends PaleoException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new acces champ exception.
	 * 
	 * @param str
	 *            the str
	 */
	public AccesChampException(String str) {
		super("Champ inaccessible : " + str);
	}

}
