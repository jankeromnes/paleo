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
package paleo.code;

import java.io.PrintWriter;

import paleo.exceptions.PaleoException;
import paleo.outils.ConstructeurCode;

// TODO: Auto-generated Javadoc
/**
 * Le Constructeur du Code Utilisateur.
 */
public class ConstructeurCodeUtilisateur extends ConstructeurCode {

	/**
	 * Instantiates a new cC user.
	 * 
	 * @param packages
	 *            the packages
	 * @param code
	 *            the code
	 */
	public ConstructeurCodeUtilisateur(String packages, String code) {
		super(packages, code, "CodeUtilisateur");
	}

	/* (non-Javadoc)
	 * @see paleo.build.ConstructeurDeCode#ecrireDebut(java.io.PrintWriter)
	 */
	@Override
	public void ecrireDebut(PrintWriter pw) throws PaleoException {
		pw.println("\n\tpublic static void main(String[] argv) {");
	}

	/* (non-Javadoc)
	 * @see paleo.build.ConstructeurDeCode#ecrireFin(java.io.PrintWriter)
	 */
	@Override
	public void ecrireFin(PrintWriter pw) throws PaleoException {
		pw.println("\t}");
	}

}
