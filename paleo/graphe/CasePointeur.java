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
package paleo.graphe;

import java.lang.reflect.Field;

import paleo.exceptions.AccesChampException;

// TODO: Auto-generated Javadoc
/**
 * Une Case de schema memoire pointant vers un Tableau.
 */
public class CasePointeur extends Case {

	/** The o. */
	private Object o;

	/**
	 * Instantiates a new case pointeur.
	 * 
	 * @param nom
	 *            the nom
	 * @param obj
	 *            the obj
	 */
	public CasePointeur(String nom, Object obj) {
		super(nom, (obj == null ? "null" : "" + obj.hashCode()));
		o = obj;
	}

	/* (non-Javadoc)
	 * @see paleo.graphe.Noeuds#construire()
	 */
	@Override
	public void construire() throws AccesChampException {
		try {
			if (!graphe.contient(valeur) && (o != null)) {
				Tableau t = new Tableau(valeur, className(o.getClass()
						.getName()));
				Field[] champs = o.getClass().getDeclaredFields();
				for (Field c : champs) {
					c.setAccessible(true);
					if (c.get(o) == null) {
						t.add(new LigneValeur(c.getName(), "null", t));
					} else if (c.getType().isPrimitive()) {
						t.add(new LigneValeur(c.getName(), c.get(o).toString(),
								t));
					} else {
						LignePointeur lp = new LignePointeur(c.getName(), c
								.get(o), t);
						t.add(lp);
						lp.setGraphe(graphe);
						lp.construire();
					}
				}
				graphe.add(t);
			}
		} catch (IllegalAccessException e) {
			throw new AccesChampException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see paleo.graphe.Case#generer()
	 */
	@Override
	public void generer() {
		GenerateurDot.getInstance().printCasePointeur(nom);
	}

	/* (non-Javadoc)
	 * @see paleo.graphe.Case#genererArc()
	 */
	@Override
	public void genererArc() {
		GenerateurDot.getInstance().printArc(nom + ":0", valeur);
	}
}
