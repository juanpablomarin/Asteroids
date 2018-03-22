import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import sun.audio.*;
import javax.swing.Timer;

/**
 * JUAN MARIN 01-12-15 ICS 4U1 ASTEROIDS
 */

public class AsteroidsGame extends JPanel implements ActionListener, MouseMotionListener, MouseListener {

	// Declare Global Variables
	private ImageIcon[] imgBackground;
	private int[] xPos;
	private int score, tick;
	private Timer backgroundTimer, laserTimer, asteroidTimer, asteroidRockTimer;
	private JLabel lblScore, lblLives;
	private SpaceMusic music;
	private Spaceship ship;
	private Laser beam;
	private Asteroid[] asteroid;
	private Random rnd;
	private JPanel topPanel;
	private boolean check;

	public static void main(String[] args) {

		// Create new Asteroids Game
		new AsteroidsGame();
	}

	public AsteroidsGame() {

		// Initialize variables, including objects
		ship = new Spaceship();
		music = new SpaceMusic();
		beam = new Laser();
		asteroid = new Asteroid[20];
		score = 0;
		rnd = new Random();

		// Start a for loop that initializes each asteroid object within the
		// asteroid array then set the size of each individual asteroid
		for (int i = 0; i < asteroid.length; i++) {
			asteroid[i] = new Asteroid();
			asteroid[i].setSize(rnd.nextInt(3));
		}

		// create ImageIcon array
		imgBackground = new ImageIcon[2];

		// Set the images in each spot of the array to an image
		for (int i = 0; i < imgBackground.length; i++) {
			imgBackground[i] = new ImageIcon("images\\space_background.png");
		}

		// xPosition array
		xPos = new int[] { 0, imgBackground[0].getIconWidth() };

		// Initialize timers
		laserTimer = new Timer(1, this);
		backgroundTimer = new Timer(50, this);
		asteroidTimer = new Timer(40, this);
		asteroidRockTimer = new Timer(500, this);

		// create new JLabel title that shows the title asteroids and set its
		// properties
		JLabel lblTitle = new JLabel();
		lblTitle.setIcon(new ImageIcon("images\\asteroids_title.png"));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setPreferredSize(new Dimension((int) (imgBackground[0].getIconWidth() * 0.70), 50));

		// create a score label and set its properties, also output the current
		// score
		lblScore = new JLabel();
		lblScore.setText(Integer.toString(score));
		lblScore.setPreferredSize(new Dimension(200, 50));
		lblScore.setFont(new Font("Neuropol", Font.BOLD, 48));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.LIGHT_GRAY);

		// create a label the displays the amount of lives the player has and
		// also set that label's properties
		lblLives = new JLabel();
		lblLives.setPreferredSize(new Dimension(140, 30));
		lblLives.setHorizontalAlignment(SwingConstants.CENTER);
		lblLives.setIcon(new ImageIcon("images\\lives" + ship.getLives() + ".png"));

		// create a new panel that holds the title, the score, and the lives
		// labels and set its properties
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		topPanel.setBackground(Color.BLACK);
		topPanel.add(lblScore);
		topPanel.add(lblTitle);
		topPanel.add(lblLives);

		// set focus to the JPanel, add both a mouse and mouse motion listener
		setFocusable(true);
		addMouseMotionListener(this);
		addMouseListener(this);
		requestFocus();

		// create a new JFrame and set its properties that includes the title
		// and then set it to visible
		JFrame frame = new JFrame();
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(this, BorderLayout.CENTER);
		frame.setTitle("Asteroids");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(imgBackground[0].getIconWidth(), imgBackground[0].getIconHeight());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// start the moving background timer
		backgroundTimer.start();

		// start playing the music
		music.playMusic();

		// Call method spawnRocks that spawns the rocks when program starts up
		spawnRocks();

