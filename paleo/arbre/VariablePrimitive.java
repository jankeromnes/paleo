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
 * Une Variable Primitive.
 */
public class VariablePrimitive extends Variable {

	/**
	 * Instancie une variable primitive.
	 * 
	 * @param nom
	 *            le nom
	 */
	public VariablePrimitive(String nom) {
		super(nom, true);
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
		s.append("\", \"\"+");
		s.append(nom);
		s.append(");");
		return s.toString();
	}

}
