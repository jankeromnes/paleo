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

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * L'Arbre Abstrait.
 */
public class Arbre {

	/** Les noeuds de l'arbre. */
	private HashMap<String, Noeuds> noeuds;

	/**
	 * Instancie un nouvel arbre.
	 */
	public Arbre() {
		noeuds = new HashMap<String, Noeuds>();
	}

	/**
	 * Ajoute un nouveau noeud de variable.
	 * 
	 * @param n
	 *            le nouveau noeud
	 */
	public void add(Noeuds n) {
		noeuds.put("" + n.getNom(), n);
	}

	/**
	 * Affecte une variable.
	 * 
	 * @param clef
	 *            la clef correspondant a la variable
	 */
	public void affecter(String clef) {
		if (contient(clef)) {
			add((noeuds.get(clef).estPrimitif() ? new VariablePrimitive(clef)
					: new VariableObjet(clef)));
		}
	}

	/**
	 * Annule une variable.
	 * 
	 * @param clef
	 *            la clef
	 */
	public void annuler(String clef) {
		if (contient(clef)) {
			add(new VariableNulle(clef));
		}
	}

	/**
	 * Teste si l'arbre contient une variable.
	 * 
	 * @param clef
	 *            la clef
	 * @return vrai si la variable est trouvee
	 */
	public boolean contient(String clef) {
		return noeuds.containsKey(clef);
	}

	/**
	 * Ecrire le code de l'arbre generant le graphe.
	 * 
	 * @param pw
	 *            le printwriter
	 */
	public void ecrire(PrintWriter pw) {
		Set<String> clefs = noeuds.keySet();
		for (String clef : clefs) {
			pw.println("\t\t" + noeuds.get(clef).generer());
			pw.println("\t\t_paleographe.add(_paleocase"
					+ noeuds.get(clef).getNom() + ");");
		}
	}

	/**
	 * Retire un noeud de l'arbre.
	 * 
	 * @param clef
	 *            la clef
	 */
	public void remove(String clef) {
		if (contient(clef)) {
			noeuds.remove(clef);
		}
	}
}
