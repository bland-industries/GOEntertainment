/*****************************************************************


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete 
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.io.Serializable;

import utilities.*;

public class TVSeason extends Rental {
	
	/** The Season*/ 
	private int season;

	/*****************************************************************
	Constructor for the TVSeason Rentable class. Simply calls the 
	super constructor and sets the TVSeason's rental cost
	 *****************************************************************/
	public TVSeason(){
		super();
		setCostPerDay(2.12);
	}
	
	/*****************************************************************
	Title constructor of the TVSeason Subclass of Rental sets the 
	title and calls the super constructor.
	@param title Title of the Rental
	 *****************************************************************/
	public TVSeason(String title){
		super(title);
		setCostPerDay(2.12);
	}
	
	/*****************************************************************
	Constructor for the TVSeason Rentable class. Calls the super 
	constructor and sets the Season's rental cost and season
	@param title Title of the TV Show
	@param year Year the TV show was originally produced.
	@param type Genre and type of the TV Show
	@param content Content rating according to FCC
	@param season Number of season.
	@param actor1 Name of the first primary actor.
	@param actor2 Name of the second primary actor.
	@param actor3 Name of the third primary actor.
	@param actor4 Name of the fourth primary actor.
	@param actor5 Name of the fifth primary actor.
	 *****************************************************************/
	public TVSeason(String title, int year, RentalCategory type, 
			ContentRating content, int season , String actor1 , 
			String actor2 , String actor3 , String actor4 , 
			String actor5, String iconLocal){

			super(title, year, type, content,  actor1, actor2, actor3,
			actor4, actor5, iconLocal);
				
			this.setSeason(season);
				
			setCostPerDay(2.12);
	}

	/*****************************************************************
	Returns the season of this TVSeason
	@return int
	 *****************************************************************/
	public int getSeason() {
		return season;
	}
    
	/*****************************************************************
	Sets the season of this TVSeason
	@param season 
	 *****************************************************************/
	public void setSeason(int season) {
		this.season = season;
	}
	
	/*****************************************************************
	Converts the data of this TVSeason into a string
	@return String
	 *****************************************************************/
	public String saveString(){
		return ("TV"+"ZzZ"+title+"ZzZ"+year+"ZzZ"+type+"ZzZ"+content+
		"ZzZ"+season+"ZzZ"+actor1+"ZzZ"+actor2+"ZzZ"+actor3+"ZzZ"+
		actor4+"ZzZ"+actor5+"ZzZ"+iconLocation);
	}
	
	/*****************************************************************
	Converts the data of this TVSeason into a string
	@return String
	 *****************************************************************/
	public String toString(){
		return ("TV"+" "+title+" "+year+" "+type+" "+content+" "+
	    season+" "+actor1+" "+actor2+" "+actor3+" "+actor4+" "+actor5+
	    " "+iconLocation);
	}
}