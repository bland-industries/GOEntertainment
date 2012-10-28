/****************************************************************



@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/

/****************************************************************
Notes:

need a spot to click for a new account.
 *****************************************************************/
package goEntBeta2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import utilities.LoginButtonEnum;
import utilities.ProgButton;
import utilities.ProgramStyle;
import xtra.TestAccount;

public class ViewLogin extends JPanel implements View{


	private ImageIcon light;

	private int windowWidth;

	private JLabel message;
	private JTextField userNameField;
	private JPasswordField passwordField;
	
	private String userName;
	private String password;
	
	ViewUserNewAccount newAccount;

	private final Dimension TEXTAREA = new Dimension (225,200);
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

	 *****************************************************************/
	private void setLogin () {

		JPanel main = new JPanel();
		main.setPreferredSize(TEXTAREA);

		main.setOpaque(false);

		message = new JLabel("");
		
		JLabel userLabel = new JLabel("USER NAME");
		userLabel.setFont(ProgramStyle.getFont(9));
		
		userNameField = new JTextField ("thomas@bwata.com", 15);							//need to change set word
		//userNameField.addActionListener(new FieldListener());
		
		JLabel passLabel = new JLabel("PASSWORD");
		passLabel.setFont(ProgramStyle.getFont(9));
		
		passwordField = new JPasswordField ("password", 15);						//need to change set word
		//passwordField.addActionListener(new FieldListener());


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

	 *****************************************************************/
	public String getUserName() {
		return userNameField.getText();
	}
	
	/*****************************************************************

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
	public void showList(RentalList list) {
		// TODO Auto-generated method stub

	}
	@Override
	public void showAccount(Account account) {
		// TODO Auto-generated method stub

	}
}