		// start the asteroid timer for the asteroids to start moving
		asteroidTimer.start();

	}

	// create a private method called spawn rocks
	private void spawnRocks() {

		// create a for loop that spawns the rocks
		for (int i = 0; i < asteroid.length; i++) {

			check = false;
			// check overlap method is called
			checkOverlap();
			// spawn the asteroid at a random x pos spot off the screen
			asteroid[i].setX(rnd.nextInt(4000) + getWidth());

			// check if the yPosition is within a reasonable area on the screen,
			// if most the asteroid is cut off and cannot be viewed, respawn the
			// y position as long as its off the screen
			do {
				check = false;
				asteroid[i].setY(rnd.nextInt(imgBackground[0].getIconHeight()));
				if (asteroid[i].getY() + asteroid[i].getHeight() >= getHeight()
						|| asteroid[i].getY() <= topPanel.getHeight()) {
					check = true;
				}
			} while (check == true);
		}

	}

	// create a private method called checkOverlap that checks if asteroids
	// overla
	private void checkOverlap() {

		// Start a for loop thats as long as the asteroid array
		for (int i = 0; i < asteroid.length; i++) {

			// If the loop is starting at spot 0, do nothing
			if (i == 0) {

			} else { // If the loop is not at spot 0

				// start a for loop, thats equivalent to the other for loop that
				// counts backwards
				for (int j = i; j > 0; j--) {

					// If the asteroid current asteroid intersects with the
					// asteroid before it
					if (asteroid[i].getBounds().intersects(asteroid[j - 1].getBounds())) {

						do {
							// spawn the rock
							check = false;
							asteroid[i].setX(rnd.nextInt(4000) + getWidth());
							do {
								check = false;
								asteroid[i].setY(rnd.nextInt(imgBackground[0].getIconHeight()));
								if (asteroid[i].getY() + asteroid[i].getHeight() >= getHeight()
										|| asteroid[i].getY() <= topPanel.getHeight()) {
									check = true;
								}
							} while (check == true);
							// process up until here is the same as it was in
							// the spawnRocks() method
							j = i;

							// keep respawning as long as the two asteroids in
							// question are overlapping
						} while (asteroid[i].getBounds().intersects(asteroid[j - 1].getBounds()));

					}

				}

			}

		}

	}

	// create a private method called respawnRock() that respawns the asteroids
	// whether it was exploded or whether it goes off the screen
	private void respawnRock() {

		// create a for loop that is as long as the asteroid array
		for (int i = 0; i < asteroid.length; i++) {

			// if the asteroid had exploded or if the asteroid goes off the
			// screen
			if (asteroid[i].isExploded() == true || asteroid[i].getX() + asteroid[i].getWidth() <= 0) {

				check = false;
				asteroid[i].setX(rnd.nextInt(4000) + getWidth());
				do {
					check = false;
					asteroid[i].setY(rnd.nextInt(imgBackground[0].getIconHeight()));
					if (asteroid[i].getY() + asteroid[i].getHeight() >= getHeight()
							|| asteroid[i].getY() <= topPanel.getHeight()) {
						check = true;
					}
				} while (check == true);
				// process up until this point is the same as it was in the
				// spawnRocks() method

				// set if the asteroid had exploded to false, and generate a new
				// size for that rock and call the overlap method
				asteroid[i].setExploded(false);
				asteroid[i].setSize(rnd.nextInt(3));
				checkOverlap();
			}
		}
	}

	// create a private method for if the laser beam hits the asteroid
	private void checkHit() {

		for (int i = 0; i < asteroid.length; i++) {

			// if the beam is fired and the beam intersects with the asteroid
			// but the asteroid has exploded, do not increase the score
			if (beam.isFired() == true && beam.getBounds().intersects(asteroid[i].getBounds())
					&& asteroid[i].isExploded() == true) {

				score += 0;

			}

			// if the beam is fired and the beam intersects with the asteroid
			else if (beam.isFired() == true && beam.getBounds().intersects(asteroid[i].getBounds())) {

				// increase the score by 10, set if the beam is fired to false,
				// stop the laser timer, update the score label, decrease the
				// asteroid size*, set the location of the beam back to the
				// center of the ship
				score += 10;
				beam.fired(false);
				laserTimer.stop();
				lblScore.setText(Integer.toString(score));
				asteroid[i].setSize(asteroid[i].getSize() - 1);
				if (asteroid[i].getSize() < 0) {
					// *if the asteroid's size is less than 0, the asteroid has
					// exploded
					asteroid[i].setExploded(true);
				}
				beam.setLocation(ship.getX() + (ship.getWidth() / 2), ship.getY() + (ship.getHeight() / 2));
			}
		}
	}

	// create a private method that checks if the ship has collided with an
	// asteroid
	private void checkShipCollision() {

		for (int i = 0; i < asteroid.length; i++) {

			// if the ship has intersected or collided with the asteroid and the
			// asteroid hasnt exploded
			if (ship.getBounds().intersects(asteroid[i].getBounds()) && asteroid[i].isExploded() == false) {

				// set the lives to the current amount of lives - 1, stop the
				// background timer, stop the laser timer, set if the beam is
				// fired to false, set the ship exploded to true
				ship.setLives(ship.getLives() - 1);
				backgroundTimer.stop();
				laserTimer.stop();
				beam.fired(false);
				ship.setExploded(true);

				if (ship.getLives() <= 0) {

					// if the player has lost all 3 lives, prompt them that they
					// lost all 3 lives with a JOptionPane and output their
					// score and then prompt them if they would like to play
					// again with a JOptionPane,if yes, call restart method, if
					// no close the program

					JOptionPane.showMessageDialog(null, "You lost all 3 lives! Your score was " + score + "!",
							"Asteroids", JOptionPane.INFORMATION_MESSAGE);

					int choice = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Asteroids",
							JOptionPane.YES_NO_OPTION);

					if (choice == JOptionPane.YES_OPTION) {
						restart();
					} else {
						System.exit(0);
					}

					// when the player loses a life, tell them that they lost a
					// life and tell them how many lives they have remaining and
					// then update the lives label to the remaining amount
				} else {
					JOptionPane.showMessageDialog(null, "You're dead!\nYou have " + ship.getLives() + " remaining!",
							"Asteroids", JOptionPane.INFORMATION_MESSAGE);
					lblLives.setIcon(new ImageIcon("images\\lives" + ship.getLives() + ".png"));

				}
			}

		}

	}

	public void actionPerformed(ActionEvent e) {

		// if the backgroundTimer ticks, move the background to the left
		if (e.getSource() == backgroundTimer) {
			for (int i = 0; i < imgBackground.length; i++) {
				xPos[i] -= 5;

				if (xPos[i] + imgBackground[i].getIconWidth() <= 0) {
					xPos[i] = getWidth();
				}
			}

		}
		// asteroidRockTimer is supposed to keep track of how long the rock
		// stays on the exploded image on screen
		// if the asteroidRockTimer is running, increase the tick by 1 and if
		// the tick is equal to 2, if the asteroid explosion is true, respawn
		// that rock, set the tick back to 0 and break out of the loop
		if (e.getSource() == asteroidRockTimer) {

			tick++;

			if (tick >= 2) {
				for (int i = 0; i < asteroid.length; i++) {
					if (asteroid[i].isExploded() == true) {
						respawnRock();
						asteroidRockTimer.stop();
						asteroid[i].setExploded(false);
						tick = 0;
						break;
					}

				}

			}

		}

		if (e.getSource() == asteroidTimer) {
			for (int i = 0; i < asteroid.length; i++) {

				// if the asteroid is exploded, start the asteroid rock timer,
				// and stop moving that asteroid

				if (asteroid[i].isExploded() == true) {

					asteroidRockTimer.start();
					asteroid[i].moveAsteroid(0);

				} else {

					// else, move that asteroid by 10 pixels and constantly call
					// the respawn rock method that will check if its exploded
					// or off the screen
					asteroid[i].moveAsteroid(10);
					respawnRock();
				}

				// check if the ship has collided while the asteroid timer is
				// ticking
				checkShipCollision();

				// if the ship has exploded, set it back to false and respawn
				// all the rocks off the screen
				if (ship.getExploded() == true) {

					ship.setExploded(false);
					spawnRocks();
				} else {
					// start back up the timers
					asteroidTimer.start();
					backgroundTimer.start();
				}
			}
		}

		// if the laserTimer is running
		if (e.getSource() == laserTimer) {

			// if the laser goes off the screen, set the if the beam is fired to
			// false, stop the laser timer, set the beam back to the ship
			// location
			if (beam.getX() + beam.getWidth() >= getWidth()) {
				beam.fired(false);
				laserTimer.stop();
				beam.setLocation(ship.getX() + (ship.getWidth() / 2), ship.getY() + (ship.getHeight() / 2));

			} else {
				// if its still on screen, beam fired is true and move the beam
				// by 10 pixels, and constantly check if the laser has hit
				beam.fired(true);
				beam.move(10);
				checkHit();
			}

		}

		// repaint the component
		repaint();
	}

	// create a private method that restarts the game, set the score to 0, set
	// the lives back to 3, spawn the rocks again and update the lives and score
	// labels, stops then starts the music again
	private void restart() {

		music.stopMusic();
		score = 0;
		ship.setLives(3);
		spawnRocks();
		lblLives.setIcon(new ImageIcon("images\\lives" + ship.getLives() + ".png"));
		lblScore.setText(Integer.toString(score));
		music.playMusic();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		// create a new graphics 2d object
		Graphics2D g2 = (Graphics2D) g;

		// draw the backgrond
		for (int i = 0; i < imgBackground.length; i++) {
			g2.drawImage(imgBackground[i].getImage(), xPos[i], 0, this);
		}

		// draw the ship
		ship.drawShip(g2);

		// draw the asteroids
		for (int i = 0; i < asteroid.length; i++) {
			asteroid[i].drawAsteroid(g2);
		}

		// if the beam hasnt been fired, dont draw the beam at all, else draw
		// the laser
		if (beam.isFired() == false) {

		} else {
			beam.drawLaser(g2);
		}

	}

	public void mouseMoved(MouseEvent e) {

		// set the location of the ship to the x and y position of the mouse
		// minus the width and height of the ship correspondingly, divided by 2
		// so the ship's center is located at the tip of the mouse, and whenever
		// the mouse moves, repaint
		ship.setLocation(e.getX() - ship.getWidth() / 2, e.getY() - ship.getHeight() / 2);

		if (beam.isFired() == false) {
			beam.setLocation(ship.getX() + (ship.getWidth() / 2), ship.getY() + (ship.getHeight() / 2));
		} else {

		}

		repaint();

	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// if the left click button was pressed, start the laser timer and set
		// if the beam is fired to true
		if (e.getButton() == MouseEvent.BUTTON1) {
			beam.fired(true);
			laserTimer.start();
		}

	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
