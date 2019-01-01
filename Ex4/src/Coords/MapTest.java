package Coords;


import Coords.MyCoords;
import Geom.Point3D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class MapTest {
	private Point3D inPixel;
	private Point3D inPixel1;
	private BufferedImage myImage;
	{
		try {
			String mapPath = "Ariel.png";
			myImage = ImageIO.read(new File(mapPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Map frameConverter;
	@Before
	public void setUp() {
		inPixel = new Point3D(422,237);
		inPixel1 = new Point3D(353,432);
		frameConverter = new Map();
	}
	@Test
	public void pixelToPoint3D() {
		Point3D expected = new Point3D(32.10979442049724,35.206255140318355);
		Point3D actual = frameConverter.pixel2coord(inPixel.x(),inPixel.y());
		Assert.assertTrue(expected.close2equals(actual,0.01));
	}
	@Test
	public void point3DToPixel() {
		Point3D actualCoordinates = new Point3D(32.1031356881926,35.20859012949533);
		Point3D actualPixel = frameConverter.coord2pixel(actualCoordinates);
		double pXdistance = frameConverter.destpixels(inPixel,actualCoordinates);
		Assert.assertTrue(actualPixel.close2equals(inPixel,pXdistance));
	}
	@Test
	public void distanceBetweenPixels() {
		double expectedDistance = Math.sqrt(42768.1534);
		double actual = frameConverter.destpixels(inPixel1,inPixel );
		Assert.assertEquals(expectedDistance,actual,1);
	}
}
