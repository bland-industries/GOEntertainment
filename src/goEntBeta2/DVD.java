/*****************************************************************


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.io.Serializable;

import utilities.*;

public class DVD extends Rental {
	
	
	/** The Director*/ 
	private String director;
	
	/*****************************************************************
	Constructor for the DVD Rentable class. Simply calls the super 
	constructor and sets the DVD's rental cost
	 *****************************************************************/
	public DVD(){
		super();
		setCostPerDay(3.18);
	}
	
	/*****************************************************************
	Title constructor of the DVD Subclass of Rental sets the title 
	and calls the super constructor.
	@param title Title of the Rental
	 *****************************************************************/
	public DVD(String title){
		super(title);
		setCostPerDay(3.18);
	}
	
	/*****************************************************************
	Constructor for the DVD Rentable class. Calls the super 
	constructor and sets the DVD's rental cost
	@param title Title of the DVD
	@param year Year the DVD show was originally produced.
	@param type Genre and type of the DVD
	@param content Content rating according to MPAA
	@param director Name of the director
	@param actor1 Name of the first primary actor.
	@param actor2 Name of the second primary actor.
	@param actor3 Name of the third primary actor.
	@param actor4 Name of the fourth primary actor.
	@param actor5 Name of the fifth primary actor.
	 *****************************************************************/
	public DVD(String title, int year, RentalCategory type, 
			ContentRating content, String director, String actor1 , 
			String actor2 , String actor3 , String actor4 , 
			String actor5, String iconLocal){
				
	    super(title, year, type,content, actor1, actor2, actor3,
		actor4, actor5, iconLocal);
	    
	    this.director = director;
				
		setCostPerDay(3.18);	
	}
	
	/*****************************************************************
	Returns the director of this DVD
	@return String
	 *****************************************************************/
	public String getDirector() {
		return director;
	}
    
	/*****************************************************************
	Sets the director of this DVD
	@param director Director of this DVD
	 *****************************************************************/
	public void setDirector(String director) {
		this.director = director;
	}
	
	/*****************************************************************
	Converts the data of this DVD into a string
	@return String
	 *****************************************************************/
	@Override
	public String saveString(){
		return ("DVD"+"ZzZ"+title+"ZzZ"+year+"ZzZ"+type+"ZzZ"+content+
		"ZzZ"+director+"ZzZ"+actor1+"ZzZ"+actor2+"ZzZ"+actor3+"ZzZ"+
		actor4+"ZzZ"+actor5+"ZzZ"+iconLocation);
	}
	
	/*****************************************************************
	Converts the data of this DVD into a string
	@return String
	 *****************************************************************/
	@Override
	public String toString(){
		return ("DVD "+title+" "+year+" "+type+" "+content+" "+
	director+" "+actor1+" "+actor2+" "+actor3+" "+actor4+" "+
	actor5+" "+iconLocation);
	}

	
}