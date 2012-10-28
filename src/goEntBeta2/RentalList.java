/*****************************************************************
This manages lists of the rental objects.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import utilities.*;

public class RentalList extends AbstractListModel {

	ArrayList<Rental> rentalList;

	/** this is to be able to get a title for the list*/
	public String listName;

	/*****************************************************************
	Constructor for the list. create a blank list, and add the tile
	to the veriables. The purpose of the title is to say what is in
	the list. Typically it will be the sorting parameters like 
	Action, Wii, or even Tom Hanks. it can also be something like
	"Tyler's favorite movies".
	 *****************************************************************/
	public RentalList (String name) {

		rentalList= new ArrayList<Rental>();
		this.listName = name;


	}


	/*****************************************************************
	Adds a rental object to the list. only add a duplicate copy of the
	rental if it is a Rented type object.
	 *****************************************************************/
	public void add (Rental rental) {

		rentalList.add(rental);

	}


	/*****************************************************************
	Adds a rental list to the list. needs to make sure that there is
	no duplicates being added.
	 *****************************************************************/
	public void add (RentalList addList) {

		/**boolean variable to represent if the list has a duplicate*/
		 boolean duplicate;
		 
		 /** the index of a variable in an Array List */
		 int index =0;
		 
		for(int i = 0; i<rentalList.size();i++){
			duplicate = false;
			for(int j = 0; j<addList.getSize();j++){
				if(rentalList.get(i).equals(addList.getIndex(i))){
					duplicate = true;
					index = j;
				}
			}
			if(duplicate == false)
				rentalList.add(addList.getIndex(index)); 
		}

	
	}

	/*****************************************************************
	returns the list of movies in this object
	 *****************************************************************/
	public ArrayList<Rental> getList () {

		return rentalList;
	}

	/*****************************************************************
	returns a RentalList based on the contents of this list and the
	given search parameter
	 *****************************************************************/
//	public RentalList get (RentalCategory type) {
//		
//		ArrayList<RentalList> categoryList = new ArrayList<RentalList>();
//		for(int i=0; i<rentalList.size(); i++){
//			if(rentalList.get(i).getType().equals(type))
//				categoryList.add(getIndex(i));
//		}
//		
//		
//
//		return categoryList;
//	}

	/*****************************************************************
	returns a RentalList based on the contents of this list and the
	given search parameter
	 *****************************************************************/
	public RentalList get (String searchString) {

		return null;
	}

	/*****************************************************************
	returns a RentalList based on the contents of this list and the
	given search parameter, any rental made between the two given years
	 *****************************************************************/
	public RentalList get (int firstYear, int secondYear) {

		return null;
	}

	/*****************************************************************
	returns a single Rental object based on the contents of this list
	and the given search parameter
	 *****************************************************************/
	public Rental getItem (String searchString) {

		return null;
	}
	
	/*****************************************************************
	returns a single Rental object based on the contents of this list
	and the given search parameter
	 *****************************************************************/
	public Rental getItem (int rentalID) {

		return null;
	}
	
	/*****************************************************************
	returns a single Rental object based on the contents of this list
	and the given search parameter
	 *****************************************************************/
	public Rental getIndex (int index) {

		return rentalList.get(index);
	}
	
	/*****************************************************************
	removes a single Rental object based on the contents of this list
	and the given search parameter
	 *****************************************************************/
	public void removeIndex(int index){
		rentalList.remove(index);
	}
	
	/*****************************************************************
	this should be saving it as serializble
	 *****************************************************************/
	public void save () {

	}
	
	/*****************************************************************
	this should be loading it as serializble
	 *****************************************************************/
	public void load () {

	}

	/*****************************************************************
	This is the method that returns 
	 *****************************************************************/
	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return rentalList.get(index);
	}

	/*****************************************************************
	
	 *****************************************************************/
	@Override
	public int getSize() {
		return rentalList.size();
	}

}
