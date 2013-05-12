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
 * La Ligne d'un Tableau pointant vers un autre Tableau.
 */
public class LignePointeur extends Ligne {

	/** The o. */
	private Object o;

	/**
	 * Instantiates a new ligne pointeur.
	 * 
	 * @param nom
	 *            the nom
	 * @param obj
	 *            the obj
	 * @param tab
	 *            the tab
	 */
	public LignePointeur(String nom, Object obj, Tableau tab) {
		super(nom, "" + obj.hashCode(), tab);
		o = obj;
	}

	/* (non-Javadoc)
	 * @see paleo.graphe.Noeuds#construire()
	 */
	@Override
	public void construire() throws AccesChampException {
		try {
			if (!graphe.contient(valeur)) {
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
	 * @see paleo.graphe.Ligne#generer()
	 */
	@Override
	public void generer() {
		GenerateurDot.getInstance().printLignePointeur(nom);
	}

	/* (non-Javadoc)
	 * @see paleo.graphe.Ligne#genererArc()
	 */
	@Override
	public void genererArc() {
		GenerateurDot.getInstance().printArc(tableau.getNom() + ":" + nom,
				valeur);
	}
}
