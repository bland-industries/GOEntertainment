package goEntBeta2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import utilities.*;

import utilities.ProgramStyle;

public class ViewUserHome extends JPanel implements View {

	RentalList rentalHistory;

	JList list;


	/*****************************************************************

	 *****************************************************************/
	public ViewUserHome (RentalList rentalHistory) {
		this.rentalHistory = rentalHistory;

		//sets the preferences for this JPanel
		this.setMaximumSize(ProgramStyle.actionPanelSize());
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,25,10));
		this.setLayout(new BorderLayout());


		this.add(setUpTitlePanel(), BorderLayout.NORTH);
		this.add(setUpActionPanel(), BorderLayout.CENTER);
	}

	/*****************************************************************

	 *****************************************************************/
	private JPanel setUpTitlePanel () {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);


		JLabel label = new JLabel ("WELCOME " + ProgramStyle.getCurrentUser().toUpperCase());
		label.setFont(ProgramStyle.getFont(75));
		label.setForeground(ProgramStyle.WHITESIGN);
		panel.add(label, BorderLayout.NORTH);

		JLabel textJLabel = new JLabel("MOVIES YOU CURRENTLY HAVE RENTED OUT...");
		textJLabel.setFont(ProgramStyle.getFont(15));
		textJLabel.setForeground(ProgramStyle.WHITESIGN);
		textJLabel.setOpaque(false);
		panel.add(textJLabel, BorderLayout.SOUTH);

		return panel;

	}



	/*****************************************************************

	 *****************************************************************/
	private JPanel setUpActionPanel () {

		//Sets up the action panel
		JPanel actionPanel = new JPanel();
		actionPanel.setBorder(BorderFactory.createEmptyBorder(20,20,30,27));
		actionPanel.setLayout(new BorderLayout());
		actionPanel.setOpaque(false);

		//Sets up the JList to display the items
		list = new JList(rentalHistory);
		list.setCellRenderer(ProgramStyle.getUserRenderer());
		list.setOpaque(false);

		//Place the JList into a scrollable window
		JScrollPane scrollPane = new JScrollPane(list,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

		//Add all the parts together
		actionPanel.add(scrollPane, BorderLayout.CENTER);
		return actionPanel;

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
	private Dimension getLocation (Graphics page, String title, int fontSize) {
		FontMetrics fm = page.getFontMetrics(ProgramStyle.getFont(fontSize));
		Dimension stringSize = new Dimension(fm.stringWidth(title), 
				fm.getHeight());

		return stringSize;
	}




	@Override
	public void showList(RentalList list) {
		//this needs to show the rentals to be returned

	}




	@Override
	public void showAccount(Account account) {}
}
