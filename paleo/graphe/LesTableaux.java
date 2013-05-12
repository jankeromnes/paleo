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
package paleo.graphe;

import java.util.HashMap;
import java.util.Set;

import paleo.exceptions.PaleoException;

// TODO: Auto-generated Javadoc
/**
 * La collection des Tableaux du Graphe Abstrait.
 */
public class LesTableaux {

	/** The tableaux. */
	private HashMap<String, Tableau> tableaux;

	/**
	 * Instantiates a new les tableaux.
	 */
	public LesTableaux() {
		tableaux = new HashMap<String, Tableau>();
	}

	/**
	 * Adds the.
	 * 
	 * @param l
	 *            the l
	 */
	public void add(Tableau l) {
		tableaux.put("" + l.getNom(), l);
	}

	/**
	 * Construire.
	 * 
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void construire() throws PaleoException {
		Set<String> clefs = tableaux.keySet();
		for (String clef : clefs) {
			tableaux.get(clef).construire();
		}
	}

	/**
	 * Contient.
	 * 
	 * @param clef
	 *            the clef
	 * @return true, if successful
	 */
	public boolean contient(String clef) {
		return tableaux.containsKey(clef);
	}

	/**
	 * Generer.
	 */
	public void generer() {
		Set<String> clefs = tableaux.keySet();
		for (String clef : clefs) {
			tableaux.get(clef).generer();
		}
	}

	/**
	 * Generer arc.
	 */
	public void genererArc() {
		Set<String> clefs = tableaux.keySet();
		for (String clef : clefs) {
			tableaux.get(clef).genererArc();
		}
	}

}
