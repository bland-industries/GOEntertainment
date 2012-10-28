package xtra;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListOfItems extends AbstractListModel {

	ArrayList<SingleItem> listOfItems;
	
	
	public ListOfItems () {
		listOfItems = new ArrayList<SingleItem>();
		listOfItems.add(new SingleItem(1));
		listOfItems.add(new SingleItem(2));
//		listOfItems.add(new SingleItem(3));
//		listOfItems.add(new SingleItem(1));
//		listOfItems.add(new SingleItem(2));
//		listOfItems.add(new SingleItem(3));
//		listOfItems.add(new SingleItem(1));
//		listOfItems.add(new SingleItem(2));
//		listOfItems.add(new SingleItem(3));
		
	}
	
	@Override
	public Object getElementAt(int arg0) {
		System.out.println ("getNumber: " + arg0);
		
		
		return listOfItems.get(arg0);
		
		
		
		
//		System.out.println ("getting the renderer");
//		
//		JPanel panel = new JPanel();
//		SingleItem item = listOfItems.get(arg0);
//		
//		JLabel label = new JLabel(item.getImage());
//		panel.add(label);
//		
//		label = new JLabel(item.getWord());
//		panel.add(label);
//		
//		label = new JLabel("" + item.getNum());
//		panel.add(label);
//		
//		return panel;
		
		
		
		
		//return listOfItems.get(arg0).getWord();
		
		
	}

	@Override
	public int getSize() {
		System.out.println ("ArraySize: " + listOfItems.size());
		return listOfItems.size();
	}

}
