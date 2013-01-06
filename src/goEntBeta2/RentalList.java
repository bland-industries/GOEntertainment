/*****************************************************************
This manages lists of the rental objects.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractListModel;

import utilities.*;

public class RentalList extends AbstractListModel implements Serializable {

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

		load();
	}

	public void printList () {
		for (Rental str: rentalList)
			System.out.println(str);
	}

	/*****************************************************************
	 * returns the name of a list
	 *****************************************************************/
	public String getListName() {
		return listName;
	}

	/*****************************************************************
	 *Sets the current list name equal to the parameter list name
	 *@param listName the name to set for the list
	 *****************************************************************/
	public void setListName(String listName) {
		this.listName = listName;
	}

	/*****************************************************************
	 * returns the rental list with the added rental
	 * @param rental the rented object.
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
		
		//goes through the rental list to add
		for(int i = 0; i < addList.getSize();i++){ 
			duplicate = false;
			for(int j = 0; j < rentalList.size(); j++){
				if(rentalList.get(j).equals(addList.getIndex(i))){
					duplicate = true;
				}
			}
			if(duplicate == false)
				rentalList.add(addList.getIndex(i)); 
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
	public RentalList get (RentalCategory type) {

		RentalList categoryList = new RentalList(listName + ": " +
				type.toString());
		for(int i=0; i<rentalList.size(); i++){
			if(rentalList.get(i).getType().equals(type))
				categoryList.add(getIndex(i));
		}

		return categoryList;
	}

	/*****************************************************************
	 * returns a RentalList based on the contents of this list and the
	 * given search parameter
	 * @param searchString the word(s) you want searched for.
	 *****************************************************************/
	public RentalList get (String searchString) {

		RentalList stringList = new RentalList(listName + ": " 
				+searchString);
		for(int i=0; i<rentalList.size(); i++){
			if(rentalList.get(i).getTitle().toLowerCase().
					contains(searchString))
				stringList.add(getIndex(i));
		}

		return stringList;
	}

	/*****************************************************************
	returns a RentalList based on the contents of this list and the
	given search parameter, any rental made between the two given years
	 *****************************************************************/
	public RentalList get (int firstYear, int secondYear) {


		RentalList yearList;

		//if the second year is greater then the first seach between
		if (secondYear > firstYear) {
			yearList = new RentalList(listName + "between "+firstYear+
					" and "+secondYear);
			for(int i=0; i<rentalList.size(); i++){
				int movieYear = rentalList.get(i).getYear();
				if(movieYear <= secondYear && movieYear >= firstYear)
					yearList.add(getIndex(i));
			}
		}
		//if second year lesser then first search only after first
		else {
			yearList = new RentalList(listName + ": After "+firstYear);
			for(int i=0; i<rentalList.size(); i++){
				int movieYear = rentalList.get(i).getYear();
				if(movieYear >= firstYear)
					yearList.add(getIndex(i));
			}
		}

		return yearList;
	}

	/*****************************************************************
	 *Returns the genre for a movie or TV show
	 *@param media the tv show or movie with a genre
	 *****************************************************************/
	public RentalList getType (String media) {

		RentalList stringList;
		
		if (media.equals("TV")) {
			stringList = new RentalList(listName + ": TV");
			for(int i=0; i<rentalList.size(); i++){
				if(rentalList.get(i)instanceof TVSeason)
					stringList.add(getIndex(i));
			}
		}
		else {
			stringList = new RentalList(listName + ": Movies");
			for(int i=0; i<rentalList.size(); i++){
				if(rentalList.get(i)instanceof DVD)
					stringList.add(getIndex(i));
			}
		}


		return stringList;



	}

	/*****************************************************************
	returns a single Rental object based on the contents of this list
	and the given search parameter
	 *****************************************************************/
	public Rental getItem (String searchString) {

		Rental movie = null;
		for(int i=0; i<rentalList.size(); i++){
			if(rentalList.get(i).getTitle().equalsIgnoreCase(searchString)){
				movie = rentalList.get(i);

			}
		}
		return movie;
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
	 *if the rental list contains the passed rental, return the rental
	 *@parm rental the rental to be checked for
	 *****************************************************************/
	public boolean contains(Rental rental) {
		return rentalList.contains(rental);
	}

	/*****************************************************************
	this should be saving it as serializble
	 *****************************************************************/
	public void save () {
		try {
			FileOutputStream fos = new FileOutputStream(listName);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(rentalList);
			os.writeObject(listName);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*****************************************************************
	this should be loading it as serializble
	 *****************************************************************/
	@SuppressWarnings("unchecked")
	public void load () {
		try {
			FileInputStream fis = new FileInputStream(listName);
			ObjectInputStream is = new ObjectInputStream(fis);

			rentalList =  (ArrayList<Rental>) is.readObject();
			fireIntervalAdded(this, 0, rentalList.size() - 1);
			listName = (String) is.readObject();
			is.close();
		} 
		catch (Exception ex) {
		}
	}

	/*****************************************************************
	 *Loads a list given the file name serializable
	 *@param fileName the name of the file
	 *****************************************************************/
	public void loadList(String fileName){
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream is = new ObjectInputStream(fis);

			rentalList =  (ArrayList<Rental>) is.readObject();
			fireIntervalAdded(this, 0, rentalList.size() - 1);
			listName = (String) is.readObject();
			is.close();
		} 
		catch (Exception ex) {
		}
	}

	/*****************************************************************
	 *loads a file from a text file given the file name
	 *@param fileName the name of the file to be loaded
	 *****************************************************************/
	public void loadTXT (String fileName) {
		//find the file
		Scanner scan;
		Scanner lineScan;
		try {
			scan = new Scanner(new File (fileName));

		} 
		catch (FileNotFoundException e) {
			scan = new Scanner("not going to work");
		}

		while (scan.hasNext()) {

			Rental rental;
			String line = scan.nextLine();

			String[] var = line.split("ZzZ");

			//			for (String str: var)
			//				System.out.println(str);

			if (var[0].equals("TV")) {
				rental = new TVSeason (
						var[1], //title
						Integer.parseInt(var[2]), //year
						RentalCategory.valueOf(var[3]), //genre
						ContentRating.valueOf(var[4]), //rating
						Integer.parseInt(var[2]), //season
						var[6], //actors
						var[7], 
						var[8], 
						var[9], 
						var[10],
						var[11]);
			}
			else {
				rental = new DVD (
						var[1], //title
						Integer.parseInt(var[2]), //year
						RentalCategory.valueOf(var[3]), //genre
						ContentRating.valueOf(var[4]), //rating
						var[5], //director
						var[6], //actors
						var[7], 
						var[8], 
						var[9], 
						var[10],
						var[11]);
			}
			rentalList.add(rental);
		}


		printList();
	}

	/*****************************************************************
	Saves the data for future use from the storage arrays.
	 *****************************************************************/
	public void saveTXT (String name) {
		try {
			File bbfile = new File (name);
			PrintWriter myoutput = new PrintWriter (bbfile);

			for(int i = 0; i < rentalList.size(); i++)
				myoutput.println(((Rental) rentalList.get(i)).saveString());

			myoutput.close(); 
		}
		catch (FileNotFoundException e)
		{
			System.err.println ("Could not open the file" + e.getMessage());
		}
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
	 * This returns the size of a rental list.
	 *****************************************************************/
	@Override
	public int getSize() {
		return rentalList.size();
	}

	public void remove (Rental rental){
		rentalList.remove(rental);
	}

}
