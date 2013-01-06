/*****************************************************************
This displays the lists passed into the user side of the GUI.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JPanel;

public class ViewUserSettings extends JPanel implements View  {

	private String password;
	private String username;
	/***/
	private JPanel actionPanel;
	
	/***/
	private ArrayList<GoButton> buttons;
	
	

	/*****************************************************************
	
	*****************************************************************/
	@Override
	public void showItem (Object obj) {
		// TODO Auto-generated method stub
		
	}
	
	/*****************************************************************
	
	*****************************************************************/
	public void showAccounts (ArrayList<Account> accounts) {
		
	}
	
	/*****************************************************************
	
	*****************************************************************/
	@Override
	public void showList(AbstractListModel list) {
		// TODO Auto-generated method stub
		
	}

	/*****************************************************************
	
	*****************************************************************/
	private void setButtons() {
		// TODO Auto-generated method stub
		
	}

	
}
