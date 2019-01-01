package GIS;

import Geom.Point3D;

public class BOX {

	private int id;
	private Point3D p0;
	private Point3D p1;
	private double x_left_up;
	private double y_left_up;
	private double x_right_down;
	private double y_right_down;

	private static int _count = 0;
	public BOX(Point3D p0,Point3D p1) {
		this.id=id;
		this.p0=p0;
		this.p1=p1;
		this.x_left_up = p0.x();
		this.y_left_up = p1.y();
		this.x_right_down = p1.x();
		this.y_right_down = p0.y();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Point3D getP0() {
		return p0;
	}
	public void setP0(Point3D p0) {
		this.p0 = p0;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BOX [p0=" + p0 + ", p1=" + p1 + "]";
	}
	public Point3D getP1() {
		return p1;
	}
	public void setP1(Point3D p1) {
		this.p1 = p1;
	}
	public boolean inside(Point3D n) {

		if(n.x() > p1.x() && (n.x()< p0.x()) && (n.y() > p1.y())  &&n.y() < p0.y() ) {
			return false;
		}else {
			return true;
		}
	}
}

