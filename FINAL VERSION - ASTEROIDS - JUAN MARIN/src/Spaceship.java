import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Spaceship {

	private int xPos, yPos, width, height, lives;
	private ImageIcon imgPlayer, imgShip, imgExplosion;
	private boolean isExploded;

	/**
	 * Creates a default spaceship object which defines the x position to 0, the
	 * y position to 0, the image of the ship to default ship, the image of the
	 * explosion to a default image and then sets the default image of the
	 * player to the ship image. Also sets the amount of lives the player has to
	 * 3, then sets the width and height of the ship object to the width and
	 * height of the ImageIcon imgPlayer.
	 */
	public Spaceship() {

		xPos = 0;
		yPos = 0;
		imgShip = new ImageIcon("images\\spaceship.png");
		imgExplosion = new ImageIcon("images\\exploded_ship.png");
		imgPlayer = imgShip;
		lives = 3;
		width = imgPlayer.getIconWidth();
		height = imgPlayer.getIconHeight();

	}

	/**
	 * Passes an integer parameter y, which is then stored into the class field
	 * yPos that sets the y position of the spaceship object.
	 * 
	 * @param y
	 *            Sets the yPos of the Spaceship object.
	 */
	public void setY(int y) {
		yPos = y;
	}

	/**
	 * Passes an integer parameter x, which is then stored into the class field
	 * xPos that sets the position of the spaceship object.
	 * 
	 * @param x
	 *            Sets the xPos of the spaceship object.
	 */
	public void setX(int x) {
		xPos = x;
	}

	/**
	 * Passes an integer parameter w, that is stored into the class field width,
	 * which determines how wide the spaceship object is going to be.
	 * 
	 * @param w
	 *            Defines the width of the spaceship object.
	 */
	public void setWidth(int w) {
		width = w;
	}

	/**
	 * Passes an interger parameter h, that is stored into the class field
	 * height, which determines the length of the spaceship object.
	 * 
	 * @param h
	 *            Defines the height of the spaceship object.
	 */
	public void setHeight(int h) {
		height = h;
	}

	/**
	 * Uses the Graphics2D class to draw the spaceship object. The method
	 * g2.drawImage is used to then get the image of imgPlayer, then takes the
	 * xPos, yPos, and draws it on null since there is nothing to draw on within
	 * the class itself. Will draw when method is used on a component.
	 * 
	 * @param g2
	 *            Needs an instance of a Graphics2D class in order for this
	 *            method to be used.
	 */
	public void drawShip(Graphics2D g2) {
		g2.drawImage(imgPlayer.getImage(), xPos, yPos, null);
	}

	/**
	 * Passes a boolean to determine whether or not the ship object has
	 * exploded. Sets the class field isExploded to whatever was passed. Also
	 * changes the image dependent on whether isExploded is true or false. If
	 * isExploded is true, set the player image to the image of the explosion
	 * otherwise, set the image to the image of the spaceship.
	 * 
	 * @param b
	 *            Sets if isExploded is rather true or false.
	 */
	public void setExploded(boolean b) {
		isExploded = b;

		if (isExploded == true) {
			imgPlayer = imgExplosion;
		} else {
			imgPlayer = imgShip;
		}
	}

	/**
	 * Passes two integers that define both the x position and y position of the
	 * spaceship object on a component.
	 * 
	 * @param x
	 *            Sets the x position of the spaceship.
	 * @param y
	 *            Sets the y position of the spaceship.
	 */
	public void setLocation(int x, int y) {
		xPos = x;
		yPos = y;
	}

	/**
	 * Returns the bounds of the object by returning a rectangle. A new
	 * rectangle is created with its properties defined by the x position, y
	 * position, width and height of the spaceship object
	 * 
	 * @return a new Rectangle that is defined by the spaceships xPos, yPos,
	 *         width and height properties.
	 */
	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, width, height);
	}

	/**
	 * Returns whether or not the spaceship object has exploded or not.
	 * 
	 * @return isExploded is rather true or false.
	 */
	public boolean getExploded() {
		return isExploded;
	}

	/**
	 * Passes an integer that sets the amount of lives a spaceship object has.
	 * 
	 * @param l
	 *            Sets the amount of lives a spaceship has.
	 */
	public void setLives(int l) {
		lives = l;
	}

	/**
	 * Return method that returns an integer representing the x position of the
	 * spaceship on the component.
	 * 
	 * @return Return an integer that represents the position along the x axis.
	 */
	public int getX() {
		return xPos;
	}

	/**
	 * Return method that returns an integer representing the x position of the
	 * spaceship on the component.
	 * 
	 * @return Return an integer that represents the position along the y axis.
	 */
	public int getY() {
		return yPos;
	}

	/**
	 * Return method that returns an integer representing the width of the
	 * spaceship object.
	 * 
	 * @return Return int that represents the object's width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Return method that returns an integer representing the height of the
	 * spaceship object.
	 * 
	 * @return Return int that represents the object's height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Return method that returns an integer representing the amount of lives a
	 * spaceship has.
	 * 
	 * @return Return int that represents the object's amount of lives.F
	 */
	public int getLives() {
		return lives;
	}

}
