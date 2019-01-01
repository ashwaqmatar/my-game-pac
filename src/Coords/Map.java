package Coords;

import Coords.MyCoords;
import Geom.Point3D;

public class Map {
	
	private Point3D rotation_right_down,rotation_left_up;
	private double x,y;
	private String Diractroymap;
	
	
	/**
	 * starting constructor
	 */
	public Map() {
		this.rotation_left_up = new Point3D(32.105770,  35.202469);
		this.rotation_right_down = new Point3D(32.101899, 35.211588);
		this.x = this.rotation_right_down.y()-this.rotation_left_up.y();
		this.y = this.rotation_right_down.x()-this.rotation_left_up.x();
		this.Diractroymap = "Pictures&Icones/Ariel.png";	
	}

	/**
	 * Constructor
	 * @param rotation_right_down
	 * @param rotation_left_up
	 * @param pic
	 */
	public Map(Point3D rotation_right_down, Point3D rotation_left_up, String pic) {
		super();
		this.rotation_right_down = rotation_right_down;
		this.rotation_left_up = rotation_left_up;
		this.Diractroymap = pic;
		this.x = this.rotation_right_down.y()-this.rotation_left_up.y();
		this.y = this.rotation_right_down.x()-this.rotation_left_up.x();
	}

	/**
	 * @return the rotation_right_down
	 */
	public Point3D getRotation_right_down() {
		return rotation_right_down;
	}

	/**
	 * @return the rotation_left_up
	 */
	public Point3D getRotation_left_up() {
		return rotation_left_up;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return the pic
	 */
	public String getDiractroymap() {
		return Diractroymap;
	}

	/**
	 * @param rotation_right_down the rotation_right_down to set
	 */
	public void setRotation_right_down(Point3D rotation_right_down) {
		this.rotation_right_down = rotation_right_down;
	}

	/**
	 * @param rotation_left_up the rotation_left_up to set
	 */
	public void setRotation_left_up(Point3D rotation_left_up) {
		this.rotation_left_up = rotation_left_up;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @param pic the pic to set
	 */
	public void setDiractroymap(String pic) {
		this.Diractroymap = pic;
	}
	
	/**
	 * function that gives us pixel coordinates from gps point
	 * @param gps_point  
	 * @return
	 */
	public Point3D coord2pixel(Point3D gps_point) {	
	return new Point3D( (double)((gps_point.y()-rotation_left_up.y())/x),	(double)((gps_point.x()-rotation_left_up.x())/y));		
	}

	/**
	 * the reverse of the function up  
	 * @param x
	 * @param y
	 * @return
	 */
	public Point3D pixel2coord(double x, double y) {	
		return new Point3D(	(double)(x * this.x+rotation_left_up.y()),(double)( y * this.y+rotation_left_up.x()));
	}
	
	/**
	 * 3D distance between two pixel points
	 * @param p1
	 * @param p2
	 * @return distance
	 */
	public double destpixels(Point3D p1, Point3D p2) {
		MyCoords gps_point=new MyCoords();
		return gps_point.distance3d(pixel2coord(p1.x(), p1.y()), pixel2coord(p2.x(),p2.y()));

	}
	
	
	


}