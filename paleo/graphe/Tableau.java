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

// TODO: Auto-generated Javadoc
/**
 * Un Tableau unique representant un objet dans le schema memoire.
 */
public class Tableau extends Noeuds {

	/** The lignes. */
	private LesLignes lignes;

	/**
	 * Instantiates a new tableau.
	 * 
	 * @param nom
	 *            the nom
	 * @param valeur
	 *            the valeur
	 */
	public Tableau(String nom, String valeur) {
		super(nom, valeur);
		lignes = new LesLignes(nom);
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

	/* (non-Javadoc)
	 * @see paleo.graphe.Noeuds#generer()
	 */
	@Override
	public void generer() {
		GenerateurDot.getInstance().printTableau(nom, valeur, lignes);
	}

	/**
	 * Generer arc.
	 */
	public void genererArc() {
		lignes.genererArc();
	}
}
