package GIS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import Geom.Point3D;

public  class MetaData implements Meta_data {


private long data;
	private String color;	
	Point3D Orientation;


	public MetaData() {	
	this.color=getColor();
	this.data=getUTC();
	this.Orientation=get_Orientation();
	}
	/**
	 * @param data the data to set
	 */
	public void setData(long data) {
		this.data = data;
	}


	/**
	 * returns the Universal Time Clock associated with this data;
	 */
	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		Long data = new Date().getTime();
		return data;	}
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return Orientation ;
	}
	public String getColor() {
		return this.color;
	}

	public void setColor(String Color) {
		this.color = Color;
	}
	/**
	 * return a String representing this data
	 */
	@Override
	public String toString() {
		return ""+data+ "," + Orientation + "," + color+" ";

	}
	@Override
	public void setUTC(String DateAndTime) throws ParseException {
		// TODO Auto-generated method stub
		long millis  = DateToMilis(DateAndTime);
		data = millis;
	}
	
	private long DateToMilis(String dateAndTime) throws ParseException
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date date = format.parse(dateAndTime);
		long millis = date.getTime();
		return millis;
	}

	

}
