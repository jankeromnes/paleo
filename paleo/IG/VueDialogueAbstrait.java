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

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import paleo.modele.ModelePaleo;
import paleo.outils.GestionnaireIcones;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueDialogueAbstrait.
 */
@SuppressWarnings("serial")
public abstract class VueDialogueAbstrait extends JDialog implements Vue {

	/** Le modele de paleo (cf MVC). */
	protected ModelePaleo modele;
	
	/** La fenetre principale. */
	protected IGPaleo fenetre;
	
	/** Le contenu de la boite de dialogue. */
	protected Container contenu;
	
	/** Les boutons. */
	protected JPanel boutons;
	
	/** Le bouton valider. */
	protected JButton valider;
	
	/** Le bouton annuler. */
	protected JButton annuler;
	
	/** Le booleen valide. */
	private boolean valide;

	// Boite de Dialogue abstraite, Vue du modele
	/**
	 * Instancie une nouvelle boite de dialogue.
	 * 
	 * @param nom
	 *            le nom de la boite
	 * @param modele
	 *            le modele de paleo
	 * @param fenetre
	 *            la fenetre
	 */
	public VueDialogueAbstrait(String nom, ModelePaleo modele, IGPaleo fenetre) {
		super(fenetre, nom, true);
		this.modele = modele;
		this.fenetre = fenetre;
		setSize(500, 200); // taille standard (a redefinir eventuellement)
		setLocationRelativeTo(null); // au centre de l'ecran

		contenu = getContentPane();
		contenu.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		// Boutons communs a toutes les Boites de Dialogue : Valider et Annuler
		valider = new JButton("Valider");
		valider.setIcon(GestionnaireIcones.getInstance().getIcone("accept"));
		valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				valide = true;
				setVisible(false);
			}

		});

		annuler = new JButton("Annuler");
		annuler.setIcon(GestionnaireIcones.getInstance().getIcone("remove"));
		annuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}

		});

		// On place ces boutons dans un JPanel : Boutons
		boutons = new JPanel();
		boutons.add(valider);
		boutons.add(annuler);

		// Ajout de la Vue au modele
		modele.ajouterVue(this);
	}

	/**
	 * Est Valide.
	 * 
	 * @return vrai si l'utilisateur a valide la boite
	 */
	public boolean estValide() {
		return valide;
	}

	/**
	 * Generer les composants de la boite.
	 */
	public abstract void genererComposants();

	/**
	 * Ouvrir.
	 * Methode appellee pour afficher la Boite de Dialogue
	 */
	public void ouvrir() {
		valide = false;
		genererComposants();
		setVisible(true);
	}

	/** 
	 * Ici, le rafraichissement n'entraine pas de mise a jour
	 * (a redefinir si contenu modifiable)
	 * 
	 */
	/* (non-Javadoc)
	 * @see paleo.IG.Vue#rafraichir()
	 */
	@Override
	public void rafraichir() {
	}
}
