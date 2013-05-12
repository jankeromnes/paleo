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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import paleo.modele.ModelePaleo;
import paleo.modele.Schema;
import paleo.outils.GestionnaireIcones;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueModuleEditeur.
 */
public class VueModuleEditeur extends VueModuleAbstrait {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Les onglets de l'editeur. */
	private JTabbedPane onglets;
	
	/** L'objet onglet actif. */
	private JPanel onglet;
	
	/** La vue du code. */
	private VueCode vueCode;
	
	/** La vue du schema memoire. */
	private VueImage vueImage;
	
	/** Le panneau separant les deux vues precedentes. */
	private JSplitPane splitPane;

	/**
	 * Instantiates a new vue module editeur.
	 * 
	 * @param modelePaleo
	 *            le modele paleo
	 * @param fenetrePaleo
	 *            la fenetre paleo
	 */
	public VueModuleEditeur(ModelePaleo modelePaleo, IGPaleo fenetrePaleo) {
		super("Editeur de Schemas", // nom
				GestionnaireIcones.getInstance().getIcone("image_edit"), // icone
				new PositionsRelatives(24, 0, 76, 70), // position (x,y) et taille(x,y)
				new Dimension(400, 300), // dimensions minimales
				fenetrePaleo, // fenetre principale
				modelePaleo); // modele

		JButton nouveau = new JButton();
		nouveau.setIcon(gestionnaireIcones.getIcone("add"));
		nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modele.nouveauSchema();
			}
		});
		menu.add(nouveau);

		JButton sauver = new JButton();
		sauver.setIcon(gestionnaireIcones.getIcone("accept"));
		sauver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modele.getProjet().equals("")) {
					VueDialogueSauvegarde vueDialogueSauvegarde = new VueDialogueSauvegarde(
							modele, fenetre);
					vueDialogueSauvegarde.ouvrir();
					if (vueDialogueSauvegarde.estValide()) {
						modele.setNom(vueDialogueSauvegarde.getNom());
						modele.setProjet(vueDialogueSauvegarde.getProjet());
						modele.sauvegarderSchemaActif();
					}
				} else {
					modele.sauvegarderSchemaActif();
				}
			}
		});
		menu.add(sauver);

		JButton fermer = new JButton();
		fermer.setIcon(gestionnaireIcones.getIcone("remove"));
		fermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.fermerSchemaActif();
			}
		});
		menu.add(fermer);

		JButton reculer = new JButton();
		reculer.setIcon(gestionnaireIcones.getIcone("skip_backward"));
		reculer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lancerModele(-1);
			}
		});
		menu.add(reculer);

		JButton executer = new JButton();
		executer.setIcon(gestionnaireIcones.getIcone("play"));
		executer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lancerModele(0);
			}
		});
		menu.add(executer);

		JButton avancer = new JButton();
		avancer.setIcon(gestionnaireIcones.getIcone("skip_forward"));
		avancer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lancerModele(1);
			}
		});
		menu.add(avancer);

		JButton zoomer = new JButton();
		zoomer.setIcon(gestionnaireIcones.getIcone("search_add"));
		zoomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vueImage.zoomer();
			}
		});
		menu.add(zoomer);

		JButton dezoomer = new JButton();
		dezoomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vueImage.dezoomer();
			}
		});
		dezoomer.setIcon(gestionnaireIcones.getIcone("search_remove"));
		menu.add(dezoomer);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		vueCode = new VueCode(modele, new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void indiquerChangement() {
				int ongletActif = onglets.getSelectedIndex();
				String nomOnglet = onglets.getTitleAt(ongletActif);
				if (!nomOnglet.startsWith("*")) {
					nomOnglet = "*" + nomOnglet;
				}
				onglets.setTitleAt(ongletActif, nomOnglet);
				onglets.setIconAt(ongletActif, gestionnaireIcones
						.getIcone("image_edit"));
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				indiquerChangement();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				indiquerChangement();
			}

		});
		vueImage = new VueImage(modele);

		JScrollPane scrollVueCode = new JScrollPane(vueCode);
		JScrollPane scrollVueImage = new JScrollPane(vueImage);

		splitPane.setLeftComponent(scrollVueCode);
		splitPane.setRightComponent(scrollVueImage);
		splitPane.setResizeWeight(0.57);

		onglet = new JPanel(new BorderLayout());
		onglet.add(splitPane, BorderLayout.CENTER);

		genererOnglets();
		contenu.add(onglets);
	}

	/**
	 * Generer les onglets.
	 */
	private void genererOnglets() {
		onglets = new JTabbedPane();
		for (Schema schema : modele) {
			String nomTab = (schema.estSauvegarde() ? "" : "*")
					+ schema.getNom();
			String nomIcone = "image" + (schema.estSauvegarde() ? "" : "_edit");
			onglets.addTab(nomTab, gestionnaireIcones.getIcone(nomIcone), null);
		}
		int ongletActif = modele.getIndexSchemaActif();
		onglets.setComponentAt(ongletActif, onglet);
		onglets.setSelectedIndex(ongletActif);

		onglets.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				modele.activerSchema(onglets.getSelectedIndex());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
	}
	
	private void lancerModele(int sens) {
		if (!modele.estSauvegarde()) {
			if (modele.getProjet().equals("")) {
				VueDialogueSauvegarde vueDialogueSauvegarde = new VueDialogueSauvegarde(
						modele, fenetre);
				vueDialogueSauvegarde.ouvrir();
				if (vueDialogueSauvegarde.estValide()) {
					modele.setNom(vueDialogueSauvegarde.getNom());
					modele.setProjet(vueDialogueSauvegarde.getProjet());
					modele.sauvegarderSchemaActif();
					modele.lancer(sens);
				}
			} else {
				modele.sauvegarderSchemaActif();
				modele.lancer(sens);
			}
		} else {
			modele.lancer(sens);
		}
	}

	/* (non-Javadoc)
	 * @see paleo.IG.Vue#rafraichir()
	 */
	@Override
	public void rafraichir() {
		contenu.removeAll();
		genererOnglets();
		contenu.add(onglets);
		onglets.revalidate();
	}
}
