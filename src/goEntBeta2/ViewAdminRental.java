/*****************************************************************
This displays the rental items in a dialog box

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

public class ViewAdminRental extends JDialog{

	/**The Item to be displayed*/
	Rental rental;
	
	/**The account open at the time*/
	Account openAccount;

	/**The size of the box*/
	Dimension size;

	/**The main panel to display*/
	JPanel mainPanel;

	/*****************************************************************
	Constructor for the dialog if the object is not rented by user
	 *****************************************************************/
	public ViewAdminRental (JFrame frame, Rental rental, Account openAccount) {
		super (frame, true);
		
		this.rental = rental;
		this.openAccount = openAccount;
		
		//removes frame buttons
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		//gets the screen size
		Dimension dim = ProgramStyle.windowSize();

		size = new Dimension(600,600);

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
	Constructor for the dialog if the object is rented by user
	 *****************************************************************/
	public ViewAdminRental (JFrame frame, Rented rented) {
		super (frame, true);
		
		
		this.rental = rented.rental;
		this.openAccount = rented.renter;
		
		//removes frame buttons
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		//gets the screen size
		Dimension dim = ProgramStyle.windowSize();

		size = new Dimension(600,600);

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
	Makes teh panel to fit nicely into the dialog box
	 *****************************************************************/
	public class DialogPanel extends JPanel {

		/*****************************************************************
		Creates the dialog box
		 *****************************************************************/
		public DialogPanel () {
			this.setPreferredSize(size);
			this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
			this.setLayout(new BorderLayout());
			this.setOpaque(false);

			this.add(setUpTitlePanel(), BorderLayout.NORTH);
			this.add(setUpMainPanel(), BorderLayout.CENTER);
			//this.add(setUpButtonPanel(), BorderLayout.SOUTH);
		}

		/*****************************************************************
		Makes a title panel
		 *****************************************************************/
		private JPanel setUpTitlePanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setOpaque(false);

			//adds the text desired
			JTextArea welcomeJLabel = new JTextArea(rental.getTitle());
			welcomeJLabel.setFont(ProgramStyle.getFont(30));
			welcomeJLabel.setForeground(ProgramStyle.WHITESIGN);
			welcomeJLabel.setOpaque(false);
			welcomeJLabel.setHighlighter(null);
			welcomeJLabel.setWrapStyleWord(true);  
			welcomeJLabel.setLineWrap(true);
			panel.add(welcomeJLabel, BorderLayout.CENTER);

			//cancel button in the top right
			GoButton cancel= new GoButton(LoginButtonEnum.CANCEL);
			cancel.setButtonSize(new Dimension(125, 45));
			panel.add(cancel, BorderLayout.EAST);

			return panel;
		}

		/*****************************************************************
		Sets up the main panel for the information
		 *****************************************************************/
		private JPanel setUpMainPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setOpaque(false);

			Font font = ProgramStyle.getFont(12);

			//Set up the West portion of the panel************************
			JPanel westPanel = new JPanel();
			westPanel.setLayout(new GridLayout(0,1));
			westPanel.setOpaque(false);

			JTextArea titleJLabel = new JTextArea(rental.getTitle());
			titleJLabel.setFont(ProgramStyle.getFont(20));
			titleJLabel.setOpaque(false);
			titleJLabel.setEditable(false);
			titleJLabel.setHighlighter(null);
			titleJLabel.setWrapStyleWord(true);  
			titleJLabel.setLineWrap(true);
			//westPanel.add(titleJLabel);
			
			if (rental instanceof TVSeason)
				westPanel.add(new JLabel("Season: "+((TVSeason) rental).getSeason()));
			
			westPanel.add(new JLabel("Year: " + rental.getYear()));
			westPanel.add(new JLabel("Genre: "+rental.getType()));
			westPanel.add(new JLabel("Rating: "+rental.getContent()));
			if (rental instanceof DVD)
				westPanel.add(new JLabel("Director: "+((DVD) rental).getDirector()));
			
			westPanel.add(new JLabel("Actors/Actresses"));
			westPanel.add(new JLabel(rental.getActor1()));
			westPanel.add(new JLabel(rental.getActor2()));
			westPanel.add(new JLabel(rental.getActor3()));
			westPanel.add(new JLabel(rental.getActor4()));
			westPanel.add(new JLabel(rental.getActor5()));
			
			//cost per day
			westPanel.add(new JLabel("Cost per day: $" + rental.getCostPerDay()));
			
			//ALl this depends on the information 
			

			panel.add(westPanel, BorderLayout.WEST);

			//Set up the East portion of the panel************************
			JPanel eastPanel = new JPanel();
			eastPanel.setPreferredSize(new Dimension (50, 150));
			eastPanel.setLayout(new GridLayout(0,1));
			eastPanel.setOpaque(false);

			//all this depends on the information
			
			panel.add(eastPanel, BorderLayout.EAST);

			return panel;
		}

		/*****************************************************************
		Makes the button panel on the bottom
		 *****************************************************************/
		private JPanel setUpButtonPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,3));
			panel.setOpaque(false);

			//this depends on the set up of the account.
			
			// add or remove from favorites
			if (openAccount.getFavoritesList().contains(rental)) {
				GoButton faveButton = new GoButton(UserButtonEnum.REMOVE_FAVORITE);
				faveButton.setButtonSize(new Dimension(180, 45));
				faveButton.setPassedString(rental.getTitle());
				panel.add(faveButton);
			}
			else{
				GoButton faveButton = new GoButton(UserButtonEnum.ADD_FAVORITE);
				faveButton.setButtonSize(new Dimension(180, 45));
				faveButton.setPassedString(rental.getTitle());
				panel.add(faveButton);
			}

			//add or remove from rent list
			if (openAccount.getReservedList().contains(rental)) {
				GoButton faveButton = new GoButton(UserButtonEnum.REMOVE_RENTLIST);
				faveButton.setButtonSize(new Dimension(180, 45));
				faveButton.setPassedString(rental.getTitle());
				panel.add(faveButton);
			}
			else {
				GoButton faveButton = new GoButton(UserButtonEnum.ADD_RENTLIST);
				faveButton.setButtonSize(new Dimension(180, 45));
				faveButton.setPassedString(rental.getTitle());
				panel.add(faveButton);
			}
			
			//rent or return the rental
			if (!rental.isChecked()) {
				GoButton rentButton = new GoButton(UserButtonEnum.RENT);
				rentButton.setButtonSize(new Dimension(180, 45));
				rentButton.setPassedString(rental.getTitle());
				panel.add(rentButton);
			}
			else if (openAccount.getCheckedOutList().containsItem(rental)) {
				GoButton returnButton = new GoButton(UserButtonEnum.RETURN);
				returnButton.setButtonSize(new Dimension(180, 45));
				returnButton.setPassedString(rental.getTitle());
				panel.add(returnButton);
			}
			return panel;
		}

		/*****************************************************************
		Paints pretty pictures
		@param page Graphics - like you do
		 *****************************************************************/
		public void paintComponent (Graphics page) {
			ProgramStyle.paintSign(page, 0, 0, size.width, size.height,
					ProgramStyle.GREENSIGN, false);
			
			ImageIcon image = new ImageIcon("Images/MovieImages/" +
						rental.getIconLocation());
			image.paintIcon(this, page, 360, 150);
		}
	}
}
