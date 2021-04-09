package game;


import java.awt.Image;

//import java.awt.Color;

/** 
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public interface Painter {
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the 
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);
	
	/**
	 * Fills a rectangle. Parameters x and y specify the position of the 
	 * rectangle, parameters width and height the size of the rectangle.
	 */
	public void fillRect(int x, int y, int width, int height);
	
	/**
	 * Given a string representing a color, returns the 'Color' value code.
	 */
	public java.awt.Color getColor();
	
	/**
	 * Sets the current color of the Graphics object (Painter in this case)
	 */
	public void setColor(java.awt.Color color);
	
	/**
	 * Sets the current coordinate of the Graphics object (Painter in this case)
	 */
	public void translate(int x, int y);
	
	/**
	 * If a text is associated with the shape this method centers and paints that
	 * text.
	 */
	public void drawCentredText(String text, int x, int y, int width, int height);
	
	/**
	 * Paints the specified image, given the img object
	 */
	public void drawImage(Image img, int x, int y, int width, int height);
}
