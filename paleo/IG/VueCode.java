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
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultEditorKit;

import paleo.modele.ModelePaleo;
import paleo.outils.GestionnaireIcones;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueCode.
 */
public class VueCode extends JPanel implements Vue {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le modele (cf MVC). */
	private ModelePaleo modele;
	
	/** La liste des numeros de lignes. */
	private JList numeros;
	
	/** Le modele pour la liste des numeros. */
	private ModeleListeArrets modeleNumeros;
	
	/** Le code utilisateur. */
	private JTextArea texte;
	
	/** L'ecouteur de modification. */
	private DocumentListener ecouteurModification;
	
	/** L'ecouteur placant l'etoile et l'icone de fichier non sauvegarde. */
	private DocumentListener ecouteurEtoile;
	
	/** Le selecteur de ligne. */
	private Action selecteurLigne;
	
	/** Le selecteur de mot. */
	private Action selecteurMot;

	/**
	 * Instancie une VueCode.
	 * 
	 * @param modelePaleo
	 *            le modele de paleo (cf MVC)
	 * @param ecouteur
	 *            l'ecouteur placant l'etoile
	 */
	public VueCode(ModelePaleo modelePaleo, DocumentListener ecouteur) {
		super();

		setLayout(new BorderLayout());

		modele = modelePaleo;
		
		modeleNumeros = new ModeleListeArrets();
		numeros = new JList(modeleNumeros);
		numeros.setBackground(new Color(240, 240, 240));
		numeros.setCellRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,boolean cellHasFocus) {            
	            JLabel label = (JLabel)value;
	            label.setOpaque(true);
	            label.setFont(texte.getFont());
	            return label;
	        }
		});
		numeros.addMouseListener(new MouseListener() {
			@Override
		    public void mouseClicked(MouseEvent e) {
		        modele.toggleArret(numeros.locationToIndex(e.getPoint()));
		    }
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});

		texte = new JTextArea();
		texte.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				modeleNumeros.numeroter(texte.getLineCount());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				modeleNumeros.numeroter(texte.getLineCount());
			}
		});

		ecouteurModification = new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				modele.setCode(texte.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				modele.setCode(texte.getText());
			}
		};

		ecouteurEtoile = ecouteur;

		texte.getDocument().addDocumentListener(ecouteurModification);
		texte.getDocument().addDocumentListener(ecouteurEtoile);
		
		selecteurLigne = getAction(DefaultEditorKit.selectLineAction);
		selecteurMot = getAction(DefaultEditorKit.selectWordAction);

		add(numeros, BorderLayout.WEST);
		numeros.setFixedCellHeight(texte.getFontMetrics(texte.getFont()).getHeight());
		numeros.setFixedCellWidth(texte.getFontMetrics(texte.getFont()).getHeight());
		add(texte, BorderLayout.CENTER);
		modele.ajouterVue(this);

	}
 
	/**
	 * Recupere une action pour la zone de code utilisateur.
	 * 
	 * @param name
	 *            le nom de l'action a recuperer
	 * @return l'action a recuperer
	 */
	private Action getAction(String name) {
		Action action = null;
		Action[] actions = texte.getActions();
		int i = 0;
		while(action == null && i < actions.length) {
			if (name.equals(actions[i].getValue(Action.NAME).toString())) {
				action = actions[i];
			}
			i++;
		}
		return action;
	}

	/* (non-Javadoc)
	 * @see paleo.IG.Vue#rafraichir()
	 */
	@Override
	public void rafraichir() {
		texte.getDocument().removeDocumentListener(ecouteurEtoile);
		texte.getDocument().removeDocumentListener(ecouteurModification);
		texte.setText(modele.getSchemaActif().getCode());
		texte.revalidate();
		texte.getDocument().addDocumentListener(ecouteurModification);
		texte.getDocument().addDocumentListener(ecouteurEtoile);
		modeleNumeros.setEtat(modele.getPointeur(), modele.getArrets(), modele.getErreurs());
	}
	
	/**
	 * La Classe ModeleListeArrets.
	 */
	private class ModeleListeArrets extends AbstractListModel {
		
		/** Le nombre de lignes. */
		int lignes;
		
		/** Le pointeur de la ligne actuelle. */
		int pointeur;
		
		/** Les points d'arret. */
		Vector<Integer> arrets;
		
		/** Les points d'erreur. */
		Vector<Integer> erreurs;
		
		/**
		 * Instancie une nouveau modele de liste d'arrets.
		 */
		public ModeleListeArrets() {
			lignes = 1;
			setEtat(-1, new Vector<Integer>(), new Vector<Integer>());
		}
		
		/**
		 * Definit l'etat des points.
		 * 
		 * @param pointeur
		 *            le pointeur
		 * @param arrets
		 *            les arrets
		 * @param erreurs
		 *            les erreurs
		 */
		public void setEtat(int pointeur, Vector<Integer> arrets, Vector<Integer> erreurs) {
			this.pointeur = pointeur;
			this.arrets = arrets;
			this.erreurs = erreurs;
		}

		/**
		 * Numeroter.
		 * 
		 * @param lignes
		 *            le nouveau nombre de lignes
		 */
		public void numeroter(int lignes) {
			this.lignes = lignes;
			fireContentsChanged(this, 0, lignes-1);
		}

		/* (non-Javadoc)
		 * @see javax.swing.ListModel#getElementAt(int)
		 */
		@Override
		public Object getElementAt(int i) {
			JLabel element = null;
			int j = 0;
			boolean erreur = false;
			while(!erreur && j < erreurs.size()) {
				if (erreurs.get(j) == i) erreur = true;
				j++;
			}
			if(erreur) {
				element = new JLabel(GestionnaireIcones.getInstance().getIcone("remove", numeros.getFixedCellWidth(), numeros.getFixedCellHeight()));
			}
			else {
				if(pointeur == i) {
					element = new JLabel(GestionnaireIcones.getInstance().getIcone("next", numeros.getFixedCellWidth(), numeros.getFixedCellHeight()));
				}
				else {
					j = 0;
					boolean arret = false;
					while(!arret && j < arrets.size()) {
						if (arrets.get(j) == i) arret = true;
						j++;
					}
					if(arret) {
						element = new JLabel(GestionnaireIcones.getInstance().getIcone("pause", numeros.getFixedCellWidth(), numeros.getFixedCellHeight()));
					}
					else element = new JLabel((i < 9 ? " " : "") + (i+1));
				}
			}
			return element;
		}

		/* (non-Javadoc)
		 * @see javax.swing.ListModel#getSize()
		 */
		@Override
		public int getSize() {
			return lignes;
		}
		
	}
}
