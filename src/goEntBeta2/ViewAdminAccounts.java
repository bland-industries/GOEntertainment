/*****************************************************************
Unimplemented class. 

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;

public class ViewAdminAccounts extends JPanel{

	/**This is the list to display in the View*/
	JList list;
	
	/**Contains buttons that act upon the JScrollPane*/
	JPanel subButtonPanel;
	
	/*****************************************************************
	constructor creates a JList from accounts & places in JScrollPane
	*****************************************************************/
	public ViewAdminAccounts(AbstractListModel accounts){
		
		list = new JList(accounts);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        
        JScrollPane listPane = new JScrollPane(list);
        
	    this.setLayout(new BorderLayout());
	    this.add(listPane, BorderLayout.CENTER);
        
	}
}
