/*****************************************************************
This is the user account object that contains all the information
for each user. This includes all the typical, address, email, 
and account id. This also has the password String. Last it contains
the rental history of the account, and the favorite lists and 
reserved lists.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;



import utilities.*;

public class Account {

	/**This is the unique accountID*/
	int accountID;

	//	//all getters or setters need to be contingent on this being true
	//	boolean accountOpen;

	/**First name on the account*/
	String firstName;

	/**Last name on the account*/
	String lastName;

	String eMail; //i think use the email for username??

	String address;

	String password;

	RentalList rentalHistory;

	RentalList reservedList;

	RentalList favoritesList;


	public Account () {
		rentalHistory = new RentalList("HISTORY");
		
		rentalHistory.add(new DVD("Fifth Element", 1995, RentalCategory.ACTION, ContentRating.PG13, "Luc Besson", null));
		rentalHistory.add(new VideoGame("Zelda", 2005, RentalCategory.CHILDRENS, ContentRating.E, "Nindendo", 1));
		rentalHistory.add(new TVEpisode("Capitol Critters", 1989, RentalCategory.HORROR, ContentRating.E10, 4, 3));
		rentalHistory.add(new DVD("Fifth Element", 1995, RentalCategory.ACTION, ContentRating.PG13, "Luc Besson", null));
		rentalHistory.add(new VideoGame("Zelda", 2005, RentalCategory.CHILDRENS, ContentRating.E, "Nindendo", 1));
		rentalHistory.add(new TVEpisode("Capitol Critters", 1989, RentalCategory.HORROR, ContentRating.E10, 4, 3));
		rentalHistory.add(new DVD("Fifth Element", 1995, RentalCategory.ACTION, ContentRating.PG13, "Luc Besson", null));
		rentalHistory.add(new VideoGame("Zelda", 2005, RentalCategory.CHILDRENS, ContentRating.E, "Nindendo", 1));
		rentalHistory.add(new TVEpisode("Capitol Critters", 1989, RentalCategory.HORROR, ContentRating.E10, 4, 3));
	}

	/*****************************************************************

	 *****************************************************************/
	public boolean rent(Rental rental){
		return false;
	}

	/*****************************************************************
	This is set to return a movie where it will figure the cost and
	return the price of the time rented.

	@param rental Rented object that is to be returned.
	@return Rented unit that has been returned. 
	 *****************************************************************/
	public Rented returnRental(Rented rental){

		rental.getCostForRent();
		
		return rental;
	}

	/*****************************************************************
	 * This checks to see if the submitted password matches the account
	 * password.
	 * @param password the submitted password.
	 *****************************************************************/
	public boolean checkPassword(String password){
		if(this.password.equals(password))
			return true;

		else
			return false;	}

	/*****************************************************************
	 * This returns the rental history for the account.
	 *****************************************************************/

	public RentalList getRentalHistory() {
		return rentalHistory;
	}

	/*****************************************************************
	 * This adds the rental to the account reserve list.
	 * @param FIGURE OUT WHAT THE PARAMTER ACTUALLY IS!!!!
	 *****************************************************************/
	public boolean addToReserve(Rental rental){
				for(int i = 0; i<reservedList.getSize();++i){
					if(reservedList.getIndex(i).equals(rental))
						return false;
					else
						reservedList.add(rental);
				}
		return true;

		//you can not use the "reservedList[i] because it is not
		//an array. you will have to have a search in the RentalList
		//class to determine if the object is there or not.
	}

	/*****************************************************************
	 * This returns the reserved list.
	 *****************************************************************/

	public RentalList getReservedList() {
		return reservedList;
	}

	/*****************************************************************
	 * This returns the favorites list.
	 *****************************************************************/

	public RentalList getFavoritesList() {
		return favoritesList;
	}

	/*****************************************************************
	 * This returns the account ID.
	 *****************************************************************/

	public int getAccountID() {
		return accountID;
	}

	/*****************************************************************
	 * This returns the account user's first name.
	 *****************************************************************/

	public String getFirstName() {
		return firstName;
	}

	/*****************************************************************
	 * This sets the user's first name.
	 * @param firstName the first name for the user.
	 *****************************************************************/

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*****************************************************************
	 * This returns user's last name.
	 *****************************************************************/

	public String getLastName() {
		return lastName;
	}

	/*****************************************************************
	 * This sets the user's last name.
	 * @param lastName the last name for the user.
	 *****************************************************************/

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*****************************************************************
	 * This returns the email for the current user.
	 *****************************************************************/

	public String geteMail() {
		return eMail;
	}

	/*****************************************************************
	 * This sets the email for the user.
	 * @param eMail the email for the user
	 *****************************************************************/

	public void seteMail(String eMail) {
		this.eMail = eMail.toLowerCase();
	}

	/*****************************************************************
	 * This returns the street address for the user.
	 *****************************************************************/

	public String getAddress() {
		return address;
	}

	/*****************************************************************
	 * This sets the street address for the user.
	 * @param address the street address for the user.
	 *****************************************************************/

	public void setAddress(String address) {
		this.address = address;
	}

	/*****************************************************************
	 * This returns the password for the user.
	 *****************************************************************/

	public String getPassword() {
		return password;
	}

	/*****************************************************************
	 * This sets the password for the user.
	 * @param password the password for the user.
	 *****************************************************************/

	public void setPassword(String password) {
		this.password = password;
	}

	/*****************************************************************
	 * This removes a rental from a list.
	 * @param rental the rental to be removed from the reserved list.
	 *****************************************************************/

	public void removeReserve(Rental rental){
					for(int i = 0; i<reservedList.getSize(); i++)
						if(reservedList.getIndex(i).equals(rental))
							reservedList.removeIndex(i);

	}


}
