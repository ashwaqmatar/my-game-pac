package GIS;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Coords.Map;
import File_format.CSVReader;
import Geom.Point3D;
import Algorithm.ShortestPathAlgo;
/**
 * This class represents the function that manages the games (pacmans, fruits ..)
 * It has many features.
 *
 */
public class game {

	public  ArrayList<Packman> Packmanarr = new ArrayList<>();
	public  ArrayList<Fruit> Fruits_arr = new ArrayList<>();
	public 	String file_directory;
	public Map map = new Map();
/**
 * Constractor
 * @param p
 * @param f
 */
	public game(ArrayList<Packman> p , ArrayList<Fruit> f) {
		this.Packmanarr = p;
		this.Fruits_arr = f;

	}
/**
 * setters and getters
 * @param file_directory
 */

	public void setfile_dir(String file_directory) {
		this.file_directory = file_directory;
	}
	public String getdirectory() {
		return this.file_directory;
	}
	
	
	/************************************/
	
	
	
	public  void save2Csv() throws FileNotFoundException {
		StringBuilder snb = new StringBuilder();
		PrintWriter pw = new PrintWriter(new File(getdirectory()+".csv"));

		String[] headers = {"Type","id"	,"Lat"	,"Lon"	,"Alt"	,"Speed/Weight"	,"Radius"};
		for (int i = 0; i < headers.length; i++) {

			snb.append(headers[i]);
			snb.append(",");	
		}

		snb.append(this.Fruits_arr.size());
		snb.append('\n');
		snb.append(this.Packmanarr.size());
		snb.append(',');

		for (int i = 0; i < Packmanarr.size(); i++) {
			Packmanarr.get(i).packLocation = map.pixel2coord(Packmanarr.get(i).getP().x(), Packmanarr.get(i).getP().y());
		}
		for (int i = 0; i < Fruits_arr.size(); i++) {
			Fruits_arr.get(i).fruit = map.pixel2coord(Fruits_arr.get(i).getfruit().x(), Fruits_arr.get(i).getfruit().y());

		}

		for (int i = 0; i < this.Packmanarr.size(); i++) {
			snb.append("P");
			snb.append(',');
			snb.append(i);
			snb.append(',');
			snb.append(Packmanarr.get(i).packLocation.x());
			snb.append(',');
			snb.append(Packmanarr.get(i).packLocation.y());
			snb.append(',');
			snb.append(Packmanarr.get(i).packLocation.z());
			snb.append(',');
			snb.append(this.Packmanarr.get(i).getSpeed());
			snb.append(',');
			snb.append(this.Packmanarr.get(i).getred());
			snb.append('\n');
		}
		for (int i = 0; i < Fruits_arr.size(); i++) {
			snb.append("F");
			snb.append(',');
			snb.append(i);
			snb.append(',');
			snb.append(Fruits_arr.get(i).fruit.x());
			snb.append(',');
			snb.append(Fruits_arr.get(i).fruit.y());
			snb.append(',');
			snb.append(Fruits_arr.get(i).fruit.z());
			snb.append(',');	
			snb.append(this.Fruits_arr.get(i).getWeight());
			snb.append('\n');

		}
		snb.append('\n');

		pw.write(snb.toString());
		pw.close();

	}


	public void Csvread() throws IOException{		
		List<String[]> SV = CSVReader.readFile2(getdirectory());

		for (int i = 1; i < SV.size(); i++) {
			String[] RoW = SV.get(i);

			if(RoW[0].equals("P")) {
				Packmanarr.add(new Packman( map.coord2pixel(new Point3D(RoW[2],RoW[3],RoW[4])), Double.parseDouble(RoW[5]), Double.parseDouble(RoW[6])));	
			}
			if(RoW[0].equals("F")) {
				Fruits_arr.add(new Fruit(map.coord2pixel(new Point3D(RoW[2],RoW[3],RoW[4])),  Integer.parseInt(RoW[5])));
			}
		}


	}



