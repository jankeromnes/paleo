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
package paleo.batch;

import paleo.exceptions.PaleoException;
import paleo.modele.ModelePaleo;
import paleo.outils.Lanceur;

// TODO: Auto-generated Javadoc
/**
 * La Classe Principale du mode Batch.
 */
public class PaleoBatch {

	/**
	 * La methode main.
	 * 
	 * @param argv
	 *            les arguments
	 */
	public static void main(String[] argv) {
		if (argv.length == 2) {
			new PaleoBatch(argv[0], argv[1]);
		}
	}

	/** Le modele de paleo (cf MVC). */
	ModelePaleo modele;

	/**
	 * Instanciation d'une interface Paleo en ligne de commande.
	 * 
	 * @param cheminCode
	 *            le chemin du fichier code
	 * @param cheminPackages
	 *            le chemin du fichier packages
	 */
	public PaleoBatch(String cheminCode, String cheminPackages) {
		modele = new ModelePaleo(cheminCode, cheminPackages);
		modele.lancer(0);
		for(StringBuilder message : modele.getLogs()) System.err.println(message.toString());
		try {
			new Lanceur().eog(modele.getChemin("schema"));
		} catch (PaleoException e) { System.err.println(e.getMessage()); } 
	}
}

