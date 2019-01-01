package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class myGIS_element implements GIS_element {
	MetaData md ;
	Point3D p;
	private String MAC;
	private String SSID;
	private String AuthMode;
	private double Lat;
	private double Lon;
	private double AltitudeMeters;
	private int Channel;
	private int RSSI; 
	private int AccuracyMeters;
	private String type;

	/**
	 * this Constractor include all the information foreach element.
	 * all the elements that the constartor get he is String type cuse we read it from csv file.
	 * 
	 * @param MAC
	 * @param SSID
	 * @param AuthMode
	 * @param FirstSeen
	 * @param Channel
	 * @param RSSI
	 * @param Lat
	 * @param Lon
	 * @param AltitudeMeters
	 * @param AccuracyMeters
	 * @param type
	 */
	public myGIS_element(String MAC , String SSID,String AuthMode,String Channel,
			String RSSI,String Lat,String Lon , String  AltitudeMeters,String AccuracyMeters, String type) {
        md=new MetaData();
		setMAC(MAC); // String 
		setSSID(SSID); //String 
		setAuthMode(AuthMode); //String
		setType(type);; // String 
		setp(Lat,Lon,AltitudeMeters);
		setChannel(Channel); // int
		setRSSI(RSSI); // int
		setAccuracyMeters(AccuracyMeters);		
		
		
	}
	/// Getters ////

	public String getMAC() {
		return MAC;
	}

	public String getSSID() {
		return SSID;
	}

	public String getAuthMode() {
		return AuthMode;
	}


	public double getAltitudeMeters() {
		return AltitudeMeters;
	}
	public double getLat() {
		return Lat;
	}
	public double getLon() {
		return Lon;
	}
	public double getAccuracyMeters() {
		return AccuracyMeters;
	}
	public int getChannel() {
		return Channel;
	}

	public int getRSSI() {
		return RSSI;
	}
	public String getType() {
		return type;
	}


	/// end Getters //

	/// Setters ///

	public void setChannel(String Channel) {
		int theChannel = Integer.parseInt(Channel);
		this.Channel = theChannel;
	}
	public void setRSSI(String RSSI) {
		int theRSSI = Integer.parseInt(RSSI);
		this.RSSI = theRSSI;
	}
	public void setAccuracyMeters(String AccuracyMeters) {

		double theAccuracyMeters = Double.parseDouble(AccuracyMeters);
		this.AccuracyMeters =(int) theAccuracyMeters;
	}

	public Point3D setp(String lat, String lon , String AltitudeMeters) {
		p = new Point3D(lat, lon, AltitudeMeters);
		return p;
	}	

	public void setType(String type) {
		this.type = type;
	}

	public void setAuthMode(String AuthMode) {
		this.AuthMode = AuthMode;
	}
	public void setSSID(String name) {
		this.SSID = name;
	}
	public void setMAC(String MAC) {
		this.MAC = MAC;
	}


	/// end Setters ///


	@Override
	public Geom_element getGeom() {
		// TODO Auto-generated method stub
		return this.p;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return this.md;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
		MyCoords m = new MyCoords();
		Point3D ans = m.add(this.p, vec);
		p = new Point3D(ans.x(),ans.y(),ans.z());

	}

	/**
	 * Create a String of the element and adiing the meta data information.
	 * @return the element
	 */
	public String toString() {

		return ""+MAC +"," +SSID+","+AuthMode+","+Channel+","+RSSI+","+p.toString()+","+AccuracyMeters+","+type+","+getData().toString();
	

	}



}
