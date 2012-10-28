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

import java.util.ArrayList;

import javax.swing.JPanel;

public class ViewAdmin extends JPanel implements View {
	
	/***/
	private JPanel actionPanel;
	
	/***/
	private ArrayList<GoButton> buttons;
	
	

	
	/*****************************************************************
	
	*****************************************************************/
	public ViewAdmin () {
		
		setButtons();
	}
	

	/*****************************************************************
	
	*****************************************************************/
	@Override
	public void showAccount(Account account) {
		// TODO Auto-generated method stub
		
	}
	
	/*****************************************************************
	
	*****************************************************************/
	public void showAccounts (ArrayList<Account> accounts) {
		
	}
	
	/*****************************************************************
	
	*****************************************************************/
	@Override
	public void showList(RentalList list) {
		// TODO Auto-generated method stub
		
	}

	/*****************************************************************
	
	*****************************************************************/
	private void setButtons() {
		// TODO Auto-generated method stub
		
	}

	
	

	
	
	
	
	
	
	
	
}
