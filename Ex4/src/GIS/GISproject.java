package GIS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GISproject implements GIS_project{
	private MetaData data;
	public GISproject() {//MetaData data
	super();
	this.data=data;
	}
	Set<GIS_layer> ST=new HashSet <GIS_layer>(); 

	
	/**
	 * This functions take all the layer that he got and made a project file that include all the layers and the elements made a kml file.
	 * @param output
	 */
	public void to_KML(String output) {
		String[]name= {"MAC", "SSID", "AuthMode", "Channel", "RSSI", "Lat", "Lon", "AltitudeMeters", "AccuracyMeters", "type","UTC","Orientation ","Color"};

		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
						"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\">\r\n" + 
						"<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"<Folder><name>Wifi Networks</name>\n\n";
		content.add(kmlstart);
		String kmlend = "</Folder>\n" + 
				"</Document>\n</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(GIS_layer current_layer: this){


				Iterator<GIS_element> myelm = current_layer.iterator();

				while(myelm.hasNext()) {

					String s = myelm.next().toString();
					
					String[] data=s.split(",");

					String kmlelement ="<Placemark>\n" +
							"<name><![CDATA["+data[1]+"]]></name>\n" +
							"<description>"+
							"<![CDATA[B"
							+name[0]+": <b>"+data[0]+" </b><br/>"
							+name[2]+": <b>"+data[2]+" </b><br/>"
							+name[3]+": <b>"+data[3]+" </b><br/>"
							+name[4]+": <b>"+data[4]+" </b><br/>" // rssi
							+name[5]+": <b>"+data[5]+" </b><br/>" // latitauo
							+name[6]+": <b>"+data[6]+" </b><br/>" // logntiue
							+name[7]+": <b>"+data[7]+" </b><br/>" // altito to meter
							+name[8]+": <b>"+data[8]+" </b><br/>" //accaryto meter
							+name[9]+": <b>"+data[9]+" </b><br/>" //type wifi
							+name[10]+": <b>"+data[10]+" </b><br/>" //type wifi
							+name[11]+": <b>"+data[11]+" </b><br/>" //type wifi
							+name[12]+": <b>"+data[12]+" </b><br/>" //type wifi



							+"]]></description>\n" +"<styleUrl>#"+data[12]+"</styleUrl>"+
							"<Point>\n" +
							"<coordinates>"+data[6]+","+data[5]+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";

					content.add(kmlelement);

				}
			}

			content.add(kmlend);
			bw.write(String.join("\n", content));
			System.out.println("Operation Complete");
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean add(GIS_layer arg0) {
		// TODO Auto-generated method stub
		return ST.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> arg0) {
		// TODO Auto-generated method stub
		return ST.addAll(arg0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		ST.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return ST.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return ST.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return ST.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		// TODO Auto-generated method stub
		return ST.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return ST.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return ST.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return ST.retainAll(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return ST.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return ST.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return ST.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return  this.data;
	}

}
