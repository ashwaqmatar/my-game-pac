package GIS;
import Coords.Map;
import Geom.Point3D;
import GIS.Packman;
import java.util.ArrayList;


public class path 
{
	private ArrayList<Fruit>thepath;
	Map the_map=new Map();
	public double time_path;
	ArrayList<Packman> test_t_path;

	public path()
	{
		thepath = new ArrayList<>();
		this.time_path=0;
	}

	/**
	 * @return the time_path
	 */
	public double getTime_path() {
		return time_path;
	}

	/**
	 * @param time_path the time_path to set
	 */
	public void setTime_path(double time_path) {
		this.time_path = time_path;
	}
	/**
	 * @return the test
	 */
	public ArrayList<Packman> gettest_t_path() {
		return test_t_path;
	}
	/**
	 * @return the the_path
	 */
	public ArrayList<Fruit> getCpath() {
		return this.thepath;
	}

	/**
	 * @param the_path the the_path to set
	 */
	public void setpath1(ArrayList<Fruit> the_path) {
		this.thepath = the_path;
	}

	public double Time2Points(Packman p , Fruit f) {
		if (the_map.destpixels(p.getP(), f.getfruit()) < p.getred()) {
			return 0;
			}
		else {	
					return (the_map.destpixels(p.getP(), f.getfruit())-p.getred())/p.getSpeed();

		}
	}
	public double CalPathTime(Packman packman) {
		double cal = 0;
		double totalTime = 0;
		Packman t = new Packman(packman);
		for (int i = 0; i < packman.getpath().getCpath().size(); i++) {
			cal =Time2Points(packman,packman.getpath().getCpath().get(i));
			totalTime +=cal;
			packman.setPackLocation(packman.getpath().getCpath().get(i).getfruit());
		}
		packman.getpath().setTime_path(totalTime);
		packman.setPackLocation(t.getP());

		return totalTime;
	}

	public Point3D nextpoint(Packman p,Fruit f ,double t ) {
		double y=p.getP().y()/((p.getpath().getTime_path()))+0.05;
		double x=p.getP().x()/((p.getpath().getTime_path()))+0.05;
		double xt=p.getP().x()+x*(-p.getP().x()+f.getfruit().x());
		double yt=p.getP().y()+y*(-p.getP().y()+f.getfruit().y());
		Point3D ans=new Point3D(xt,yt);
		return ans;
	}




}
