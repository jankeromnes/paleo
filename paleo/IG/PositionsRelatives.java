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

// TODO: Auto-generated Javadoc
/**
 * La Classe PositionsRelatives.
 */
public class PositionsRelatives {
	
	/** La position horizontale d'un module. */
	private double pourcentX;
	
	/** La position verticale d'un module. */
	private double pourcentY;
	
	/** La largeur d'un module. */
	private double pourcentLargeur;
	
	/** La hauteur d'un module. */
	private double pourcentHauteur;

	/**
	 * Instancie un objet de positions relatives.
	 * 
	 * @param pourcentX
	 * @param pourcentY
	 * @param pourcentLargeur
	 * @param pourcentHauteur
	 */
	public PositionsRelatives(double pourcentX, double pourcentY,
			double pourcentLargeur, double pourcentHauteur) {
		setPourcentX(pourcentX);
		setPourcentY(pourcentY);
		setPourcentLargeur(pourcentLargeur);
		setPourcentHauteur(pourcentHauteur);
	}

	/**
	 * Recupere la hauteur.
	 * 
	 * @return la hauteur
	 */
	public double getPourcentHauteur() {
		return pourcentHauteur;
	}

	/**
	 * Recupere la largeur.
	 * 
	 * @return la largeur
	 */
	public double getPourcentLargeur() {
		return pourcentLargeur;
	}

	/**
	 * Recupere la position horizontale.
	 * 
	 * @return la position horizontale
	 */
	public double getPourcentX() {
		return pourcentX;
	}

	/**
	 * Recupere la position verticale.
	 * 
	 * @return la position verticale
	 */
	public double getPourcentY() {
		return pourcentY;
	}

	/**
	 * Definit la hauteur.
	 * 
	 * @param pourcentHauteur
	 *            la nouvelle hauteur
	 */
	public void setPourcentHauteur(double pourcentHauteur) {
		this.pourcentHauteur = pourcentHauteur;
	}

	/**
	 * Definit la largeur.
	 * 
	 * @param pourcentLargeur
	 *            la nouvelle largeur
	 */
	public void setPourcentLargeur(double pourcentLargeur) {
		this.pourcentLargeur = pourcentLargeur;
	}

	/**
	 * Definit la position horizontale.
	 * 
	 * @param pourcentX
	 *            la nouvelle position horizontale
	 */
	public void setPourcentX(double pourcentX) {
		this.pourcentX = pourcentX;
	}

	/**
	 * Definit la position verticale.
	 * 
	 * @param pourcentY
	 *            la position verticale
	 */
	public void setPourcentY(double pourcentY) {
		this.pourcentY = pourcentY;
	}
}
