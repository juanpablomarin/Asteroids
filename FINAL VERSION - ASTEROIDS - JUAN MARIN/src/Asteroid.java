import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Asteroid {

	private int xPos, yPos, width, height, size, hp;
	private ImageIcon imgSmall, imgMedium, imgLarge, imgExploded, imgAsteroid;
	private boolean isExploded;

	/**
	 * Create a default asteroid that sets its xPos and yPos to 0, set its HP to
	 * 3, set the default images of imgSmall, imgMedium, imgLarge, and
	 * imgExploded to default images sets the image of imgAsteroid to the image
	 * imgSmall, sets the width and height to the width and height of the image
	 * within imgAsteroid, sets the size to 0.
	 */
	public Asteroid() {

		xPos = 0;
		yPos = 0;
		hp = 3;
		imgSmall = new ImageIcon("images\\asteroid0.png");
		imgMedium = new ImageIcon("images\\asteroid1.png");
		imgLarge = new ImageIcon("images\\asteroid2.png");
		imgExploded = new ImageIcon("images\\explosion.png");
		imgAsteroid = imgSmall;
		isExploded = false;
		width = imgAsteroid.getIconWidth();
		height = imgAsteroid.getIconHeight();
		size = 0;
	}

	/**
	 * Passes an integer that sets the yPosition of the asteroid object.
	 * 
	 * @param y
	 *            sets the y position of the object.
	 */
	public void setY(int y) {
		yPos = y;
	}

	/**
	 * Passes an integer that sets the xPosition of the asteroid object.
	 * 
	 * @param x
	 *            sets the x position of the object.
	 */
	public void setX(int x) {
		xPos = x;
	}

	/**
	 * Passes an integer that sets the width of the asteroid object.
	 * 
	 * @param w
	 *            sets the width of asteroid object.
	 */
	public void setWidth(int w) {
		width = w;
	}

	/**
	 * Passes an integer that sets the height of the asteroid object.
	 * 
	 * @param h
	 *            sets the height of asteroid object.
	 */
	public void setHeight(int h) {
		height = h;
	}

	/**
	 * Passes an integer that sets the amount of hp the asteroid has.
	 * 
	 * @param h
	 *            sets hp of the asteroid object
	 */
	public void setHp(int h) {
		hp = h;
	}

	/**
	 * Sets the image of the asteroid by taking an imageicon parameter
	 * 
	 * @param img
	 *            sets the asteroid img to this parameter
	 */
	public void setAsteroidImage(ImageIcon img) {
		imgAsteroid = img;
	}

	/**
	 * Method that draws the asteroid object by taking the object, the x
	 * position and y positiion
	 */
	public void drawAsteroid(Graphics2D g2) {

		g2.drawImage(imgAsteroid.getImage(), xPos, yPos, null);

	}

	/**
	 * Passes to integers that sets the location of the asteroid object along
	 * the x axis and y axis
	 * 
	 * @param x
	 *            sets the xposition of the asteroid object
	 * @param y
	 *            sets the yposition of the asteroid object
	 */
	public void setLocation(int x, int y) {
		xPos = x;
		yPos = y;
	}

	/**
	 * Method that takes a parameter and sets the size of the asteroid object.
	 * If the parameter is 0, set the image of the asteroid to a small asteroid
	 * and set its hp to 1. If the parameter is 1, set the image of the asteroid
	 * to a medium sized asteroid and set its hp to 2. If the parameter is 2,
	 * set the image of the asteroid to a large asteroid and set its hp to 3 If
	 * the size is less than 0, the image of the asteroid is changed to an image
	 * of an explosion, and sets the class field isExploded to true. Also sets
	 * the width and height of the asteroid object to width and height of the
	 * current image within imgAsteroid
	 * 
	 * @param s
	 *            determines the size of the asteroid, from 0 - 2 being small to
	 *            large, if parameter s is going to be less than 0, imgAsteroid
	 *            is set to an explosion image
	 */
	public void setSize(int s) {
		size = s;

		if (s == 0) {
			imgAsteroid = imgSmall;
			hp = 1;

		} else if (s == 1) {
			imgAsteroid = imgMedium;
			hp = 2;

		} else if (s == 2) {
			imgAsteroid = imgLarge;
			hp = 3;
		}

		if (size < 0) {
			imgAsteroid = imgExploded;
			isExploded = true;
		}

		width = imgAsteroid.getIconWidth();
		height = imgAsteroid.getIconHeight();
	}

	/**
	 * Passes an boolean that determines if the asteroid has exploded or not
	 * 
	 * @param b
	 *            sets if the class field isExploded is rather true or false
	 */
	public void setExploded(boolean b) {
		isExploded = b;
	}

	/**
	 * Passes an integer that moves the asteroid to the left an indicated amount
	 * of pixels. It is left because this class moves the asteroids from right
	 * to left
	 * 
	 * @param i
	 *            determines how many pixels the asteroid will move
	 */
	public void moveAsteroid(int i) {
		xPos -= i;
	}

	/**
	 * Default move method that moves the asteroid, a default amount of pixels
	 * which is 10.
	 */
	public void moveAsteroid() {
		xPos -= 10;
	}

	/**
	 * Return an int value of how much HP an asteroid object has.
	 * 
	 * @return how much hp an asteroid has.
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Return method that returns the current image of imgAsteroid
	 * 
	 * @return current image of imgAsteroid
	 */
	public Image getImage() {
		return imgAsteroid.getImage();
	}

	/**
	 * Returns the bounds of the object by returning a rectangle. A new
	 * rectangle is created with its properties defined by the x position, y
	 * position, width and height of the asteroid object.
	 * 
	 * @returna new Rectangle that is defined by the spaceships xPos, yPos,
	 *          width and height properties.
	 */
	public Rectangle getBounds() {

		return new Rectangle(xPos, yPos, width, height);
	}

	/**
	 * Return method that returns an int value representing the width of the
	 * object
	 * 
	 * @return int that represents the width of object
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Return method that returns an int value representing the height of the
	 * object
	 * 
	 * @return int that represents the height of object
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Return method returns an int value representing the xPosition of the
	 * object
	 * 
	 * @return int that represents xPos
	 */
	public int getX() {
		return xPos;
	}

	/**
	 * Return method returns an int value representing the yPosition of the
	 * object
	 * 
	 * @return int that represents yPos
	 */
	public int getY() {
		return yPos;
	}

	/**
	 * Return method that returns an int value representing the size of the
	 * asteroid
	 * 
	 * @return int that represents the size of the asteroid object
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Return method that returns whether or not an asteroid has exploded in a
	 * boolean
	 * 
	 * @return boolean that represents if asteroid has exploded or not
	 */
	public boolean isExploded() {
		return isExploded;
	}

}
