/****************************************************************
 * The Admin side of the View portion. used to organize minipulate
 * the date. Not a lot of the ideas were able to be implimented.
 * 
@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.awt.BorderLayout;

import java.awt.Graphics;


import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.*;


public class ViewAdmin extends JPanel implements View{

	/**The main panel to hold the info and the lists*/
	private JPanel mainPanel;

	/**reads out information on selected element within ActionPanel*/
	private Object infoObject;

	/** If true, actionPanel = ViewAdminRental, if false, actionPanel
	 * is an instance of ViewAdminAccounts. */
	private boolean isAccountActive;

	/**The list to be displayed in the action panel*/
	AbstractListModel localList;

	/**The current view this view is showing*/
	View openView;

	/*****************************************************************

	 *****************************************************************/
	public ViewAdmin (AbstractListModel list) {

		//basic settings
		this.setPreferredSize(ProgramStyle.windowSize());
		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		//sets the default information
		infoObject = new Object();
		localList = list;

		//add the items
		setButtonBar();
		showList(list);
	}

	/*****************************************************************
	Paints the background and the sign posts
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
	Area to disply information that was never fully implimented.
	 *****************************************************************/
	private void setTitleBar () {
		//this sends the info panel the account rental or rented
		mainPanel.add(new ViewAdminInfo(infoObject), BorderLayout.NORTH);
	}

	/*****************************************************************
	The panel containing all the buttons
	 *****************************************************************/
	private void setButtonBar () {
		JPanel panel = new JPanel();
		panel.setPreferredSize(ProgramStyle.buttonPanelSize());
		panel.setOpaque(false);

		panel.add(new JLabel(new ImageIcon("Images/ShieldSignLG.png")));

		//Button purpose based on the Enum passed
		panel.add(new GoButton(AdminButtonEnum.VIEW_ACCOUNTS));
		panel.add(new GoButton(AdminButtonEnum.VIEW_RENTALS));
		panel.add(new GoButton(AdminButtonEnum.VIEW_RENTEDS));
		panel.add(new GoButton(AdminButtonEnum.LOAD_TXT));
		panel.add(new GoButton(AdminButtonEnum.LOAD_FILE));
		panel.add(new GoButton(AdminButtonEnum.SAVE_TXT));
		panel.add(new GoButton(AdminButtonEnum.SAVE_FILE));
		panel.add(new GoButton(AdminButtonEnum.SEARCH_ACCOUNTS));
		panel.add(new GoButton(UserButtonEnum.SEARCH_LIST));
		panel.add(new GoButton(AdminButtonEnum.ADD_RENTAL));


		//adds the log out button
		GoButton logoutButton = new GoButton (AdminButtonEnum.LOGOUT);
		panel.add(logoutButton);

		this.add(panel, BorderLayout.WEST);
	}


	/*****************************************************************
	The action panel to show the lists
	 *****************************************************************/
	public void setAction () {
		//this shows the accounts panel as the default
		mainPanel.add(new ViewAdminList(localList),
				BorderLayout.CENTER);
	}

	/*****************************************************************
	This changes the actionPanel to the account settings and changes
	 the buttons on the left to the appropriate buttons.
	 *****************************************************************/
	public void showAccountSettings (Account account) {
	}

	/*****************************************************************
	Shows the list that was passed to the View
	 *****************************************************************/
	@Override
	public void showList(AbstractListModel list) {
		if (mainPanel != null)
			this.remove(mainPanel);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);

		localList = list;

		setTitleBar();
		setAction();

		this.add(mainPanel, BorderLayout.CENTER);

		revalidate();
		updateUI();
		repaint();
	}

	/*****************************************************************
	Shows the item that would have been seen in the info bar
	 *****************************************************************/
	@Override
	public void showItem (Object obj) {
		this.remove(mainPanel);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);

		infoObject = obj;

		setTitleBar();
		setAction();

		revalidate();
		updateUI();
		repaint();
	}
}
