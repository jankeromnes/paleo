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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import paleo.modele.ModelePaleo;
import paleo.outils.GestionnaireProjets;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueDialogueSauvegarde.
 */
public class VueDialogueSauvegarde extends VueDialogueAbstrait {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le choix du nom du schema. */
	private JTextField choixNom;
	
	/** Le choix du projet. */
	private JComboBox choixProjet;

	/**
	 * Instancie une nouvelle boite de dialogue pour la sauvegarde d'un schema.
	 * 
	 * @param modele
	 *            le modele
	 * @param fenetre
	 *            la fenetre
	 */
	public VueDialogueSauvegarde(ModelePaleo modele, IGPaleo fenetre) {
		super("Sauver un Schema", modele, fenetre);
		setSize(250, 155);
	}

	/* (non-Javadoc)
	 * @see paleo.IG.VueDialogueAbstrait#genererComposants()
	 */
	@Override
	public void genererComposants() {
		contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));

		choixNom = new JTextField(modele.getNom());
		contenu.add(new JLabel("Nom du schema :"));
		contenu.add(choixNom);

		choixProjet = new JComboBox(GestionnaireProjets.getInstance()
				.listerProjets());
		choixProjet.setEditable(true);
		contenu.add(new JLabel("Nom du projet :"));
		contenu.add(choixProjet);

		contenu.add(boutons);
	}

	/**
	 * Recupere le nom du schema.
	 * 
	 * @return le nom du schema
	 */
	public String getNom() {
		return choixNom.getText();
	}

	/**
	 * Recupere le nom du projet.
	 * 
	 * @return le nom du projet
	 */
	public String getProjet() {
		return choixProjet.getSelectedItem().toString();
	}

}
