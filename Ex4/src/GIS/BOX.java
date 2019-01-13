package GIS;

import Coords.MyCoords;
import Geom.Point3D;

public class BOX {

	private Point3D p0; // left down
	private Point3D p1; // right up
	private Point3D p2; // right down
	private Point3D p3;// left up

	/********************************************/

	private MyCoords coord = new MyCoords();

	public BOX(Point3D p0,Point3D p1) {
		this.p0=p0;
		this.p1=p1;
		this.p2 = new Point3D(p1.x(),p2.y());
		this.p3 = new Point3D(p2.x(),p1.y());
	}
	/******getters && setters******/

	public Point3D getP0() {
		return p0;
	}
	public void setP0(Point3D p0) {
		this.p0 = p0;
	}	public Point3D getP1() {
		return p1;
	}
	public void setP1(Point3D p1) {
		this.p1 = p1;
	}
	public Point3D getP2() {
		return p2;
	}
	public void setP2(Point3D p2) {
		this.p2 = p2;
	}public Point3D getP3() {
		return p3;
	}
	public void setP3(Point3D p3) {
		this.p3 = p3;
	}


	public  Point3D NextPoint(Point3D p1 , Point3D f1) {

		//	1000==  the time from (x1,y1) to (x2,y2) example: 300.
		double xt = p1.x()+p1.x()/1000*(f1.x()-p1.x());
		double yt= p1.y()+p1.y()/1000*(f1.y()-p1.y());
		return new Point3D(xt,yt);

	}
	public BOX addToConver() {

		Point3D p1 = new Point3D(this.p1.x()-0.0010,this.p1.y()+0.0010);//down left
		Point3D p2 = new Point3D(this.p2.x()+0.0010,this.p2.y()-0.0010);//up right

		return new BOX(p1,p2);
	}
	public boolean checkit1 (Point3D m, Point3D theClose) {
		Point3D temp  = new Point3D(m);

		while(coord.distance3d(temp, theClose) >= 1) {
			if((temp.x() >= p1.x() && temp.x() <= p2.x()) && (temp.y() <= p2.y() && temp.y() >= p1.y())) {
				return true;
			}

			temp = NextPoint(temp, theClose);
		}
		return false;
	}


	public double inside(Point3D M, Point3D theClose) {

		Point3D Height_x_right = new Point3D(M.x(),p2.y());
		Point3D Height_x_left = new Point3D(M.x(),p1.y());
		Point3D Width_y_up = new Point3D(p2.x(),M.y());


		if((coord.distance3d(M, Height_x_right) <= 1.0)){
			if(M.x() > p2.x() && M.y() > p1.y()) {
				return 3;
			}else {
				return 4;
			}
		}
		if((coord.distance3d(M, Width_y_up) <=1.0)&& (M.y() <= p2.y()) && (M.y() >= p1.y())){
			if(!checkit1(M,theClose)) {
				System.out.println("can do stright 3");
				return 5;
			}
			return 3;
		}
		if((coord.distance3d(M, Height_x_left) <= 1.0) && M.x() >= p1.x()){
			System.out.println(coord.distance3d(M, Height_x_left));
			if(!checkit1(M,theClose)) {

				System.out.println("can do stright 4");
				return 5;
			}
			return 4;

		}
		return 5;

	}	
	@Override
	public String toString() {
		return "BOX [p0=" + p0 + ", p1=" + p1 + "]";
	}

}


