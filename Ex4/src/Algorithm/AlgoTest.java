package Algorithm;

import java.util.ArrayList;

import Coords.Map;
import Coords.MyCoords;
import GIS.Fruit;
import GIS.game;
import GIS.Ghost;
import GIS.Packman;
import GIS.player;
import GIS.BOX;
import Geom.Point3D;


public class AlgoTest {
	private ArrayList<Fruit> fruits = new ArrayList<>(); // Arraylist of fruit
	private ArrayList<Packman> Packmans = new ArrayList<>();//Arraylist of Packman 
	private ArrayList<BOX> boxs = new ArrayList<>();//Arraylist of boxs 
	private ArrayList<Ghost> ghosts = new ArrayList<>();//Arraylist of ghosts 
	private ArrayList<Point3D> ans;
	private ArrayList<BOX> newBoxs;
	private MyCoords m;
	private player player = new player(new Point3D(0,0,0),1,1);
	private Map theMap = new Map();// create a Map object
	int verif=0;


	/**
	 * Contractor of ShortestPathAlgo Who receives Game Object
	 * @param theGame Object Game receiv 
	 */
	public AlgoTest(game theGame) {	

		ArrayList<Fruit> clone = new ArrayList<Fruit>(theGame.Fruits_arr.size());  for (Fruit item : theGame.Fruits_arr) clone.add(item);
		this.fruits = clone;	//Create a new fruit for not to overwrite Game data later
		this.Packmans = theGame.Packmanarr;
		this.player = theGame.player;
		this.boxs = theGame.BOX_arr;
		this.ghosts = theGame.Ghostarr;
		this.m = new MyCoords();
		this.ans = new ArrayList<>();
		this.ans = addingTo1List(this.Packmans,this.fruits);
		this.newBoxs = boxList(boxs);

	}


	public ArrayList<BOX> getBoxs() {
		return boxs;
	}


	public void setBoxs(ArrayList<BOX> boxs) {
		this.boxs = boxs;
	}


	public double update_Game(player p2, double dir) { 

		Point3D theClose = TheCloserFurit(p2,ans);

		if(CheckTheWayToFruit(p2.get_player_Location(),theClose)==false) {
			dir = (m.myDir(theClose, theClose));
			return dir;
		}else {
			Point3D connverTogo = theCloseConner(p2.get_player_Location(),theClose);
			double rotate = checkBox(p2.get_player_Location(),dir,theClose);
			System.out.println("rotate is:"+rotate);
			if(rotate !=5) {
				return rotate;
			}else {
				dir = m.myDir(connverTogo, p2.get_player_Location());
				System.out.println("the dir is: "+dir);
				return dir;	

			}
		}
	}


	private ArrayList<BOX> boxList(ArrayList<BOX> boxs) {
		ArrayList<BOX> ansBoxs = new ArrayList<>();

		for (int i = 0; i < boxs.size(); i++) {

			BOX bans = boxs.get(i).addToConver();

			Point3D boxGPS_0 = theMap.pixel2coord(bans.getP0().x(), bans.getP0().y());
			Point3D boxGPS_1 = theMap.pixel2coord(bans.getP1().x(), bans.getP1().y());
			BOX b = new BOX(boxGPS_0,boxGPS_1);


			ansBoxs.add(b);

		}
		return ansBoxs;



	}

	private ArrayList<Point3D> addingTo1List(ArrayList<Packman> Packmans,ArrayList<Fruit> fruits) {


		ArrayList<Point3D> ans = new ArrayList<>();


		for (int i = 0; i < fruits.size(); i++) {
			Point3D fGPS = theMap.pixel2coord(fruits.get(i).getfruit().x(), fruits.get(i).getfruit().y());
			ans.add(fGPS);
		}


		return ans;
	}


	public Point3D TheCloserFurit(player M,ArrayList<Point3D> fruits_packs) {



		double FastTime = CalTime2Points(M,fruits_packs.get(0));
		Point3D theMostCloser = fruits_packs.get(0);
		double tempTime = 0;

		for (int i = 1; i < fruits_packs.size(); i++) {
			tempTime = CalTime2Points(M, fruits_packs.get(i));

			if(tempTime < FastTime)	{
				FastTime = tempTime;
				theMostCloser = fruits_packs.get(i);
			}	
		}

		return theMostCloser;
	}


	public double CalTime2Points(player M , Point3D point) {
		if (m.distance3d(M.get_player_Location(), point) < M.getred()) {

			return 0;
		}
		else {	
			return (m.distance3d(M.get_player_Location(), point)-M.getred())/M.getSpeed();

		}
	}


	public double checkBox(Point3D m, double dir, Point3D theClose) {
		for (int i = 0; i < newBoxs.size(); i++) {
			if(newBoxs.get(i).inside(m, theClose) == 1) {
				dir = 90;
				return dir;
			}
			else if(newBoxs.get(i).inside(m, theClose) == 2) {
				dir = 0;
				return dir;
			}
			else if(newBoxs.get(i).inside(m, theClose) == 3) {
				dir = 270;
				return dir;
			}
			else if(newBoxs.get(i).inside(m, theClose) == 4) {
				dir = 180;
				return dir;
			}

		}
		return 5;

	}

	public Point3D theCloseConner(Point3D player, Point3D theClose) {


		double closeConner_min_Final=0;
		double theConner = 0;
		Point3D ans  = new Point3D(player);
		Point3D theConnerAns = new Point3D(newBoxs.get(0).getP0());
		Point3D temp  = new Point3D(player);


		for (int i = 0; i < newBoxs.size(); i++) {

			while(m.distance3d(temp, theClose) >= 1) {
				
				if(newBoxs.get(i).checkit1(temp,theClose)==true){
					
				
					theConner =m.distance3d(theConnerAns,player);
					closeConner_min_Final = theConner;

					theConner = m.distance3d(newBoxs.get(i).getP1(),player); 
					if(theConner < closeConner_min_Final){
						closeConner_min_Final = theConner;  
						theConnerAns = newBoxs.get(i).getP1();
					}
					theConner = m.distance3d(newBoxs.get(i).getP2(),player);
					if(theConner < closeConner_min_Final){
						closeConner_min_Final = theConner; 
						theConnerAns = newBoxs.get(i).getP2();
					}
					theConner = m.distance3d(newBoxs.get(i).getP3(),player);
					if(theConner < closeConner_min_Final){
						closeConner_min_Final = theConner; 
						theConnerAns = newBoxs.get(i).getP3();
					}

				}
				ans = theNextPoint(temp, theClose);
				temp = ans;
			}


		}
		return theConnerAns;


	}



	public boolean CheckTheWayToFruit (Point3D p, Point3D theClose) {

		for (int i = 0; i < newBoxs.size(); i++) {
			if(newBoxs.get(i).checkit1(p, theClose)==true) {
				return true;
			}
		}
		return false	;

	}


	public  Point3D theNextPoint(Point3D p1 , Point3D f1) {

		double dt = 10000; // the time from (x1,y1) to (x2,y2) example: 300.

		double Vx = p1.x()/dt;
		double Vy = p1.y()/dt;

		double xt = p1.x()+Vx*(f1.x()-p1.x());
		double yt= p1.y()+Vy*(f1.y()-p1.y());
		return new Point3D(xt,yt);

	}





}









