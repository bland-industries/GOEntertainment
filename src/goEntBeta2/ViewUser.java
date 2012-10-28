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
	
in the browse section have a search icon of the list to be able 
narrow the list from that list.

maybe have a status enum to know what it is the user is doing
at the time...(Acount, Search, Browse...) this allows this 
class to know how to behave when it receives instruction.
	
*****************************************************************/


package goEntBeta2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.*;
import xtra.TestRentalList;

public class ViewUser extends JPanel implements View{

	/***/
	private JPanel actionPanel;
	
	/***/
	private JPanel buttonPanel;
	
	/***/
	private ArrayList<GoButton> buttons;
	
	/***/
	View openView;
	
	/*****************************************************************
	
	*****************************************************************/
	public ViewUser (RentalList rentalHistory) {
		
		this.setPreferredSize(ProgramStyle.windowSize());
		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);
		
		
		panel.add(setTitleBar(), BorderLayout.NORTH);
		setDefaultAction(rentalHistory);
		panel.add(actionPanel, BorderLayout.CENTER);
		
		
		setButtonBar();
		this.add(panel, BorderLayout.CENTER);
		
	}
	
	/*****************************************************************
	
	*****************************************************************/
	public void paintComponent (Graphics page) {
		ProgramStyle.paintBackground(this, page);
		//right side post
		ProgramStyle.SignPost.paintIcon(this, page, 
				ProgramStyle.buttonPanelSize().width/2 -
				ProgramStyle.SignPost.getIconWidth()/2, 25);
		//left action panel post
		ProgramStyle.SignPost.paintIcon(this, page, 
				(int) (ProgramStyle.buttonPanelSize().width +
						ProgramStyle.windowSize().width * .15), 125);
		//right action panel post
		ProgramStyle.SignPost.paintIcon(this, page, 
				(int) (ProgramStyle.windowSize().width - 
						ProgramStyle.windowSize().width * .15), 125);

	}
	
	/*****************************************************************
	
	*****************************************************************/
	private JPanel setTitleBar () {
		JPanel panel = new JPanel();
		panel.setPreferredSize(ProgramStyle.titlePanelSize());
		panel.setOpaque(false);
		
		GoButton accountInfoButton = new GoButton (UserButtonEnum.VIEW_ACCOUNT);
		accountInfoButton.useImageForButton();
		
		GoButton logoutButton = new GoButton (UserButtonEnum.LOGOUT);
		logoutButton.useImageForButton();
		
		panel.add(logoutButton);
		
		panel.add(accountInfoButton);
		
		
		return panel;
	}
	
/*****************************************************************
	
	*****************************************************************/
	private void setButtonBar () {
		JPanel panel = new JPanel();
		panel.setPreferredSize(ProgramStyle.buttonPanelSize());
		panel.setOpaque(false);
		
		panel.add(new JLabel(new ImageIcon("Images/ShieldSignLG.png")));
		panel.add(new GoButton(UserButtonEnum.SEARCH_MOVIES));
		panel.add(new GoButton(UserButtonEnum.SEARCH_LIST));
		panel.add(new GoButton(UserButtonEnum.VIEW_ACCOUNT));

		
		panel.add(new GoButton(UserButtonEnum.SEARCH_MOVIES));
	
		
		this.add(panel, BorderLayout.WEST);
	}
	
	
	/*****************************************************************
	
	*****************************************************************/
	public void setDefaultAction (RentalList rentalHistory) {

		actionPanel = new ViewUserHome(rentalHistory);

	}
	
	
	
	/*****************************************************************
	This changes the actionPanel to the account settings and changes
	 the buttons on the left to the appropriate buttons.
	*****************************************************************/
	public void showAccountSettings (Account account) {
		
	}
	
	/*****************************************************************
	This opens a dialog box to narrow the search
	*****************************************************************/
	public void showSearchOptions () {
		
	}
	
	public void setButtons () {

	}
	
	
	@Override
	public void showList(RentalList list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

}
