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
import GIS.Fruit;
import GIS.game;
import GIS.Packman;
import GIS.path;
import Geom.Point3D;
import Algorithm.ShortestPathAlgo;

/**
 * This Class manages the graphical representation of the entire program.
 * the class is an implements of MouseListener is an extents of JFrame.
 * More: http://www.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
 *
 */
public class MyFarme extends JFrame implements MouseListener
{



	public BufferedImage myImage;
	public BufferedImage packimage;
	public BufferedImage Fruitimage;
	public BufferedImage DEED_Fruit;
	public BufferedImage DEED_Pack;

	double radius = 1;
	int speed = 1;
	MenuBar menuBarOption = new MenuBar();
	public Map theMap = new Map();
	public  ArrayList<Packman> Packman_arr = new ArrayList<>();
	public  ArrayList<Fruit> Fruits_arr = new ArrayList<>();
	public boolean Start_game=false;
	public boolean drwaline = false;
	private int isGamer=0;// if is Gamer==1 --> Fruit :::: if is Gamer == -1 --> Packman 
	private game mygame=new game(Packman_arr, Fruits_arr);
	public ArrayList<Packman> ArrayTemp=new ArrayList<>();
	path TheCloserPackman;


	public MyFarme() 
	{
		initGUI();		
		this.addMouseListener(this); 

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
			packimage = ImageIO.read(new File("Pictures&Icones/packnew.png")); 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
		try {	
			Fruitimage = ImageIO.read(new File("Pictures&Icones/furti.png")); 
		}
		catch (IOException e)
		{ 
			e.printStackTrace();
		}
		Menu theMenu = new Menu("File"); 
		menuBarOption.add(theMenu);
		MenuItem runItem = new MenuItem("Run");
		theMenu.add(runItem);

		MenuItem restart_item = new MenuItem("Rest");	
		theMenu.add(restart_item);

		MenuItem exit = new MenuItem("Exit");
		theMenu.add(exit);




		Menu Add = new Menu("Add"); 
		menuBarOption.add(Add);

		MenuItem Item_Packman = new MenuItem("Packman");
		MenuItem item_Fruit = new MenuItem("Fruit");		
		Add.add(Item_Packman);
		Add.add(item_Fruit);



		Menu Add_import=new Menu ("Import");
		MenuItem Csv_read = new MenuItem("Csv");
		Add_import.add(Csv_read);


		menuBarOption.add(Add_import);
		Menu Add_export=new Menu ("Export");
		menuBarOption.add(Add_export);
		MenuItem Csv_writing_csv = new MenuItem(" Csv ");
		MenuItem Csv_writing_kml = new MenuItem(" Kml ");
		Add_export.add(Csv_writing_csv);
		Add_export.add(Csv_writing_kml);

		this.setMenuBar(menuBarOption);


		/**
		 * Turn on the buttons
		 */

		runItem.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				if(mygame.Packmanarr.size() >0 && mygame.Fruits_arr.size() > 0 ) {
					Start_game=true;
					drwaline = true;
					isGamer=2;
					for (int i = 0; i < Packman_arr.size(); i++) {
						ArrayTemp.add(new Packman(Packman_arr.get(i)));
						ArrayTemp.get(i).getpath().setpath1(Packman_arr.get(i).getpath().getCpath());
					}

					packSmiulation();


				}
				Start_game = false;

			}
		});

		restart_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				radius = 1;
				speed = 1;
				new MenuBar();
				new Map();
				Packman_arr = new ArrayList<>();
				Fruits_arr = new ArrayList<>();
				mygame=new game(Packman_arr, Fruits_arr);
				isGamer=0;
				Start_game=false;
				drwaline = false;
				ArrayTemp=new ArrayList<>();
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
		item_Fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 1;
			}
		});

		Item_Packman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = -1;

			}
		});




		Csv_read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setDialogTitle("Select an Csv File");
				fileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","CSV");
				fileChooser.addChoosableFileFilter(filter);

				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile().getPath());
					game theGame = new game(mygame.Packmanarr,mygame.Fruits_arr);
					theGame.setfile_dir(fileChooser.getSelectedFile().getPath());
					try {
						theGame.Csvread();
						mygame.Packmanarr = theGame.Packmanarr;
						mygame.Fruits_arr = theGame.Fruits_arr;
						mygame.file_directory = theGame.file_directory;
						isGamer = 2;

						repaint();

					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		});





		Csv_writing_csv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Export to Csv File");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","CSV");
				jfc.addChoosableFileFilter(filter);

				int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());


					mygame.setfile_dir(selectedFile.getAbsolutePath());
					try {
						mygame.save2Csv();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}


			}

		});


		Csv_writing_kml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Export to KML File");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("kml","KML");
				jfc.addChoosableFileFilter(filter);

				int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());


					mygame.setfile_dir(selectedFile.getAbsolutePath());




					try {
						mygame.save_to_kml();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}


				}


			}

		});

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





	public void paint(Graphics g) {

		Image scaledImage = myImage.getScaledInstance(this.getWidth(),this.getHeight(),myImage.SCALE_SMOOTH);
		g.drawImage(scaledImage, 8,50, getWidth()-17, getHeight()-60,null);


		Graphics2D g2 = (Graphics2D)g;

		g2.setStroke(new BasicStroke(3));

		double x1 = 0;
		double y1 = 0 ;
		double x2 = 0;
		double y2 = 0 ;


		if (isGamer!=0) {
			for (int i=0; i<mygame.Fruits_arr.size(); i++) 
			{
				x1=(int)(mygame.Fruits_arr.get(i).getfruit().x()*getWidth());
				y1=(int)(mygame.Fruits_arr.get(i).getfruit().y()*getHeight());	

				g.drawImage(Fruitimage, (int)x1-5, (int)y1-6,30, 30, null);

			}
		}

		for (int j=0; j<mygame.Packmanarr.size(); j++) {

			x1=(mygame.Packmanarr.get(j).getP().x()*getWidth());
			y1=(mygame.Packmanarr.get(j).getP().y()*getHeight());	

			g.drawImage(packimage, (int)x1-6,(int) y1-7,30, 30, null);

		}

		if(drwaline == true) {


			for(Packman pack : ArrayTemp) {

				if (ArrayTemp.size()==1)
				{
					double x1_ =  pack.getP().x();
					double y1_ =  pack.getP().y();
					double x2_ =  Fruits_arr.get(0).getfruit().x();
					double y2_ =  Fruits_arr.get(0).getfruit().y();

					g.setColor(Color.blue);
					g.drawLine((int)(x1_*getWidth()), (int)(y1_*getHeight()),(int)(x2_*getWidth()), (int)(y2_*getHeight()));


					for (int i = 1; i < Fruits_arr.size(); i++) {

						x1 =  Fruits_arr.get(i).getfruit().x();
						y1 =  Fruits_arr.get(i).getfruit().y();
						x2 =  Fruits_arr.get(i-1).getfruit().x();
						y2 =  Fruits_arr.get(i-1).getfruit().y();

						g.setColor(Color.blue);
						g.drawLine((int)(x1*getWidth()), (int)(y1*getHeight()),(int)(x2*getWidth()), (int)(y2*getHeight()));	

					}
				}

				if(pack.getpath().getCpath().size() != 0) {
					double x1_ =  pack.getP().x();
					double y1_ =  pack.getP().y();
					double x2_ =  pack.getpath().getCpath().get(0).getfruit().x();
					double y2_ =  pack.getpath().getCpath().get(0).getfruit().y();

					g.setColor(Color.blue);
					g.drawLine((int)(x1_*getWidth()), (int)(y1_*getHeight()),(int)(x2_*getWidth()), (int)(y2_*getHeight()));


					for (int i = 1; i < pack.getpath().getCpath().size(); i++) {

						x1 =  pack.getpath().getCpath().get(i).getfruit().x();
						y1 =  pack.getpath().getCpath().get(i).getfruit().y();
						x2 =  pack.getpath().getCpath().get(i-1).getfruit().x();
						y2 =  pack.getpath().getCpath().get(i-1).getfruit().y();

						g.setColor(Color.blue);
						g.drawLine((int)(x1*getWidth()), (int)(y1*getHeight()),(int)(x2*getWidth()), (int)(y2*getHeight()));	

					}
				}

			}
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

}