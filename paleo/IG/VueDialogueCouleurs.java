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

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import paleo.modele.ModelePaleo;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueDialogueCouleurs.
 */
public class VueDialogueCouleurs extends VueDialogueAbstrait {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La liste des couleurs. */
	private JList listeCouleurs;
	
	/** Le modele de la liste des couleurs. */
	private ModeleListeCouleurs modeleListeCouleurs;
	
	/** Le choix du nom de la classe. */
	private JTextField choixNomClasse;

	/**
	 * Instancie une boite de dialogue pour choisir les couleurs du schema memoire.
	 * 
	 * @param modelePaleo
	 *            le modele paleo
	 * @param fenetre
	 *            la fenetre
	 */
	public VueDialogueCouleurs(ModelePaleo modelePaleo, IGPaleo fenetre) {
		super("Gestion des Couleurs", modelePaleo, fenetre);
		setSize(250, 200);
		
		choixNomClasse = new JTextField();
		choixNomClasse.setHorizontalAlignment(JTextField.CENTER);
	}

	/* (non-Javadoc)
	 * @see paleo.IG.VueDialogueAbstrait#genererComposants()
	 */
	@Override
	public void genererComposants() {
		listeCouleurs = new JList();
		modeleListeCouleurs = new ModeleListeCouleurs(modele.getCouleurs());
		listeCouleurs.setModel(modeleListeCouleurs);
		listeCouleurs.setLayoutOrientation(JList.VERTICAL);
		listeCouleurs.setCellRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,boolean cellHasFocus) {            
	            JLabel label = (JLabel)value;
	            label.setOpaque(true);
	            label.setFont(((JLabel)value).getFont());
	            label.setHorizontalAlignment(JLabel.CENTER);
	            if(isSelected) label.setBorder(BorderFactory.createLoweredBevelBorder());
	            return label;
	        }
		});
		listeCouleurs.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					modeleListeCouleurs.supprimerSelection(listeCouleurs.getSelectedIndices());
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}
		});

		JButton couleur = new JButton("Couleur");
		couleur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				choixNomClasse.setBackground(JColorChooser.showDialog(VueDialogueCouleurs.this, "Couleur pour la classe "+choixNomClasse.getText(), choixNomClasse.getForeground()));
			}
		});

		JButton ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color couleur = choixNomClasse.getBackground();
				String chaine = String.format("#%02X%02X%02X", couleur.getRed(), couleur.getGreen(), couleur.getBlue());
				modeleListeCouleurs.ajouterCouleur(choixNomClasse.getText(), chaine);
				choixNomClasse.setText("");
				choixNomClasse.setForeground(Color.BLACK);
				choixNomClasse.setBackground(Color.WHITE);
			}
		});

		JPanel panneauAjouter = new JPanel();
		panneauAjouter.setLayout(new BoxLayout(panneauAjouter, BoxLayout.X_AXIS));
		panneauAjouter.add(couleur);
		panneauAjouter.add(choixNomClasse);
		panneauAjouter.add(ajouter);

		contenu.add(new JScrollPane(listeCouleurs));
		contenu.add(panneauAjouter);
		contenu.add(boutons);
	}

	/**
	 * Recupere les couleurs.
	 * 
	 * @return les couleurs
	 */
	public HashMap<String,String> getCouleurs() {
		return modeleListeCouleurs.getCouleurs();
	}
	
	/**
	 * La Classe ModeleListeCouleurs.
	 */
	private class ModeleListeCouleurs extends DefaultListModel {
		
		/** Les couleurs. */
		private HashMap<String,String> couleurs;
		
		/**
		 * Instancie un nouvea modele pour une liste de couleurs.
		 * 
		 * @param couleurs
		 *            les couleurs
		 */
		public ModeleListeCouleurs(HashMap<String,String> couleurs) {
			super();
			this.couleurs = couleurs;
		}
		
		/**
		 * Ajouter un nom de classe avec une couleur.
		 * 
		 * @param nomClasse
		 *            le nom de la classe
		 * @param couleur
		 *            la couleur
		 */
		public void ajouterCouleur(String nomClasse, String couleur) {
			couleurs.put(nomClasse, couleur);
			fireContentsChanged(this, 0, getSize()-1);
		}

		/**
		 * Recupere les couleurs.
		 * 
		 * @return les couleurs
		 */
		public HashMap<String, String> getCouleurs() {
			return couleurs;
		}

		/* (non-Javadoc)
		 * @see javax.swing.DefaultListModel#getElementAt(int)
		 */
		@Override
		public Object getElementAt(int i) {
			String nomClasse = couleurs.keySet().toArray()[i].toString();
			JLabel couleur = new JLabel(nomClasse);
			couleur.setBackground(Color.decode(couleurs.get(nomClasse)));
			return couleur;
		}
		
		/**
		 * Supprimer la selection actuelle.
		 * 
		 * @param is
		 *            les indices de la selection
		 */
		public void supprimerSelection(int[] is) {
			int total = is.length;
			for (int i = 0; i < total; i++) {
				couleurs.remove(couleurs.keySet().toArray()[is[total - i - 1]].toString());
			}
			fireContentsChanged(this, 0, getSize()-1);
		}

		/* (non-Javadoc)
		 * @see javax.swing.DefaultListModel#getSize()
		 */
		@Override
		public int getSize() {
			return couleurs.size();
		}
		
	}

}
