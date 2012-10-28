/*****************************************************************


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.util.ArrayList;

import utilities.*;

public class DVD extends Rental{


	/** The director of a movie*/
	private String director;

	/**A list of the main actors*/ 
	private ArrayList<String> actors;
	
	/*****************************************************************
	
	 *****************************************************************/
	public DVD(){
		super();
		setCostPerDay(3.18);
	}
	
	public DVD(String title){
		super(title);
		setCostPerDay(3.18);
	}
	
	public DVD(String title, int year, RentalCategory type, 
			ContentRating content, String director, ArrayList<String> actors){
				
				super(title, year, type,content);
				
				this.setDirector(director);
				
				this.setActors(actors);
				
				setCostPerDay(3.18);	
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public ArrayList<String> getActors() {
		return actors;
	}

	public void setActors(ArrayList<String> actors) {
		this.actors = actors;
	}
}