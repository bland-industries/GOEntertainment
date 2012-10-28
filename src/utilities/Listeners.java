/*****************************************************************
This is the listener holding tank for all the actions that need to
be performed. 

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.xml.bind.Marshaller.Listener;

public class Listeners {

	/***/
	private static ArrayList<ActionListener> listeners = 
									new ArrayList<ActionListener>();
	
	

	/*****************************************************************

	 *****************************************************************/
	public static ActionListener getListener(String type) {
		ActionListener tempListener;
		
		for (int index = 0; index < listeners.size(); index ++){
			if (listeners.get(index).toString() == (type))
				return listeners.get(index);
		}
		
		return null;
	}

	/*****************************************************************

	 *****************************************************************/
	public static void addListener(ActionListener listener) {
		listeners.add(listener);
	}
	
	
}
