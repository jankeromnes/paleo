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
package paleo.IG;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import paleo.modele.ModelePaleo;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueDialogueNouveauProjet.
 */
public class VueDialogueNouveauProjet extends VueDialogueAbstrait {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le choix du nom de projet. */
	private JTextField nomProjet;

	/**
	 * Instancie une boite de dialogue permettant de creer un nouveau projet.
	 * 
	 * @param modele
	 *            le modele
	 * @param fenetre
	 *            la fenetre
	 */
	public VueDialogueNouveauProjet(ModelePaleo modele, IGPaleo fenetre) {
		super("Nouveau Projet", modele, fenetre);
		setSize(250, 120);
	}

	/* (non-Javadoc)
	 * @see paleo.IG.VueDialogueAbstrait#genererComposants()
	 */
	@Override
	public void genererComposants() {
		contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));

		nomProjet = new JTextField();
		contenu.add(new JLabel("Nom du Projet :"));
		contenu.add(nomProjet);

		contenu.add(boutons);
	}

	/**
	 * Recupere le nom choisi pour le projet.
	 * 
	 * @return le nom choisi pour le projet
	 */
	public String getNomProjet() {
		return nomProjet.getText();
	}

}
