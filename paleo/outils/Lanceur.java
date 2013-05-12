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
package paleo.outils;

import java.io.IOException;
import java.io.InputStream;

import paleo.exceptions.DotException;
import paleo.exceptions.ESException;
import paleo.exceptions.JavaException;
import paleo.exceptions.JavacException;
import paleo.exceptions.PaleoException;

// TODO: Auto-generated Javadoc
/**
 * Le Lanceur des commandes propres a Paleo.
 */
public class Lanceur {

	/** Le processus a lancer. */
	private Process process;
	
	/** Le flot d'entree. */
	private InputStream inputStream;
	
	/** Le champ des erreurs eventuelles. */
	private StringBuilder erreur;

	/**
	 * Instancie un nouveau lanceur.
	 */
	public Lanceur() {
	}

	/**
	 * Lance une commande de compilation (javac).
	 * 
	 * @param str
	 *            le chemin du fichier paleo a compiler
	 * @throws PaleoException
	 *            renvoie une exception paleo en cas de probleme
	 */
	public void compile(String str) throws PaleoException {
		runProcess("javac paleo/" + str + ".java");
		if (erreur.length() != 0) {
			throw new JavacException(erreur.toString());
		}
	}

	/**
	 * Lance une commande de generation d'image (dot).
	 * 
	 * @param str
	 *            le chemin des fichiers dot a lire et png a generer
	 * @throws PaleoException
	 *            renvoie une exception paleo en cas de probleme
	 */
	public void dot(String str) throws PaleoException {
		runProcess("dot -Tpng " + str + ".dot -o " + str + ".png");
		if (erreur.length() != 0) {
			throw new DotException(erreur.toString());
		}
	}

	/**
	 * Lance une commande d'affichage externe de l'image en mode batch (eog).
	 * 
	 * @param str
	 *            le chemin de l'image a afficher
	 * @throws PaleoException
	 *            renvoie une exception paleo en cas de probleme
	 */
	public void eog(String str) throws PaleoException {
		runProcess("eog " + str + ".png");
	}

	/**
	 * Lance une commande d'execution (java).
	 * 
	 * @param str
	 *            le chemin de la classe java a executer
	 * @throws PaleoException
	 *            renvoie une exception paleo en cas de probleme
	 */
	public void java(String str) throws PaleoException {
		runProcess("java paleo/" + str);
		if (erreur.length() != 0) {
			throw new JavaException(erreur.toString());
		}
	}

	/**
	 * Methode privee pour lancer un processus.
	 * 
	 * @param command
	 *            la commande a lancer
	 * @throws PaleoException
	 *            renvoie une exception paleo en cas de probleme
	 */
	private void runProcess(String command) throws PaleoException {
		try {
			process = Runtime.getRuntime().exec(command);
			inputStream = process.getErrorStream();
			erreur = new StringBuilder("");
			int c;
			while ((c = inputStream.read()) != -1) {
				erreur.append((char) c);
			}
			inputStream.close();
		} catch (IOException e) {
			throw new ESException(e.getMessage());
		}
	}
}
