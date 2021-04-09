package game.game_logic;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlockDGUI extends JPanel implements KeyListener{
	
	private JPanel _boardPanel;

	public BlockDGUI(ScoreBoard score) {
		_boardPanel = new Board(score);
		this.add(_boardPanel);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("BlockD Game");
				frame.add(new BlockDGUI(new ScoreBoard()));
		
				// Set window properties.
				frame.setSize(1000, 1000);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	public void keyTyped(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    Command command = null;
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	        	command = new upCommand();
	            break;
	        case KeyEvent.VK_DOWN:
	        	command = new downCommand();
	            break;
	        case KeyEvent.VK_LEFT:
	        	command = new leftCommand();
	            break;
	        case KeyEvent.VK_RIGHT :
	        	command = new rightCommand();
	            break;
	        case KeyEvent.VK_SPACE :
	        	command = new breakCommand();
	            break;
	        default:
	        	System.out.println("Invalid key pressed");
	        	break;
	     }
	}
	
	/**
	 * Called by the Swing framework whenever this AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this AnimationViewer 
	 * object has been opened, exposed or moved.
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);
		
		_boardPanel.paint(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
