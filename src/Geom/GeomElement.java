package Geom;
public class GeomElement implements Geom_element {
	
	String[] s;
	
	public GeomElement(String [] s) {
		this.s=s;
	}
	
	@Override
	public double distance3D(Point3D p) {
		Point3D p2= new Point3D(6,7,8);
		double distance=p.distance3D(p2);
		return distance;
	}

	@Override
	public double distance2D(Point3D p) {
		Point3D p2= new Point3D(6,7,8);
		double distance=p.distance2D(p2);
		return distance;
	}
}