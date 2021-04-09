package game;


import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(new Color(212, 212, 212));
	}

	/**
	 * @see spaceshapes.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see spaeshapes.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * @see spaeshapes.Painter.fillRect.
	 */
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);
	}
	
	/**
	 * @see spaeshapes.Painter.getColor.
	 */
	public java.awt.Color getColor(){
		return _g.getColor();
	}
	
	/**
	 * @see spaeshapes.Painter.setColor.
	 */
	public void setColor(java.awt.Color color){
		_g.setColor(color);
	}

	/**
	 * @see spaeshapes.Painter.translate.
	 */
	public void translate(int x, int y) {
		_g.translate(x, y);
	}

	/**
	 * @see spaeshapes.Painter.drawCentredText.
	 */
	public void drawCentredText(String text, int x, int y, int width, int height) {
		FontMetrics metrics = _g.getFontMetrics();
		int xPosition = (width - metrics.stringWidth(text)) / 2;
		int yPosition = (metrics.getAscent() + (height - (metrics.getAscent() + metrics.getDescent())) / 2);
		_g.drawString(text, (xPosition + x), (yPosition + y));
	}

	/**
	 * @see spaeshapes.Painter.drawImage.
	 */
	public void drawImage(Image img, int x, int y, int width, int height) {
		_g.drawImage(img, x, y, width, height, null);
	}
}
