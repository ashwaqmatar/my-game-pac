package GIS;

import Geom.Point3D;

public class Ghost {
	
	double rG;//radius
	double speedG;

	Point3D ghostLocation;
	   path ghostpath ;

	/**
	 * Constractor
	 */ 
	
	public Ghost(Point3D G , double S, double R) {
		this.speedG  = S;
		this.rG = R;
		ghostpath = new path();
		this.ghostLocation = G;

		
		
	}
	/**
	 * Copy Constractor
	 *
	 */
	public Ghost(Ghost G) {
		this(G.ghostLocation,G.speedG,G.rG);
		ghostpath=new path();
	}

	/**
	 * @return the r
	 */
	public double getredG() {
		return rG;
	}
	/**
	 * @return the packLocation
	 */
	public Point3D getG() {
		return ghostLocation;
	}
	/**
	 * @return the packmanpath
	 */
	public path getpathG() {
		return ghostpath;
	}
	/**
	 * @return the speed
	 */
	public double getSpeedG() {
		return speedG;
	}	
	
	/**
	 * @param r the r to set
	 */
	public void setredG(double rG) {
		this.rG = rG;
	}
	
	/**
	 * @param packLocation the packLocation to set
	 */
	public void setghostLocation(Point3D ghostLocation) {
		this.ghostLocation = ghostLocation;
	}
	
	/**
	 * @param speed the speed to set
	 */
	public void setSpeedG(double speedG) {
		this.speedG = speedG;
	}

	
	@Override
	public String toString() {
		return "Packman ["+ghostLocation.toString()+" Radius= "+rG+"speed=" + speedG + "]";
	}
	
	
	


}

