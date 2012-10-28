package goEntBeta2;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ViewBackground implements ActionListener{

	/**Image used for the backGround*/
	ImageIcon backGround;
	
	JPanel panel;
	
	
	/**Timer set to move added images.*/
	//Timer timer;
	
	/*****************************************************************
	Paints the background image and the other images to be added later

	@param page Graphics because that is what you do.
	 *****************************************************************/
	public ViewBackground (JPanel panel) {
		//this is the panel to refresh and repaint
		this.panel = panel;
		
		
		//Collects the background image
		backGround = new ImageIcon("Images/CloudBackground.png");
		

		
		//sets up the timer
		//timer = new Timer (1000, this);
	
		//Starts the timer for later Use.
//		timer.start();
		
	}
	/*****************************************************************
	Paints the background image and the other images to be added later

	@param page Graphics because that is what you do.
	 *****************************************************************/
	public void paintComponent (Component c, Graphics page) {
		backGround.paintIcon(c, page, 0, 0);

		
	}
	
	/*****************************************************************
	Timer actionPerformed when the timer fires. 

	@param e ActionEvent that wont do anything
	 *****************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		panel.repaint();
	}
	
	
	
}
