/****************************************************************
displays price and makes them pay

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;

import utilities.LoginButtonEnum;
import utilities.ProgramStyle;
import utilities.Rental;
import utilities.Rented;
import utilities.UserButtonEnum;

public class ViewUserCheckout extends JDialog{

	Rental rental;
	
	double owed;

	Dimension size;

	JPanel mainPanel;

	/*****************************************************************
	 * creates the dialog box to display values
	 *****************************************************************/
	public ViewUserCheckout (JFrame frame, double cost) {
		super (frame, true);
		owed = cost;
		
		//removes frame buttons
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		//gets the screen size
		Dimension dim = ProgramStyle.windowSize();

		size = new Dimension(600,200);

		// Determine the new location of the window, centered on the screen
		int x = (dim.width/2) - (size.width/2);
		int y = (dim.height/2) - (size.height/2);

		// Moves the window to the center
		this.setLocation(x, y);

		//adds the panel
		this.getContentPane().add(new DialogPanel ());
		pack();
	}

	/*****************************************************************
	 * creates the panel for the dialog frame
	 *****************************************************************/
	public class DialogPanel extends JPanel {

		/*****************************************************************
		 * sets the settings for the panel
		 *****************************************************************/
		public DialogPanel () {
			this.setPreferredSize(size);
			this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
			this.setLayout(new BorderLayout());
			this.setOpaque(false);

			this.add(setUpTitlePanel(), BorderLayout.NORTH);

		}

		/*****************************************************************
		 * Sets up the title panel and populates it
		 *****************************************************************/
		private JPanel setUpTitlePanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setOpaque(false);

			//puts the logo in the top left corner
			panel.add(new JLabel(new ImageIcon("Images/ShieldSign.png")),
					BorderLayout.WEST);

			//Text section on the top right of the panel
			JPanel textPanel = new JPanel();
			textPanel.setLayout(new BorderLayout());
			textPanel.setOpaque(false);

			//adds the text desired
			//adds the text desired
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			 
			JLabel welcomeJLabel = new JLabel("YOU OWE " + nf.format(owed));
			welcomeJLabel.setFont(ProgramStyle.getFont(40));
			welcomeJLabel.setForeground(ProgramStyle.WHITESIGN);
			welcomeJLabel.setOpaque(false);
			textPanel.add(welcomeJLabel, BorderLayout.NORTH);

			//Text section on the top right of the panel
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			buttonPanel.setOpaque(false);
			
			GoButton payButton = new GoButton(UserButtonEnum.PAY);
			payButton.setButtonSize(new Dimension(180, 45));
			buttonPanel.add(payButton, BorderLayout.WEST);
			
			GoButton cancelButton = new GoButton(LoginButtonEnum.CANCEL);
			cancelButton.setButtonSize(new Dimension(180, 45));
			buttonPanel.add(cancelButton, BorderLayout.EAST);

			textPanel.add(buttonPanel, BorderLayout.SOUTH);
			
			panel.add(textPanel, BorderLayout.CENTER);


			return panel;
		}



		/*****************************************************************
		 * paints the background
		@param page Graphics - like you do
		 *****************************************************************/
		public void paintComponent (Graphics page) {
			ProgramStyle.paintSign(page, 0, 0, size.width, size.height,
					ProgramStyle.GREENSIGN, false);

		}

	}

}
