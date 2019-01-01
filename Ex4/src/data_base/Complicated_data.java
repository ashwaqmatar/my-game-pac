package data_base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Complicated_data implements Serializable{
	private Date _now;
	private ArrayList<String> _data;
	
	public Complicated_data() {
		_now = new Date();
		_data = new ArrayList<String>();
		for(int i=0;i<10;i++) {
			_data.add("yet another String "+i);
		}
	}
//	public Date getDate() {return this._now;}
	public String toString() {
		String ans ="";
		ans+=this.getClass().getSimpleName()+"  time created: "+this._now.toString();
		for(int i=0;i<_data.size();i++) {
			ans+="\n"+_data.get(i);
		}
		return ans;
	}
	
	
}
