/*****************************************************************
This displays the lists passed into the admin side of the GUI.

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

public class ViewAdminList extends JPanel {

	/**List to be displayed*/
	AbstractListModel itemList;

	/**Title of the list*/
	JList list;

	/**listener when the list is acted upon*/
	ActionListener listener;

	/*****************************************************************
	Constructor sets the basic settings
	 *****************************************************************/
	public ViewAdminList (AbstractListModel itemList) {
		this.itemList = itemList;
		listener = Listeners.getListener("ListListener");

		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,25,10));
		this.setLayout(new BorderLayout());

		this.add(setUpActionPanel(), BorderLayout.CENTER);
	}

	/*****************************************************************
	Sets up the panel that displays the list
	 *****************************************************************/
	private JPanel setUpActionPanel () {

		//Sets up the action panel
		JPanel actionPanel = new JPanel();
		actionPanel.setBorder(BorderFactory.createEmptyBorder(30,30,40,37));
		actionPanel.setLayout(new BorderLayout());
		actionPanel.setOpaque(false);

		//Sets up the JList to display the items
		list = new JList(itemList);
		list.addMouseListener(new ListClickListener());
		list.setOpaque(false);
		list.setBackground(ProgramStyle.GREENSIGN);

		//Place the JList into a scrollable window
		JScrollPane scrollPane = new JScrollPane(list,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

		//Add all the parts together
		actionPanel.add(scrollPane, BorderLayout.CENTER);
		return actionPanel;
	}

	/*****************************************************************
	Paints the pretty background
	 *****************************************************************/
	public void paintComponent (Graphics page) {

		ProgramStyle.paintSign(page, 0, 0, 
				ProgramStyle.actionPanelSize().width, 
				ProgramStyle.actionPanelSize().height,
				ProgramStyle.GREENSIGN, false);
	}

	/*****************************************************************
	The listener for the mouse click on the list.
	 *****************************************************************/

	public class ListClickListener extends MouseAdapter {

		/*****************************************************************
			The listener for the mouse click on the list.
		 *****************************************************************/
		public void mouseClicked(MouseEvent event){
			if(event.getClickCount() == 2){
				int index = list.locationToIndex(event.getPoint());
				ListModel dlm = list.getModel();
				Object item = dlm.getElementAt(index);;
				list.ensureIndexIsVisible(index);
				listener.actionPerformed(new ActionEvent(item, index, ""));
			}
		}

	}
}