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
package paleo.modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;

import paleo.IG.Vue;
import paleo.exceptions.PaleoException;
import paleo.outils.GestionnaireErreurs;
import paleo.outils.GestionnaireProjets;

// TODO: Auto-generated Javadoc
/**
 * La Classe du ModelePaleo (cf MVC).
 */
public class ModelePaleo implements Iterable<Schema> {

	/** Le gestionnaire des projets. */
	private GestionnaireProjets gestionnaireProjets = GestionnaireProjets
			.getInstance();
	
	/** Le journal de bord. */
	private Vector<StringBuilder> logs;
	
	/** Les vues (cf MVC). */
	private Vector<Vue> vues;
	
	/** Les objets schema. */
	private Vector<Schema> schemas;
	
	/** Le schema actif. */
	private int schemaActif;

	/**
	 * Constructeur appele par l'interface graphique : nouveau modele paleo avec un nouveau schema.
	 */
	public ModelePaleo() {
		vues = new Vector<Vue>();
		schemas = new Vector<Schema>();
		nouveauSchema();
		activerSchema(0);
		logs = new Vector<StringBuilder>();
	}

	/**
	 * Constructeur appele par le mode Batch : nouveau modele paleo avec importation de fichiers code et packages.
	 * 
	 * @param cheminCode
	 *            le chemin du fichier code
	 * @param cheminPackages
	 *            le chemin du fichier packages
	 */
	public ModelePaleo(String cheminCode, String cheminPackages) {
		vues = new Vector<Vue>();
		schemas = new Vector<Schema>();
		importerSchema(cheminCode, cheminPackages);
		getSchemaActif().setProjet("PaleoBatch");
		logs = new Vector<StringBuilder>();
	}

	/**
	 * Activer schema.
	 * 
	 * @param numeroSchema
	 *            le numero schema a activer
	 */
	public void activerSchema(int numeroSchema) {
		if (numeroSchema < 0) {
			schemaActif = 0;
		} else if (numeroSchema >= schemas.size()) {
			schemaActif = schemas.size() - 1;
		} else if (numeroSchema != schemaActif) {
			schemaActif = numeroSchema;
		}
		prevenirVues();
	}

	/**
	 * Ajouter un message au journal.
	 * 
	 * @param message
	 *            le message a ajouter au journal
	 */
	public void ajouterALog(String message) {
		logs.lastElement().append(message + "\n");
	}

	/**
	 * Ajouter vue.
	 * 
	 * @param vue
	 *            la nouvelle vue a ajouter
	 */
	public void ajouterVue(Vue vue) {
		vues.add(vue);
	}

	/**
	 * Effacer projet.
	 * 
	 * @param nomProjet
	 *            le nom du projet
	 */
	public void effacerProjet(String nomProjet) {
		for (String nomSchema : gestionnaireProjets.listerSchemasDuProjet(nomProjet)) {
			effacerSchema(nomSchema, nomProjet);
		}
		prevenirVues();
	}

	/**
	 * Effacer schema.
	 * 
	 * @param nomSchema
	 *            le nom du schema
	 * @param nomProjet
	 *            le nom du projet
	 */
	public void effacerSchema(String nomSchema, String nomProjet) {
		fermerSchema(getSchema(nomSchema, nomProjet));
		gestionnaireProjets.effacerSchema(nomSchema, nomProjet);
		if (gestionnaireProjets.listerSchemasDuProjet(nomProjet).size() == 0) {
			gestionnaireProjets.effacerProjet(nomProjet);
		}
		prevenirVues();
	}

	/**
	 * Est sauvegarde.
	 * 
	 * @return vrai si le schema actif est sauvegarde
	 */
	public boolean estSauvegarde() {
		return getSchemaActif().estSauvegarde();
	}

