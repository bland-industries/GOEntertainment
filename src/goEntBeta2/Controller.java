/*****************************************************************
This class is God. This is the hub where all action passes through.
everything the user does calls on this class to react and make
changes to the accounts and rentals. 

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utilities.*;
import xtra.TestAccount;

public class Controller {


	/**This is the account that is open to have access to when a person
	  logs on*/
	private Account openAccount;

	/**This is the view that is displayed*/
	private View openView;

	/**The mainFrame that contains all the GUI side*/
	private JFrame frame;

	/**The list of all the accounts*/
	private ArrayList<Account> accounts;

	/**The master list of all the rental objects*/
	private RentalList primeRentalList;

	/**Master list of all the Rented objects*/
	private RentalList primeRentedList;

	/**THis can be all the saved lists i want to show*/
	private ArrayList<RentalList> savedRentalLists;

	JDialog dialog;

	/*****************************************************************

	 *****************************************************************/
	public Controller () {
		//Note: the eos computers are 1280 X 1024


		//This sets the listeners for the Buttons.
		setListeners();

		//sets up the main frame and background of the whole program
		frame = new JFrame ("GO Ent");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//Set the log in view 
		openView = new ViewLogin();
		frame.getContentPane().add(((JPanel) openView));

		//show the background
		frame.pack();
		frame.setVisible(true);

		accounts = new ArrayList<Account>();

		
		//Test COde
		Account account = new Account();

		account.seteMail("Thomas@bwata.com");
		account.setFirstName("Thomas");
		account.setLastName("Verstraete");
		account.setPassword("password");

		String adr = "123 Fun St." + "*";
		adr = adr + "" + "*";
		adr = adr + "Grand Haven" + "*";
		adr = adr + "mi" + "*";
		adr = adr + "49417" + "*";
		account.setAddress(adr);

		accounts.add(account);
		




	}

	/*****************************************************************

	 *****************************************************************/
	public void setListeners () {

		Listeners.addListener(new RentListener());
		Listeners.addListener(new LoginButtonListener());
		Listeners.addListener(new UserButtonListener());
		Listeners.addListener(new AdminButtonListener());
	}

	/*****************************************************************
	Takes the input username and password, searches the accounts 
	arraylist for the username, if the username is an account then
	it checks the given password against the account password. If it is
	true then "login" to the account if not notify the view.

	@param userName String of the given username to search for
	@param password String to compare the passwords of the account
	 *****************************************************************/
	private void checkLogin (String userName, String password) {
		int index = 0;
		for (; index < accounts.size(); index ++) {
			if (userName.equals(accounts.get(index).geteMail())) {
				openAccount = accounts.get(index);
				break;
			}
		}
		try  {
			if (openAccount.getPassword().equals(password)) {
				//this needs to call a method to open up the account in the view
				ProgramStyle.setCurrentUser(openAccount.getFirstName());
				showViewUser();
			}
			else {
				openAccount = null;
				((ViewLogin) openView).failedLogin();
			}

		} catch (NullPointerException e ) {
			openAccount = null;
			((ViewLogin) openView).failedLogin();
		}
	}

	/******************************************************************

	 *****************************************************************/
	private void checkNewAccount (ViewUserNewAccount newAccountDialog) {
		if (newAccountDialog.checkInput()) {
			
			boolean duplicate = false;
			int index = 0;
			for (; index < accounts.size() && !duplicate; index ++) {
				if (newAccountDialog.getTypedEMail().equals(accounts.get(index).geteMail())) {
					duplicate = true;
				}
			}

			if (duplicate) {
				//bad username already exists
				newAccountDialog.accountExists();
			}
			else {
				Account test = newAccountDialog.getNewAccount();
				accounts.add(test);
				openAccount = test;
				ProgramStyle.setCurrentUser(openAccount.getFirstName());
				showViewUser();
				newAccountDialog.dispose();
			}
		}
	}



	/*****************************************************************

	 *****************************************************************/
	private void showViewUser () {
		frame.getContentPane().removeAll();
		openView = new ViewUser(openAccount.getRentalHistory());
		
		frame.getContentPane().add(((JPanel) openView));
		frame.repaint();
		frame.pack();
	}

	/*****************************************************************

	 *****************************************************************/
	private void showLogin () {
		frame.getContentPane().removeAll();
		openView = new ViewLogin();
		frame.getContentPane().add(((JPanel) openView));
		frame.repaint();
		frame.pack();
	}

	/*****************************************************************

	 *****************************************************************/
	public void addAccount () {

	}

	/*****************************************************************

	 *****************************************************************/
	public void addRental () {

	}

	/*****************************************************************
	this should convert a rental object to a rented object and add it
	to the Account rented list, and the primeRentedList and mark 
	the original rental object as not in stock.
	 *****************************************************************/
	public void rentUnit (Rental toRent) {

	}

	/*****************************************************************
	This takes 
	 *****************************************************************/
	public void returnUnit (Rented rentedReturn) {

	}

	/*****************************************************************
	These will search through the primeList and get the results
	 *****************************************************************/
	public void search (int year1, int year2 ) {

	}

	/*****************************************************************
	These will search through the primeList and get the results
	 *****************************************************************/
	public void search (String searchString) {

	}

	/*****************************************************************
	These will search through the primeList and get the results
	 *****************************************************************/
	public void search (RentalCategory type) {

	}


	/*****************************************************************
	This is a specific listener for the Rental processes. This takes
	the passed object which should be a Rental Object and adds it to 
	the openAccount, it also adds to the rented list.

	this can also take a rented object and return it back into inventory
	and do what needs to be done with a rental is returned
	 *****************************************************************/
	public class RentListener implements ActionListener {

		/***/
		private String listenType = "RentButton";

		/*****************************************************************

		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			String title =((ProgButton) event.getSource()).getTitle();
			String type =((ProgButton) event.getSource()).getClassName();

			System.out.println("" + title + " from " + type);
		}

		/*****************************************************************

		 *****************************************************************/
		@Override
		public String toString () {
			return listenType;
		}
	}

	/*****************************************************************
	This is the listener for all user side buttons. the passed object is
	the button enum where this will determine base on the enum 
	what actions need to be performed.
	 *****************************************************************/
	public class LoginButtonListener implements ActionListener {

		/***/
		private String listenType = "LoginButton";

		/*****************************************************************

		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			switch (((LoginButtonEnum) event.getSource())) {

			case LOGIN:
				String uN = ((ViewLogin) openView).getUserName();
				String pW = ((ViewLogin) openView).getPassword();
				checkLogin(uN, pW);
				break;

			case CREATE_ACCOUNT:
				checkNewAccount((ViewUserNewAccount) dialog);
				//System.exit(0);
				break;

			case NEW_ACCOUNT:
				dialog = new ViewUserNewAccount(frame);
				dialog.show();
				break;

			case CANCEL:
				dialog.dispose();
				break;


			default:
				break;



			}



		}

		/*****************************************************************

		 *****************************************************************/

		@Override
		public String toString () {
			return listenType;
		}
	}

	/*****************************************************************
	This is the listener for all user side buttons. the passed object is
	the button enum where this will determine base on the enum 
	what actions need to be performed.
	 *****************************************************************/
	public class UserButtonListener implements ActionListener {

		/***/
		private String listenType = "UserButton";


		/*****************************************************************

		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			switch (((UserButtonEnum) event.getSource())) {

			case LOGOUT:
				showLogin();
				break;

			case CATEGORIES:
				System.out.println("" + ((UserButtonEnum) event.getSource()));
				break;

			case SEARCH_LIST:
				System.out.println("" + ((UserButtonEnum) event.getSource()));
				break;

			case SEARCH_MOVIES:
				System.out.println("" + ((UserButtonEnum) event.getSource()));
				break;

			case VIEW_ACCOUNT:
				System.out.println("" + ((UserButtonEnum) event.getSource()));
				break;

			default:
				System.out.println("default user switch");
				break;



			}

		}

		/*****************************************************************

		 *****************************************************************/

		@Override
		public String toString () {
			return listenType;
		}
	}

	/*****************************************************************
	This is the listener for all admin side buttons. the passed object is
	the button enum where this will determine base on the enum 
	what actions need to be performed.
	 *****************************************************************/
	public class AdminButtonListener implements ActionListener {

		/***/
		private String listenType = "AdminButton";

		/*****************************************************************

		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			String title =((ProgButton) event.getSource()).getTitle();
			String type =((ProgButton) event.getSource()).getClassName();

			System.out.println("" + title + " from " + type);

		}

		/*****************************************************************

		 *****************************************************************/
		@Override
		public String toString () {
			return listenType;
		}
	}

}
