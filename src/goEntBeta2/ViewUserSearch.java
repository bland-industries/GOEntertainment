
/*****************************************************************
Dialog box showing the options to search the list.

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utilities.LoginButtonEnum;
import utilities.ProgramStyle;
import utilities.Rental;
import utilities.RentalCategory;
import utilities.Rented;
import utilities.UserButtonEnum;

public class ViewUserSearch extends JDialog{


	/**A pull down menu of TV, Movie or Either*/
	JComboBox typeComboBox;

	/**A pull down menu containing the RentalCategories enum*/
	JComboBox genreComboBox;

	/**Text field to type in the search parameters*/
	JTextField stringSearch;

	/**Formatted text field for the year to search after*/
	JFormattedTextField year1;

	/**Formatted text field for the year to search before*/
	JFormattedTextField year2;

	/**Size of the dialog window*/
	Dimension size;


	/*****************************************************************
	Constructor for the frame portion of the dialog box. Determines	
	the characteristics of the dialog frame.

	@param frame JFrame to set the dialog box to.
	 *****************************************************************/
	public ViewUserSearch (JFrame frame) {
		super (frame, true);

		//removes frame buttons
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		//gets the screen size
		Dimension dim = ProgramStyle.windowSize();

		//Size to set the dialog box to.
		size = new Dimension(600,375);

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
	Getter to collect the input type of media. Three options being
	TV, Movie or Either.

	@return String choice of type of media.
	 *****************************************************************/
	public String getTypeChosen () {
		//TODO return the type of chosen
		return (String) typeComboBox.getSelectedItem();
	}

	/*****************************************************************
	Collects he chosen genre
	 *****************************************************************/
	public RentalCategory getGenreChosen () {
		// return the genre of chosen
		return (RentalCategory) genreComboBox.getSelectedItem();
	}

	/*****************************************************************
	Collects the search string
	 *****************************************************************/
	public String getSearchString () {
		// return the string of the search typed in
		return stringSearch.getText();
	}

	/*****************************************************************
	Collects the input years
	 *****************************************************************/
	public int[] getSearchYears () {
		// return the two years chosen to search between
		int[] years = new int[2];
		try { //checks if the input is right
			years[0] = Integer.parseInt((String) year1.getText());
			years[1] = Integer.parseInt((String) year2.getText());
		} catch (NumberFormatException e) {
			this.dispose();
		} 
		return years;
	}


	/*****************************************************************
	The panel placed inside the dialog frame
	 *****************************************************************/
	public class DialogPanel extends JPanel {

		/*****************************************************************
		Constructor, sets up the preferences.
		 *****************************************************************/
		public DialogPanel () {
			this.setPreferredSize(size);
			this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
			this.setLayout(new BorderLayout());
			this.setOpaque(false);

			this.add(setUpTitlePanel(), BorderLayout.NORTH);
			this.add(setUpMainPanel(), BorderLayout.CENTER);
			this.add(setUpButtonPanel(), BorderLayout.SOUTH);
		}

		/*****************************************************************
		Builds the tile panel
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

			//adds the text desired, and styling
			JLabel welcomeJLabel = new JLabel("SEARCH FOR...");
			welcomeJLabel.setFont(ProgramStyle.getFont(40));
			welcomeJLabel.setForeground(ProgramStyle.WHITESIGN);
			welcomeJLabel.setOpaque(false);
			textPanel.add(welcomeJLabel, BorderLayout.NORTH);

			//adds the text desired, and styling
			JLabel textJLabel = new JLabel("SEARCH WITH ANY OR ALL OF THE FOLLOWING");
			textJLabel.setFont(ProgramStyle.getFont(12));
			textJLabel.setForeground(ProgramStyle.WHITESIGN);
			textJLabel.setOpaque(false);
			textPanel.add(textJLabel, BorderLayout.SOUTH);

			panel.add(textPanel, BorderLayout.CENTER);

			return panel;
		}

		/*****************************************************************
		Builds the Main panel
		 *****************************************************************/
		private JPanel setUpMainPanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
			panel.setOpaque(false);

			Font font = ProgramStyle.getFont(12);

			//Set up the West portion of the panel************************
			JPanel categoryPanel = new JPanel();
			categoryPanel.setLayout(new GridLayout(0,1));
			categoryPanel.setOpaque(false);

			categoryPanel.add(new JLabel("Search by Type..."));
			String[] type = {"Either", "Movies", "TV Shows"};
			typeComboBox = new JComboBox(type);
			categoryPanel.add(typeComboBox);

			categoryPanel.add(new JLabel("Search by Genre..."));
			genreComboBox = new JComboBox(RentalCategory.values());
			categoryPanel.add(genreComboBox);

			panel.add(categoryPanel, BorderLayout.WEST);

			//Set up the Center portion of the panel************************
			JPanel stringPanel = new JPanel();
			stringPanel.setLayout(new GridLayout(0,1));
			stringPanel.setOpaque(false);

			stringPanel.add(new JLabel("Search for..."));
			stringSearch = new JTextField();
			stringPanel.add(stringSearch);

			panel.add(stringPanel, BorderLayout.CENTER);

			//Set up the East portion of the panel************************
			JPanel yearPanel = new JPanel();
			yearPanel.setLayout(new GridLayout(0,1));
			yearPanel.setOpaque(false);

			yearPanel.add(new JLabel("Search Between the Years..."));
			year1 = new JFormattedTextField(1900);
			yearPanel.add(year1);
			yearPanel.add(new JLabel("And..."));
			year2 = new JFormattedTextField(2012);
			yearPanel.add(year2);

			panel.add(yearPanel, BorderLayout.EAST);

			return panel;
		}

		/*****************************************************************
		builds the button panel at the bottom
		 *****************************************************************/
		private JPanel setUpButtonPanel() {
			JPanel panel = new JPanel();
			panel.setOpaque(false);

			GoButton searchButton = new GoButton(UserButtonEnum.SEARCH);
			searchButton.setButtonSize(new Dimension(180, 45));
			panel.add(searchButton);

			GoButton cancelButton = new GoButton(LoginButtonEnum.CANCEL);
			cancelButton.setButtonSize(new Dimension(180, 45));
			panel.add(cancelButton);

			return panel;
		}

		/*****************************************************************
		Paints the pretty background

		@param page Graphics - like you do
		 *****************************************************************/
		public void paintComponent (Graphics page) {
			ProgramStyle.paintSign(page, 0, 0, size.width, size.height,
					ProgramStyle.GREENSIGN, false);
		}
	}
}
