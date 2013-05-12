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
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import paleo.modele.ModelePaleo;
import paleo.outils.GestionnaireIcones;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueModuleConsole.
 */
public class VueModuleConsole extends VueModuleAbstrait {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le journal des messages de paleo. */
	private JTextArea log;
	
	/** La taille precedente du journal, pour n'afficher que les dernieres entrees. */
	private int lastLogSize;

	/**
	 * Instancie un module de vue console affichant les messages de paleo.
	 * 
	 * @param modele
	 *            le modele
	 * @param fenetre
	 *            la fenetre
	 */
	public VueModuleConsole(ModelePaleo modele, IGPaleo fenetre) {
		super("Console", // nom
				GestionnaireIcones.getInstance().getIcone("comment"), // icone
				new PositionsRelatives(0, 70, 100, 30), // position (x,y) et
														// taille(x,y)
				new Dimension(400, 100), // dimensions minimales
				fenetre, // fenetre principale
				modele); // modele

		log = new JTextArea();
		log.setEditable(false);
		contenu.add(new JScrollPane(log));
		lastLogSize = 0;

		rafraichir();
	}

	/* (non-Javadoc)
	 * @see paleo.IG.Vue#rafraichir()
	 */
	@Override
	public void rafraichir() {
		Vector<StringBuilder> logs = modele.getLogs();
		StringBuilder texte = new StringBuilder();
		while (logs.size() > lastLogSize) {
			texte.append(logs.lastElement().toString());
			lastLogSize++;
		}
		log.setText((texte.length() > 0 ? texte.toString() : ">"));
	}
}