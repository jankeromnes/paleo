package paleo.code;

import geometrie.*; 

class CodeUtilisateur {

	public static void main(String[] argv) {
		int k = 333;
		Point p1 = new Point(1,2);
		p1.deplacer(k,1);
		p1.deplacer(2,2*k);
		p1 = null;
		// termine
	}

}
