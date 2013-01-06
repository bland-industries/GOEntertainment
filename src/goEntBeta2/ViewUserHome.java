/****************************************************************
the default view when logged in

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import goEntBeta2.ViewUserList.ListClickListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import utilities.*;

import utilities.ProgramStyle;

public class ViewUserHome extends JPanel implements View {

	AbstractListModel itemsList;

	JList list;

	ActionListener listener;


	/*****************************************************************
	 * constructor
	 *****************************************************************/
	public ViewUserHome (AbstractListModel itemsList) {
		this.itemsList = itemsList;

		listener = Listeners.getListener("ListListener");

		//sets the preferences for this JPanel
		this.setMaximumSize(ProgramStyle.actionPanelSize());
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,25,10));
		this.setLayout(new BorderLayout());


		this.add(setUpTitlePanel(), BorderLayout.NORTH);
		this.add(setUpActionPanel(), BorderLayout.CENTER);
	}

	/*****************************************************************
	 * sets up the title panel and populates it
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
	 * sets up action panel and populates it
	 *****************************************************************/
	private JPanel setUpActionPanel () {

		//Sets up the action panel
		JPanel actionPanel = new JPanel();
		actionPanel.setBorder(BorderFactory.createEmptyBorder(20,20,30,27));
		actionPanel.setLayout(new BorderLayout());
		actionPanel.setOpaque(false);

		//Sets up the JList to display the items
		list = new JList(itemsList);
		list.addMouseListener(new HomeClickListener());
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
	 * paints graphics to use
	 *****************************************************************/
	public void paintComponent (Graphics page) {

		ProgramStyle.paintSign(page, 0, 0, 
				ProgramStyle.actionPanelSize().width, 
				ProgramStyle.actionPanelSize().height,
				ProgramStyle.GREENSIGN, false);
	}

	/*****************************************************************
	 * determines the location of the string for printing
	 *****************************************************************/
	private Dimension getLocation (Graphics page, String title, int fontSize) {
		FontMetrics fm = page.getFontMetrics(ProgramStyle.getFont(fontSize));
		Dimension stringSize = new Dimension(fm.stringWidth(title), 
				fm.getHeight());

		return stringSize;
	}

	@Override
	public void showList(AbstractListModel list) {}

	@Override
	public void showItem (Object obj) {}

	/****************************************************************
	Listens for the double click on the list
	 *****************************************************************/
	public class HomeClickListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				int index = list.locationToIndex(e.getPoint());
				ListModel dlm = list.getModel();
				Object item = dlm.getElementAt(index);;
				list.ensureIndexIsVisible(index);
				System.out.println("Double clicked on " + item);
				listener.actionPerformed(new ActionEvent(item, index, ""));

			}
		}
	}
}
