/*****************************************************************
Enum for the different categories of the rental objects.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

public enum RentalCategory {
	ACTION, HORROR, COMEDY, DRAMA, ANIMATION, CHILDRENS, 
	
	WII, XBOX360, PS3;
	
	private boolean movie;
	
	private RentalCategory () {
		
	}
	
	public boolean isMovie(RentalCategory type ) {
		return false;
		
	}

}
