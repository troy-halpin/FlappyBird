import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Pipe {
	private int xPos;
	private int yPos;
	private int speed;
	private int width;
	private int height;
	private int gap;
	private Rectangle2D.Double pipeBottom;
	private Rectangle2D.Double pipeTop;

	public Pipe(int x) {
		xPos = x;
		yPos = (int)(Math.random()*400) + 100;
		speed = -10;
		width = 45;
		height = 500;
		gap = 180;
		pipeBottom = new Rectangle2D.Double(xPos, yPos, width, height);
		pipeTop = new Rectangle2D.Double(xPos, (yPos - gap - height), width, height);
	}

	/*The pipes move from right to left across the screen, and loop back around.
	The gap between them is set but they appear at random Y positions*/
	public void move() {
		xPos += speed;

		if (xPos <= 0) {
			xPos = 800;
			yPos = (int)(Math.random()*400) + 100;
		}

		pipeTop.setRect(xPos, yPos, width, height);
		pipeBottom.setRect(xPos, (yPos - gap - height), width, height);
	}

	public void paint(Graphics2D brush) {
		brush.draw(pipeBottom);
		brush.draw(pipeTop);
		brush.setColor(Color.GREEN);
		brush.fill(pipeBottom);
		brush.fill(pipeTop);
	}

	public Rectangle2D.Double getBottomBounds() {
		return pipeBottom;
	}

	public Rectangle2D.Double getTopBounds() {
		return pipeTop;
	}
}