	/**
	 * Exporter une fichier de projet.
	 * 
	 * @param cheminFichier
	 *            le chemin vers lequel enregistrer
	 */
	public void exporterFichierProjet(String cheminFichier) {
		try {
			ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream(
					cheminFichier));
			s.writeObject(getSchemasDuProjet(getSchemaActif().getProjet()));
			s.close();
		} catch (IOException e) {
			nouveauLog(e.getMessage());
		}
	}

	/**
	 * Exporter un fichier de schema.
	 * 
	 * @param cheminFichier
	 *            le chemin vers lequel enregistrer
	 */
	public void exporterFichierSchema(String cheminFichier) {
		try {
			ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream(
					cheminFichier));
			s.writeObject(getSchemaActif());
			s.close();
		} catch (IOException e) {
			nouveauLog(e.getMessage());
		}
	}

	/**
	 * Fermer un schema.
	 * 
	 * @param schema
	 *            le schema a fermer
	 */
	public void fermerSchema(Schema schema) {
		int nouveauSchemaActif = schemaActif
				- (schemaActif >= schemas.indexOf(schema) ? 1 : 0);
		schemas.remove(schema);
		if (schemas.size() == 0) {
			nouveauSchema();
		}
		activerSchema(nouveauSchemaActif);
	}

	/**
	 * Fermer le schema actif.
	 */
	public void fermerSchemaActif() {
		fermerSchema(getSchemaActif());
	}
	
	/**
	 * Recupere les points d'arrets du schema actif.
	 * 
	 * @return les points d'arrets du schema actif
	 */
	public Vector<Integer> getArrets() {
		return getSchemaActif().getArrets();
	}

	/**
	 * Recupere le chemin du schema actif
	 * 
	 * @return le chemin du schema actif
	 */
	public String getChemin() {
		return getSchemaActif().getChemin();
	}

	/**
	 * Recupere le chemin d'un fichier du schema actif
	 * 
	 * @param le nom du fichier
	 * @return le chemin du schema actif
	 */
	public String getChemin(String chaine) {
		return getSchemaActif().getChemin(chaine);
	}

	/**
	 * Recupere le set de couleurs du schema actif.
	 * 
	 * @return le set de couleurs du schema actif
	 */
	public HashMap<String, String> getCouleurs() {
		return getSchemaActif().getCouleurs();
	}
	
	/**
	 * Recupere les erreurs du schema actif.
	 * 
	 * @return les erreurs du schema actif
	 */
	public Vector<Integer> getErreurs() {
		return getSchemaActif().getErreurs();
	}

	/**
	 * Recupere l'image du schema actif
	 * 
	 * @return l'image du schema actif
	 */
	public ImageIcon getImage() {
		return getSchemaActif().getImage();
	}

	/**
	 * Recupere l'index du schema actif.
	 * 
	 * @return l'index du schema actif
	 */
	public int getIndexSchemaActif() {
		return schemaActif;
	}

	/**
	 * Recupere la liste des packages du schema actif.
	 * 
	 * @return la liste des packages du schema actif
	 */
	public Vector<String> getListePackages() {
		return getSchemaActif().getListePackages();
	}

	/**
	 * Recupere le journal de bord.
	 * 
	 * @return le journal de bord
	 */
	public Vector<StringBuilder> getLogs() {
		return logs;
	}

	/**
	 * Recupere le nom du schema actif.
	 * 
	 * @return le nom du schema actif
	 */
	public String getNom() {
		return getSchemaActif().getNom();
	}

	/**
	 * Recupere les packages disponibles dans le classpath.
	 * 
	 * @return les packages disponibles dans le classpath
	 */
	public Vector<String> getPackagesDisponibles() {
		Vector<String> packagesDisponibles = new Vector<String>();
		Vector<String> packagesActuels = getListePackages();
		for (String nomPackage : gestionnaireProjets.listerPackagesClasspath()) {
			if (!packagesActuels.contains(nomPackage)) {
				packagesDisponibles.add(nomPackage);
			}
		}
		return packagesDisponibles;
	}
	
	/**
	 * Recupere le pointeur du dernier point d'arret du schema actif.
	 * 
	 * @return le pointeur du dernier point d'arret du schema actif
	 */
	public int getPointeur() {
		return getSchemaActif().getPointeur();
	}

	/**
	 * Recupere le nom du projet du schema actif.
	 * 
	 * @return le nom du projet du schema actif
	 */
	public String getProjet() {
		return getSchemaActif().getProjet();
	}

	/**
	 * Recupere schema en fonction de son nom et de son projet.
	 * 
	 * @param nomSchema
	 *            le nom du schema
	 * @param nomProjet
	 *            le nom du projet
	 * @return le schema
	 */
	public Schema getSchema(String nomSchema, String nomProjet) {
		Schema schema = null;
		for (Schema s : this) {
			if (s.getNom().equals(nomSchema) && s.getProjet().equals(nomProjet)) {
				schema = s;
			}
		}
		return schema;
	}

	/**
	 * Recupere le schema actif.
	 * 
	 * @return le schema actif
	 */
	public Schema getSchemaActif() {
		return schemas.get(schemaActif);
	}

	/**
	 * Recupere tous les schemas.
	 * 
	 * @return tous les schemas
	 */
	public Vector<Schema> getSchemas() {
		return schemas;
	}

	/**
	 * Recupere tous les schemas d'un projet.
	 * 
	 * @param nomProjet
	 *            le nom du projet
	 * @return tous les schemas du projet
	 */
	public Vector<Schema> getSchemasDuProjet(String nomProjet) {
		Vector<Schema> collection = new Vector<Schema>();
		for (Schema schema : this) {
			if (nomProjet.equals(schema.getProjet())) {
				collection.add(schema);
			}
		}
		return collection;
	}

	/**
	 * Importer un fichier projet.
	 * 
	 * @param cheminFichier
	 *            le chemin du fichier projet
	 */
	@SuppressWarnings("unchecked")
	public void importerFichierProjet(String cheminFichier) {
		try {
			ObjectInputStream s = new ObjectInputStream(new FileInputStream(
					cheminFichier));
			for (Schema schema : (Vector<Schema>) s.readObject()) {
				importerSchema(schema);
			}
			s.close();
		} catch (Exception e) {
			nouveauLog(e.getMessage());
		}
	}

	/**
	 * Importer un fichier schema.
	 * 
	 * @param cheminFichier
	 *            le chemin fichier schema
	 */
	public void importerFichierSchema(String cheminFichier) {
		try {
			ObjectInputStream s = new ObjectInputStream(new FileInputStream(
					cheminFichier));
			importerSchema((Schema) s.readObject());
			s.close();
		} catch (Exception e) {
			nouveauLog(e.getMessage());
		}
	}

	/**
	 * Importer un schema.
	 * 
	 * @param schema
	 *            le schema a importer
	 */
	public void importerSchema(Schema schema) {
		if (!schemas.contains(schema)) {
			schemas.add(schema);
			schema.setSauvegarde(false);
			sauvegarderSchema(schema);
			activerSchema(schemas.size() - 1);
		}
	}

	/**
	 * Importer un schema avec des fichiers code et packages.
	 * 
	 * @param cheminCode
	 *            le fichier code
	 * @param cheminPackages
	 *            le fichier packages
	 */
	public void importerSchema(String cheminCode, String cheminPackages) {
		try {
			schemas.add(new Schema(schemas.size(), cheminCode, cheminPackages));
			activerSchema(schemas.size() - 1);
		} catch (PaleoException e) {
			nouveauLog(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Schema> iterator() {
		return schemas.iterator();
	}

	/**
	 * Lancer le code jusqu'au point d'arret choisi.
	 */
	public void lancer(int sens) {
		if (!estSauvegarde()) {
			sauvegarderSchemaActif();
		}
		GestionnaireErreurs.getInstance().initialiser();
		try {
			getSchemaActif().lancer(sens);
		} catch (PaleoException e) {
			nouveauLog(e.getMessage());
			getSchemaActif().setErreurs(GestionnaireErreurs.getInstance().getErreurs());
		}
		prevenirVues();
	}

	/**
	 * Nouveau message dans le journal de bord.
	 * 
	 * @param message
	 *            le nouveau message
	 */
	public void nouveauLog(String message) {
		logs.add(new StringBuilder("> "));
		ajouterALog(message);
	}

	/**
	 * Nouveau projet.
	 * 
	 * @param nomProjet
	 *            le nom du nouveau projet
	 * @param avecNouveauSchema
	 *            s'il faut aussi creer un nouveau schema
	 */
	public void nouveauProjet(String nomProjet, boolean avecNouveauSchema) {
		File dossierProjet = new File("paleo/projets/" + nomProjet);
		if (!dossierProjet.exists()) {
			dossierProjet.mkdir();
			if (avecNouveauSchema) {
				new File("paleo/projets/" + nomProjet + "/" + nomProjet + "_1")
						.mkdir();
				ouvrirProjet(nomProjet);
			}
			prevenirVues();
		}
	}

	/**
	 * Nouveau schema.
	 */
	public void nouveauSchema() {
		schemas.add(new Schema(schemas.size()));
		activerSchema(schemas.size() - 1);
	}

	/**
	 * Ouvrir tous les schemas d'un projet.
	 * 
	 * @param nomProjet
	 *            le nom du projet
	 */
	public void ouvrirProjet(String nomProjet) {
		for (String nomSchema : gestionnaireProjets
				.listerSchemasDuProjet(nomProjet)) {
			ouvrirSchema(nomSchema, nomProjet);
		}
	}

	/**
	 * Ouvrir un schema.
	 * 
	 * @param nomSchema
	 *            le nom du schema
	 * @param nomProjet
	 *            le nom du projet
	 */
	public void ouvrirSchema(String nomSchema, String nomProjet) {
		try {
			if (!schemaEstOuvert(nomSchema, nomProjet)) {
				schemas.add(new Schema(nomSchema, nomProjet));
				activerSchema(schemas.size() - 1);
			} else {
				activerSchema(schemas.indexOf(getSchema(nomSchema, nomProjet)));
			}
		} catch (PaleoException e) {
			nouveauLog(e.getMessage());
		}
	}

	/**
	 * Prevenir les vues que le modele a ete modifie.
	 */
	public void prevenirVues() {
		for (Vue vue : vues) {
			vue.rafraichir();
		}
	}

	/**
	 * Sauvegarder tous les schemas du projet actif.
	 */
	public void sauvegarderProjet() {
		String nomProjet = getSchemaActif().getProjet();
		for (Schema schema : this) {
			if (nomProjet.equals(schema.getProjet())) {
				sauvegarderSchema(schema);
			}
		}
	}

	/**
	 * Sauvegarder un schema.
	 * 
	 * @param schema
	 *            le schema
	 */
	public void sauvegarderSchema(Schema schema) {
		try {
			nouveauProjet(schema.getProjet(), false);
			schema.sauvegarder();
		} catch (PaleoException e) {
			nouveauLog(e.getMessage());
		}
		prevenirVues();
	}

	/**
	 * Sauvegarder le schema actif.
	 */
	public void sauvegarderSchemaActif() {
		sauvegarderSchema(getSchemaActif());
	}

	/**
	 * Teste si un schema est actuellement ouvert dans le modele.
	 * 
	 * @param nomSchema
	 *            le nom du schema
	 * @param nomProjet
	 *            le nom du projet
	 * @return vrai si le schema est ouvert
	 */
	public boolean schemaEstOuvert(String nomSchema, String nomProjet) {
		return getSchema(nomSchema, nomProjet) != null;
	}

	/**
	 * Definit le code du schema actif.
	 * 
	 * @param text
	 *            le nouveau code
	 */
	public void setCode(String text) {
		if (!getSchemaActif().getCode().equals(text)) {
			getSchemaActif().setCode(text);
		}
	}
	
	/**
	 * Definit les couleurs du schema actif.
	 * 
	 * @param couleurs
	 *            les nouvelles couleurs
	 */
	public void setCouleurs(HashMap<String,String> couleurs) {
		getSchemaActif().setCouleurs(couleurs);
		prevenirVues();
	}

	/**
	 * Definit la liste des packages du schema actif.
	 * 
	 * @param packages
	 *            la nouvelle liste des packages
	 */
	public void setListePackages(Vector<String> packages) {
		getSchemaActif().setListePackages(packages);
		prevenirVues();
	}

	/**
	 * Definit le nom du schema actif.
	 * 
	 * @param chaine
	 *            le nouveau nom
	 */
	public void setNom(String chaine) {
		getSchemaActif().setNom(chaine);
	}

	/**
	 * Definit le texte des packages du schema actif.
	 * 
	 * @param text
	 *            le nouveau texte des packages
	 */
	public void setPackages(String text) {
		if (!getSchemaActif().getListePackages().equals(text)) {
			getSchemaActif().setPackages(text);
		}
	}

	/**
	 * Definit le nom du projet du schema actif.
	 * 
	 * @param chaine
	 *            le nouveau nom de projet
	 */
	public void setProjet(String chaine) {
		getSchemaActif().setProjet(chaine);
	}

	/**
	 * Change la nature d'un point d'arret dans le code du schema actif.
	 * 
	 * @param arret
	 *            le point d'arret a supprimer ou ajouter
	 */
	public void toggleArret(int arret) {
		getSchemaActif().toggleArret(arret);
		prevenirVues();
	}

}
