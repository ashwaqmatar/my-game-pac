package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class GISlayer implements GIS_layer {
	private MetaData data;
HashSet<GIS_element> ST=new HashSet <GIS_element>(); 
	// TODO Auto-generated constructor stub


	@Override
	public boolean add(GIS_element arg0) {
		// TODO Auto-generated method stub
		return ST.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
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
	public Iterator<GIS_element> iterator() {
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
		return this.data;
	}

}
