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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import paleo.modele.ModelePaleo;
import paleo.outils.GestionnaireIcones;


// TODO: Auto-generated Javadoc
/**
 * La Classe IGPaleo.
 */
public class IGPaleo extends JFrame {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * La methode main.
	 * 
	 * @param argv les arguments
	 */
	public static void main(String[] argv) {
		new IGPaleo();
	}

	/** Les modules graphiques (fenetres internes). */
	private JDesktopPane modules;

	/**
	 * Instancie l'interface graphique de paleo.
	 */
	public IGPaleo() {
		super("Bienvenue dans PALEO - les schemas memoire, tout simplement");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		setIconImage(GestionnaireIcones.getInstance().getIcone("application")
				.getImage());

		addComponentListener(new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				redimensionnerModules();
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
			}
		});

		ModelePaleo modele = new ModelePaleo();
		setJMenuBar(new VueMenus(modele, this));
		modules = new JDesktopPane();

		VueModuleArborescence vueModuleArborescence = new VueModuleArborescence(
				modele, this);
		modules.add(vueModuleArborescence);

		VueModuleEditeur vueModuleEditeur = new VueModuleEditeur(modele, this);
		modules.add(vueModuleEditeur);

		VueModuleConsole vueModuleConsole = new VueModuleConsole(modele, this);
		modules.add(vueModuleConsole);

		setContentPane(modules);

		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);

		setVisible(true);
	}

	/**
	 * Redimensionne les modules dans la fenetres principale.
	 */
	public void redimensionnerModules() {
		for (Component composant : modules.getComponents()) {
			((VueModuleAbstrait) composant).redimensionner();
		}
	}
}
