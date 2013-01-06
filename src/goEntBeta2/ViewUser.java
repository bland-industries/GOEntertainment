/****************************************************************
The main portal for the main user interface

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.*;


public class ViewUser extends JPanel implements View{

	/**the main action panel*/
	private JPanel mainPanel;
	
	/**this is the list panel*/
	private JPanel actionPanel;
	
	/**contains the buttons*/
	private JPanel buttonPanel;
	
	/**The view this is immplementing*/
	View openView;
	
	/*****************************************************************
	 * Basic constructor which sets the parameters
	 * @param itemsList default list at startup
	*****************************************************************/
	public ViewUser (AbstractListModel itemsList) {
		
		this.setPreferredSize(ProgramStyle.windowSize());
		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);
		
		
		mainPanel.add(setTitleBar(), BorderLayout.NORTH);
		setDefaultAction(itemsList);
		mainPanel.add(actionPanel, BorderLayout.CENTER);
		
		
		setButtonBar();
		this.add(mainPanel, BorderLayout.CENTER);
		
	}
	
	/*****************************************************************
	 * Paints the background
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
	 * Filler panel for esthetics 
	*****************************************************************/
	private JPanel setTitleBar () {
		JPanel panel = new JPanel();
		panel.setPreferredSize(ProgramStyle.titlePanelSize());
		panel.setOpaque(false);
		
		return panel;
	}
	
	/*****************************************************************
	 * Adds the buttons
	*****************************************************************/
	private void setButtonBar () {
		JPanel panel = new JPanel();
		panel.setPreferredSize(ProgramStyle.buttonPanelSize());
		panel.setOpaque(false);
		
		panel.add(new JLabel(new ImageIcon("Images/ShieldSignLG.png")));
		panel.add(new GoButton(UserButtonEnum.SEARCH_LIST));
		panel.add(new GoButton(UserButtonEnum.LIBRARY));	
		panel.add(new GoButton(UserButtonEnum.FAVORITES)); //gets the favorites list of the user
		panel.add(new GoButton(UserButtonEnum.RENTLIST));	//gets the to rent list from user
		panel.add(new GoButton(UserButtonEnum.HISTORY));	//gets the rental history of the user
		panel.add(new GoButton(UserButtonEnum.CHECKED_OUT));	//gets this un-returned rental list from user

		
		
		GoButton accountInfoButton = new GoButton (UserButtonEnum.VIEW_ACCOUNT);
		accountInfoButton.useImageForButton();
		
		GoButton logoutButton = new GoButton (UserButtonEnum.LOGOUT);
		logoutButton.useImageForButton();
		
		panel.add(logoutButton);
		
		panel.add(accountInfoButton);
	
		
		this.add(panel, BorderLayout.WEST);
	}
	
	
	/*****************************************************************
	 * Sets the default action panel to the default list passed
	*****************************************************************/
	public void setDefaultAction (AbstractListModel itemsList) {

		actionPanel = new ViewUserHome(itemsList);

	}
	
	/*****************************************************************
	 * Displays list in action panel
	*****************************************************************/	
	@Override
	public void showList(AbstractListModel list) {
		this.remove(mainPanel);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);

		mainPanel.add(setTitleBar(), BorderLayout.NORTH);
		actionPanel = new ViewUserList(list);
		mainPanel.add(actionPanel, BorderLayout.CENTER);
		
		this.add(mainPanel, BorderLayout.CENTER);
		
		actionPanel.revalidate();
		actionPanel.updateUI();
		actionPanel.repaint();
		revalidate();
		updateUI();
		repaint();
		
	}

	@Override
	public void showItem (Object obj) {}

}
