/*****************************************************************
Draws the background for the views.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class ViewBackground {

	/**Image used for the backGround*/
	ImageIcon backGround;
	
	/**The panel to display*/
	JPanel panel;
	
	/*****************************************************************
	Paints the background image and the other images to be added later

	@param page Graphics because that is what you do.
	 *****************************************************************/
	public ViewBackground (JPanel panel) {
		//this is the panel to refresh and repaint
		this.panel = panel;
		
		//Collects the background image
		backGround = new ImageIcon("Images/CloudBackground.png");
	}
	/*****************************************************************
	Paints the background image and the other images to be added later

	@param page Graphics because that is what you do.
	 *****************************************************************/
	public void paintComponent (Component c, Graphics page) {
		backGround.paintIcon(c, page, 0, 0);
	}
}
