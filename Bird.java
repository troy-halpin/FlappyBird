import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class Bird {

	private int speed;
	private int acceleration;
	private int xPos;
	private int yPos;
	private int height;
	private int width;
	private ImageIcon img;


	//Uses a bird image pulled off the internet
	public Bird() {
		speed = -15;
		acceleration = 1;
		xPos = 200;
		yPos = 300 - height/2;
		img = new ImageIcon("bird.png");
		height = img.getIconHeight();
		width = img.getIconWidth();
		xPos = 200;
		yPos = 300 - height/2;
	}

	//Mimics gravity, will accelerate downwards
	public void move() {
		speed += acceleration;
		yPos += speed;
	}
	//Moves the bird up. Can adjust to change difficulty
	public void jump() {
		speed = -15;
		
	}

	public void paint(Graphics2D brush) {
		brush.drawImage(img.getImage(), xPos, yPos, null);
	}

	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(xPos, yPos, width, height);
	}
}
