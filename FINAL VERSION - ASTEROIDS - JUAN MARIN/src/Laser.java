import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Laser {

	private int xPos, yPos, width, height;
	private ImageIcon imgLaser;
	private boolean isFired;


	/**
	 * No arg constructor that defines a default laser object. Setting the x
	 * position to 0, the y position to 0, setting imgLaser to a new Image Icon
	 * of a laserbeam sets the width of the object to the width of the icon of
	 * imgLaser, sets the height of the object to the height of the icon of
	 * imgLaser and sets the boolean isFired to false.
	 */
	public Laser() {

		xPos = 0;
		yPos = 0;
		imgLaser = new ImageIcon("images\\laserbeam.png");
		width = imgLaser.getIconWidth();
		height = imgLaser.getIconHeight();
		isFired = false;
	}

	/**
	 * Uses the Graphics2D class to draw the laser object. The method
	 * g2.drawImage is used to then get the image of imgLaser, then takes the
	 * xPos, yPos, and draws it on null since there is nothing to draw on within
	 * the class itself. Will draw when method is used on a component.
	 * 
	 * @param g2
	 */
	public void drawLaser(Graphics2D g2) {

		g2.drawImage(imgLaser.getImage(), xPos, yPos, null);

	}

	/**
	 * Passes an integer parameter y, which is then stored into the class field
	 * yPos that sets the y position of the laser object.
	 * 
	 * @param y
	 *            Sets the yPos of the laser object.
	 */
	public void setY(int y) {
		yPos = y;
	}

	/**
	 * Passes an integer parameter x, which is then stored into the class field
	 * xPos that sets the x position of the laser object.
	 * 
	 * @param x
	 *            Sets the xPos of the laser object.
	 */
	public void setX(int x) {
		xPos = x;
	}

	/**
	 * Passes an integer that sets the width of the laser object.
	 * 
	 * @param w
	 *            int that sets the width of the object.
	 */
	public void setWidth(int w) {
		width = w;
	}

	/**
	 * Passes an integer that sets the height of the laser object.
	 * 
	 * @param h
	 *            int that sets the width of the object.
	 */
	public void setHeight(int h) {
		height = h;
	}

	/**
	 * Passes a boolean to determine whether or not if the laser has been fired.
	 * 
	 * @param b
	 *            boolean that determines if the laser has been fired or not.
	 */
	public void fired(boolean b) {
		isFired = b;
	}

	/**
	 * A method that has no parameters which will 'move' the laser object by
	 * adding 10, which will represent pixels, to the class field xPos.
	 */
	public void move() {
		xPos += 10;
	}

	/**
	 * A method that passes an int parameter which will determine how much the
	 * laser will 'move' by adding i to the class field xPos.
	 * 
	 * @param i
	 *            int that determines how much the laser will move by in pixels.
	 */
	public void move(int i) {
		xPos += i;
	}

	/**
	 * Passes two integers that define both the x position and y position of the
	 * laser object on a component.
	 * 
	 * @param x
	 *            Sets the x position of the laser object.
	 * @param y
	 *            Sets the y position of the laser object.
	 */
	public void setLocation(int x, int y) {
		xPos = x;
		yPos = y;
	}

	/**
	 * Method that returns a boolean that represents whether or not the laser
	 * object has been fired or n ot.
	 * 
	 * @return
	 */
	public boolean isFired() {
		return isFired;
	}

	/**
	 * Returns the bounds of the object by returning a rectangle. A new
	 * rectangle is created with its properties defined by the x position, y
	 * position, width and height of the laser object.
	 * 
	 * @return a new Rectangle that is defined by the spaceships xPos, yPos,
	 *         width and height properties.
	 */
	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, width, height);
	}

	/**
	 * Method that returns an int representing the x position of the laser
	 * object on the component.
	 * 
	 * @return int that represents the xPos of the laser object.
	 */
	public int getX() {
		return xPos;
	}

	/**
	 * Method that returns an int representing the y position of the laser
	 * object on the component.
	 * 
	 * @return int that represents the xPos of the laser object.
	 */
	public int getY() {
		return yPos;
	}

	/**
	 * Method that returns an int representing the width of the laser object.
	 * 
	 * @return int representing the width of the laser object.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Method that returns an int representing the height of the laser object.
	 * 
	 * @return int representing the height of the laser object.
	 */
	public int getHeight() {
		return height;
	}

}
