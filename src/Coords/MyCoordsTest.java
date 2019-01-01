package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.prism.image.Coords;

import Geom.Point3D;

class MyCoordsTest {
	MyCoords coords;

	 Point3D p1;
	 Point3D p2;
	 Point3D p3;


	@BeforeEach
	void setUp() throws Exception {
		coords =new MyCoords();
		// Coordinates copied from the examples in the attached Exel file.
		p1 =new Point3D(32.103315, 35.209039, 670);
		p2 = new Point3D(32.106352, 35.205225, 650);
		p3 = new Point3D(337.699, -359.249, -20);
	}
	@AfterEach
	void tearDown() throws Exception {
	}
	/**
	 * Test method for {@link Coords.MyCoords#add(Geom.Point3D, Geom.Point3D)}.
	 */
	@Test
	void testAdd() {
		Point3D answer= new Point3D(32.106352000071396,35.20522500219698,650.0);
		assertEquals(true, coords.add(p1, p3).equals(answer));
	}
	/**
	 * Test method for {@link Coords.MyCoords#distance3d(Geom.Point3D, Geom.Point3D)}.
	 */
	@Test
	void testDistance3d() {
		double answer=493.05233183241336;
		assertEquals(answer, coords.distance3d(p1, p2));
	}

	@Test
	void testVector3D() {
		Point3D pTemp;
		pTemp = coords.vector3D(p1, p2);
		
		assertEquals(p3.x(), pTemp.x(),0.001,"The Vector Latitude is wrong");
		assertEquals(p3.y(), pTemp.y(),0.001,"The Vector longtidude is wrong");
		assertEquals(p3.z(), pTemp.z(),0.001,"The Vector atitude is wrong");	
	}

	  @Test
	void testAzimuth_elevation_dist() {
		double [] azimuth_elevation_dist= coords.azimuth_elevation_dist(p1, p2);
		double [] expected_r= {313.2304203264989, 92.32476351738653, 493.05233183241336};
		System.out.println("the azimuth_elevation_dist in polar coordinate: ["+azimuth_elevation_dist[0]+", "+azimuth_elevation_dist[1]+", "+azimuth_elevation_dist[2]+"]");

		assertEquals(azimuth_elevation_dist[0],expected_r[0],0.01);
		assertEquals(azimuth_elevation_dist[1],expected_r[1],0.01);
		assertEquals(azimuth_elevation_dist[2],expected_r[2],0.01);
		}

	@Test
	void testIsValid_GPS_Point() {
		Point3D invalidP = new Point3D(190, 30, 200);		
		assertEquals(false, coords.isValid_GPS_Point(invalidP),"The function for Valid GPS Point is wrong");
		assertEquals(true, coords.isValid_GPS_Point(p1),"The function for Valid GPS Point is wrong");

	}
}