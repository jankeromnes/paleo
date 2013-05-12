package geometrie;

public class Point {
	
	private double i;
	private double j;
	
	public Point() {
		
	}
	
	public Point(double i, double j) {
		
	}

	public void setX(double i) {
		this.i = i;
	}

	public double getX() {
		return i;
	}

	public void setY(double j) {
		this.j = j;
	}

	public double getY() {
		return j;
	}

	public void deplacer(int k, int l) {
		this.i += k;
		this.j += l;
	}

}
