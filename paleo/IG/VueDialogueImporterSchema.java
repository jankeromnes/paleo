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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import paleo.modele.ModelePaleo;
import paleo.outils.GestionnaireProjets;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueDialogueImporterSchema.
 */
public class VueDialogueImporterSchema extends VueDialogueAbstrait {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le gestionnaire de projets. */
	private GestionnaireProjets gestionnaireProjets;
	
	/** Le panneau du choix de fichier schema. */
	private JPanel panneauSchema;
	
	/** Le panneau du choix de fichiers code et packages. */
	private JPanel panneauCodePackages;
	
	/** Le bord normal. */
	private Border bordNormal;
	
	/** Le bord selectionne. */
	private Border bordSelection;
	
	/** Le selecteur de fichier. */
	private JFileChooser selecteurFichier;
	
	/** Le selecteur de fichier schema. */
	private JFileChooser selecteurFichierSchema;
	
	/** Le selecteur des fichiers code et packages. */
	private JFileChooser selecteurFichierCodePackages;
	
	/** Le chemin du fichier schema. */
	private JTextField choixCheminSchema;
	
	/** Le bouton du fichier schema. */
	private JButton boutonFichierSchema;
	
	/** Le chemin du fichier code. */
	private JTextField choixCheminCode;
	
	/** Le bouton du fichier code. */
	private JButton boutonFichierCode;
	
	/** Le chemin du fichier packages. */
	private JTextField choixCheminPackages;
	
	/** Le bouton du fichier packages. */
	private JButton boutonFichierPackages;

	/**
	 * Instancie une boite de dialogue pour importer un schema.
	 * 
	 * @param modele
	 *            le modele
	 * @param fenetre
	 *            la fenetre
	 */
	public VueDialogueImporterSchema(ModelePaleo modele, IGPaleo fenetre) {
		super("Importer un Schema", modele, fenetre);
		setSize(300, 250);
		gestionnaireProjets = GestionnaireProjets.getInstance();
		selecteurFichierSchema = new JFileChooser();
		selecteurFichierSchema.setFileFilter(gestionnaireProjets
				.getFiltreFichier(gestionnaireProjets.getTypeFichierSchema()));
		selecteurFichierCodePackages = new JFileChooser();
	}

	/**
	 * Selectionner le panneau des fichiers code et packages.
	 */
	private void activerModeCodePackages() {
		panneauSchema.setBorder(bordNormal);
		choixCheminSchema.setText("");
		if (choixCheminSchema.hasFocus()) {
			choixCheminCode.grabFocus();
		}
		panneauCodePackages.setBorder(bordSelection);
		selecteurFichier = selecteurFichierCodePackages;
	}

	/**
	 * Selectionner le panneau du fichier schema.
	 */
	private void activerModeSchema() {
		panneauCodePackages.setBorder(bordNormal);
		choixCheminCode.setText("");
		choixCheminPackages.setText("");
		if (choixCheminCode.hasFocus() || choixCheminPackages.hasFocus()) {
			choixCheminSchema.grabFocus();
		}
		panneauSchema.setBorder(bordSelection);
		selecteurFichier = selecteurFichierSchema;
	}

	/**
	 * Choisir un fichier.
	 * 
	 * @return vrai, si l'utilisateur a valide son choix
	 */
	private boolean choisirFichier() {
		return (selecteurFichier.showOpenDialog(fenetre) == JFileChooser.APPROVE_OPTION);
	}

	/* (non-Javadoc)
	 * @see paleo.IG.VueDialogueAbstrait#genererComposants()
	 */
	@Override
	public void genererComposants() {
		contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));

		// Creation des ecouteurs de click
		MouseListener activeurModeSchema = new MouseListener() {
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
				activerModeSchema();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		};

		MouseListener activeurModeCodePackages = new MouseListener() {
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
				activerModeCodePackages();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		};

		selecteurFichier = selecteurFichierSchema;
		bordNormal = BorderFactory.createCompoundBorder();
		bordSelection = BorderFactory.createLoweredBevelBorder();
		panneauSchema = new JPanel();
		panneauCodePackages = new JPanel();

		JPanel panneauFichierSchema = new JPanel();
		choixCheminSchema = new JTextField();
		choixCheminSchema.addMouseListener(activeurModeSchema);
		choixCheminSchema.setColumns(20);
		panneauFichierSchema.add(choixCheminSchema);

		boutonFichierSchema = new JButton("...");
		boutonFichierSchema.addMouseListener(activeurModeSchema);
		boutonFichierSchema.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (choisirFichier()) {
					choixCheminSchema.setText(getFichier());
				}
			}
		});
		panneauFichierSchema.add(boutonFichierSchema);

		panneauSchema.add(new JLabel(gestionnaireProjets
				.getChaineTypeFichier(gestionnaireProjets
						.getTypeFichierSchema())
				+ " :"));
		panneauSchema.add(panneauFichierSchema);

		panneauSchema.addMouseListener(activeurModeSchema);
		panneauSchema.setBorder(bordSelection);
		contenu.add(panneauSchema);

		JPanel panneauFichierCode = new JPanel();
		choixCheminCode = new JTextField();
		choixCheminCode.addMouseListener(activeurModeCodePackages);
		choixCheminCode.setColumns(20);
		panneauFichierCode.add(choixCheminCode);

		boutonFichierCode = new JButton("...");
		boutonFichierCode.addMouseListener(activeurModeCodePackages);
		boutonFichierCode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (choisirFichier()) {
					choixCheminCode.setText(getFichier());
				}
			}
		});
		panneauFichierCode.add(boutonFichierCode);

		panneauCodePackages.add(new JLabel("Fichier code (texte) :"));
		panneauCodePackages.add(panneauFichierCode);

		JPanel panneauFichierPackages = new JPanel();
		choixCheminPackages = new JTextField();
		choixCheminPackages.addMouseListener(activeurModeCodePackages);
		choixCheminPackages.setColumns(20);
		panneauFichierPackages.add(choixCheminPackages);

		boutonFichierPackages = new JButton("...");
		boutonFichierPackages.addMouseListener(activeurModeCodePackages);
		boutonFichierPackages.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (choisirFichier()) {
					choixCheminPackages.setText(getFichier());
				}
			}
		});
		panneauFichierPackages.add(boutonFichierPackages);

		panneauCodePackages.add(new JLabel("Fichier packages (texte) :"));
		panneauCodePackages.add(panneauFichierPackages);

		panneauCodePackages.addMouseListener(activeurModeCodePackages);
		panneauCodePackages.setBorder(bordNormal);
		contenu.add(panneauCodePackages);

		contenu.add(boutons);
		pack();
	}

	/**
	 * Recupere le chemin du fichier code.
	 * 
	 * @return le chemin du fichier code
	 */
	public String getCheminCode() {
		return choixCheminCode.getText();
	}

	/**
	 * Recupere the chemin packages.
	 * 
	 * @return le chemin du fichier packages
	 */
	public String getCheminPackages() {
		return choixCheminPackages.getText();
	}

	/**
	 * Recupere le chemin du fichier schema.
	 * 
	 * @return le chemin du fichier schema
	 */
	public String getCheminSchema() {
		return choixCheminSchema.getText();
	}

	/**
	 * Recupere le fichier selectionne.
	 * 
	 * @return le fichier selectionne
	 */
	private String getFichier() {
		return selecteurFichier.getSelectedFile().toString();
	}
}
