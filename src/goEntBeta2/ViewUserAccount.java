/****************************************************************
displays the account information for editing

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import utilities.LoginButtonEnum;
import utilities.ProgramStyle;
import utilities.UserButtonEnum;


public class ViewUserAccount extends JDialog{
	/** account to edit*/
	Account account;

	/** eMail to edit*/
	JLabel eMailLabel;
	JTextField eMail;

	/** first name to edit*/
	JLabel firstNameLabel;
	JTextField firstName;

	/** last name to edit*/
	JLabel lastNameLabel;
	JTextField lastName;

	/** street address to edit*/
	JLabel addressLabel;
	JTextField address1;
	JTextField address2;

	/** city to edit*/
	JLabel cityLabel;
	JTextField city;

	/** state to edit*/
	JLabel stateLabel;
	JTextField state;

	/** zip code to edit*/
	JLabel zipLabel;
	JTextField zipCode;

	/** password to edit*/
	JLabel passwordLabel;
	JTextField password1;

	/** password confirm to edit*/
	JLabel confirmPasswordLabel;
	JTextField password2;

	/** the size of the user account panel */
	Dimension size;

	/** the main panel for the user account */
	JPanel mainPanel;

	/*****************************************************************
	 * set the parameters of the dialog box
	 *****************************************************************/
	public ViewUserAccount (JFrame frame, Account account) {
		super (frame, true);
		this.account = account;

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
	 * this makes sure that all user input is correctly formatted
	 *****************************************************************/
	public boolean checkInput() {
		boolean[] checks = new boolean[4];
		
		//checks each if they are invalid and changes the label
		checks[0] = checkPassword();
		checks[1] = checkEMail();
		checks[2] = checkStateCode();
		checks[3] = checkZipCode();
		
		//if one is invalid it will return false;
		for (boolean check: checks) 
			if (!check)
				return false;
		
		//if all are valid returns true
		return true;

	}

	/*****************************************************************
	 * checks to see if the entered password and actual password is 
	 * equal
	 *****************************************************************/
	private boolean checkPassword()	{
		//checks to see if the two passwords are equal
		if (password1.getText().length() == 0) {
			System.out.println ("password blank");
			passwordLabel.setForeground(Color.RED);
			passwordLabel.setText("PLEASE ENTER A PASSWORD:");
			return false;
		}
		else if (password1.getText().equals(password2.getText())){
			System.out.println ("password ok");
			confirmPasswordLabel.setForeground(Color.BLACK);
			confirmPasswordLabel.setText("CONFIRM PASSWORD:");
			passwordLabel.setForeground(Color.BLACK);
			passwordLabel.setText("PASSWORD:");
			return true;
		}
		else {
			System.out.println ("password not equal");
			passwordLabel.setForeground(Color.RED);
			passwordLabel.setText("PASSWORDS NOT EQUAL:");
			confirmPasswordLabel.setForeground(Color.RED);
			confirmPasswordLabel.setText("PASSWORDS NOT EQUAL:");
			return false;
		}
	}

	/*****************************************************************
	 * Checks to make sure that the email entered is valid
	 *****************************************************************/
	private boolean checkEMail() {
		String test = eMail.getText();

		int atIndex = test.indexOf('@');
		int dotIndex = test.lastIndexOf('.');

		//tests if there is an @ symbol
		if (atIndex <= 0) {
			System.out.println ("no at sign");
			eMailLabel.setForeground(Color.RED);
			eMailLabel.setText("INVALID EMAIL:");
			return false;
		}

		//tests if there are charactor between the last dot and the @
		if (dotIndex - atIndex < 1){
			System.out.println ("dot - @ = " + (dotIndex - atIndex));
			eMailLabel.setForeground(Color.RED);
			eMailLabel.setText("INVALID EMAIL:");
			return false;
		}

		//tests if there is a dot not in the last position
		if (dotIndex < 0 && dotIndex < test.length()-1){
			System.out.println ("dot invalid");
			eMailLabel.setForeground(Color.RED);
			eMailLabel.setText("INVALID EMAIL:");
			return false;
		}

		System.out.println ("email ok");
		eMailLabel.setForeground(Color.BLACK);
		eMailLabel.setText("EMAIL:");
		return true;

	}

	/*****************************************************************
	 * this checks to make sure the state code entered is 2 characters
	 * long
	 *****************************************************************/
	private boolean checkStateCode() {
		//checks to make sure the state code is two char long
		if(state.getText().length() != 2) {
			System.out.println ("state length wrong");
			stateLabel.setForeground(Color.RED);
			stateLabel.setText("INVALID STATE CODE:");
			return false;
		}
		else if(!state.getText().matches("[A-Z]+")) {//(".*\\d.*")) {
			System.out.println ("state has numbers");
			stateLabel.setForeground(Color.RED);
			stateLabel.setText("INVALID STATE CODE:");
			return false;
		}
		else {
			System.out.println ("state ok");
			stateLabel.setForeground(Color.BLACK);
			stateLabel.setText("STATE CODE:");
			return true;
		}
	}

	/*****************************************************************
	 * This checks to make sure the zip code is 5 characters long
	 *****************************************************************/
	private boolean checkZipCode() {
		//checks to make sure the state code is five char long
		if(zipCode.getText().length() != 5 || !zipCode.getText().matches("[0-9]+")) {
			System.out.println ("zip not 5 and not only numbers");
			zipLabel.setForeground(Color.RED);
			zipLabel.setText("INVALID ZIP CODE:");
			return false;
		}
		else {
			System.out.println ("zip ok");
			zipLabel.setForeground(Color.BLACK);
			zipLabel.setText("ZIP CODE:");
			return true;
		}

	}

	/*****************************************************************
	 * gets the typed email
	 *****************************************************************/
	public String getTypedEMail () {
		return eMail.getText().toLowerCase();
	}

	/*****************************************************************
	 * returns the account that was created by the dialog panels
	 *****************************************************************/
	public Account getAccount () {
			
		//self explanitory setting of the Account variables.
		account.seteMail(eMail.getText().toLowerCase());
		account.setFirstName(firstName.getText());
		account.setLastName(lastName.getText());
		account.setPassword(password1.getText());
		
		///set up the address to be saved as one long string
		String adr = address1.getText() + "ZzZ";
		adr = adr + address2.getText() + "ZzZ";
		adr = adr + city.getText() + "ZzZ";
		adr = adr + state.getText() + "ZzZ";
		adr = adr + zipCode.getText() + "ZzZ";
		account.setAddress(adr);
		
		return account;
	}

	/*****************************************************************
	 * creates the panel to put inside the dialog frame
	 *****************************************************************/
	public class DialogPanel extends JPanel {

		/*****************************************************************
		 * sets the preferences of the panel
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
		 * sets up the title panel and populates it
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
			JTextArea welcomeJLabel = new JTextArea("ACCOUNT INFO");
			welcomeJLabel.setFont(ProgramStyle.getFont(40));
			welcomeJLabel.setForeground(ProgramStyle.WHITESIGN);
			welcomeJLabel.setOpaque(false);
			welcomeJLabel.setEditable(false);
			welcomeJLabel.setHighlighter(null);
			welcomeJLabel.setWrapStyleWord(true);  
			welcomeJLabel.setLineWrap(true);
			textPanel.add(welcomeJLabel, BorderLayout.NORTH);

			JTextArea textJLabel = new JTextArea("");
			textJLabel.setFont(ProgramStyle.getFont(12));
			textJLabel.setForeground(ProgramStyle.WHITESIGN);
			welcomeJLabel.setEditable(false);
			welcomeJLabel.setHighlighter(null);
			textJLabel.setOpaque(false);
			textJLabel.setWrapStyleWord(true);  
			textJLabel.setLineWrap(true);
			textPanel.add(textJLabel, BorderLayout.SOUTH);

			panel.add(textPanel, BorderLayout.CENTER);


			return panel;
		}

		/*****************************************************************
		 * sets up the main panel and populates it
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

			//email label and field
			eMailLabel = new JLabel("E-MAIL ADDRESS:");
			eMailLabel.setFont(font);
			eMail = new JTextField(account.geteMail(),20);

			westPanel.add(eMailLabel);
			westPanel.add(eMail);

			//first and last name labels and fields
			firstNameLabel = new JLabel("FIRST NAME:");
			firstNameLabel.setFont(font);
			firstName = new JTextField(account.getFirstName(), 20);

			westPanel.add(firstNameLabel);
			westPanel.add(firstName);

			lastNameLabel = new JLabel("LAST NAME:");
			lastNameLabel.setFont(font);
			lastName = new JTextField(account.getLastName(), 20);

			westPanel.add(lastNameLabel);
			westPanel.add(lastName);

			//Password1
			passwordLabel = new JLabel("PASSWORD:");
			passwordLabel.setFont(font);
			password1 = new JTextField(account.getPassword(), 20);

			westPanel.add(passwordLabel);
			westPanel.add(password1);

			//Password2
			confirmPasswordLabel = new JLabel("CONFIRM PASSWORD:");
			confirmPasswordLabel.setFont(font);
			password2 = new JTextField(account.getPassword(), 20);

			westPanel.add(confirmPasswordLabel);
			westPanel.add(password2);

			panel.add(westPanel, BorderLayout.WEST);

			//Set up the East portion of the panel************************
			JPanel eastPanel = new JPanel();
			eastPanel.setLayout(new GridLayout(0,1));
			eastPanel.setOpaque(false);

			//the address is the only thing on the east side
			addressLabel = new JLabel("ADDRESS:");
			addressLabel.setFont(font);

			String[] addressStrings = account.getAddress().split("ZzZ");
			
			//two lines of address
			address1 = new JTextField(addressStrings[0], 20);
			address2 = new JTextField(addressStrings[1], 20);
			eastPanel.add(addressLabel);
			eastPanel.add(address1);
			eastPanel.add(address2);


			//City
			cityLabel = new JLabel("CITY:");
			cityLabel.setFont(font);
			city = new JTextField(addressStrings[2], 20);

			eastPanel.add(cityLabel);
			eastPanel.add(city);

			//state
			stateLabel = new JLabel("STATE CODE:");
			stateLabel.setFont(font);
			state = new JTextField(addressStrings[3], 2);

			eastPanel.add(stateLabel);
			eastPanel.add(state);

			//zip
			zipLabel = new JLabel("ZIP CODE:");
			zipLabel.setFont(font);
			zipCode = new JTextField(addressStrings[4], 5);

			eastPanel.add(zipLabel);
			eastPanel.add(zipCode);

			panel.add(eastPanel, BorderLayout.EAST);

			return panel;


		}

		/*****************************************************************
		 * sets up the button panel and populates it
		 *****************************************************************/
		private JPanel setUpButtonPanel() {
			JPanel panel = new JPanel();
			//buttonPanel.setLayout(new BorderLayout());
			panel.setOpaque(false);

			panel.add(new GoButton(UserButtonEnum.SAVE_ACCOUNT));
			panel.add(new GoButton(LoginButtonEnum.CANCEL));


			return panel;
		}

		/*****************************************************************
		 * paints pretty pictures / paints the background
		 * 
		@param page Graphics - like you do
		 *****************************************************************/
		public void paintComponent (Graphics page) {
			ProgramStyle.paintSign(page, 0, 0, size.width, size.height,
					ProgramStyle.GREENSIGN, false);

		}

	}

}

