/****************************************************************
This is to allow objects to pass thru the View side with ease 
and of no mind to some portions.


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;
import javax.swing.AbstractListModel;

public interface View {

	/*****************************************************************
	Allows an AbstractListModel to be passed into the GUI
	*****************************************************************/
	abstract void showList (AbstractListModel list);
	
	/*****************************************************************
	Allows an object to be passed into the view of any type
	*****************************************************************/
	abstract void showItem (Object obj);
	
	
}
