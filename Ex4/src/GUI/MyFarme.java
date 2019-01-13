package GUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Coords.Map;
import Coords.MyCoords;
import GIS.Fruit;
import GIS.Game;
import GIS.Ghost;
import GIS.game;
import GIS.BOX;

import GIS.Packman;
import GIS.path;
import Geom.Point3D;
import Algorithm.AlgoTest;
import Algorithm.ShortestPathAlgo;
import Robot.Play;

/**
 *This class manages the graphical interface of pacman game.
 * the class is an implements of MouseListener is an extents of JFrame.
 * More: http://www.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
 *
 */
public class MyFarme extends JFrame implements MouseListener, KeyListener
{
	private static final long serialVersionUID = 1L;

	/***********************Seting the game Characters**************/

	public  ArrayList<Packman> Packman_arr = new ArrayList<>();
	public  ArrayList<Fruit> Fruits_arr = new ArrayList<>();
	public ArrayList<Ghost> Ghost_arr=new ArrayList<>();
	public ArrayList<BOX> BOX_arr=new ArrayList<>();
	private game mygame=new game(Packman_arr, Fruits_arr,Ghost_arr, BOX_arr);
	public ArrayList<Packman>ArrayTemp=new ArrayList<>();
	public Map theMap = new Map();
	path  theclosepackman ;
	public Play startgame  ;
	private MyCoords coord =new MyCoords();
	path TheCloserPackman;






	/******************************Set images******************/
	public  Graphics dbg;
	public BufferedImage myImage;
	public BufferedImage pack;
	public BufferedImage Fruit;
	public BufferedImage ghost;
	public BufferedImage player;
	public BufferedImage BOX;


	double radius = 1;
	int speed = 1;


	public boolean game_player  = false;
	private double dir;
	public boolean click  = false;
	Image image ;
	private int isGamer=0;
	public boolean Solo_game=false;

	MenuBar menuBarOption = new MenuBar();//menu Bar in the interface 



	game temp_run = new  game(Packman_arr, Fruits_arr,Ghost_arr, BOX_arr);
	public MyFarme() 
	{
		initGUI();		
		this.addMouseListener(this);
		this.addKeyListener(this);


	}


	private void initGUI() {


		try {
			myImage = ImageIO.read(new File(theMap.getDiractroymap()));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
		try {	
			pack = ImageIO.read(new File("Pictures&Icones/packnew.png")); 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
		try {	
			Fruit = ImageIO.read(new File("Pictures&Icones/furti.png")); 
		}
		catch (IOException e)
		{ 
			e.printStackTrace();
		}
		try {
			BOX = ImageIO.read(new File("Pictures&Icones/box.png"));
		} catch (IOException e) 
		{ 
			e.printStackTrace();
		}
		try {	
			ghost = ImageIO.read(new File("Pictures&Icones/ghost.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();	
		}
		try {
			player = ImageIO.read(new File("Pictures&Icones/player.png"));
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}

		Menu theMenu = new Menu("File"); 
		// Game insert \\
		Menu Add_Insert =new Menu ("Insert");
		Menu AddMenu = new Menu("Add"); 



		menuBarOption.add(theMenu);
		menuBarOption.add(AddMenu);
		menuBarOption.add(Add_Insert);


		// Game control 
		MenuItem commandMan  = new MenuItem(" Manual");
		MenuItem commandAuto = new MenuItem("Auto");
		MenuItem restart_item = new MenuItem("Rest");	
		MenuItem exit = new MenuItem("Exit");
		MenuItem Player_item = new MenuItem("Player ");	
		MenuItem Csv_read = new MenuItem("Csv");






		theMenu.add(commandMan);
		theMenu.add(commandAuto);
		theMenu.add(restart_item);
		theMenu.add(exit);

		AddMenu.add(Player_item);
		Add_Insert.add(Csv_read);



		this.setMenuBar(menuBarOption);




		/**
		 * Turn on the buttons
		 */

		commandMan.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				startgame.setIDs(208727354,205441884,313332736);
				if (game_player==true&& mygame.player != null)
				{
					startgame.getBoard();
					click = true;
					isGamer = 4;
					startgame.start();

					Thread thread = new Thread(){
						ArrayList<String> board_data = startgame.getBoard();


						public void run(){ 


							while(startgame.isRuning()){ 

								try {
									sleep(200);
								} 
								catch (InterruptedException e) 
								{	
									e.printStackTrace();	
								}

								startgame.rotate(dir);
								board_data = startgame.getBoard();
								String info = startgame.getStatistics();
								System.out.println(info);


								try {
									mygame.makeGame(board_data);
								} 
								catch (IOException e1) 
								{
									e1.printStackTrace();
								}
								repaint();
							}
						}
					};

					thread.start();

				}
				else 
					JOptionPane.showMessageDialog(null,"EROR: Choose Player to start te Game ");
			}
		});




		Player_item.addActionListener  (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				isGamer = 2;

			}
		});


		



