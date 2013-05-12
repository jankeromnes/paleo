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

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;

import paleo.code.ConstructeurCodePaleo;
import paleo.code.ConstructeurCodeUtilisateur;
import paleo.exceptions.EcritureFichierException;
import paleo.exceptions.LectureFichierException;
import paleo.exceptions.PaleoException;
import paleo.outils.GestionnaireErreurs;
import paleo.outils.Lanceur;

// TODO: Auto-generated Javadoc
/**
 * Les Objets Schema utilises par le Modele.
 */
public class Schema implements Serializable {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le nom du schema. */
	private String nom;
	
	/** Le nom du projet. */
	private String projet;
	
	/** Le code. */
	private String code;
	
	/** La liste des packages. */
	private Vector<String> packages;
	
	/** Les couleurs. */
	private HashMap<String,String> couleurs;
	
	/** L'image. */
	private ImageIcon image;
	
	/** Le pointeur. */
	private int pointeur;
	
	/** Les points d'arret. */
	private Vector<Integer> arrets;
	
	/** Les points d'erreur. */
	private Vector<Integer> erreurs;
	
	/** Si le fichier est sauvegarde. */
	private boolean sauvegarde;

	/**
	 * Instancie un nouveau schema avec un indice.
	 * 
	 * @param i
	 *            l'indice du nouveau schema
	 */
	public Schema(int i) {
		setNom("nouveau_" + (i + 1));
		setProjet("");
		setCode("");
		setPackages();
		setCouleurs();
		setEtat();
		setSauvegarde(false);
	}

	/**
	 * Instancie un nouveau schema avec un indice et un fichier code.
	 * 
	 * @param i
	 *            l'indice du nouveau schema
	 * @param cheminCode
	 *            le chemin du fichier code
	 * @throws PaleoException
	 *            renvoie une exception paleo en cas de probleme
	 */
	public Schema(int i, String cheminCode) throws PaleoException {
		setNom("nouveau_" + (i + 1));
		setProjet("");
		setCode(lireFichier(cheminCode));
		setPackages();
		setCouleurs();
		setEtat();
		setSauvegarde(false);
	}

	/**
	 * Instancie un nouveau schema avec indice, fichier code et fichier packages.
	 * 
	 * @param i
	 *            l'indice du nouveau schema
	 * @param cheminCode
	 *            le chemin du fichier code
	 * @param cheminPackages
	 * 			  le chemin du fichier packages
	 * @throws PaleoException
	 *            renvoie une exception paleo en cas de probleme
	 */
	public Schema(int i, String cheminCode, String cheminPackages)
			throws PaleoException {
		this(i, cheminCode);
		setPackages(lireFichier(cheminPackages).split("\n"));
	}

	/**
	 * Instancie un nouveau schema avec nom et projet.
	 * 
	 * @param nomSchema
	 *            le nom du schema
	 * @param nomProjet
	 *            le nom du projet
	 * @throws PaleoException
	 *             renvoie une exception paleo en cas de probleme
	 */
	public Schema(String nomSchema, String nomProjet) throws PaleoException {
		setNom(nomSchema);
		setProjet(nomProjet);
		setCode(lireFichier(getChemin("code.txt")));
		setPackages(lireFichier(getChemin("packages.txt")).split("\n"));
		chargerCouleurs();
		initialiserImage();
		setEtat();
		setSauvegarde(true);
	}
	
	/**
	 * Avancer le pointeur jusqu'au prochain point d'arret
	 */
	public void avancerPointeur() {
		boolean trouve = false;
		for(int i = 0 ; i < arrets.size() && !trouve ; i++) {
			if(pointeur < arrets.get(i)) {
				setPointeur(arrets.get(i));
				trouve = true;
			}
		}
		if(!trouve) setPointeur(-1);
	}
	
	/**
	 * Charger le fichier des couleurs.
	 */
	@SuppressWarnings("unchecked")
	public void chargerCouleurs() {
		try {
			ObjectInputStream s = new ObjectInputStream(new FileInputStream(getChemin("couleurs.hashmap")));
			setCouleurs((HashMap<String,String>) s.readObject());
			s.close();
		} catch (Exception e) { setCouleurs(); }
	}

	/**
	 * Ecrire un fichier texte.
	 * 
	 * @param contenu
	 *            le contenu
	 * @param cheminFichier
	 *            le chemin du fichier
	 * @throws PaleoException
	 *            renvoie une exception paleo en cas de probleme
	 */
	private void ecrireFichier(String contenu, String cheminFichier)
			throws PaleoException {
		try {
			FileWriter writer = new FileWriter(new File(cheminFichier));
			writer.write(contenu);
			writer.close();
		} catch (IOException e) {
			throw new EcritureFichierException(cheminFichier);
		}
	}

