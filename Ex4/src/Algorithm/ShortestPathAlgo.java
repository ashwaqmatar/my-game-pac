package Algorithm;

import java.util.ArrayList;
import java.util.Comparator;

import Coords.Map;
import GIS.Fruit;
import GIS.game;
import GIS.Packman;
import GIS.Path;
import GIS.path;
import GIS.player;
import Geom.Point3D;

/**
 * This class manages our algorithms that know how to find the best way for my Packman
 * There are several different methods depending on the quantity of packmans and the speed.
 * Example Algo Details: https://fr.wikipedia.org/wiki/Algorithme_glouton
 */
public class ShortestPathAlgo {

	private ArrayList<Fruit> fruits = new ArrayList<>(); // Arraylist of fruit
	private ArrayList<Packman> Packmans = new ArrayList<>();//Arraylist of Packman 
	private Map themap = new Map();
	private player player = new player(new Point3D(0,0,0),1,1);



	/**
	 * Contractor of ShortestPathAlgo Who receives Game Object
	 * @param theGame Object Game receiv 
	 */
	public ShortestPathAlgo() {
	}

	public ShortestPathAlgo(game theGame) {	


		ArrayList<Fruit> clone = new ArrayList<Fruit>(theGame.Fruits_arr.size());for (Fruit item : theGame.Fruits_arr) clone.add(item);
		this.fruits = clone;	//Create a new fruit for not to overwrite Game data later
		this.Packmans = theGame.Packmanarr;
		this.player = theGame.player;

	}

	/**
	 * Algorithm that rolls for only a Pac-Man.
	 * Detail: www.101computing.net/pacman-ghost-algorith
	 * @param oriPackman Receiv Pac_man 
	 * @return Path with the arraylist sort according to my best Path
	 * 
	 */
	public path algoSinglePackman(Packman oriPackman){
		ArrayList<Fruit> Tempfruits = this.fruits;
		Tempfruits=oriPackman.getpath().getCpath();

		Packman tempPackman = new Packman(oriPackman.getP(),oriPackman.getSpeed(),oriPackman.getred());
		path Dis = distanceAlgo(tempPackman, Tempfruits);
		path p = FastOnePack(tempPackman, Tempfruits);

		double timeFor1 = Dis.CalPathTime(tempPackman);

		double timeFor2 = p.CalPathTime(tempPackman);




		if(timeFor1 < timeFor2) {
			Dis.setTime_path(timeFor1);
			return Dis;
		}else {
			p.setTime_path(timeFor2);
			return p;

		}



	}
	/**
	 * Help function that calculates the closest distance between a packman and fruits
	 * @param packman Receiv Packman Only
	 * @param myFurits Arraylist in Fruits 
	 * @return the Path the most suitable
	 */
	public path FastOnePack (Packman packman, ArrayList<Fruit> myFurits) {

		if (myFurits.isEmpty()) {
			return packman.getpath();
		}


		Fruit theCloserTemp = TheCloserFurit(packman,myFurits); // the closer furit to packman 
		packman.getpath().getCpath().add(theCloserTemp);
		packman.setPackLocation(theCloserTemp.getfruit());
		myFurits.remove(Index(theCloserTemp, myFurits));

		return FastOnePack(packman,myFurits);


	}
	/**
	 * Method that calculates the distance between a pacman and each fruit
	 * @param packman a single Packman on which we will calculate the distance
	 * @param myFurits Fruit list
	 * @return Path with distance add
	 */
	public path distanceAlgo(Packman packman, ArrayList<Fruit> myFurits) {

		ArrayList<Double> SortPathByDis = new ArrayList<>();
		path ans = new path();

		for (int i = 0; i < myFurits.size(); i++) {
			double temp = themap.destpixels(packman.getP(), myFurits.get(i).getfruit());
			SortPathByDis.add(temp);
		}
		double temp;
		for(double distance : SortPathByDis) {


			for (int j = 0; j < myFurits.size(); j++) {
				temp = themap.destpixels(packman.getP(), myFurits.get(j).getfruit());
				if(temp == distance) {
					ans.getCpath().add(myFurits.get(j));
					break;
				}


			}
		}

		return ans;
	}

	/**
	 * Algorithm that calculates the course of several pacman and who know how to return:
	 * what will be the course of each pacman to have the best time 
	 * Fonction Help
	 * 
	 * @return Pacman's ArrayList to which will be added to each Pacman a path of the course he will perform
	 * 
	 */

