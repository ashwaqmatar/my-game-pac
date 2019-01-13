package GIS;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Coords.Map;
import Geom.Point3D;
import Algorithm.ShortestPathAlgo;
import Robot.Play;

/**
 * This class represents the function that manages the games (pacmans, fruits ..)
 * It has many features.
 *
 */
public class game {
	/**set  variables*/
	public player player;
	public  ArrayList<Packman> Packmanarr = new ArrayList<>();
	public  ArrayList<Fruit> Fruits_arr = new ArrayList<>();
	public  ArrayList<Ghost> Ghostarr = new ArrayList<>();
	public  ArrayList<BOX> BOX_arr = new ArrayList<>();
	public Play play;



	public 	String file_directory="";
	public Map map = new Map();
	/**
	 * Constractor
	 * @param p
	 * @param f
	 */
	public game(ArrayList<Packman> p , ArrayList<Fruit> F, ArrayList<Ghost> G,ArrayList<BOX> B) {
		this.Packmanarr = p;
		this.Fruits_arr = F;
		this.BOX_arr = B;
		this.Ghostarr = G;


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
	public game(Play play) throws IOException {
		this.play = play;
		makeGame(play.getBoard());
	}
	


	public  void save2Csv() throws FileNotFoundException {
		StringBuilder snb = new StringBuilder();
		PrintWriter pw = new PrintWriter(new File(getdirectory()+".csv"));
		/**set the data in arr*/
		String[] HEADER = {"Type","id"	,"Lat"	,"Lon"	,"Alt"	,"Speed/Weight"	,"Radius"};
		for (int i = 0; i < HEADER.length; i++) {

			snb.append(HEADER[i]);
			snb.append(",");	
		}

		snb.append(this.Fruits_arr.size());
		snb.append(',');
		snb.append(this.Packmanarr.size());

		snb.append(',');
		snb.append(this.BOX_arr.size());
		snb.append('\n');

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

public void makeGame(ArrayList<String > board) throws IOException{		
		ArrayList<String > Board =board;
		Fruits_arr=new  ArrayList<>();
		Packmanarr=new ArrayList<>();
		this.BOX_arr=new ArrayList<>();
		Ghostarr=new ArrayList<>();

		for (int i = 1; i < Board.size(); i++) {

			String line =Board.get(i);
			String[] RoW = line.split(",");
			// creat the new  player wih argomints (p, speed, radius)  
			//first convert the coord (P) to pixel
			if(RoW[0].equals("M")) {
				player = new player(map.coord2pixel(new Point3D(RoW[2],RoW[3],RoW[4])), Double.parseDouble(RoW[5]), Double.parseDouble(RoW[6]));
			}
			// creat the new  packman wih argomints p, speed,radius  
			//first convert the coord (P) to pixel
			if(RoW[0].equals("P")) {
				Packmanarr.add(new Packman( map.coord2pixel(new Point3D(RoW[2],RoW[3],RoW[4])), Double.parseDouble(RoW[5]), Double.parseDouble(RoW[6])));	
			}
			// creat the new  Fruit wih argomints p, Weight
			//first convert the coord (P) to pixel
			if(RoW[0].equals("F")) {
				Fruits_arr.add(new Fruit(map.coord2pixel(new Point3D(RoW[2],RoW[3],RoW[4])),  Integer.parseInt(RoW[5])));
			}
			// creat the new  Fruit wih argomints p1, p2 (set the coordenata for the box )
			//first convert the coord (P) to pixel
			if(RoW[0].equals("B")) {
				BOX_arr.add(new BOX (map.coord2pixel(new Point3D(RoW[2],RoW[3],RoW[4])),map.coord2pixel(new Point3D(RoW[5],RoW[6],RoW[7]))));

			}// creat the new  Ghost wih argomints (p, speed, radius)
			//first convert the coord (P) to pixel
			if(RoW[0].equals("G")) {
				Ghostarr.add(new Ghost(map.coord2pixel(new Point3D(RoW[2],RoW[3],RoW[4])),Double.parseDouble(RoW[5]) , Double.parseDouble(RoW[6])));	
			}
		}
	}


}
