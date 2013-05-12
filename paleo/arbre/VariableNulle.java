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
 * Une Variable Nulle.
 */
public class VariableNulle extends Variable {

	/**
	 * Instancie une variable nulle.
	 * 
	 * @param nom
	 *            le nom
	 */
	public VariableNulle(String nom) {
		super(nom, false);
	}

	/* (non-Javadoc)
	 * @see paleo.arbre.Noeuds#generer()
	 */
	@Override
	public String generer() {
		StringBuilder s = new StringBuilder("CaseValeur _paleocase");
		s.append(nom);
		s.append(" = new CaseValeur(");
		s.append('"');
		s.append(nom);
		s.append("\", ");
		s.append("\"null\"");
		s.append(");");
		return s.toString();
	}

}
