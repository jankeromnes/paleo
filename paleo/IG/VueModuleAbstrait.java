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
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;

import paleo.modele.ModelePaleo;
import paleo.outils.GestionnaireIcones;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueModuleAbstrait.
 */
public abstract class VueModuleAbstrait extends JInternalFrame implements Vue {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le modele de paleo (cf MVC). */
	protected ModelePaleo modele;
	
	/** La fenetre. */
	protected IGPaleo fenetre;
	
	/** L'objet de position/taille relatives du module. */
	private PositionsRelatives position;
	
	/** Le contenu du module. */
	protected Container contenu;
	
	/** Le menu du module. */
	protected JMenuBar menu;
	
	/** Le gestionnaire des icones. */
	protected GestionnaireIcones gestionnaireIcones;

	/**
	 * Instancie un nouveau module graphique (fenetre interne de l'interface graphique de paleo).
	 * 
	 * @param nom
	 *            le nom du module
	 * @param icone
	 *            l'icone du module
	 * @param position
	 *            la position du module
	 * @param minimum
	 *            la taille minimale
	 * @param fenetre
	 *            la fenetre
	 * @param modele
	 *            le modele
	 */
	public VueModuleAbstrait(String nom, ImageIcon icone,
			PositionsRelatives position, Dimension minimum, IGPaleo fenetre,
			ModelePaleo modele) {
		super(nom, true, // resizable
				false, // closable
				true, // maximizable
				true // iconifiable
		);

		setFrameIcon(icone);

		this.modele = modele;
		this.fenetre = fenetre;
		this.position = position;
		contenu = getContentPane();
		menu = new JMenuBar();
		setJMenuBar(menu);
		gestionnaireIcones = GestionnaireIcones.getInstance();

		setMinimumSize(minimum);

		modele.ajouterVue(this);
		setVisible(true);
	}

	/**
	 * Redimensionner.
	 */
	public void redimensionner() {
		Dimension dimensions = fenetre.getContentPane().getSize();
		setLocation(
				(int) (dimensions.getWidth() * position.getPourcentX() * 0.01),
				(int) (dimensions.getHeight() * position.getPourcentY() * 0.01));
		setSize(
				(int) (dimensions.getWidth() * position.getPourcentLargeur() * 0.01),
				(int) (dimensions.getHeight() * position.getPourcentHauteur() * 0.01));
	}
}
