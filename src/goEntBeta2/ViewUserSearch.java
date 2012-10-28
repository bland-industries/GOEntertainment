package goEntBeta2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import utilities.ProgramStyle;
import xtra.TestRentalList;

public class ViewUserSearch extends JPanel implements View  {

	/***/
	private JPanel actionPanel;

	private JPanel titlePanel;

	/***/
	private ArrayList<GoButton> buttons;

	/**This is the list to display in the View*/ 
	JList list;
	JTextArea textArea;
	TestRentalList rentalList;


	public ViewUserSearch (TestRentalList rentalList) {
		//sets the parameter variables
		this.rentalList = rentalList;

		//sets the preferences for this JPanel
		this.setPreferredSize(ProgramStyle.actionPanelSize());
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(60,20,25,10));
		this.setLayout(new BorderLayout());

		//Sets up the Title Panel
		titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		JLabel label = new JLabel("Action Movies...");			//
		label.setFont(ProgramStyle.getFont(30));
		titlePanel.add(label);
		this.add(titlePanel, BorderLayout.NORTH);

		//Sets up the action panel
		actionPanel = new JPanel();
		actionPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,9));
		actionPanel.setLayout(new BorderLayout());
		actionPanel.setOpaque(false);

		//Sets up the JList to display the items
		list = new JList(rentalList);
		list.setCellRenderer(ProgramStyle.getUserRenderer());
		list.setOpaque(false);

		//Place the JList into a scrollable window
		JScrollPane scrollPane = new JScrollPane(list,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

		//Add all the parts together
		actionPanel.add(scrollPane, BorderLayout.CENTER);
		this.add(actionPanel, BorderLayout.CENTER);

	}

	/*****************************************************************

	 *****************************************************************/
	public void paintComponent (Graphics page) {

		ProgramStyle.paintSign(page, 0, 0, 
				ProgramStyle.actionPanelSize().width, 
				ProgramStyle.actionPanelSize().height,
				ProgramStyle.GREENSIGN, false);
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