		Csv_read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.data")));
				fileChooser.setDialogTitle("Select an Csv File");
				fileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","CSV");
				fileChooser.addChoosableFileFilter(filter);

				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile().getPath());

					
					startgame=new Play(fileChooser.getSelectedFile().getPath());
					try {
						mygame=new game(startgame);
					Solo_game=true ;
					

					} catch (IOException e2) {
						e2.printStackTrace();
					}
					isGamer=4;
					mygame.setfile_dir(fileChooser.getSelectedFile().getPath());
					repaint();


				}
			}
		});
	

		commandAuto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (game_player==true) {
					JOptionPane.showMessageDialog(null,"The player will be written to a new random point close to a Fruit ");
				}

				startgame.setIDs(208727354,205441884,313332736);
				click = false;
				System.out.println(mygame.Fruits_arr.get((int)(Math.random()*mygame.Fruits_arr.size())).getfruit().x());
				Point3D temp_locat=theMap.pixel2coord(mygame.Fruits_arr.get((int)(Math.random()*mygame.Fruits_arr.size())).getfruit().x(), mygame.Fruits_arr.get((int)(Math.random()*mygame.Fruits_arr.size())).getfruit().y());

				startgame.setInitLocation(temp_locat.x(),temp_locat.y());

				if(mygame.player != null) {
					isGamer = 4;
					startgame.getBoard();


					startgame.start();

					Thread thread = new Thread(){
						ArrayList<String> board_data = startgame.getBoard();


						public void run(){ 




							while(startgame.isRuning()){ 

								try {
									sleep(200);
								} 
								catch (InterruptedException e) 
								{
									e.printStackTrace();
								}

								startgame.rotate(dir);
								board_data = startgame.getBoard();
								String info = startgame.getStatistics();
								System.out.println(info);

								try {
									temp_run.makeGame(board_data);
									Point3D covertedfromPixel2 = theMap.pixel2coord(mygame.player.get_player_Location().x(), mygame.player.get_player_Location().y());
									Point3D covertedfromPixel3 = theMap.pixel2coord(temp_run.player.get_player_Location().x(), temp_run.player.get_player_Location().y());

									AlgoTest algo = new AlgoTest(temp_run);

									temp_run.player.set_player_Location(covertedfromPixel3);
									mygame.player.set_player_Location(covertedfromPixel2);



									double theDir = algo.update_Game(temp_run.player , dir);

									dir = theDir;

								} 
								catch (IOException e) {	
									e.printStackTrace();
								}


								try {
									mygame.makeGame(board_data);
								} catch (IOException e1) 
								{
									e1.printStackTrace();
								}
								repaint();						
							}
						}
					};
					thread.start();
				}
			}


		});

		restart_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		    	radius = 1;
				speed = 1;
				Packman_arr = new ArrayList<>();
				Fruits_arr = new ArrayList<>();
				BOX_arr = new ArrayList<>();
				Ghost_arr = new ArrayList<>();
				mygame=new game(Packman_arr, Fruits_arr,Ghost_arr,BOX_arr);
				new MenuBar();
				new Map();
				Solo_game=false;
				isGamer=0;
				ArrayTemp=new ArrayList<>();
				game_player=false;
				TheCloserPackman=null;
				repaint();
			}
		});


		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}


	public void update(Graphics g){

			paint(g);
		}
		
	public void paint(Graphics g) {

		if(dbg==null){
			image = createImage(5000,5000);
			dbg = image.getGraphics();

		}		
		dbg.drawImage(image, 8,50, getWidth()-17, getHeight()-60,null);


		

		double x1 = 0;
		double y1 = 0 ;
		double x2 = 0;
		double y2 = 0 ;


		if (isGamer!=0&& mygame.Fruits_arr.size() > 0) {
			
			for (int i=0; i<mygame.Fruits_arr.size(); i++) 
			{
				x1=(int)(mygame.Fruits_arr.get(i).getfruit().x()*getWidth());
				y1=(int)(mygame.Fruits_arr.get(i).getfruit().y()*getHeight());	

				g.drawImage(Fruit, (int)x1, (int)y1,20, 20, null);

			}
		}
		for (int j=0; j<mygame.BOX_arr.size(); j++) {
			double height = (mygame.BOX_arr.get(j).getP1().y()*getHeight())-(mygame.BOX_arr.get(j).getP0().y()*getHeight());
			double width = (mygame.BOX_arr.get(j).getP1().x()*getWidth())-(mygame.BOX_arr.get(j).getP0().x()*getWidth());
			dbg.drawImage(BOX, (int)(mygame.BOX_arr.get(j).getP0().x()*getWidth()),(int) (mygame.BOX_arr.get(j).getP0().y()*getHeight()),(int)width, (int)height, null);

		}

		for (int j=0; j<mygame.Packmanarr.size(); j++) {

			x1=(mygame.Packmanarr.get(j).getP().x()*getWidth());
			y1=(mygame.Packmanarr.get(j).getP().y()*getHeight());	

			dbg.drawImage(pack, (int)x1,(int) y1,20, 20, null);

		}	
		for (int j=0; j<mygame.Ghostarr.size(); j++) {
			x1=(mygame.Ghostarr.get(j).getG().x()*getWidth());
			y1=(mygame.Ghostarr.get(j).getG().y()*getHeight());	

			dbg.drawImage(ghost, (int)x1,(int) y1,20, 20, null);

		}

		if(mygame.player!=null){
			x1=(mygame.player.get_player_Location().x()*getWidth());
			y1=(mygame.player.get_player_Location().y()*getHeight());	

			dbg.setColor(Color.cyan);
			dbg.fillOval((int)x1,(int) y1, 10, 10);
		}
		g.drawImage(image, 0, 0, this);
	}
		
	private void  packSmiulation() {
		ArrayList<Packman> myPackmens = new ArrayList<>();

		ShortestPathAlgo algo = new ShortestPathAlgo(mygame);
		if(mygame.Packmanarr.size() == 1) {
			myPackmens = mygame.Packmanarr;
			path p = algo.algoSinglePackman(myPackmens.get(0));
			Fruits_arr=p.getCpath();
			myPackmens.get(0).getpath().setpath1(p.getCpath());
			myPackmens.get(0).getpath().setTime_path(p.getTime_path());
		}
		else 
		{
			myPackmens = algo.algoMultiPackmans();
		}
		for (Packman packman : myPackmens)
		{

			Thread thread = new Thread() 
			{
				@Override
				public void run()
				{

					for (int i = 0; i < packman.getpath().getCpath().size(); i++) {


						for (int j = 0; j < packman.getpath().getTime_path(); j++) {
							if (i == packman.getpath().getCpath().size()) {
								continue;
							}
							Point3D ans = packman.getpath().nextpoint(packman,packman.getpath().getCpath().get(i) , j);
							packman.setPackLocation(ans);
							repaint();

							if(packman.getpath().Time2Points(packman,packman.getpath().getCpath().get(i) ) <= 0) {
								continue;
							}
							try {
								sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
				}
			};
			thread.start();


		}
	}









	@Override
	public void mouseClicked(MouseEvent arg) {

		double x_temp=arg.getX();
		x_temp=x_temp/getWidth();


		double y_temp=arg.getY();
		y_temp=y_temp/getHeight();
		Point3D point_return=new Point3D(x_temp, y_temp, 0);
		Point3D covertedfromPixel = theMap.pixel2coord(x_temp, y_temp);


		if (isGamer==(1))
		{	
			mygame.Fruits_arr.add(new Fruit(point_return,1));

			System.out.println("Fruit "+covertedfromPixel.toString());

			repaint();

		}else if (isGamer==(-1))
		{
			mygame.Packmanarr.add(new Packman(point_return, radius, speed));
			System.out.println("Packman "+covertedfromPixel.toString());

			repaint();
		}

	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub		

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args)
	{
		MyFarme win = new MyFarme();
		win.setVisible(true);

		win.setSize(win.myImage.getWidth(),win.myImage.getHeight());
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}