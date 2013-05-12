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
package paleo.arbre;

// TODO: Auto-generated Javadoc
/**
 * Une Variable Objet.
 */
public class VariableObjet extends Variable {

	/**
	 * Instancie une variable objet.
	 * 
	 * @param nom
	 *            le nom
	 */
	public VariableObjet(String nom) {
		super(nom, false);
	}

	/* (non-Javadoc)
	 * @see paleo.arbre.Noeuds#generer()
	 */
	@Override
	public String generer() {
		StringBuilder s = new StringBuilder("CasePointeur _paleocase");
		s.append(nom);
		s.append(" = new CasePointeur(");
		s.append('"');
		s.append(nom);
		s.append("\", ");
		s.append(nom);
		s.append(");");
		return s.toString();
	}

}
