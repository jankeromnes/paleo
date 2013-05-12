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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import paleo.modele.ModelePaleo;
import paleo.outils.GestionnaireIcones;
import paleo.outils.GestionnaireProjets;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueModuleArborescence.
 */
public class VueModuleArborescence extends VueModuleAbstrait {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** L'arborescence des projets et schemas. */
	private JTree arborescence;
	
	/** Les icones personnalisees de l'arborescence. */
	private DefaultTreeCellRenderer iconesArbre;

	/**
	 * Instancie un module de vue arborescente des projets et schemas de paleo.
	 * 
	 * @param modelePaleo
	 *            le modele paleo (cf MVC)
	 * @param fenetrePaleo
	 *            la fenetre paleo
	 */
	public VueModuleArborescence(ModelePaleo modelePaleo, IGPaleo fenetrePaleo) {
		super("Projet PALEO", // nom
				GestionnaireIcones.getInstance().getIcone("folder_full"), // icone
				new PositionsRelatives(0, 0, 24, 70), // position (x,y) et
														// taille(x,y)
				new Dimension(160, 300), // dimensions minimales
				fenetrePaleo, // fenetre principale
				modelePaleo); // modele

		iconesArbre = new DefaultTreeCellRenderer();
		iconesArbre.setOpenIcon(gestionnaireIcones.getIcone("folder16x16"));
		iconesArbre.setClosedIcon(gestionnaireIcones
				.getIcone("folder_full16x16"));
		iconesArbre.setLeafIcon(gestionnaireIcones.getIcone("image16x16"));

		rafraichir();

		JButton nouveau = new JButton();
		nouveau.setIcon(gestionnaireIcones.getIcone("add"));
		nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VueDialogueNouveauProjet vueDialogueNouveauProjet = new VueDialogueNouveauProjet(
						modele, fenetre);
				vueDialogueNouveauProjet.ouvrir();
				if (vueDialogueNouveauProjet.estValide()) {
					modele.nouveauProjet(vueDialogueNouveauProjet
							.getNomProjet(), true);
				}
			}
		});
		menu.add(nouveau);

		JButton ouvrir = new JButton();
		ouvrir.setIcon(gestionnaireIcones.getIcone("folder"));
		ouvrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ouvrir(arborescence.getSelectionPaths());
			}
		});
		menu.add(ouvrir);

		JButton effacer = new JButton();
		effacer.setIcon(gestionnaireIcones.getIcone("remove"));
		effacer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				effacer(arborescence.getSelectionPaths());
			}
		});
		menu.add(effacer);
	}

	/**
	 * Effacer un projet ou un schema.
	 * 
	 * @param selection
	 *            la selection actuelle
	 */
	private void effacer(TreePath[] selection) {
		if (selection != null) {
			for (TreePath chemin : selection) {
				// projet
				if (chemin.getPathCount() == 2) {
					String nomProjet = chemin.getPathComponent(1).toString();
					modele.effacerProjet(nomProjet);
				}
				// noeud = schema
				else if (chemin.getPathCount() == 3) {
					String nomProjet = chemin.getPathComponent(1).toString();
					String nomSchema = chemin.getPathComponent(2).toString();
					modele.effacerSchema(nomSchema, nomProjet);
				}
			}
		}
	}

	/**
	 * Generer l'arborescence.
	 */
	public void genererArborescence() {

		// Créer la racine.
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode(
				"Mes Projets");

		for (String nomProjet : GestionnaireProjets.getInstance()
				.listerProjets()) {
			DefaultMutableTreeNode projet = new DefaultMutableTreeNode(
					nomProjet);

			for (String nomSchema : GestionnaireProjets.getInstance()
					.listerSchemasDuProjet(nomProjet)) {
				DefaultMutableTreeNode schema = new DefaultMutableTreeNode(
						nomSchema);
				projet.add(schema);
			}
			racine.add(projet);
		}

		arborescence = new JTree(racine);
		arborescence.setCellRenderer(iconesArbre);

		arborescence.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					ouvrir(arborescence.getSelectionPaths());
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		arborescence.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ouvrir(arborescence.getSelectionPaths());
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

	}

	/**
	 * Ouvrir un projet ou un schema.
	 * 
	 * @param selection
	 *            the selection
	 */
	private void ouvrir(TreePath[] selection) {
		if (selection != null) {
			for (TreePath chemin : selection) {
				// projet
				if (chemin.getPathCount() == 2) {
					String nomProjet = chemin.getPathComponent(1).toString();
					modele.ouvrirProjet(nomProjet);
				}
				// noeud = schema
				else if (chemin.getPathCount() == 3) {
					String nomProjet = chemin.getPathComponent(1).toString();
					String nomSchema = chemin.getPathComponent(2).toString();
					modele.ouvrirSchema(nomSchema, nomProjet);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see paleo.IG.Vue#rafraichir()
	 */
	@Override
	public void rafraichir() {
		contenu.removeAll();
		genererArborescence();
		contenu.add(new JScrollPane(arborescence));
		for (int i = 0; i < arborescence.getRowCount(); i++) {
			arborescence.expandRow(i);
		}
		revalidate();
	}

}