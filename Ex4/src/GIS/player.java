package GIS;

import Geom.Point3D;

public class player {

	double r;//radius
	double speed;

	Point3D playerLocation;

	/**
	 * Constractor
	 */ 

	public player(Point3D p , double S, double R) {
		this.speed  = S;
		this.r = R;
		this.playerLocation = p;



	}
	/**
	 * Copy Constractor
	 *
	 */
	public player(player p) {
		this(p.playerLocation,p.speed,p.r);
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
	public Point3D get_player_Location() {
		return playerLocation;
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
	public void set_player_Location(Point3D packLocation) {
		this.playerLocation = playerLocation;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}


	@Override
	public String toString() {
		return "player ["+playerLocation.toString()+" Radius= "+r+"speed=" + speed + "]";
	}





}
