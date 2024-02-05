package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;
	private int scores = 0;
	private int delay = 1;
	private Timer timer;
	private int playerX = 310;
	private int playerY = 540;
	private int ballposX = 300;
	private int ballposY = 450;
	private int ballXdir = -2;
	private int ballYdir = -4;
	private Map map = new Map(7, 7);
	private int bricks = map.rows() * map.colums();

	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setBackground(Color.DARK_GRAY);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

	}

	public void paint(Graphics gr) {
		super.printComponent(gr);
//		gr.setColor(Color.white);
//		gr.fillRect(1, 1, 692, 592);
		gr.setColor(Color.yellow);
		gr.fillRect(0, 0, 4, 592);
		gr.setColor(Color.red);
		gr.fillRect(683, 0, 3, 592);
		gr.setColor(Color.blue);
		gr.fillRect(0, 0, 692, 3);
		gr.setColor(Color.blue);
		gr.fillRect(playerX, playerY, 100, 8);
		gr.setColor(Color.green);
		gr.fillOval(ballposX, ballposY, 20, 20);
		map.drow((Graphics2D) gr);
		gr.setColor(Color.black);
		gr.setFont(new Font("serif", Font.BOLD, 20));
		gr.drawString("Score :" + scores, 10, 30);

		if (ballposY >= playerY) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			gr.setColor(Color.red);
			gr.setFont(new Font("serif", Font.BOLD, 30));
			gr.drawString("Game Over, Score: " + scores, 190, 300);
			gr.setFont(new Font("serif", Font.BOLD, 20));
			gr.drawString("Press Enter to Restart: ", 220, 330);

		}
		if (bricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			gr.setColor(Color.green);
			gr.setFont(new Font("serif", Font.BOLD, 40));
			gr.drawString("You Win, Score: " + scores, 170, 300);
			gr.setFont(new Font("serif", Font.BOLD, 20));
			gr.drawString("Press Enter to Restart: ", 230, 350);

		}
//		gr.dispose();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				playerX = 310;
				playerY = 540;
				ballposX = 300;
				ballposY = 450;
				ballXdir = -2;
				ballYdir = -4;
				map = new Map(7, 7);
				bricks = map.rows() * map.colums();
				scores = 0;
				repaint();

			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play) {
			outer: for (int i = 0; i < map.rows(); i++) {
				for (int j = 0; j < map.colums(); j++) {
					int brickX = j * map.brickWidth + 80;
					int brickY = i * map.brickHeight + 50;
					Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
					Rectangle brickRect = new Rectangle(brickX, brickY, map.brickWidth, map.brickHeight);
					if (ballRect.intersects(brickRect) && map.getBreakValue(i, j) == 1) {
						scores += 5;
						map.setBreakValue(0, i, j);
						bricks--;
						if (ballposX + 19 <= brickX || ballposX + 1 >= brickX + map.brickWidth) {
							ballXdir = -ballXdir;
						} else {
							ballYdir = -ballYdir;

						}
						break outer;

					}

				}
			}
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(playerX, playerY, 100, 8) || ballposY < 0) {
				ballYdir = -ballYdir;
			}

			if (ballposX < 0 || ballposX > 670) {
				ballXdir = -ballXdir;
			}
			ballposX += ballXdir;
			ballposY += ballYdir;
		}
		repaint();
	}

	private void moveLeft() {
		play = true;
		if (playerX > 8) {
			playerX -= 30;
		}
		if (playerX < 8) {
			playerX = 8;
		}
	}

	private void moveRight() {
		play = true;
		if (playerX < 580) {
			playerX += 30;
		}
		if (playerX > 580) {
			playerX = 580;
		}

	}

}