	public void save_to_kml() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(getdirectory()+".kml"));

		
		
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
						"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document>\r\n<name> Points with TimeStamps</name>\r\n <Style id=\"red\">\r\n" + 
						"<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"Packman\"><IconStyle><Icon><href>http://www.iconhot.com/icon/png/quiet/256/pac-man.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"Fruit\"><IconStyle><Icon><href>http://www.stickpng.com/assets/images/580b57fcd9996e24bc43c316.png</href></Icon></IconStyle></Style>\r\n" + 
						"\r\n" + 
						"    <Style id=\"check-hide-children\">\r\n" + 
						"      <ListStyle>\r\n" + 
						"        <listItemType>checkHideChildren</listItemType>\r\n" + 
						"      </ListStyle>\r\n" + 
						"    </Style>\r\n" + 
						"    <styleUrl>#check-hide-children</styleUrl>"+
						"\r\n"+"<Folder><name>GAME PACKMAN</name>\n\n";

		content.add(kmlstart);
		String[] nameData = {"Type","id","Lat","Lon","Speed/Weight"	,"Radius"};

		String kmlend = "</Folder>\n" + 
				"</Document>\n</kml>";

		ArrayList<Packman> myPackmens = new ArrayList<>();
        ShortestPathAlgo algo = new ShortestPathAlgo(this);
		

		if(Packmanarr.size() == 1) {
			myPackmens =Packmanarr;
			path p = algo.algoSinglePackman(myPackmens.get(0));
			myPackmens.get(0).getpath().setpath1(p.getCpath());
			myPackmens.get(0).getpath().setTime_path(p.getTime_path());
		}else {	myPackmens = algo.algoMultiPackmans();}



		for (int i = 0; i < myPackmens.size(); i++) {
			myPackmens.get(i).packLocation = map.pixel2coord(myPackmens.get(i).getP().x(), myPackmens.get(i).getP().y());

			for (int j = 0; j < myPackmens.get(i).getpath().getCpath().size(); j++) {
				myPackmens.get(i).getpath().getCpath().get(j).fruit =
						map.pixel2coord( myPackmens.get(i).getpath().getCpath().get(j).getfruit().x(),  myPackmens.get(i).getpath().getCpath().get(j).getfruit().y());

			}
		}

		LocalDateTime now_start=LocalDateTime.now();
		LocalDateTime temp_start_official=now_start;
		LocalDateTime now_start_end=temp_start_official.plusHours(6);
		LocalDateTime temp_start=now_start;


		int j=(-1); 

		for (Packman packman_for : myPackmens)
		{
			j++;

			now_start=	temp_start_official;

			String kmlelement ="<Placemark>\n" +
					"<name><![CDATA[ PACKMAN START "+j+"]]></name>\n" +
					"<description>"+
					"<![CDATA["
					+nameData[0]+": <b> PACKMAN  </b><br/>"
					+nameData[1]+": <b> PACKMAN Start Number"+j+" </b><br/>"
					+nameData[2]+": <b>"+packman_for.packLocation.x()+" </b><br/>" 
					+nameData[3]+": <b>"+packman_for.packLocation.y()+" </b><br/>" 
					+nameData[4]+": <b>"+packman_for.getSpeed()+" </b><br/>" 
					+nameData[5]+": <b>"+packman_for.getred()+" </b><br/>" // altito to meter


					+"]]></description>\n" +
					"<TimeStamp>\r\n" + 
					"        <when>"+now_start+"</when>\r\n" + 
					"      </TimeStamp>"+
					"<styleUrl>#Packman</styleUrl>"+
					"<Point>\n" +
					"<coordinates>"+packman_for.packLocation.y()+","+packman_for.packLocation.x()+"</coordinates>" +
					"</Point>\n" +
					"</Placemark>\n";


			content.add(kmlelement);


			if(packman_for.getpath().getCpath().size()==0)
			{

				String kmlelement2 ="<Placemark>\n" +
						"<name><![CDATA[ PACKMAN Without PATH "+j +"]]></name>\n" +
						"<description>"+
						"<![CDATA["
						+nameData[0]+": <b> PACKMAN  </b><br/>"
						+nameData[1]+": <b> PACKMAN  Without PATH Number"+j+" </b><br/>"
						+nameData[2]+": <b>"+packman_for.packLocation.x()+" </b><br/>" 
						+nameData[3]+": <b>"+packman_for.packLocation.y()+" </b><br/>" 
						+nameData[4]+": <b>"+packman_for.getSpeed()+" </b><br/>" 
						+nameData[5]+": <b>"+Packmanarr.get(j).getred()+" </b><br/>" // altito to meter



						+"]]></description>\n" +
						"<TimeStamp>\r\n" + 
						"        <when>"+now_start_end+"</when>\r\n" + 
						"      </TimeStamp>"+
						"<styleUrl>#Packman</styleUrl>"+
						"<Point>\n" +
						"<coordinates>"+packman_for.packLocation.y()+","+packman_for.packLocation.x()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";


				content.add(kmlelement2);

			}
				
				for (int i = 0; i < packman_for.getpath().getCpath().size(); i++) {

					now_start=now_start.plusMinutes(5);
					temp_start=now_start.plusMinutes(10);




					kmlelement ="<Placemark>\n" +
							"<name><![CDATA[ FRUIT "+(i)+",Pac:"+j+"]]></name>\n <description>"+
							"<![CDATA["
							+nameData[0]+": <b> FRUIT  </b><br/>"
							+nameData[1]+": <b> FRUIT Number :"+i+",Pac:"+j+" </b><br/>"
							+nameData[2]+": <b>"+packman_for.getpath().getCpath().get(i).getfruit().x()+" </b><br/>" 
							+nameData[3]+": <b>"+packman_for.getpath().getCpath().get(i).getfruit().y()+" </b><br/>" 
							+nameData[4]+": <b>"+packman_for.getpath().getCpath().get(i).getWeight()+" </b><br/>" 


						+"]]></description>\n" +
						"<TimeStamp>\r\n" + 
						"        <when>"+now_start+"</when>\r\n" + 
						"      </TimeStamp>"+
						"<styleUrl>#Fruit</styleUrl>"+
						"<Point>\n" +
						"<coordinates>"+packman_for.getpath().getCpath().get(i).getfruit().y()+","
						+packman_for.getpath().getCpath().get(i).getfruit().x()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";



					content.add(kmlelement);			
					
					
					
					
					if(i+1==packman_for.getpath().getCpath().size()) temp_start=now_start_end;

					String kmlelement2 ="<Placemark>\n" +
							"<name><![CDATA[ PACKMAN Moving "+j+", "+i+"]]></name>\n" +
							"<description>"+
							"<![CDATA["
							+nameData[0]+": <b> PACKMAN  </b><br/>"
							+nameData[1]+": <b> PACKMAN Moving "+j+", "+i+"</b><br/>"
							+nameData[2]+": <b>"+packman_for.packLocation.x()+" </b><br/>" 
							+nameData[3]+": <b>"+packman_for.packLocation.y()+" </b><br/>" 
							+nameData[4]+": <b>"+packman_for.getSpeed()+" </b><br/>" 
							+nameData[5]+": <b>"+Packmanarr.get(j).getred()+" </b><br/>" // altito to meter



							+"]]></description>\n" +
							"<TimeStamp>\r\n" + 
							"        <when>"+temp_start+"</when>\r\n" + 
							"      </TimeStamp>"+
							"<styleUrl>#Packman</styleUrl>"+
							"<Point>\n" +
							"<coordinates>"+packman_for.getpath().getCpath().get(i).getfruit().y()+","
							+packman_for.getpath().getCpath().get(i).getfruit().x()+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";


					content.add(kmlelement2);

				}


		}

		content.add(kmlend);
		pw.write(String.join("\n", content));
		System.out.println("Operation Complete");
		pw.close();
	} 

}
