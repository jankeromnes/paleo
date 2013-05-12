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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;

import paleo.exceptions.EcritureFichierException;
import paleo.exceptions.PaleoException;

// TODO: Auto-generated Javadoc
/**
 * Le Graphe Abstrait de Paleo.
 * @uml.dependency   supplier="paleo.graphe.LesCases"
 */
public class Graphe {

	/** The file. */
	private String file;
	
	/** The cases. */
	private LesCases cases;
	
	/** The tableaux. */
	private LesTableaux tableaux;

	/**
	 * Instantiates a new graphe.
	 * 
	 * @param fileName
	 *            the file name
	 * @param couleurs
	 *            the couleurs
	 */
	public Graphe(String fileName, HashMap<String,String> couleurs){
		file = fileName;
		cases = new LesCases();
		tableaux = new LesTableaux();
		if(couleurs != null) GenerateurDot.getInstance().setCouleurs(couleurs);
	}

	/**
	 * Adds the.
	 * 
	 * @param cp
	 *            the cp
	 */
	public void add(CasePointeur cp) {
		if (!cp.getValeur().equals("null")) {
			cases.add(cp);
			cp.setGraphe(this);
		} else {
			add(new CaseValeur(cp.getNom(), "null"));
		}
	}

	/**
	 * Adds the.
	 * 
	 * @param cv
	 *            the cv
	 */
	public void add(CaseValeur cv) {
		cases.add(cv);
		cv.setGraphe(this);
	}

	/**
	 * Adds the.
	 * 
	 * @param t
	 *            the t
	 */
	public void add(Tableau t) {
		tableaux.add(t);
		t.setGraphe(this);
	}

	/**
	 * Construire.
	 * 
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void construire() throws PaleoException {
		cases.construire();
	}

	/**
	 * Contient.
	 * 
	 * @param clef
	 *            the clef
	 * @return true, if successful
	 */
	public boolean contient(String clef) {
		return tableaux.contient(clef);
	}

	/**
	 * Generer.
	 * 
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void generer() throws PaleoException {
		try {
			GenerateurDot.getInstance().printGraphe(cases, tableaux,
					new PrintWriter(new BufferedWriter(new FileWriter(file))));
		} catch (IOException e) {
			throw new EcritureFichierException(e.getMessage());
		}
	}

	/**
	 * @uml.property  name="lesCases"
	 * @uml.associationEnd  inverse="graphe:paleo.graphe.LesCases"
	 */
	private LesCases lesCases;

	/**
	 * Getter of the property <tt>lesCases</tt>
	 * @return  Returns the lesCases.
	 * @uml.property  name="lesCases"
	 */
	public LesCases getLesCases() {
		return lesCases;
	}

	/**
	 * Setter of the property <tt>lesCases</tt>
	 * @param lesCases  The lesCases to set.
	 * @uml.property  name="lesCases"
	 */
	public void setLesCases(LesCases lesCases) {
		this.lesCases = lesCases;
	}

	/**
	 * @uml.property  name="lesCases1"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="graphe1:paleo.graphe.LesCases"
	 */
	private Collection<LesCases> lesCases1;

	/**
	 * Getter of the property <tt>lesCases1</tt>
	 * @return  Returns the lesCases1.
	 * @uml.property  name="lesCases1"
	 */
	public Collection<LesCases> getLesCases1() {
		return lesCases1;
	}

	/**
	 * Setter of the property <tt>lesCases1</tt>
	 * @param lesCases1  The lesCases1 to set.
	 * @uml.property  name="lesCases1"
	 */
	public void setLesCases1(Collection<LesCases> lesCases1) {
		this.lesCases1 = lesCases1;
	}

	/**
	 * @uml.property  name="cases"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="_paleographe:paleo.graphe.LesCases"
	 */
	private Collection<LesCases> cases1;

	/**
	 * Getter of the property <tt>cases</tt>
	 * @return  Returns the cases1.
	 * @uml.property  name="cases"
	 */
	public Collection<LesCases> getCases() {
		return cases1;
	}

	/**
	 * Setter of the property <tt>cases</tt>
	 * @param cases  The cases1 to set.
	 * @uml.property  name="cases"
	 */
	public void setCases(Collection<LesCases> cases) {
		cases1 = cases;
	}

	/**
	 * @uml.property  name="tableaux"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="graphe:paleo.graphe.LesTableaux"
	 */
	private Collection<LesTableaux> tableaux1;

	/**
	 * Getter of the property <tt>tableaux</tt>
	 * @return  Returns the tableaux1.
	 * @uml.property  name="tableaux"
	 */
	public Collection<LesTableaux> getTableaux() {
		return tableaux1;
	}

	/**
	 * Setter of the property <tt>tableaux</tt>
	 * @param tableaux  The tableaux1 to set.
	 * @uml.property  name="tableaux"
	 */
	public void setTableaux(Collection<LesTableaux> tableaux) {
		tableaux1 = tableaux;
	}
}
