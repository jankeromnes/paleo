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

import paleo.exceptions.AccesChampException;

// TODO: Auto-generated Javadoc
/**
 * Un Noeud Abstrait du Graphe Abstrait.
 */
public abstract class Noeuds {

	/** The nom. */
	protected String nom;
	
	/** The valeur. */
	protected String valeur;
	
	/** The graphe. */
	protected Graphe graphe;

	/**
	 * Instantiates a new noeuds.
	 * 
	 * @param nom
	 *            the nom
	 * @param valeur
	 *            the valeur
	 */
	public Noeuds(String nom, String valeur) {
		this.nom = nom;
		this.valeur = valeur;
	}

	/**
	 * Class name.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String className(String str) {
		return str.substring(str.lastIndexOf('.') + 1, str.length());
	}

	/**
	 * Construire.
	 * 
	 * @throws AccesChampException
	 *             the acces champ exception
	 */
	public void construire() throws AccesChampException {
	}

	/**
	 * Generer.
	 */
	public abstract void generer();

	/**
	 * Gets the nom.
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Gets the valeur.
	 * 
	 * @return the valeur
	 */
	public String getValeur() {
		return valeur;
	}

	/**
	 * Sets the graphe.
	 * 
	 * @param g
	 *            the new graphe
	 */
	public void setGraphe(Graphe g) {
		graphe = g;
	}
}
