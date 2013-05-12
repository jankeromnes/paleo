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

import paleo.exceptions.PaleoException;

// TODO: Auto-generated Javadoc
/**
 * La collection des Cases du Graphe Abstrait.
 */
public class LesCases {

	/** The cases. */
	private ArrayList<Case> cases;

	/**
	 * Instantiates a new les cases.
	 */
	public LesCases() {
		cases = new ArrayList<Case>(0);
	}

	/**
	 * Adds the.
	 * 
	 * @param c
	 *            the c
	 */
	public void add(Case c) {
		cases.add(c);
	}

	/**
	 * Construire.
	 * 
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void construire() throws PaleoException {
		for (Case c : cases) {
			c.construire();
		}
	}

	/**
	 * Generer.
	 */
	public void generer() {
		for (Case c : cases) {
			c.generer();
		}
	}

	/**
	 * Generer arc.
	 */
	public void genererArc() {
		for (Case c : cases) {
			c.genererArc();
		}
	}

	/**
	 * Var list.
	 * 
	 * @return the string
	 */
	public String varList() {
		StringBuilder output = new StringBuilder();
		for (Case c : cases) {
			output.append(";" + c.getNom());
		}
		return output.toString();
	}

	/**
	 * @uml.property  name="graphe"
	 * @uml.associationEnd  inverse="lesCases:paleo.graphe.Graphe"
	 */
	private Graphe graphe;

	/**
	 * Getter of the property <tt>graphe</tt>
	 * @return  Returns the graphe.
	 * @uml.property  name="graphe"
	 */
	public Graphe getGraphe() {
		return graphe;
	}

	/**
	 * Setter of the property <tt>graphe</tt>
	 * @param graphe  The graphe to set.
	 * @uml.property  name="graphe"
	 */
	public void setGraphe(Graphe graphe) {
		this.graphe = graphe;
	}

}