	/**
	 * Est sauvegarde.
	 * 
	 * @return vrai s'il n'y a pas eu de modifications depuis la derniere sauvegarde
	 */
	public boolean estSauvegarde() {
		return sauvegarde;
	}
	
	/**
	 * Recupere les points d'arret dans le code.
	 * 
	 * @return les points d'arret dans le code
	 */
	public Vector<Integer> getArrets() {
		return arrets;
	}

	/**
	 * Recupere le texte des packages.
	 * 
	 * @return le texte des packages
	 */
	public String getChainePackages() {
		StringBuilder constructeurChaine = new StringBuilder();
		for (String chaine : getListePackages()) {
			constructeurChaine.append(chaine + "\n");
		}
		return constructeurChaine.toString();
	}

	/**
	 * Recupere le chemin du schema.
	 * 
	 * @return le chemin du schema
	 */
	public String getChemin() {
		return getCheminProjet() + "/" + nom;
	}

	/**
	 * Recupere le chemin d'un fichier composant du schema.
	 * 
	 * @param fichier
	 *            le fichier composant du schema
	 * @return le chemin du fichier
	 */
	public String getChemin(String fichier) {
		return getChemin() + "/" + fichier;
	}

	/**
	 * Recupere le chemin du projet.
	 * 
	 * @return le chemin du projet
	 */
	public String getCheminProjet() {
		return "paleo/projets/" + projet;
	}

	/**
	 * Recupere le code.
	 * 
	 * @return le code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Recupere les couleurs.
	 * 
	 * @return les couleurs
	 */
	public HashMap<String,String> getCouleurs() {
		return couleurs;
	}
	
	/**
	 * Recupere les erreurs.
	 * 
	 * @return les erreurs
	 */
	public Vector<Integer> getErreurs() {
		return erreurs;
	}

	/**
	 * Recupere l'image.
	 * 
	 * @return l'image
	 */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * Recupere la liste des packages.
	 * 
	 * @return la liste des packages
	 */
	public Vector<String> getListePackages() {
		return packages;
	}

	/**
	 * Recupere le nom du schema.
	 * 
	 * @return le nom du schema
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Recupere le pointeur du dernier point d'arret.
	 * 
	 * @return le pointeur du dernier point d'arret
	 */
	public int getPointeur() {
		return pointeur;
	}
	
	public String getPortionCode(int sens) {
		String portionCode = "";
		if (sens > 0) avancerPointeur();
		else if (sens < 0) reculerPointeur();
		if(sens == 0 || getPointeur() == -1) {
			portionCode = code;
			setPointeur(-1);
		}
		else {
			String lignes[] = code.split("\n");
			StringBuilder constructeurChaine = new StringBuilder();
			for(int i = 0 ; i <= getPointeur() && i < lignes.length ; i++)
				constructeurChaine.append(lignes[i]+"\n");
			portionCode = constructeurChaine.toString();
		}
		return portionCode;
	}

	/**
	 * Recupere le nom du projet.
	 * 
	 * @return le nom du projet
	 */
	public String getProjet() {
		return projet;
	}

	/**
	 * Initialiser l'image du schema.
	 */
	private void initialiserImage() {
		Image objetImage = new  ImageIcon(getChemin("schema.png")).getImage();
		objetImage.flush();
		image = new ImageIcon(objetImage);
	}

	/**
	 * Lancer le code jusqu'a un point d'arret donne.
	 * 
	 * @param sens
	 *             sens<0: arret precedent, sens==0: en entier, sens>0 arret suivant
	 * 
	 * @throws PaleoException
	 *             renvoie une exception paleo en cas de probleme
	 */
	public void lancer(int sens) throws PaleoException {
		sauvegarder();
		String portionCode = getPortionCode(sens);
		ConstructeurCodeUtilisateur ccu = new ConstructeurCodeUtilisateur(getChainePackages(), portionCode);
		ccu.genererCode();
		ccu.compiler();
		ccu.executer();
		ConstructeurCodePaleo ccp = new ConstructeurCodePaleo(getChainePackages(), portionCode);
		ccp.genererCode();
		ccp.compiler();
		new Lanceur().java("code/Paleo1 " + getChemin());
		setErreurs(GestionnaireErreurs.getInstance().getErreurs());
		chargerCouleurs();
		initialiserImage();
		sauvegarder(); // On sauvegarde à nouveau car des couleurs peuvent avoir ete ajoutees
	}

