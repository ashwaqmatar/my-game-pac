package Coords;


import Geom.Point3D;

public class MyCoords implements coords_converter {

	/**
	 * RADUIS_EARTH = Constant that defines the radius of the earth.  
	 * https://en.wikipedia.org/wiki/Earth_radius
	 */
	private final  long  earthR = 6371000; //Radios of earth in meter
	private final  double  PI= Math.PI;
	public MyCoords() {}


	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double Lon_Norm = Math.cos((gps.x()*Math.PI)/180);
		double latDif =Point3D.r2d(Math.asin(local_vector_in_meter.x()/earthR));
		double destlatvalue=gps.x()+latDif;//"x"
		double radedlon = Math.asin((local_vector_in_meter.y() / (earthR * Lon_Norm)));
		double lonDif = Point3D.r2d(radedlon);
		double destlonvalue = gps.y() + lonDif;//"y"
		double destaltvalue = local_vector_in_meter.z() + gps.z();
		Point3D ans= new Point3D (destlatvalue, destlonvalue, destaltvalue);//gps(x,y,z)
		return	ans;
	}


	/** computes the 3D distance (in meters) between the two gps like points */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1)
	{

		Point3D gps= vector3D(gps0,gps1);
		return Math.sqrt(gps.x()*gps.x()+ gps.y()*gps.y());


	}


	/** computes the 3D vector (in meters) between two gps like points */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double Lon_Norm = Math.cos((gps0.x()*Math.PI)/180);

		double diff_lat = Math.toRadians(gps1.x()-gps0.x());
		double diff_lon = Math.toRadians(gps1.y()-gps0.y());
		double diff_alt = gps1.z()-gps0.z();

		double lat_meter = Math.sin(diff_lat)*earthR;
		double lon_meter = Math.sin(diff_lon)*earthR*Lon_Norm;

		Point3D point = new Point3D(lat_meter,lon_meter,diff_alt);


		return point;
	}
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] ans = new double[3];

		//calculate azimuth
		double x0 = gps0.x()*PI/180;
		double x1 = gps1.x()*PI/180;
		double dY=gps1.y()- gps0.y();
		double delta = (dY*PI)/180;

		double x = Math.sin(delta) * Math.cos(x1);
		double y = Math.cos(x0) * Math.sin(x1) - Math.sin(x0)*Math.cos(x1)*Math.cos(delta);
		double azimuth = Math.atan2(x,y);

		if(Math.toDegrees(azimuth)<0)
			azimuth = 360+Math.toDegrees(azimuth);
		else
			azimuth = Math.toDegrees(azimuth);

		//calculate distance
		double distance =distance3d(gps0, gps1);


		//calculate elevation
		double elevation= Math.toDegrees(Math.acos( (gps1.z() - gps0.z())/distance));

		ans[0]=azimuth;ans[1]=elevation;ans[2]=distance;

		return ans;
	}
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		boolean   isValid = false;
		  if (-180<= p.x() && p.x() <= 180 && -90<=p.y() && p.y() <=90 && -450 <= p.z()){
	            isValid = true;
	        }
	        return isValid;

	}
}
