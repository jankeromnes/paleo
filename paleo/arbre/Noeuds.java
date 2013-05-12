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
package paleo.arbre;

// TODO: Auto-generated Javadoc
/**
 * Le Noeud Abstrait.
 */
public abstract class Noeuds {

	/** Le nom. */
	protected String nom;
	
	/** La nature primitive. */
	protected boolean primitif;

	/**
	 * Instancie un nouveau noeud abstrait.
	 * 
	 * @param n
	 *            le nom
	 * @param p
	 *            la nature primitive
	 */
	public Noeuds(String n, boolean p) {
		nom = n;
		primitif = p;
	}

	/**
	 * Est primitif.
	 * 
	 * @return vrai si le noeud correspond a une variable primitive
	 */
	public boolean estPrimitif() {
		return primitif;
	}

	/**
	 * Generer.
	 * 
	 * @return le code permettant au graphe de generer la variable dans le schema memoire
	 */
	public abstract String generer();

	/**
	 * Recupere le nom du noeud.
	 * 
	 * @return le nom du noeud
	 */
	public String getNom() {
		return nom;
	}
}
