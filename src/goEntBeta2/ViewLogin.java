/****************************************************************
Shows the main login screen for the happy patrons to sign into

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;


import java.awt.Dimension;

import java.awt.Graphics;


import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utilities.LoginButtonEnum;
import utilities.ProgramStyle;


public class ViewLogin extends JPanel implements View{

	/**The light icon in the back*/
	private ImageIcon light;

	/**The width of the window*/
	private int windowWidth;

	/**The error message*/
	private JLabel message;
	
	/**The username field*/
	private JTextField userNameField;
	
	/**The password field*/
	private JPasswordField passwordField;
	
	/**The username label*/
	private String userName;
	
	/**The password label*/
	private String password;
	
	/**A new account for the new user*/
	private ViewUserNewAccount newAccount;

	/**the size of the text area*/
	private final Dimension TEXTAREA = new Dimension (225,200);
	
	/**distance of the text area from the top of the panel*/
	private final int TEXTFROMTOP = 350;


	/*****************************************************************
	Paints the background image and the other images to be added later

	@param page Graphics because that is what you do.
	 *****************************************************************/
	public ViewLogin () {
		windowWidth = ProgramStyle.windowSize().width;
		//Sets the properties of this
		setPreferredSize(ProgramStyle.windowSize());


		//Collects the Login in image
		light = new ImageIcon("Images/login/loginLights.png");

		setLogin();

	}
	/*****************************************************************
	Paints the background image and the other images to be added later

	@param page Graphics because that is what you do.
	 *****************************************************************/
	public void paintComponent (Graphics page) {
		ProgramStyle.paintBackground(this, page);

		light.paintIcon(this, page, windowWidth/2-light.getIconWidth()/2, -500);
	}

	/*****************************************************************
	 * creates the text area
	 *****************************************************************/
	private void setLogin () {

		JPanel main = new JPanel();
		main.setPreferredSize(TEXTAREA);

		main.setOpaque(false);

		message = new JLabel("");
		
		JLabel userLabel = new JLabel("USER NAME");
		userLabel.setFont(ProgramStyle.getFont(9));
		
		userNameField = new JTextField ("", 15);							//need to change set word
		
		JLabel passLabel = new JLabel("PASSWORD");
		passLabel.setFont(ProgramStyle.getFont(9));
		
		passwordField = new JPasswordField ("", 15);						//need to change set word

		GoButton loginButton = new GoButton (LoginButtonEnum.LOGIN);
		loginButton.useImageForButton();

		GoButton newAccountButton = new GoButton (LoginButtonEnum.NEW_ACCOUNT);
		newAccountButton.useImageForButton();

		main.add(message);
		main.add(userLabel);
		main.add(userNameField);
		main.add(passLabel);
		main.add(passwordField);
		main.add(loginButton);
		main.add(newAccountButton);


		//this pushes the main panel down
		JPanel filler = new JPanel();
		filler.setPreferredSize(new Dimension  (windowWidth, TEXTFROMTOP));
		filler.setOpaque(false);
		this.add (filler);

		this.add(main);
	}

	/*****************************************************************
	 * Changes the background image to red to show incorrect username
	 * or password.
	 *****************************************************************/
	public void failedLogin () {
		//this sets the login screen to change if the wrong username or password is entered
		light = new ImageIcon("Images/login/LoginRedLights.png");
		message.setText("INVALID USERNAME/PASSWORD");
		repaint();
		updateUI();
		System.out.println ("failed login");
	}
	
	/*****************************************************************
	 * Gets the typed username
	 *****************************************************************/
	public String getUserName() {
		return userNameField.getText();
	}
	
	/*****************************************************************
	 * Gets the typed password
	 *****************************************************************/
	@SuppressWarnings("deprecation")
	public String getPassword() {
		return passwordField.getText();
	}
	

	/*****************************************************************
	These are the unimplemented view methods. for the log in they don't
	need to be implemented but this class needs to be a View class.

	@param e ActionEvent that wont do anything
	 *****************************************************************/

	@Override
	public void showItem (Object obj) {}
	@Override
	public void showList(AbstractListModel list) {}
}
