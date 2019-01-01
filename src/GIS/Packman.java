package GIS;

import Geom.Point3D;

public class Packman {
	
	double r;//radius
	double speed;

	Point3D packLocation;
	   path packmanpath ;

	/**
	 * Constractor
	 */ 
	
	public Packman(Point3D p , double S, double R) {
		this.speed  = S;
		this.r = R;
		packmanpath = new path();
		this.packLocation = p;

		
		
	}
	/**
	 * Copy Constractor
	 *
	 */
	public Packman(Packman p) {
		this(p.packLocation,p.speed,p.r);
		packmanpath=new path();
	}

	/**
	 * @return the r
	 */
	public double getred() {
		return r;
	}
	/**
	 * @return the packLocation
	 */
	public Point3D getP() {
		return packLocation;
	}
	/**
	 * @return the packmanpath
	 */
	public path getpath() {
		return packmanpath;
	}
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}	
	
	/**
	 * @param r the r to set
	 */
	public void setred(double r) {
		this.r = r;
	}
	
	/**
	 * @param packLocation the packLocation to set
	 */
	public void setPackLocation(Point3D packLocation) {
		this.packLocation = packLocation;
	}
	
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	
	@Override
	public String toString() {
		return "Packman ["+packLocation.toString()+" Radius= "+r+"speed=" + speed + "]";
	}
	
	
	


}
