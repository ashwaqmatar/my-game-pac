package Coords;


import Geom.Point3D;

public class Main {

	public static void main(String[] args) {
		
		Point3D p0 = new Point3D(32.103315 , 35.209039, 670);
		Point3D p1 = new Point3D(32.106352, 35.205225, 650);
		MyCoords coord = new MyCoords();
		Point3D p3 = coord.vector3D(p0, p1);
		System.out.println(p3.toString());

	}
}