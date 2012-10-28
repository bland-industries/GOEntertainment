/*****************************************************************
Animated Bwata Logo 

@author Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;


public class BwataLogo extends JDialog implements ActionListener
{
	/** Array of the images to display*/
    ArrayList<ImageIcon> bwataFrames;

    /**Object that holds the Image while it is displayed */
    private JLabel displayLogo;

    /**Keeps the pace of the image change*/
    private Timer timer;

    /**keeps track of the frame displayed*/
    private int frameNumber;
    
    /**Counts how long to display the dialog box*/
    private int timerCount;

    
    /*****************************************************************
	Constructs the Logo display properties and stats the animation
	
	@param frame JFrame to attach this window to.
	*****************************************************************/
    public BwataLogo (JFrame frame) {
    	/*initializes the parent object, attach it to the current frame
    				and makes it model*/
        super(frame, true);
        
        //removes frame buttons
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        
        //new panel to contain the images
        JPanel panel = new JPanel();
        
        //gets the screen size
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        int logoWidth = 504;	//based on image
        int logoHight = 172;	//based on image

        //Panel properties
        panel.setPreferredSize(new Dimension (logoWidth, logoHight));
        panel.setBackground (Color.BLACK);

        // Determine the new location of the window, centered on the screen
        int x = (dim.width/2) - (logoWidth/2);
        int y = (dim.height/2) - (logoHight/2);

        // Moves the window to the center
        this.setLocation(x, y);

        //initializes the array of images
        bwataFrames = new ArrayList<ImageIcon>();
        for (int count = 1; count <= 5; count ++) {
            bwataFrames.add(new ImageIcon("Images/BwataLogo/Bwata" + count + ".png"));
        }

        //set up timer and counters
        timer = new Timer (750, this);
        timer.setInitialDelay(850);
        frameNumber = 0;
        timerCount = 0;

        //diplays the first image
        displayLogo = new JLabel(bwataFrames.get(frameNumber));
        panel.add(displayLogo);
        
        //finalizes the dialog settings
        this.getContentPane().add(panel);
        this.pack();

        //Begin timing of images
        timer.start();
    }
    
    /*****************************************************************
	Stops the timer when the animation is done and disposes
	the window.
	*****************************************************************/
    private void stopTimer () {
        timer.stop();
        dispose();
    }

    /*****************************************************************
   	Action to perform when timer fires. Change image on logo and 
   	cycle through the counts.
   	*****************************************************************/
    public void actionPerformed (ActionEvent event) {
    	//changes the image
        displayLogo.setIcon(bwataFrames.get(frameNumber));
        
        //determines if there is another frame to show
        if (frameNumber < bwataFrames.size()-1)
            frameNumber++;

        timerCount ++;
        //when counter reaches 8 stop and remove window
        if (timerCount >= 8)
            stopTimer();
    }
}


