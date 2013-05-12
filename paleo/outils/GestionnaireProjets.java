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

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.filechooser.FileNameExtensionFilter;

// TODO: Auto-generated Javadoc
/**
 * Le Gestionnaire des Projets.
 */
public class GestionnaireProjets {

	/** The instance. */
	private static GestionnaireProjets instance = new GestionnaireProjets();

	/**
	 * Gets the instance.
	 * 
	 * @return the instance
	 */
	public static GestionnaireProjets getInstance() {
		return instance;
	}

	/** The environnement projets. */
	private String environnementProjets;
	
	/** The type fichier schema. */
	private String typeFichierSchema;

	/** The type fichier projet. */
	private String typeFichierProjet;

	/**
	 * Instantiates a new gestionnaire projets.
	 */
	public GestionnaireProjets() {
		environnementProjets = "paleo/projets";
		typeFichierSchema = "Schema PALEO";
		typeFichierProjet = "Projet PALEO";
	}

	/**
	 * Effacement recursif.
	 * 
	 * @param chemin
	 *            the chemin
	 */
	public void effacementRecursif(File chemin) {
		if (chemin.exists()) {
			if (chemin.isDirectory()) {
				String[] enfants = chemin.list();
				for (String enfant : enfants) {
					effacementRecursif(new File(chemin, enfant));
				}
			}
			chemin.delete();
		}
	}

	/**
	 * Effacer projet.
	 * 
	 * @param nomProjet
	 *            the nom projet
	 */
	public void effacerProjet(String nomProjet) {
		effacementRecursif(new File(environnementProjets + "/" + nomProjet));
	}

	/**
	 * Effacer schema.
	 * 
	 * @param nomSchema
	 *            the nom schema
	 * @param nomProjet
	 *            the nom projet
	 */
	public void effacerSchema(String nomSchema, String nomProjet) {
		effacementRecursif(new File(environnementProjets + "/" + nomProjet
				+ "/" + nomSchema));
	}

	/**
	 * Gets the chaine type fichier.
	 * 
	 * @param typeFichier
	 *            the type fichier
	 * @return the chaine type fichier
	 */
	public String getChaineTypeFichier(String typeFichier) {
		return typeFichier + " (*." + getExtension(typeFichier) + ")";
	}

	/**
	 * Gets the environnement projets.
	 * 
	 * @return the environnement projets
	 */
	public String getEnvironnementProjets() {
		return environnementProjets;
	}

	/**
	 * Gets the extension.
	 * 
	 * @param typeFichier
	 *            the type fichier
	 * @return the extension
	 */
	public String getExtension(String typeFichier) {
		String[] mots = typeFichier.split(" ");
		StringBuilder extension = new StringBuilder();
		for (String mot : mots) {
			if (mot.length() > 0) {
				extension.append(mot.toLowerCase().charAt(0));
			}
		}
		return extension.toString();
	}

	/**
	 * Gets the filtre fichier.
	 * 
	 * @param typeFichier
	 *            the type fichier
	 * @return the filtre fichier
	 */
	public FileNameExtensionFilter getFiltreFichier(String typeFichier) {
		return new FileNameExtensionFilter(getChaineTypeFichier(typeFichier),
				getExtension(typeFichier));
	}

	/**
	 * Gets the type fichier projet.
	 * 
	 * @return the type fichier projet
	 */
	public String getTypeFichierProjet() {
		return typeFichierProjet;
	}

	/**
	 * Gets the type fichier schema.
	 * 
	 * @return the type fichier schema
	 */
	public String getTypeFichierSchema() {
		return typeFichierSchema;
	}

	/**
	 * Lister packages classpath.
	 * 
	 * @return the vector
	 */
	public Vector<String> listerPackagesClasspath() {
		Vector<String> listePackages = new Vector<String>();
		String[] classPath = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
		for (String chemin : classPath) {
			File dossier = new File(chemin);
			if (dossier.exists() && dossier.isDirectory()) {
				String[] packagesDossier = dossier.list();
				for (String nomPackage : packagesDossier) {
					File dossierPackage = new File(chemin + "/" + nomPackage);
					if (dossierPackage.exists() && dossierPackage.isDirectory()
							&& !nomPackage.startsWith(".")) {
						listePackages.add(nomPackage);
					}
				}
			}
		}
		return listePackages;
	}

	/**
	 * Lister projets.
	 * 
	 * @return the vector
	 */
	public Vector<String> listerProjets() {
		String[] projets = new File("paleo/projets").list();
		Vector<String> listeProjets = (projets == null ? new Vector<String>()
				: new Vector<String>(Arrays.asList(projets)));
		Collections.sort(listeProjets, String.CASE_INSENSITIVE_ORDER);
		return listeProjets;
	}

	/**
	 * Lister schemas du projet.
	 * 
	 * @param nomProjet
	 *            the nom projet
	 * @return the vector
	 */
	public Vector<String> listerSchemasDuProjet(String nomProjet) {
		String[] schemas = new File("paleo/projets/" + nomProjet).list();
		Vector<String> listeSchemas = (schemas == null ? new Vector<String>()
				: new Vector<String>(Arrays.asList(schemas)));
		Collections.sort(listeSchemas, String.CASE_INSENSITIVE_ORDER);
		return listeSchemas;
	}

}
