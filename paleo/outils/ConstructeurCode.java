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
package paleo.outils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import paleo.exceptions.EcritureFichierException;
import paleo.exceptions.LectureFichierException;
import paleo.exceptions.PaleoException;

// TODO: Auto-generated Javadoc
/**
 * Le Constructeur de Code.
 */
public abstract class ConstructeurCode {

	/** The packages. */
	protected String packages;
	
	/** The code. */
	protected String code;
	
	/** The nom classe. */
	protected String nomClasse;
	
	/** The lanceur. */
	private Lanceur lanceur;

	/**
	 * Instantiates a new constructeur de code.
	 * 
	 * @param p
	 *            the p
	 * @param c
	 *            the c
	 * @param n
	 *            the n
	 */
	public ConstructeurCode(String p, String c, String n) {
		packages = p;
		code = c;
		lanceur = new Lanceur();
		nomClasse = n;
	}

	/**
	 * Compiler.
	 * 
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void compiler() throws PaleoException {
		lanceur.compile("code/" + nomClasse);
	}

	/**
	 * Ecrire code.
	 * 
	 * @param pw
	 *            the pw
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void ecrireCode(PrintWriter pw) throws PaleoException {
		recopierSource(code, "\t\t%s\n", pw);
	}

	/**
	 * Ecrire debut.
	 * 
	 * @param pw
	 *            the pw
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public abstract void ecrireDebut(PrintWriter pw) throws PaleoException;

	/**
	 * Ecrire fin.
	 * 
	 * @param pw
	 *            the pw
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public abstract void ecrireFin(PrintWriter pw) throws PaleoException;

	/**
	 * Ecrire packages.
	 * 
	 * @param pw
	 *            the pw
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void ecrirePackages(PrintWriter pw) throws PaleoException {
		recopierSource(packages, "import %s.*; ", pw);
	}

	/**
	 * Executer.
	 * 
	 * @param args
	 *            the args
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void executer(String... args) throws PaleoException {
		StringBuilder s = new StringBuilder("code/");
		s.append(nomClasse);
		for (String str : args) {
			s.append(str);
		}
		lanceur.java(s.toString());
	}

	/**
	 * Generer code.
	 * 
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void genererCode() throws PaleoException {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					"paleo/code/" + nomClasse + ".java")));
			pw.println("package paleo.code;\n");
			ecrirePackages(pw);
			pw.println("\n\nclass " + nomClasse + " {");
			ecrireDebut(pw);
			ecrireCode(pw);
			ecrireFin(pw);
			pw.println("\n}");
			pw.close();
		} catch (IOException e) {
			throw new EcritureFichierException(e.getMessage());
		}
	}

	/**
	 * Recopier source.
	 * 
	 * @param source
	 *            the source
	 * @param decoration
	 *            the decoration
	 * @param pw
	 *            the pw
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void recopierSource(String source, String decoration, PrintWriter pw)
			throws PaleoException {
		try {
			BufferedReader br = new BufferedReader(new StringReader(source));
			String l = br.readLine();
			while (l != null) {
				if (l.length() != 0) {
					pw.printf(decoration, l);
				}
				l = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			throw new LectureFichierException(e.getMessage());
		}
	}

}
