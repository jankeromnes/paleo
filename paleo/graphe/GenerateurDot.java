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

import java.io.PrintWriter;
import java.util.HashMap;

import paleo.exceptions.PaleoException;

// TODO: Auto-generated Javadoc
/**
 * Le Generateur de description en langage Dot.
 */
public class GenerateurDot {

	/** The instance. */
	private static GenerateurDot instance = new GenerateurDot();
	
	/**
	 * Gets the instance.
	 * 
	 * @return the instance
	 */
	public static GenerateurDot getInstance() {
		return instance;
	}

	/** The pw. */
	private PrintWriter pw;
	
	/** The couleurs. */
	private HashMap<String,String> couleurs;
	
	/** The debut graph. */
	private String debutGraph = "\ndigraph {\n\n\trankdir = LR;\n\tnode[shape = none];\n\tedge[tailclip = false];\n";
	
	/** The commentaire var. */
	private String commentaireVar = "\n\t/* Variable %s */\n";
	
	/** The code var. */
	private String codeVar = "\t%s [label=<<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\">\n\t\t<TR><TD WIDTH=\"40\">%s</TD><TD BORDER=\"1\" WIDTH=\"40\">%s</TD></TR>\n\t</TABLE>>]\n";
	
	/** The commentaire pteur. */
	private String commentairePteur = "\n\t/* Pointeur %s */\n";
	
	/** The code pteur. */
	private String codePteur = "\t%s [label=<<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\">\n\t\t<TR><TD WIDTH=\"40\">%s</TD><TD BORDER=\"1\" WIDTH=\"40\" PORT=\"0\"> </TD></TR>\n\t</TABLE>>]\n";
	
	/** The commentaire tab. */
	private String commentaireTab = "\n\t/* Objet %s */\n";
	
	/** The code tab debut. */
	private String codeTabDebut = "\t%s [label=<<TABLE BORDER=\"0\" CELLBORDER=\"0\" CELLSPACING=\"0\">\n\t\t<TR><TD> </TD><TD BORDER=\"1\" WIDTH=\"40\" BGCOLOR=\"%s\" PORT=\"0\">%s</TD></TR>\n";
	
	/** The commentaire ligne. */
	private String commentaireLigne = "\t\t/* Attribut %s */\n";
	
	/** The code ligne valeur. */
	private String codeLigneValeur = "\t\t<TR><TD ALIGN=\"RIGHT\" WIDTH=\"40\">%s</TD><TD BORDER=\"1\" WIDTH=\"40\">%s</TD></TR>\n";
	
	/** The code ligne pteur. */
	private String codeLignePteur = "\t\t<TR><TD ALIGN=\"RIGHT\" WIDTH=\"40\">%s</TD><TD BORDER=\"1\" WIDTH=\"40\" PORT=\"%s\"> </TD></TR>\n";
	
	/** The code tab fin. */
	private String codeTabFin = "\t</TABLE>>]\n";
	
	/** The code arc. */
	private String codeArc = "\n\t%s -> %s";
	
	/** The code rank. */
	private String codeRank = "\n\n\t{ rank = same %s }\n";
	
	/** The fin graph. */
	private String finGraph = "\n}\n\n";

	/**
	 * Instantiates a new generateur dot.
	 */
	private GenerateurDot() {
		setCouleurs(new HashMap<String,String>());
	}

	/**
	 * Gets the couleur hexa.
	 * 
	 * @param valeur
	 *            the valeur
	 * @return the couleur hexa
	 */
	private String getCouleurHexa(int valeur) {
		String[] hexa = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"A", "B", "C", "D", "E", "F" };
		return hexa[(valeur / 16) % 16] + hexa[valeur % 16];
	}

	/**
	 * Gets the couleur unique par classe.
	 * 
	 * @param nomClasse
	 *            the nom classe
	 * @return the couleur unique par classe
	 */
	private String getCouleurUniqueParClasse(String nomClasse) {
		if(!couleurs.containsKey(nomClasse)) {
			int hash = ("hachage" + nomClasse).hashCode();
			int rouge = Math.abs(((hash / 200) / 200) % 200) + 55;
			int vert = Math.abs((hash / 200) % 200) + 55;
			int bleu = Math.abs(hash % 200) + 55;
			couleurs.put(nomClasse, "#" + getCouleurHexa(rouge) + getCouleurHexa(vert)
				+ getCouleurHexa(bleu));
		}
		return couleurs.get(nomClasse);
	}
	
	/**
	 * Sets the couleurs.
	 * 
	 * @param couleurs
	 *            the new couleurs
	 */
	public void setCouleurs(HashMap<String,String> couleurs) {
		this.couleurs = couleurs;
	}
	
	/**
	 * Gets the couleurs.
	 * 
	 * @return the couleurs
	 */
	public HashMap<String,String> getCouleurs() {
		return couleurs;
	}

	/**
	 * Prints the arc.
	 * 
	 * @param etiCase
	 *            the eti case
	 * @param etiTableau
	 *            the eti tableau
	 */
	public void printArc(String etiCase, String etiTableau) {
		pw.printf(codeArc, etiCase, etiTableau + ":0:w");
	}

	/**
	 * Prints the case pointeur.
	 * 
	 * @param nom
	 *            the nom
	 */
	public void printCasePointeur(String nom) {
		pw.printf(commentairePteur, nom);
		pw.printf(codePteur, nom, nom);
	}

	/**
	 * Prints the case valeur.
	 * 
	 * @param nom
	 *            the nom
	 * @param valeur
	 *            the valeur
	 */
	public void printCaseValeur(String nom, String valeur) {
		pw.printf(commentaireVar, nom);
		pw.printf(codeVar, nom, nom, valeur);
	}

	/**
	 * Prints the graphe.
	 * 
	 * @param cases
	 *            the cases
	 * @param tableaux
	 *            the tableaux
	 * @param file
	 *            the file
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void printGraphe(LesCases cases, LesTableaux tableaux,
			PrintWriter file) throws PaleoException {
		pw = file;
		pw.printf(debutGraph);
		cases.generer();
		tableaux.generer();
		cases.genererArc();
		tableaux.genererArc();
		pw.printf(codeRank, cases.varList());
		pw.printf(finGraph);
		pw.close();
	}

	/**
	 * Prints the ligne pointeur.
	 * 
	 * @param nom
	 *            the nom
	 */
	public void printLignePointeur(String nom) {
		pw.printf(commentaireLigne, nom);
		pw.printf(codeLignePteur, nom, nom);
	}

	/**
	 * Prints the ligne valeur.
	 * 
	 * @param nom
	 *            the nom
	 * @param valeur
	 *            the valeur
	 */
	public void printLigneValeur(String nom, String valeur) {
		pw.printf(commentaireLigne, nom);
		pw.printf(codeLigneValeur, nom, valeur);
	}

	/**
	 * Prints the tableau.
	 * 
	 * @param nom
	 *            the nom
	 * @param valeur
	 *            the valeur
	 * @param lignes
	 *            the lignes
	 */
	public void printTableau(String nom, String valeur, LesLignes lignes) {
		pw.printf(commentaireTab, nom);
		pw.printf(codeTabDebut, nom, getCouleurUniqueParClasse(valeur), valeur);
		lignes.generer();
		pw.printf(codeTabFin);
	}
}
