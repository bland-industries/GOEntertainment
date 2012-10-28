package goEntBeta2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import utilities.*;

public class GoButton extends JPanel {

	/***/
	ImageIcon image;

	/***/
	String title;

	/***/
	ActionListener listener;

	/***/
	ProgButton type;
	
	/*These are the sign style button options*/
	/***/
	private Dimension buttonSize;

	/***/
	private int fontSize;
	
	/***/
	private Color color;
	
	/***/
	private boolean blackBorder;
	
	/*****************************************************************
	Constructor for a sign button
	 *****************************************************************/
	public GoButton (Object object) {
		type = (ProgButton) object;

		title = type.getTitle().toUpperCase();

		listener = type.getListener();
		
		setDefaultStyle();
		
		addMouseListener(new ClickListener());
		
		setPreferredSize(buttonSize);
	}
	
	/*****************************************************************
	sets the default style choices for the sign style buttons
	 *****************************************************************/
	public void setDefaultStyle () {
		buttonSize = new Dimension (200,45);
		fontSize = 17;
		color = ProgramStyle.WHITESIGN;
		blackBorder = true;
	}


	/*****************************************************************
	Paints the image used for the "button" on the JPanel.

	@param page Graphics, like you do.
	 *****************************************************************/
	public void paintComponent (Graphics page) {
		
		try {   // if there is an image for this to paint
			image.paintIcon(this, page, 0, 0);
		}
		catch (NullPointerException e) { // if no image 
			
		ProgramStyle.paintSign(page, 0, 0, buttonSize.width, 
				buttonSize.height, color, blackBorder);
		
		page.setFont(ProgramStyle.getFont(fontSize));
		page.setColor(Color.BLACK);
		Point stringLocal = getTitleLocation(page);
		page.drawString(title,stringLocal.x, stringLocal.y);
		}
		
	}
	/*****************************************************************

	 *****************************************************************/
	private Point getTitleLocation (Graphics page) {
		
		//get the size of the string to be printed.
		FontMetrics fm = page.getFontMetrics(ProgramStyle.getFont(fontSize));
		Dimension stringSize = new Dimension(fm.stringWidth(title), 
				fm.getHeight());
		
		//Finds the center of the image
		Point center = new Point (buttonSize.width / 2, buttonSize.height / 2);
		
		//Finds the point bottom left of the string to make it centered on the image
		Point string = new Point(center.x-(stringSize.width/2), center.y + (stringSize.height/2)-3);

		return string;
	}

	/*****************************************************************

	 *****************************************************************/
	public void useImageForButton () {
		image = type.getIcon();
		buttonSize = new Dimension (image.getIconWidth(), image.getIconHeight());
		setPreferredSize(buttonSize);
	}

	/*****************************************************************

	 *****************************************************************/
	public Dimension getButtonSize() {
		return buttonSize;
	}

	/*****************************************************************

	 *****************************************************************/
	public void setButtonSize(Dimension buttonSize) {
		this.buttonSize = buttonSize;
	}

	/*****************************************************************

	 *****************************************************************/
	public int getFontSize() {
		return fontSize;
	}

	/*****************************************************************

	 *****************************************************************/
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/*****************************************************************

	 *****************************************************************/
	public Color getColor() {
		return color;
	}

	/*****************************************************************

	 *****************************************************************/
	public void setColor(Color color) {
		this.color = color;
	}

	/*****************************************************************

	 *****************************************************************/
	public boolean isBlackBorder() {
		return blackBorder;
	}

	/*****************************************************************

	 *****************************************************************/
	public void setBlackBorder(boolean blackBorder) {
		this.blackBorder = blackBorder;
	}

	/*****************************************************************
	Private Listener for the mouse to click the panel to activate the
	"button"
	 *****************************************************************/
	public class ClickListener implements MouseListener {

		/*****************************************************************
	detects the Mouse click and passes to the "button" listener the
	button type, enum.
		 *****************************************************************/
		@Override
		public void mouseClicked (MouseEvent event) {
			listener.actionPerformed(new ActionEvent (type, 0,
					title));
		}

		/*****************************************************************
	Unused interface methods
		 *****************************************************************/
		@Override
		public void mousePressed (MouseEvent event) {}

		@Override
		public void mouseReleased (MouseEvent event) {}

		@Override
		public void mouseEntered (MouseEvent event) {}

		@Override
		public void mouseExited (MouseEvent event) {}
	}

}
