package GIS;

import Geom.Point3D;
/**
 * This Class represents the Fruits of the Pacman Gam.
 * To remind him the fruits give us points in the official game
 */
public class Fruit {
	Point3D fruit;
	int Weight ; 
	/**
	 * Constractor
	 */
	public Fruit(Point3D fruit,int Weight) {
		this.fruit=fruit;
		this.Weight=Weight;

	}
	/**
	 * Copy Constractor
	 */
	public Fruit(Fruit f) {
		this(f.fruit,f.Weight);
	}
	/**
	 * @return the fruit
	 */
	public Point3D getfruit() {
		return fruit;
	}
	/**
	 * @param fruit the fruit to set
	 */
	public void setfruit(Point3D fruit) {
		this.fruit = fruit;
	}
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return Weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		Weight = weight;
	}

	@Override
	public String toString() {
		return "Fruit [fruit=" + fruit.toString() + ", Weight=" + Weight + "]";
	}

}
