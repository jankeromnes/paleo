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
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import paleo.modele.ModelePaleo;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueDialoguePackages.
 */
public class VueDialoguePackages extends VueDialogueAbstrait {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La liste des packages. */
	private JList listePackages;
	
	/** Le modele de la liste des packages. */
	private DefaultListModel modeleListePackages;
	
	/** Le choix d'un package. */
	private JComboBox choixPackage;

	/**
	 * Instancie une boite de dialogue permettant de choisir les packages utilises.
	 * 
	 * @param modelePaleo
	 *            le modele paleo
	 * @param fenetre
	 *            la fenetre
	 */
	public VueDialoguePackages(ModelePaleo modelePaleo, IGPaleo fenetre) {
		super("Configuration des Packages", modelePaleo, fenetre);
		// setSize(250, 200);
	}

	/**
	 * Ajouter un package.
	 * 
	 * @param pack
	 *            le package a ajouter
	 */
	public void ajouterPackage(String pack) {
		if (!modeleListePackages.contains(pack)) {
			modeleListePackages.addElement(pack);
		}
	}

	/* (non-Javadoc)
	 * @see paleo.IG.VueDialogueAbstrait#genererComposants()
	 */
	@Override
	public void genererComposants() {
		modeleListePackages = new DefaultListModel();
		listePackages = new JList();
		listePackages.setLayoutOrientation(JList.VERTICAL);
		listePackages.setModel(modeleListePackages);
		for (String pack : modele.getListePackages()) {
			ajouterPackage(pack);
		}
		listePackages.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					int[] indicesSelection = listePackages.getSelectedIndices();
					int total = indicesSelection.length;
					for (int i = 0; i < total; i++) {
						modeleListePackages.remove(indicesSelection[total - i
								- 1]);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});

		choixPackage = new JComboBox(modele.getPackagesDisponibles());
		choixPackage.setEditable(true);

		JButton ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ajouterPackage(choixPackage.getSelectedItem().toString());
				choixPackage.setSelectedItem("");
			}
		});

		JPanel panneauAjouter = new JPanel();
		panneauAjouter
				.setLayout(new BoxLayout(panneauAjouter, BoxLayout.X_AXIS));
		panneauAjouter.add(choixPackage);
		panneauAjouter.add(ajouter);

		JScrollPane scrollPackages = new JScrollPane(listePackages);
		scrollPackages.setPreferredSize(new Dimension(240, 100));
		contenu.add(scrollPackages);
		contenu.add(panneauAjouter);
		contenu.add(boutons);

		pack();
	}

	/**
	 * Recupere la liste des packages importes.
	 * 
	 * @return la liste des packages importes
	 */
	public Vector<String> getListePackages() {
		Vector<String> liste = new Vector<String>();
		for (int i = 0; i < modeleListePackages.getSize(); i++) {
			liste.add(modeleListePackages.getElementAt(i).toString());
		}
		return liste;
	}

}
