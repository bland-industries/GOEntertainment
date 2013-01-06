/*****************************************************************
This manages lists of the rental objects.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import utilities.*;

public class RentedList extends AbstractListModel implements Serializable {

	ArrayList<Rented> rentedList;

	/** this is to be able to get a title for the list*/
	public String listName;

	/*****************************************************************
	Constructor for the list. create a blank list, and add the tile
	to the veriables. The purpose of the title is to say what is in
	the list. Typically it will be the sorting parameters like 
	Action, or even Tom Hanks. it can also be something like
	"Tyler's favorite movies".
	 *****************************************************************/
	public RentedList (String name) {

		rentedList= new ArrayList<Rented>();
		this.listName = name;

		load();
	}

	/*****************************************************************
	 * This returns the name of a list.
	 *****************************************************************/
	public String getListName() {
		return listName;
	}

	/*****************************************************************
	Adds a rental object to the list. only add a duplicate copy of the
	rental if it is a Rented type object.
	 *****************************************************************/
	public void add (Rented rented) {

		rentedList.add(rented);

	}


	/*****************************************************************
	returns the list of movies in this object
	 *****************************************************************/
	public ArrayList<Rented> getList () {

		return rentedList;
	}



	/*****************************************************************
	returns a single Rental object based on the contents of this list
	and the given search parameter
	 *****************************************************************/
	public Rented getIndex (int index) {

		return rentedList.get(index);
	}

	
	/*****************************************************************
	 *returns a rented item from the rented list given a rental
	 *@param rental the rented object you want returned
	 *****************************************************************/
	public Rented getItem (Rental rental) {
		for (int i = (rentedList.size()-1); i >= 0; i --) {
			if ((rentedList.get(i).rental.equals(rental)))
				return rentedList.get(i);
		}
		return null;
		
		
	}


	/*****************************************************************
	removes a single Rental object based on the contents of this list
	and the given search parameter
	 *****************************************************************/
	public void removeIndex(int index){
		rentedList.remove(index);
	}

	/*****************************************************************
	 *boolean to tell if the rented list contains an item
	 *if the rental item exists return true, else return false
	 *@param rental the rental object to be searched for
	 *****************************************************************/
	public boolean containsItem(Rental rental) {
		for (Rented rented: rentedList) {
			if ((rented.rental.equals(rental)))
				return true;
		}
		return false;
	}

	/*****************************************************************
	 * Saves as serializable
	 *****************************************************************/
	public void save () {
		try {
			FileOutputStream fos = new FileOutputStream(listName);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(rentedList);
			os.writeObject(listName);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*****************************************************************
	 * loads as serializble
	 *****************************************************************/
	@SuppressWarnings("unchecked")
	public void load () {
		try {
			FileInputStream fis = new FileInputStream(listName);
			ObjectInputStream is = new ObjectInputStream(fis);

			rentedList =  (ArrayList<Rented>) is.readObject();
			fireIntervalAdded(this, 0, rentedList.size() - 1);
			listName = (String) is.readObject();
			is.close();
		} catch (Exception ex) {
		}
	}

	/*****************************************************************
	 * This is the method that returns the rented object given an index
	 * @param index, the location that the rented object is at
	 *****************************************************************/
	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return rentedList.get(index);
	}

	/*****************************************************************
	 * This returns the size of a rented list.
	 *****************************************************************/
	@Override
	public int getSize() {
		return rentedList.size();
	}

	/*****************************************************************
	 *This removes a rental from the rented list
	 *****************************************************************/
	public void remove (Rented rental){
		rentedList.remove(rental);
	}

}
