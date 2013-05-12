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

import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * Le Gestionnaire des Erreurs.
 */
public class GestionnaireErreurs {
	
	/** La liste des points d'erreurs */
	private Vector<Integer> erreurs;

	/** L'instance unique pour toute l'application Paleo. */
	private static GestionnaireErreurs instance = new GestionnaireErreurs();

	/**
	 * Gets the instance.
	 * 
	 * @return the instance
	 */
	public static GestionnaireErreurs getInstance() {
		return instance;
	}

	/**
	 * Instantiates a new gestionnaire erreurs.
	 */
	public GestionnaireErreurs() {
		initialiser();
	}
	
	/**
	 * 
	 */
	private void ajouterErreur(int i) {
		erreurs.add(new Integer(i));
	}

	/**
	 * Gestion erreurs compilation.
	 * 
	 * @param chaine
	 *            the chaine
	 * @return the string
	 */
	public String gestionErreursCompilation(String chaine) {
		String[] elements = chaine.split("paleo\\\\code\\\\CodeUtilisateur.java:");
		StringBuilder constructeurChaine = new StringBuilder(elements[0]);
		for (int i = 1; i < elements.length; i++) {
			String element = elements[i];
			int ligne = Integer.parseInt(element.substring(0, element
					.indexOf(":")));
			if (ligne < 8) {
				element = element.substring(element.indexOf(":"));
				constructeurChaine
						.append("position : Packages (configuration) "
								+ element);
			} else {
				ligne -= 7;
				ajouterErreur(ligne-1);
				element = element.substring(element.indexOf(":"));
				constructeurChaine.append("position : Code Utilisateur (ligne "
						+ ligne + ") " + element);
			}
		}
		return traduire(constructeurChaine.toString().replace(
				"location: class paleo.code.CodeUtilisateur", "vue code :"));
	}

	/**
	 * Gestion erreurs execution.
	 * 
	 * @param chaine
	 *            the chaine
	 * @return the string
	 */
	public String gestionErreursExecution(String chaine) {
		String[] elements = chaine
				.split("at paleo.code.CodeUtilisateur.main\\(CodeUtilisateur.java:");
		StringBuilder constructeurChaine = new StringBuilder(elements[0]);
		for (int i = 1; i < elements.length; i++) {
			String element = elements[i];
			int ligne = Integer.parseInt(element.substring(0, element
					.indexOf(")"))) - 7;
			ajouterErreur(ligne-1);
			element = element.substring(element.indexOf(")"));
			constructeurChaine.append("position : Code Utilisateur (ligne "
					+ ligne + element);
		}
		return traduire(constructeurChaine.toString());
	}
	
	/**
	 * 
	 */
	public Vector<Integer> getErreurs() {
		return erreurs;
	}
	
	/**
	 * 
	 */
	public void initialiser() {
		erreurs = new Vector<Integer>();
	}

	/**
	 * Traduire.
	 * 
	 * @param chaine
	 *            the chaine
	 * @return the string
	 */
	private String traduire(String chaine) {
		chaine = chaine.replace("cannot find symbol", "symbol introuvable");
		chaine = chaine.replace("does not exist", "introuvable");
		chaine = chaine.replace("error", "erreur");
		chaine = chaine.replace("method", "methode");
		chaine = chaine.replace("class", "classe");
		chaine = chaine.replace("symbol", "symbole");
		return chaine;
	}
}