	/**
	 * Lire un fichier.
	 * 
	 * @param cheminFichier
	 *            le chemin du fichier a lire
	 * @return le contenu du fichier
	 * @throws PaleoException
	 *             renvoie une exception paleo en cas de probleme
	 */
	private String lireFichier(String cheminFichier) throws PaleoException {
		String contenu = "";
		try {
			File fichier = new File(cheminFichier);
			if (fichier.exists()) {
				Scanner scanner = new Scanner(fichier).useDelimiter("\\Z");
				if (scanner.hasNext()) {
					contenu = scanner.next();
				}
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			throw new LectureFichierException(cheminFichier);
		}
		return contenu;
	}
	
	/** Recule le pointeur jusqu'au point d'arret precedent
	 * 
	 */
	private void reculerPointeur() {
		boolean trouve = false;
		for(int i = arrets.size()-1 ; i >= 0 && !trouve ; i--) {
			if(pointeur > arrets.get(i)) {
				setPointeur(arrets.get(i));
				trouve = true;
			}
		}
		if(!trouve) {
			if(arrets.size() == 0) setPointeur(-1);
			else setPointeur(arrets.get(arrets.size()-1));
		}
	}

	/**
	 * Sauvegarder.
	 * 
	 * @throws PaleoException
	 *             renvoie une exception paleo en cas de probleme
	 */
	public void sauvegarder() throws PaleoException {
		if (!sauvegarde) {
			File dossierSchema = new File(getChemin());
			if (!dossierSchema.exists()) {
				dossierSchema.mkdir();
			}
			ecrireFichier(code, getChemin("code.txt"));
			ecrireFichier(getChainePackages(), getChemin("packages.txt"));
			sauvegarderCouleurs();
			setSauvegarde(true);
		}
	}
	
	/**
	 * Sauvegarder le fichier des couleurs.
	 * 
	 * @throws PaleoException
	 *             renvoie une exception paleo en cas de probleme
	 */
	public void sauvegarderCouleurs() throws PaleoException {
		try {
			ObjectOutputStream s = new ObjectOutputStream(
					new FileOutputStream(getChemin("couleurs.hashmap")));
			s.writeObject(couleurs);
			s.close();
		} catch (IOException e) { throw new EcritureFichierException(e.getMessage()); }
	}

	/**
	 * Definit le code.
	 * 
	 * @param code
	 *            le nouveau code
	 */
	public void setCode(String code) {
		this.code = code;
		setSauvegarde(false);
	}
	
	/**
	 * Initialise les couleurs.
	 */
	public void setCouleurs() {
		setCouleurs(new HashMap<String, String>());
	}
	
	/**
	 * Definit les couleurs.
	 * 
	 * @param couleurs
	 *            les nouvelles couleurs
	 */
	public void setCouleurs(HashMap<String, String> couleurs) {
		this.couleurs = couleurs;
		setSauvegarde(false);
	}

	/**
	 * Definit la liste des packages.
	 * 
	 * @param packages
	 *            la nouvelle liste des packages
	 */
	public void setListePackages(Vector<String> packages) {
		this.packages = packages;
		setSauvegarde(false);
	}

	/**
	 * Definit le nom du schema.
	 * 
	 * @param nom
	 *            le nouveau nom du schema
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Definit les packages.
	 * 
	 * @param packages
	 *            les nouveaux packages
	 */
	public void setPackages(String... packages) {
		this.packages = new Vector<String>(Arrays.asList(packages));
		setSauvegarde(false);
	}

	/**
	 * Definit les nouveaux points d'erreur.
	 */
	public void setErreurs(Vector<Integer> erreurs) {
		this.erreurs = erreurs;
	}

	/**
	 * Initialise l'etat des points d'arret, d'erreur et le pointeur.
	 */
	public void setEtat() {
		arrets = new Vector<Integer>();
		erreurs = new Vector<Integer>();
		setPointeur(-1);
	}
	
	/**
	 * Place le pointeur.
	 * 
	 * @param p
	 *            la nouvelle position du pointeur
	 */
	public void setPointeur(int p) {
		pointeur = p;
		if(arrets.size() == 0 || pointeur > arrets.get(arrets.size()-1)) pointeur = -1;
	}

	/**
	 * Definit le nom du projet.
	 * 
	 * @param projet
	 *            le nouveau nom du projet
	 */
	public void setProjet(String projet) {
		this.projet = projet;
	}

	/**
	 * Definit le schema comme sauvegarde ou non.
	 * 
	 * @param sauvegarde
	 *            si le schema est sauvegarde
	 */
	public void setSauvegarde(boolean sauvegarde) {
		this.sauvegarde = sauvegarde;
	}

	/**
	 * Change la nature d'un point d'arret.
	 * 
	 * @param arret
	 *            le point d'arret a ajouter ou a supprimer
	 */
	public void toggleArret(int arret) {
		Integer integer = new Integer(arret);
		if(arrets.contains(integer)) arrets.remove(integer);
		else {
			arrets.add(integer);
			Collections.sort(arrets);
		}
	}

}
