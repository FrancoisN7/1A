
import java.awt.Color;

/** Un segment est défini pas ses deux points qui constituent ses
 * extrémités.  Un segment peut être affiché et translaté.
 *
 * @author	Xavier Crégut
 * @version	1.9
 */
public class Schema {

	private Segment s12;
	private Segment s13;
	private Segment s23;
	private Point bary;
	private Color couleur;
	
	
	public Schema(Point p1, Point p2, Point p3) {
	this.s12 = new Segment(p1,p2);
    this.s13 = new Segment(p1,p3);
    this.s23 = new Segment(p2,p3);
 
    this.s12.afficher();
    this.s13.afficher();
    this.s23.afficher();
    
    this.bary = new Point((p1.getX()+p2.getX()+p3.getX())/3,
                          (p1.getY()+p2.getY()+p3.getY())/3);
    
   // this.bary.afficher();
	}
}
