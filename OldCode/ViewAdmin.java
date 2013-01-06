/****************************************************************



@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/

/*****************************************************************
Notes:
the different items in the actionPanel:
-account setting (Personal info, rental history, ...
-browse movies,
-search movies. 
-search rental history
-see checked out movies
see rental history
	
there needs to be in some areas very similar actions to be able to be
performed as in the user view. 

maybe have a status enum to know what it is the user is doing
at the time...(Acount, AccountSearch, Search, Browse...) this allows this 
class to know how to behave when it receives instruction.
	
*****************************************************************/

package goEntBeta2;

import javax.swing.AbstractListModel;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;

import utilities.ProgramStyle;

public class ViewAdmin extends JPanel implements View {
	
	/**Main panel within ViewAdmin; is either an instance of
	 * ViewAdminAccounts or ViewAdminRental depending on state*/
	private JPanel actionPanel;
	
	/**Sidebar of buttons which control the application from here*/
	private JPanel buttonPanel;
	
	/**reads out information on selected element within ActionPanel*/
	private JPanel infoPanel;
	
	/** If true, actionPanel = ViewAdminRental, if false, actionPanel
	 * is an instance of ViewAdminAccounts. */
	private boolean isAccountActive;
	
	AbstractListModel localAccountList;

	/*****************************************************************
	default constructor, sets actionPanel to ViewAdminAccounts
	*****************************************************************/
	public ViewAdmin (AccountList accounts) {
		
		//keeps a local copy of the account list
		localAccountList = accounts;
		
		//set preliminary panel style
		this.setPreferredSize(ProgramStyle.windowSize());
		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		//create panels
		actionPanel = new JPanel();
		buttonPanel = new JPanel();
		infoPanel = new JPanel();
		
		//add panels to proper positions
		this.add(infoPanel, BorderLayout.SOUTH);
		this.add(buttonPanel, BorderLayout.WEST);
		this.add(actionPanel, BorderLayout.CENTER);

		setDefaultAction();
		//also sets isAccountActive to false, so the following:
		
		setButtons();
		//sets buttons for ViewAdminAccounts
	}

	
	/*****************************************************************
	sets default state of the program to the admin's account view
	*****************************************************************/
	public void setDefaultAction () {
		showList(localAccountList);
		isAccountActive = false;
	}

	
	/*****************************************************************

	*****************************************************************/
	@Override
	public void showAccount(Account account) {
		// TODO Auto-generated method stub
	}


	/*****************************************************************
	switches actionPanel to ViewAdminRental
	*****************************************************************/
	@Override
	public void showList(AbstractListModel list) {
		
		
		if(isAccountActive = true){
			//account is active, so display its rental list
			
			this.remove(actionPanel);
			actionPanel = new ViewAdminRental(list);
			this.add(actionPanel, BorderLayout.CENTER);
			isAccountActive = true;
			
		}else{	
			//account is not active, so display account list
			
			this.remove(actionPanel);
			actionPanel = new ViewAdminAccounts(localAccountList);
			this.add(actionPanel, BorderLayout.CENTER);
			isAccountActive = false;
		}
	}
	

	/*****************************************************************
	
	*****************************************************************/
	public void updateInfoPanel(){
		// TODO everything
		
		if(isAccountActive = false){
			//return data on highlighted element in rental list
		}else{
			//return info on highlighted account
		}
	}
	
	
	/*****************************************************************
	
	*****************************************************************/
	private void setButtons() {
		// TODO populate the arraylist "buttons"

		if(isAccountActive = false){
			//populate with ViewAdminAccounts buttons
			
		}else{
			//populate with ViewAdminRental buttons
		}
		
//		for (GoButton b : buttons){
//			buttonPanel.add(b);
//		}
		
	}
}