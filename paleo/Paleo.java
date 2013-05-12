/*
 * PALEO: Petite Application Logicielle d'Etude Objet
 * @author: Constant DENIS
 * 
 */
package paleo;

import paleo.exceptions.PaleoException;
import paleo.outils.Lanceur;

/**
 * La classe principale de Paleo.
 */
public class Paleo {

	/**
	 * La methode main, qui lance le mode batch ou le mode graphique en fonction des arguments.
	 * 
	 * @param args
	 *            les arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		try {
			if( (args.length == 0 ) || (args.length == 1 && args[0].equals("-sim"))) {
				new Lanceur().java("IG.IGPaleo");
			}
			else if(args.length == 3 && args[0].equals("-batch")) {
				new Lanceur().java("batch.PaleoBatch "+args[1]+" "+args[2]);
			}
			else {
				StringBuilder usage = new StringBuilder("Usage : java paleo.Paleo <mode> [ <fichier_code> <fichier_packages> ]\n");
				usage.append("<mode> =\n");
				usage.append("  <rien> ..................................... Interface Graphique\n");
				usage.append("  -sim ....................................... Interface Graphique\n");
				usage.append("  -batch <fichier_code> <fichier_packages> ... Console Batch\n");
				System.out.println(usage.toString());
			}
		} catch (PaleoException e) { System.out.println(e.getMessage()); }
	}
}
