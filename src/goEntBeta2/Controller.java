/*****************************************************************
This class is God. This is the hub where all action passes through.
everything the user does calls on this class to react and make
changes to the accounts and rentals. 

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utilities.*;


public class Controller {


	/**This is the account that is open to have access to when a person
	  logs on*/
	private Account openAccount;

	/**This is the view that is displayed*/
	private View openView;

	/**The list that is currently being displayed in the GUI*/
	private RentalList displayedList;

	/**The Rental Item that is being displayed*/
	private Rental displayedRental;

	/**The Rented item that is being displayed*/
	private Rented displayedRented;

	/**The mainFrame that contains all the GUI side*/
	private JFrame frame;

	/**The list of all the accounts*/
	private AccountList accounts;

	/**The master list of all the rental objects*/
	private RentalList primeRentalList;

	/**Master list of all the Rented objects*/
	private RentedList primeRentedList;

	/**The Username to access the admin sections*/
	private String adminUserName;

	/**The Password to access the admin sections*/
	private String adminPassword;

	/**The current dialog box shown*/
	JDialog dialog;

	/*****************************************************************
	General constructor sets up all the attributes and opens up the 
	log in screen
	Note: the eos computers are 1280 X 1024
	 *****************************************************************/
	public Controller () {

		//This sets the listeners for the Buttons.
		setListeners();

		//Sets up the admin password
		adminUserName = "AdminAmazing";		
		adminPassword = "12345";		

		//Sets up the three main lists
		primeRentalList = new RentalList("Full Library");
		accounts = new AccountList();
		primeRentedList = new RentedList("Full Rented");

		//primeRentalList.sortList();
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
	}

	/*****************************************************************
	Adds the listeners from the subclasses to the Static Listeners 
	class. 
	 *****************************************************************/
	public void setListeners () {

		Listeners.addListener(new RentListener());
		Listeners.addListener(new LoginButtonListener());
		Listeners.addListener(new UserButtonListener());
		Listeners.addListener(new AdminButtonListener());
		Listeners.addListener(new ListListener());
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
	//checks to see if there is an account with that username and gets it
		openAccount = accounts.getAccount(userName);

		if (openAccount != null){ //i there is an account returned
			if (openAccount.getPassword().equals(password)) {
			//this needs to call a method to open up the account in the view
				ProgramStyle.setCurrentUser(openAccount.getFirstName());
				showViewUser();
			}
			else {//failed password
				openAccount = null;
				((ViewLogin) openView).failedLogin();
			}
		}
		else {//failed login
			((ViewLogin) openView).failedLogin();
		}
	}

	/******************************************************************
	This checks to make sure the new account is acceptible in relation
	the the other accounts.
	
	@param newAccountDialog ViewUserNewAccount, the names speak the truth
	 *****************************************************************/
	private void checkNewAccount (ViewUserNewAccount newAccountDialog) {
		//true if all inputs are good
		if (newAccountDialog.checkInput()) {

			//checks the account list if there is already an account there
			openAccount = accounts.getAccount(newAccountDialog.getTypedEMail());

			if (openAccount == null){ //i there is no account returned
				Account test = newAccountDialog.getNewAccount();
				accounts.addAccount(test);
				openAccount = test;
				
				//sets the name of the user to the style class
				ProgramStyle.setCurrentUser(openAccount.getFirstName());
				showViewUser();
				dialog.dispose();
			}
			else {//have to use a different email.
				newAccountDialog.accountExists();
			}
		}
	}

	/******************************************************************
	This confirms that the changes made in the account info are good 
	and they don't override the old info.
	
	@param accountDialog ViewUserAccount, read the titles.
	 *****************************************************************/
	private void checkChangeAccount () {
		//checks to see if there isn't any errors in the input.
		if (((ViewUserNewAccount) dialog).checkInput()) {
			//this doesn't over ride the lists of the account.
			openAccount = ((ViewUserAccount) dialog).getAccount();
			ProgramStyle.setCurrentUser(openAccount.getFirstName());
			showViewUser();
			dialog.dispose();
		}
	}

	/*****************************************************************
	Displays the portal for the User GUI Experience. 
	 *****************************************************************/
	private void showViewUser () {
		frame.getContentPane().removeAll();
		openView = new ViewUser(openAccount.getCheckedOutList());
		frame.getContentPane().add(((JPanel) openView));
		frame.repaint();
		frame.pack();
	}

	/*****************************************************************
	Displays the portal for the Login GUI Experience. 
	 *****************************************************************/
	private void showLogin () {
		//this is the time to save all the important lists
		accounts.save();
		primeRentalList.save();
		primeRentedList.save();

		//switch the panels
		frame.getContentPane().removeAll();
		openView = new ViewLogin();
		frame.getContentPane().add(((JPanel) openView));
		frame.repaint();
		frame.pack();
	}

	/*****************************************************************
	Displays the dialog box that has the account info for altering.
	 *****************************************************************/
	private void showAccountInfo () {
		dialog = new ViewUserAccount(frame, openAccount);
		dialog.show();
	}

	/*****************************************************************
	Displays the selected rental object in a formatted Dialog box.
	
	@param rental Rental The Rental unit to display.
	 *****************************************************************/
	private void showRental (Rental rental) {
		displayedRental = rental;
		dialog = new ViewUserRental(frame, rental, openAccount);
		dialog.show();
	}

	/*****************************************************************
	Displays the dialog box giving the user the wonderful choices to 
	hack and slash the main list for easy consumption.
	 *****************************************************************/
	private void showSearchParam () {
		dialog = new ViewUserSearch(frame);
		dialog.show();
	}

	/*****************************************************************
	Displays the amount the user owns so and forces them to pay.
	All in a dialog box.
	 *****************************************************************/
	private void showCheckout () {
		dialog = new ViewUserCheckout(frame, openAccount.getAmountOwed());
		dialog.show();
	}

	/*****************************************************************
	These will search through the primeList and get the results
	*****************************************************************/
	public void search () {
		String listTitle = "";
		ViewUserSearch search = (ViewUserSearch) dialog;
		RentalList searchList = new RentalList(listTitle);
		searchList.add(primeRentalList);
		
		//checks the type of search
		if (search.getTypeChosen().equals("Movies"))
			searchList = searchList.getType("Movies");
		else if (search.getTypeChosen().equals("TV Episodes"))
			searchList = searchList.getType("TV");
		

		//checks the genre type
		if (search.getGenreChosen() != RentalCategory.NONE)
			searchList = searchList.get(search.getGenreChosen());

		//checks the search string
		if (!search.getSearchString().equals("") ||
				search.getSearchString() != null)
			searchList = searchList.get(search.getSearchString());

		//checks the years
		int[] years = search.getSearchYears();
		if (years[0] != 0 && years[1] != 0)
			searchList = searchList.get(years[0], years[1]);
				
		//after all the searches were taken care display the results
		openView.showList(searchList);
	}

	/*****************************************************************
	Displays the portal for the Amazing Administrator to work their
	magic. 
	 *****************************************************************/
	private void showAdminWindow () {
		frame.getContentPane().removeAll();
		openView = new ViewAdmin(accounts);
		frame.getContentPane().add(((JPanel) openView));
		frame.repaint();
		frame.pack();
	}

	//################################################################
	//these are the listeners. WOOOOOHOOOOOO

	/*****************************************************************
	This is a specific listener for the Rental processes. This takes
	the passed object which should be a Rental Object and adds it to 
	the openAccount, it also adds to the rented list.

	this can also take a rented object and return it back into inventory
	and do what needs to be done with a rental is returned
	 *****************************************************************/
	public class RentListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		private String listenType = "RentButton";

		/*****************************************************************
		The action performed method, like you do.
		
		@param event ActionEvent containing all the info you will need
		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			String title =((ProgButton) event.getSource()).getTitle();
			String type =((ProgButton) event.getSource()).getClassName();

			System.out.println("" + title + " from " + type);
		}

		/*****************************************************************
		This gets the determining String
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

		/**The String determining the type of listener wanted.*/
		private String listenType = "LoginButton";

		/*****************************************************************
		The action performed method, like you do.
		
		@param event ActionEvent containing all the info you will need
		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			switch (((LoginButtonEnum) event.getSource())) {

			case LOGIN:
				//pulls the typed username and password
				String uN = ((ViewLogin) openView).getUserName();
				String pW = ((ViewLogin) openView).getPassword();
				
				//checks to see if the AmazingAdmin is login in
				if (uN.equals(adminUserName) && pW.equals(adminPassword))
					showAdminWindow();
				else
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
			}
		}

		/*****************************************************************
		This gets the determining String
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

		/**The String determining the type of listener wanted.*/
		private String listenType = "UserButton";


		/*****************************************************************
		The action performed method, like you do.
		
		@param event ActionEvent containing all the info you will need
		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			switch (((UserButtonEnum) event.getSource())) {

			case HISTORY: //show the rental history
				openView.showList(openAccount.getRentalHistory());
				break;

			case FAVORITES:	//show the user favorites
				displayedList = openAccount.getFavoritesList();
				openView.showList(displayedList);
				break;

			case RENTLIST:	//show the to rent list
				displayedList = openAccount.getReservedList();
				openView.showList(displayedList);
				break;

			case SEARCH_LIST:	//display the searchdialog
				showSearchParam();
				break;

			case SEARCH:	//collect the search info to use
				search();
				dialog.dispose();
				break;

			case LIBRARY:	//show the whole library
				openView.showList(primeRentalList);
				break;

			case VIEW_ACCOUNT:	//show the account info dialog
				dialog = new ViewUserAccount(frame, openAccount);
				dialog.show();
				break;

			case CHECKED_OUT:	//show the checkout dialog box
				openView.showList(openAccount.getCheckedOutList());
				break;

			case SAVE_ACCOUNT: //save the changes to the account
				checkChangeAccount();
				break;

			case LOGOUT:	//Logs out of the account
				showCheckout();
				break;

			case RENT:	//rent the dang thing
				primeRentedList.add(openAccount.rent(displayedRental));
				dialog.dispose();
				break;

			case RETURN: //return it
				openAccount.returnRental(displayedRental);
				dialog.dispose();
				break;

			case PAY: //pay me money
				openAccount.amountPayed();
				dialog.dispose();
				showLogin();
				break;

			case ADD_FAVORITE: //add to favorites list
				openAccount.addToFavorite(displayedRental);
				dialog.dispose();
				break;

			case ADD_RENTLIST: //add to rent list
				openAccount.addToReserve(displayedRental);
				dialog.dispose();
				break;

			case REMOVE_FAVORITE: //remove from favorites list
				openAccount.removeFavorite(displayedRental);
				dialog.dispose();
				break;

			case REMOVE_RENTLIST: //remove from rent list
				openAccount.removeReserve(displayedRental);
				dialog.dispose();
				break;

			default: //oops
				showLogin();
				break;
			}
		}

		/*****************************************************************
		This gets the determining String
		 *****************************************************************/

		@Override
		public String toString () {
			return listenType;
		}
	}

	/*****************************************************************
	This is the listener for all admin side buttons. The passed object is
	the button enum where this will determine based on the enum 
	what actions need to be performed.
	 *****************************************************************/
	public class AdminButtonListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		private String listenType = "AdminButton";

		/*****************************************************************
		The action performed method, like you do.
		
		@param event ActionEvent containing all the info you will need
		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			String title =((ProgButton) event.getSource()).getTitle();
			String type =((ProgButton) event.getSource()).getClassName();

			System.out.println("" + title + " from " + type);

			switch (((AdminButtonEnum) event.getSource())) {

			case VIEW_ACCOUNTS: //show all accounts
				openView.showList(accounts);
				break;

			case VIEW_RENTALS: //show the rental library
				openView.showList(primeRentalList);
				break;

			case VIEW_RENTEDS:	//show all the rented instances
				openView.showList(primeRentedList);
				break;

			case LOAD_TXT: //loads a txt file
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				File txtFile = fc.getSelectedFile();
				String name = txtFile.getName();
				RentalList addList = new RentalList("list");
				addList.loadTXT(name);
				primeRentalList.add(addList);
				break;

			case LOAD_FILE: //loads a cereal file
				JFileChooser fc2 = new JFileChooser();
				fc2.showOpenDialog(null);
				File cerealFile = fc2.getSelectedFile();
				String name2 = cerealFile.getName();
				RentalList addList2 = new RentalList("list");
				addList2.loadList(name2);
				primeRentalList.add(addList2);
				break;

			case SAVE_TXT: //save the library as a txt file
				try {
					JFileChooser fc3 = new JFileChooser();
					fc3.showSaveDialog(null);
					File txtFile2 = fc3.getSelectedFile();
					FileOutputStream fos = new FileOutputStream(txtFile2);
					ObjectOutputStream os = new ObjectOutputStream(fos);
					os.writeObject(primeRentalList);
					os.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				break;

			case SAVE_FILE: //Saves the library as a cereal file
				JFileChooser fc3 = new JFileChooser();
				fc3.showSaveDialog(null);
				File txtFile2 = fc3.getSelectedFile();
				displayedList.saveTXT(txtFile2.getPath());
				break;

			case LOGOUT: //logs out of the amazing admin
				primeRentalList.save();
				primeRentedList.save();
				accounts.save();
				showLogin();
				break;
			}
		}

		/*****************************************************************
		This gets the determining String
		 *****************************************************************/
		@Override
		public String toString () {
			return listenType;
		}
	}

	/*****************************************************************
	This is the listener for all the lists. It works magic. It parses
	out what type of object this gets and handles it as needed.
	 *****************************************************************/
	public class ListListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		private String listenType = "ListListener";

		/*****************************************************************
		The action performed method, like you do.
		
		@param event ActionEvent containing all the info you will need
		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			
			//this is for a Rental object.
			if (event.getSource() instanceof Rental)
				showRental((Rental) event.getSource());
				
			//this is for a Rented Object.
			else if (event.getSource() instanceof Rented) {
				displayedRented = (Rented) event.getSource();
				showRental(((Rented) event.getSource()).rental);
				
			//this is for an Account Object.	
			} else if (event.getSource() instanceof Account) {
				openAccount = (Account) event.getSource();
				showAccountInfo();
			}
		}

		/*****************************************************************
		This gets the determining String
		 *****************************************************************/
		@Override
		public String toString () {
			return listenType;
		}
	}

}
