package xtra;

import goEntBeta2.ViewUserSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

public class TestDriver {


	private static TextStuff list;




	public static void main(String[] args) {

		JFrame frame = new JFrame ("Dots");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		ViewUserSearch item = new ViewUserSearch(new TestRentalList());
		//list = new TextStuff();
		
		//this tests the drawing of the sign square
		frame.getContentPane().add (item);
		
		
		
		
		//This tests the list operations
//		dListModel = new ListOfItems();
//		list = new JList(dListModel);
//		list.setCellRenderer(new RendererThing());
//		list.setFixedCellHeight(250);
//		list.setFixedCellWidth(250);
//		frame.getContentPane().add (list);


		//frame.setSize(600,400);


		frame.pack();
		frame.setVisible(true);
	}
	
	


}
