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

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 * Le Gestionnaire des Icones.
 */
public class GestionnaireIcones {

	/** The instance. */
	private static GestionnaireIcones instance = new GestionnaireIcones();

	/**
	 * Gets the instance.
	 * 
	 * @return the instance
	 */
	public static GestionnaireIcones getInstance() {
		return instance;
	}

	/** The icones. */
	private HashMap<String, ImageIcon> icones = new HashMap<String, ImageIcon>();

	/**
	 * Instantiates a new gestionnaire icones.
	 */
	public GestionnaireIcones() {
		icones = new HashMap<String, ImageIcon>();
	}

	/**
	 * Gets the icone.
	 * 
	 * @param nom
	 *            the nom
	 * @return the icone
	 */
	public ImageIcon getIcone(String nom) {
		if (!icones.containsKey(nom)) {
			icones.put(nom, new ImageIcon("paleo/icones/" + nom + ".png"));
		}
		return icones.get(nom);
	}
	
	/**
	 * Gets the icone.
	 * 
	 * @param nom
	 *            the nom
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the icone
	 */
	public ImageIcon getIcone(String nom, int x, int y) {
		return new ImageIcon(getIcone(nom).getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH));
	}
}
