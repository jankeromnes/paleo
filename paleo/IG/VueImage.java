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
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import paleo.modele.ModelePaleo;

// TODO: Auto-generated Javadoc
/**
 * La Classe VueImage.
 */
public class VueImage extends JPanel implements Vue {

	/** Le serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le modele de paleo (cf MVC). */
	private ModelePaleo modele;
	
	/** Le message par defaut. */
	private JLabel label;
	
	/** Le ratio d'affichage. */
	private double ratio;

	/**
	 * Instancie la vue de l'image d'un schema.
	 * 
	 * @param modelePaleo
	 *            le modele paleo
	 */
	public VueImage(ModelePaleo modelePaleo) {
		super();
		modele = modelePaleo;
		label = new JLabel("cliquez sur Executer");
		add(label);
		ratio = 1.;
		modele.ajouterVue(this);
	}
	
	/**
	 * Effectue un zoom sur l'image.
	 */
	public void zoomer() {
		changerRatio(ratio *= 1.5);
	}
	
	/**
	 * Recule le zoom de l'image.
	 */
	public void dezoomer() {
		changerRatio(ratio /= 1.5);
	}
	
	/**
	 * Changer le ratio.
	 * 
	 * @param r
	 *            le nouveau ratio
	 */
	private void changerRatio(double r) {
		ratio = r;
		if(ratio < 0.2) ratio = 0.2;
		if(ratio > 5) ratio = 5;
		ImageIcon image = modele.getImage();
		if(image!=null) label.setIcon(new ImageIcon(image.getImage().getScaledInstance((int)(ratio*image.getIconWidth()), (int)(ratio*image.getIconHeight()), Image.SCALE_SMOOTH)));
	}

	/* (non-Javadoc)
	 * @see paleo.IG.Vue#rafraichir()
	 */
	@Override
	public void rafraichir() {
		ratio = 1.;
		ImageIcon image = modele.getImage();
		if (image == null) {
			setBackground(new Color(240, 240, 240));
			label.setIcon(null);
			label.setText("cliquez sur Executer");
		} else {
			setBackground(new Color(255, 255, 255));
			label.setIcon(image);
			label.setText(null);
		}
	}
}
