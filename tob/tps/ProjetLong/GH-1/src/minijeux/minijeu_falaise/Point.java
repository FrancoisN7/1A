package minijeux.minijeu_falaise;

public class Point {
	private int x;		// abscisse
	private int y;		// ordonnée

	/** Construire un point à partir de son abscisse et de son ordonnée.
	 * @param vx abscisse
	 * @param vy ordonnée
	 */
	public Point(int vx, int vy) {
		this.x = vx;
		this.y = vy;
	}

	/** Obtenir l'abscisse du point.
	 * @return abscisse du point
	 */
	public int getX() {
		return this.x;
	}

	/** Obtenir l'ordonnée du point.
	 * @return ordonnée du point
	 */
	public int getY() {
		return this.y;
	}

	/** Changer l'abscisse du point.
	  * @param vx nouvelle abscisse
	  */
	public void setX(int vx) {
		this.x = vx;
	}

	/** Changer l'ordonnée du point.
	  * @param vy nouvelle ordonnée
	  */
	public void setY(int vy) {
		this.y = vy;
	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	/** Afficher le point. */
	public void afficher() {
		System.out.print(this);
	}

	/** Distance par rapport à un autre point.
	 * @param autre l'autre point
	 * @return distance entre this et autre
	 */
	public double distance(Point autre) {
		return Math.sqrt(Math.pow(autre.x - this.x, 2)
					+ Math.pow(autre.y - this.y, 2));
	}

   /** Translater le point.
	* @param dx déplacement suivant l'axe des X
	* @param dy déplacement suivant l'axe des Y
	*/
	public void translater(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}



}