	public ArrayList<Packman> algoMultiPackmans (){
		path myPath = new path();
		ArrayList<Fruit> myFurits = this.fruits;
		ArrayList<Packman> ans = new ArrayList<>();
		ArrayList<Packman> myPackmens = this.Packmans;
		//		ArrayList<Packman> tempArray = new ArrayList<>();


		for (int i = 0; i < myPackmens.size(); i++) {
			Packman p = new Packman(myPackmens.get(i).getP(),myPackmens.get(i).getSpeed(), myPackmens.get(i).getred());
			ans.add(p);
		}
		myPackmens =multiAlgo(myPackmens,myFurits);

		for (int i = 0; i < myPackmens.size(); i++) {
			myPackmens.get(i).setPackLocation(ans.get(i).getP());
		}


		for (Packman packman : myPackmens) {
			path PackmanPath = new path();
			path p = new path();
			PackmanPath = packman.getpath();
		}

		double longestTime = myPath.CalPathTime(myPackmens.get(0));
		double temp = 0;

		for (int i = 1; i < myPackmens.size(); i++) {
			temp = myPath.CalPathTime(myPackmens.get(i));
			if (temp > longestTime) {
				longestTime = temp;
			}
		}



		System.out.println("the Total time is: "+longestTime);
		return myPackmens;

	}






	/**
	 * Algorithm that calculates the course of several pacman and who know how to return:
	 * what will be the course of each pacman to have the best time 
	 * @param myPackmans Get a Pacmans ArrayList
	 * @param myFurits Get a Fruits ArrayList
	 * @return Pacman's ArrayList to which will be added to each Pacman a path of the course he will perform
	 */

	private ArrayList<Packman> multiAlgo (ArrayList<Packman> myPackmans , ArrayList<Fruit>myFurits) {
		path myPath = new path();
		if(myFurits.isEmpty()) {
			return myPackmans;
		}


		Packman thePackman = myPackmans.get(0);
		Fruit theCloserFurit = TheCloserFurit(myPackmans.get(0),myFurits);
		double FastTime = myPath.Time2Points(myPackmans.get(0),theCloserFurit);

		Packman tempPack;
		Fruit tempFruit;
		double tempTime = 0;

		for (int i = 0; i < myPackmans.size(); i++) {

			tempPack = myPackmans.get(i);
			tempFruit = TheCloserFurit(myPackmans.get(i),myFurits);
			tempTime = myPath.Time2Points(myPackmans.get(i),tempFruit);

			if (tempTime < FastTime) {
				thePackman = tempPack;
				FastTime = tempTime;
				theCloserFurit = tempFruit;
			}

		}
		thePackman.getpath().getCpath().add(theCloserFurit);
		thePackman.setPackLocation(theCloserFurit.getfruit());
		myFurits.remove(Index(theCloserFurit, myFurits));

		return multiAlgo(myPackmans ,myFurits);

	}

	/**
	 * method that calculates  the fastest point between a pac man and the path
	 * @param packman Receiv Pacman on which we will look for the fastest time
	 * @param myFurits ArrayList of Fruits on which we seek the fastest fruit of the Pac-Man
	 * @return The double time of the fastest route
	 */

	double FastSpeedFriut(Packman packman ,ArrayList<Fruit> myFurits ) {
		path p = new path();
		double fastTime = p.Time2Points(packman, myFurits.get(0));
		double tempTime = 0;

		for (int i = 1; i < myFurits.size(); i++) {

			tempTime = p.Time2Points(packman, myFurits.get(i));

			if(tempTime <fastTime) {
				fastTime = tempTime;
			}

		}
		return fastTime;

	}
	/**
	 * Return the most closers furit to the packman
	 * @param packman Receiv Pacman on which we will look for the fastest Fruit
	 * @param myFurits ARraylist Of Fruit on wiche we seek the most closers PAcman
	 * @return a Fruit the most closers of PAcman
	 */
	public Fruit TheCloserFurit(Packman packman,ArrayList<Fruit> myFurits) {
		path p = new path();

		double FastTime = p.Time2Points(packman,myFurits.get(0));
		Fruit theMostCloser = myFurits.get(0);
		double tempTime = 0;

		for (int i = 1; i < myFurits.size(); i++) {
			tempTime = p.Time2Points(packman, myFurits.get(i));

			if(tempTime < FastTime)	{
				FastTime = tempTime;
				theMostCloser = myFurits.get(i);
			}	
		}

		return theMostCloser;
	}

	/**
	 * Calculate the index of the furit.
	 * @param fruit Receiv Fruit of ArrayList
	 * @param myFurits ArrayList contain The "fruit"
	 * @return Index of Fruit (if no found return -1)
	 */
	public int Index(Fruit furit , ArrayList<Fruit> myFurits) {

		for (int i = 0; i < myFurits.size(); i++) {

			if((myFurits.get(i)==furit)) {
				return i;
			}

		}
		return -1;
	}



}