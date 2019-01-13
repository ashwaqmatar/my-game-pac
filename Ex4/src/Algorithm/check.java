package Algorithm;

import Coords.MyCoords;
import Geom.Point3D;
import GIS.BOX;


public class check {
	
	public static void main(String[] args) {
		MyCoords m = new MyCoords();

		BOX b2 = new BOX(new Point3D(32.1035470312093,35.2056556995029,0),new Point3D(32.1039590220811,35.2106165009524,0));
		BOX b1 = new BOX(new Point3D(32.1027021891685,35.2081838002415,0),new Point3D(32.1049811766491,35.2085177003391,0));
		
		Point3D a = new Point3D(32.1034880488877,35.20816543349475,0.0); //true
		Point3D a1 = new Point3D(32.10421456072313,35.20785785014635,0.0); // false
		Point3D a2 = new Point3D(32.10373127333334,35.20939944,0.0); // true
		Point3D a3 = new Point3D(32.103460303333335,35.20985539,0.0); // true

		
		



	}
	public static Point3D theNextPoint(Point3D p1 , Point3D f1) {
		
	double dt = 100; // the time from (x1,y1) to (x2,y2) example: 300.
	
	double Vx = p1.x()/dt;
	double Vy = p1.y()/dt;
	
	double xt = p1.x()+Vx*(f1.x()-p1.x());
	double yt= p1.y()+Vy*(f1.y()-p1.y());
	return new Point3D(xt,yt);
	
	}

}
