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

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * La collection des Lignes d'un Tableau.
 */
public class LesLignes {

	/** The lignes. */
	private ArrayList<Ligne> lignes;

	/**
	 * Instantiates a new les lignes.
	 * 
	 * @param nom
	 *            the nom
	 */
	public LesLignes(String nom) {
		lignes = new ArrayList<Ligne>(0);
	}

	/**
	 * Adds the.
	 * 
	 * @param l
	 *            the l
	 */
	public void add(Ligne l) {
		lignes.add(l);
	}

	/**
	 * Generer.
	 */
	public void generer() {
		for (Ligne l : lignes) {
			l.generer();
		}
	}

	/**
	 * Generer arc.
	 */
	public void genererArc() {
		for (Ligne l : lignes) {
			l.genererArc();
		}
	}

}
