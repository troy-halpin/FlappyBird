import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

public class FlappyBird {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel game = new GamePanel();
		frame.add(game);
		frame.setVisible(true);
	}

	private static class GamePanel extends JPanel {
		
		Bird bird;
		Pipe pipe1;
		Pipe pipe2;
		Timer moverTimer;
		Timer scoreTimer;
		int score;
		boolean isDead;
		ImageIcon background;

		public GamePanel() {
			bird = new Bird();
			pipe1 = new Pipe(600);
			pipe2 = new Pipe(1000);
			score = 0;
			isDead = false;
			moverTimer = new Timer(30, new GameMotion());
			scoreTimer = new Timer(1600, new ScoreCounter());
			moverTimer.start();
			scoreTimer.start();

			addKeyListener(new KeyAdapter());
			setFocusable(true);

			background = new ImageIcon("background.png");

		}

		//Setting the jumping to the spacebar
		private class KeyAdapter implements KeyListener {
			public void keyPressed(KeyEvent evt) {

				int key = evt.getKeyCode();

				if (key == KeyEvent.VK_SPACE) {
					bird.jump();
				}
			}

			//not used
			public void keyReleased(KeyEvent evt) {}
			public void keyTyped(KeyEvent evt) {}
		}

		//motion, just two pipes scrolling across the sceen and the movement of the bird
		private class GameMotion implements ActionListener {
			public void actionPerformed(ActionEvent evt) {
				pipe1.move();
				pipe2.move();
				bird.move();

				checkForHit();
				
				//If the player hasn't run into any pipes, repaint is called and the game continues
				repaint();
			}
		}

		/*Currently increases score with each repaint, not when you pass a pipe. The timing
		is very close, but if you adjust the speed it can get off.*/
		private class ScoreCounter implements ActionListener {
			public void actionPerformed(ActionEvent evt) {
				score += 1;
			}
		}

		@Override
		//'painting' the game
		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;

			g2.drawImage(background.getImage(),0,0,null);

			//game over screen
			if (isDead) {
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Serif", Font.PLAIN, 30));
				g2.drawString("Game Over!", 300, 300);
				g2.drawString("Your final score was " + score, 250, 330);
				moverTimer.stop();
				scoreTimer.stop();
			}
			//main game screen
			else {
				pipe1.paint(g2);
				pipe2.paint(g2);
				bird.paint(g2);

				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Seriff", Font.PLAIN, 20));
				g2.drawString("Score: " + score, 50, 50);
			}

		}
		//Will check to see if any part of the bird's hit box intersects a pipe
		public void checkForHit() {
			if (pipe1.getTopBounds().intersects(bird.getBounds())
			|| pipe2.getTopBounds().intersects(bird.getBounds())
			|| pipe1.getBottomBounds().intersects(bird.getBounds())
			|| pipe2.getBottomBounds().intersects(bird.getBounds())
			|| bird.getBounds().intersects(new Rectangle2D.Double(0,600,800,10))) {
				isDead = true;
			}
		}
	}
}
