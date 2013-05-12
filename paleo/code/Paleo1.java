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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import paleo.exceptions.PaleoException;
import paleo.outils.Lanceur;

// TODO: Auto-generated Javadoc
/**
 * La classe principale de la sous-application Paleo1.
 */
public class Paleo1 {

	/**
	 * La methode main.
	 * 
	 * @param argv
	 *            les arguments
	 */
	public static void main(String argv[]) {
		if (argv.length == 1) {
			try {
				Paleo1 paleo1 = new Paleo1(argv[0]);
				paleo1.lancer();
			} catch (PaleoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/** Le code paleo. */
	CodePaleo codeP1;
	
	/** Le chemin du schema. */
	String cheminSchema;

	/**
	 * Instancie un nouvel objet Paleo1.
	 * 
	 * @param cheminSchema
	 *            le chemin du schema
	 */
	public Paleo1(String cheminSchema) {
		this.cheminSchema = cheminSchema;
		this.codeP1 = new CodePaleo(lireCouleurs());
	}

	/**
	 * Lancer.
	 * 
	 * @throws PaleoException
	 *             renvoie une exception paleo en cas de probleme.
	 */
	public void lancer() throws PaleoException {
		ecrireCouleurs(codeP1.genererDot(cheminSchema+"/schema"));
		new Lanceur().dot(cheminSchema+"/schema");
	}
	
	/**
	 * Lire couleurs.
	 * 
	 * @return la hashmap des couleurs contenue dans le fichier "couleurs.hashmap"
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String,String> lireCouleurs() {
		HashMap<String,String> couleurs = new HashMap<String,String>();
		try {
			ObjectInputStream s = new ObjectInputStream(new FileInputStream(cheminSchema+"/couleurs.hashmap"));
			couleurs = ((HashMap<String,String>) s.readObject());
			s.close();
		} catch (Exception e) { /* Si le fichier de couleurs est introuvable, on travaille avec une hashmap vide */ }
		return couleurs;
	}
	
	/**
	 * Ecrire couleurs.
	 * 
	 * @param couleurs
	 *            les couleurs
	 */
	public void ecrireCouleurs(HashMap<String,String> couleurs) {
		try {
			ObjectOutputStream s = new ObjectOutputStream(
					new FileOutputStream(cheminSchema+"/couleurs.hashmap"));
			s.writeObject(couleurs);
			s.close();
		} catch (IOException e) { /* Si le fichier de couleurs ne peut pas etre cree, on ne fait rien */ }
	}

}
