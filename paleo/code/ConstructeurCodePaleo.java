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
package paleo.code;

import java.io.PrintWriter;
import java.io.StringReader;

import paleo.analyse.AnalyseurLexical;
import paleo.analyse.AnalyseurSyntaxique;
import paleo.arbre.Arbre;
import paleo.exceptions.LectureFichierException;
import paleo.exceptions.PaleoException;
import paleo.outils.ConstructeurCode;

// TODO: Auto-generated Javadoc
/**
 * Le Constructeur du Code Paleo.
 */
public class ConstructeurCodePaleo extends ConstructeurCode {

	/**
	 * Instantiates a new cC paleo.
	 * 
	 * @param packages
	 *            the packages
	 * @param code
	 *            the code
	 */
	public ConstructeurCodePaleo(String packages, String code) {
		super("java.util\npaleo.exceptions\npaleo.graphe\n" + packages, code, "CodePaleo");
	}

	/**
	 * Ecrire cases.
	 * 
	 * @param pw
	 *            the pw
	 * @throws PaleoException
	 *             the paleo exception
	 */
	public void ecrireCases(PrintWriter pw) throws PaleoException {

		try {
			AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(
				new AnalyseurLexical(new StringReader(code)));
			Arbre a = (Arbre) analyseur.parse().value;
			a.ecrire(pw);

		} catch (Exception e) {
			throw new LectureFichierException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see paleo.build.ConstructeurDeCode#ecrireDebut(java.io.PrintWriter)
	 */
	@Override
	public void ecrireDebut(PrintWriter pw) throws PaleoException {
		pw.println("\n\tprivate HashMap<String,String> _paleocouleurs;");
		pw.println("\n\tpublic " + nomClasse + "(HashMap<String,String> _paleocouleurs) {");
		pw.println("\t\tthis._paleocouleurs = _paleocouleurs;");
		pw.println("\t}");
		pw.println("\n\tpublic HashMap<String,String> genererDot(String _paleochaine) throws PaleoException {");
	}

	/* (non-Javadoc)
	 * @see paleo.build.ConstructeurDeCode#ecrireFin(java.io.PrintWriter)
	 */
	@Override
	public void ecrireFin(PrintWriter pw) throws PaleoException {
		pw.println("\n\t\tGraphe _paleographe = new Graphe(_paleochaine+\".dot\", _paleocouleurs);");
		ecrireCases(pw);
		pw.println("\n\t\t_paleographe.construire();");
		pw.println("\t\t_paleographe.generer();");
		pw.println("\t\treturn GenerateurDot.getInstance().getCouleurs();");
		pw.println("\t}");
	}
}
