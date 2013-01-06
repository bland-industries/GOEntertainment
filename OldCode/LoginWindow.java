/*****************************************************************
This is the class that holds all the JPanels in the program.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utilities.*;


public class LoginWindow extends JDialog {

	/**THis is the JPanel that holds the backGround image.*/
	JPanel backgroundPanel;

	/**This is the size of the JDialog window*/
	Dimension dialogSize;

	public LoginWindow (JFrame frame, Dimension windowSize) {
		super(frame, true);

		//Settings for the JDialog
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setResizable(false);

		//Sets dimensions of the window based on the screen size
		dialogSize = new Dimension ((windowSize.height -500), (windowSize.height -500));

		backgroundPanel = new BackGroundPanel();
		backgroundPanel.setPreferredSize(dialogSize);


		// Determine the new location of the window, centered on the screen
		int x = (windowSize.width/2) - (dialogSize.width/2);
		int y = (windowSize.height/2) - (dialogSize.height/2);

		// Moves the window to the center
		this.setLocation(x, y);

		//finalizes the JDialog
		this.getContentPane().add(backgroundPanel);
		pack();

	}



	/*****************************************************************
	This is the JPanel that actually holds all the information for 
	viewing

	@param page Graphics because that is what you do.
	 *****************************************************************/
	public class BackGroundPanel extends JPanel {

		/**Integer of how thick the border is around the window*/
		final int OFFSET = 40;

		/**Panel that contains all the content of the window*/
		JPanel mainPanel;
		
		JTextField userNameTF;
		JTextField passwordTF;
		JButton button;

		public BackGroundPanel () {
			//sets up the layout manager
			setLayout (new BorderLayout());

			//adds the Main Panel to the center within the border
			mainPanel = new JPanel();
			mainPanel.setLayout(new GridLayout(0, 1));
			mainPanel.setOpaque(false);

			//AddELements that for logging in.
			userNameTF = new JTextField( "User Name", 10);
			passwordTF = new JTextField("Password", 10);
			button = new JButton("GO");
			button.addActionListener(new ButtonListener());
			
			mainPanel.add(button, SwingConstants.CENTER);
			mainPanel.add(passwordTF, SwingConstants.CENTER);
			mainPanel.add(userNameTF, SwingConstants.CENTER);
		

			/*Add buffers for the border in order to center the 
					login items*/
			JPanel[] panels = new JPanel[4];
			for (int index = 0; index < panels.length; index ++) {
				panels[index] = new JPanel();
				panels[index].setPreferredSize(new Dimension (150, 150));
				panels[index].setOpaque(false);
			}
			
			add(panels[0], BorderLayout.NORTH);
			add(panels[1], BorderLayout.SOUTH);
			add(panels[2], BorderLayout.EAST);
			add(panels[3], BorderLayout.WEST);
			
			this.add(mainPanel, BorderLayout.CENTER);
			
		}

		/*****************************************************************
		Paints the backGround for the whole window.

		@param page Graphics because that is what you do.
		 *****************************************************************/
		public void paintComponent (Graphics page) {
			int offset;		//temporary offset for to draw items.

			page.setColor(ProgramStyle.ORANGE);
			page.fillRect(0, 0, dialogSize.width, dialogSize.height);
			
			offset = OFFSET - 10;
			page.setColor(ProgramStyle.LTORANGE);
			page.fillOval(offset, offset, 
				dialogSize.width - offset*2, dialogSize.height - offset*2);
			
			offset = OFFSET;
			page.setColor(ProgramStyle.GREENLIGHT);
			page.fillOval(offset, offset, 
				dialogSize.width - offset*2, dialogSize.height - offset*2);
			

		}

	}
	
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
		}
		
		
		
	}
}


