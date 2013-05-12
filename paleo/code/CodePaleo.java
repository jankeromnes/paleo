package paleo.code;

import java.awt.Point;
import java.util.HashMap;

import paleo.exceptions.PaleoException;
import paleo.graphe.CasePointeur;
import paleo.graphe.GenerateurDot;
import paleo.graphe.Graphe;

class CodePaleo {

	private HashMap<String,String> _paleocouleurs;

	public CodePaleo(HashMap<String,String> _paleocouleurs) {
		this._paleocouleurs = _paleocouleurs;
	}

	public HashMap<String,String> genererDot(String _paleochaine) throws PaleoException {
		Point p1 = new Point();

		Graphe _paleographe = new Graphe(_paleochaine+".dot", _paleocouleurs);
		CasePointeur _paleocasep1 = new CasePointeur("p1", p1);
		_paleographe.add(_paleocasep1);

		_paleographe.construire();
		_paleographe.generer();
		return GenerateurDot.getInstance().getCouleurs();
	}

}
