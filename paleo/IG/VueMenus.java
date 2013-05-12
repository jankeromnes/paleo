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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import paleo.modele.ModelePaleo;
import paleo.outils.GestionnaireIcones;
import paleo.outils.GestionnaireProjets;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueMenus.
 */
public class VueMenus extends JMenuBar implements Vue {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le modele de paleo (cf MVC). */
	private ModelePaleo modele;
	
	/** La fenetre. */
	private IGPaleo fenetre;
	
	/** Le gestionnaire icones. */
	private GestionnaireIcones gestionnaireIcones;
	
	/** Le gestionnaire projets. */
	private GestionnaireProjets gestionnaireProjets;

	/**
	 * Instancie la vue des menus de paleo.
	 * 
	 * @param modelePaleo
	 *            le modele paleo
	 * @param fenetrePaleo
	 *            la fenetre paleo
	 */
	public VueMenus(ModelePaleo modelePaleo, IGPaleo fenetrePaleo) {
		super();
		modele = modelePaleo;
		fenetre = fenetrePaleo;
		gestionnaireIcones = GestionnaireIcones.getInstance();
		gestionnaireProjets = GestionnaireProjets.getInstance();

		// MENU FICHIER
		JMenu fichier = new JMenu("Fichier");
		fichier.setIcon(gestionnaireIcones.getIcone("calendar_empty"));

		// Nouveau
		JMenuItem nouveau = new JMenu("Nouveau");
		nouveau.setIcon(gestionnaireIcones.getIcone("add"));
		nouveau.setMnemonic('N');

		// Nouveau Schema
		JMenuItem nouveauSchema = new JMenuItem("Nouveau Schema");
		nouveauSchema.setIcon(gestionnaireIcones.getIcone("image_add"));
		nouveauSchema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modele.nouveauSchema();
			}
		});
		nouveauSchema.setMnemonic('S');
		nouveauSchema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		nouveau.add(nouveauSchema);

		// Nouveau Projet
		JMenuItem nouveauProjet = new JMenuItem("Nouveau Projet");
		nouveauProjet.setIcon(gestionnaireIcones.getIcone("folder_add"));
		nouveauProjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VueDialogueNouveauProjet vueDialogueNouveauProjet = new VueDialogueNouveauProjet(
						modele, fenetre);
				vueDialogueNouveauProjet.ouvrir();
				if (vueDialogueNouveauProjet.estValide()) {
					modele.nouveauProjet(vueDialogueNouveauProjet
							.getNomProjet(), true);
				}
			}
		});
		nouveauProjet.setMnemonic('P');
		nouveauProjet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		nouveau.add(nouveauProjet);

		fichier.add(nouveau);

		// Sauver
		JMenu sauver = new JMenu("Sauver");
		sauver.setIcon(gestionnaireIcones.getIcone("accept"));
		sauver.setMnemonic('S');

		// Sauver Schema
		JMenuItem sauverSchema = new JMenuItem("Sauver Schema");
		sauverSchema.setIcon(gestionnaireIcones.getIcone("image_edit"));
		sauverSchema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		sauverSchema.setMnemonic('S');
		sauverSchema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		sauver.add(sauverSchema);

		// Sauver Projet
		JMenuItem sauverProjet = new JMenuItem("Sauver Projet");
		sauverProjet.setIcon(gestionnaireIcones.getIcone("folder_edit"));
		sauverProjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modele.sauvegarderProjet();
			}
		});
		sauverProjet.setMnemonic('P');
		sauverProjet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		sauver.add(sauverProjet);

		fichier.add(sauver);

		fichier.addSeparator();

		// Importer
		JMenu importer = new JMenu("Importer");
		importer.setIcon(gestionnaireIcones.getIcone("next"));
		importer.setMnemonic('I');

		// Importer Schema
		JMenuItem importerSchema = new JMenuItem("Importer Schema");
		importerSchema.setIcon(gestionnaireIcones.getIcone("image_next"));
		importerSchema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VueDialogueImporterSchema vueDialogueImporterSchema = new VueDialogueImporterSchema(
						modele, fenetre);
				vueDialogueImporterSchema.ouvrir();
				if (vueDialogueImporterSchema.estValide()) {
					String cheminSchema = vueDialogueImporterSchema
							.getCheminSchema();
					if (!cheminSchema.equals("")) {
						modele.importerFichierSchema(cheminSchema);
					} else {
						String cheminCode = vueDialogueImporterSchema
								.getCheminCode();
						String cheminPackages = vueDialogueImporterSchema
								.getCheminPackages();
						modele.importerSchema(cheminCode, cheminPackages);
					}
				}
			}
		});
		importerSchema.setMnemonic('S');
		importerSchema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));
		importer.add(importerSchema);

		// Importer Projet
		JMenuItem importerProjet = new JMenuItem("Importer Projet");
		importerProjet.setIcon(gestionnaireIcones.getIcone("folder_next"));
		importerProjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser selecteurFichierProjet = new JFileChooser();
				selecteurFichierProjet.setFileFilter(gestionnaireProjets
						.getFiltreFichier(gestionnaireProjets
								.getTypeFichierProjet()));
				if (selecteurFichierProjet.showOpenDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
					modele.importerFichierProjet(selecteurFichierProjet
							.getSelectedFile().toString());
				}
			}
		});
		importerProjet.setMnemonic('P');
		importerProjet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		importer.add(importerProjet);

		fichier.add(importer);

		// Exporter
		JMenu exporter = new JMenu("Exporter");
		exporter.setIcon(gestionnaireIcones.getIcone("up"));
		exporter.setMnemonic('E');

		// Exporter Schema
		JMenuItem exporterSchema = new JMenuItem("Exporter Schema");
		exporterSchema.setIcon(gestionnaireIcones.getIcone("image_up"));
		exporterSchema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser selecteurFichierSchema = new JFileChooser();
				selecteurFichierSchema.setFileFilter(gestionnaireProjets
						.getFiltreFichier(gestionnaireProjets
								.getTypeFichierSchema()));
				if (selecteurFichierSchema.showSaveDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
					modele.exporterFichierSchema(selecteurFichierSchema
							.getSelectedFile().toString());
				}
			}
		});
		exporterSchema.setMnemonic('S');
		exporterSchema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		exporter.add(exporterSchema);

		// Exporter Projet
		JMenuItem exporterProjet = new JMenuItem("Exporter Projet");
		exporterProjet.setIcon(gestionnaireIcones.getIcone("folder_up"));
		exporterProjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser selecteurFichierProjet = new JFileChooser();
				selecteurFichierProjet.setFileFilter(gestionnaireProjets
						.getFiltreFichier(gestionnaireProjets
								.getTypeFichierProjet()));
				if (selecteurFichierProjet.showSaveDialog(fenetre) == JFileChooser.APPROVE_OPTION) {
					modele.exporterFichierProjet(selecteurFichierProjet
							.getSelectedFile().toString());
				}
			}
		});
		exporterProjet.setMnemonic('P');
		exporterProjet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		exporter.add(exporterProjet);

		fichier.add(exporter);

		fichier.addSeparator();

		// Quitter
		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.setIcon(gestionnaireIcones.getIcone("remove"));
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		quitter.setMnemonic('Q');
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		fichier.add(quitter);

		add(fichier);

		// MENU CONFIG
		JMenu config = new JMenu("Configuration");
		config.setIcon(gestionnaireIcones.getIcone("process"));

		// Packages
		JMenuItem packages = new JMenuItem("Packages");
		packages.setIcon(gestionnaireIcones.getIcone("database"));
		packages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VueDialoguePackages vueDialoguePackages = new VueDialoguePackages(
						modele, fenetre);
				vueDialoguePackages.ouvrir();
				if (vueDialoguePackages.estValide()) {
					modele.setListePackages(vueDialoguePackages
							.getListePackages());
				}
			}
		});
		packages.setMnemonic('P');
		packages.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.ALT_MASK));
		config.add(packages);

		// Couleurs
		JMenuItem couleurs = new JMenuItem("Couleurs");
		couleurs.setIcon(gestionnaireIcones.getIcone("chart"));
		couleurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VueDialogueCouleurs vueDialogueCouleurs = new VueDialogueCouleurs(
						modele, fenetre);
				vueDialogueCouleurs.ouvrir();
				if (vueDialogueCouleurs.estValide()) {
					modele.setCouleurs(vueDialogueCouleurs.getCouleurs());
				}
			}
		});
		couleurs.setMnemonic('C');
		couleurs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.ALT_MASK));
		config.add(couleurs);

		add(config);

		modele.ajouterVue(this);
	}

	/* (non-Javadoc)
	 * @see paleo.IG.Vue#rafraichir()
	 */
	@Override
	public void rafraichir() {
	}
}
