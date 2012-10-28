/*****************************************************************


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete 
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import utilities.*;

public class VideoGame extends Rental{
	
	/** The developer of the game*/
	private String developer;

	/**Compatible number of players*/ 
	private int players;

	/*****************************************************************
	
	 *****************************************************************/
	public VideoGame(){
		super();
		setCostPerDay(5.30);
	}
	
	public VideoGame(String title){
		super(title);
		setCostPerDay(5.30);
	}
	
	public VideoGame(String title, int year, RentalCategory type, 
			ContentRating content, String developer, int players){

				super(title, year, type, content);
				
				this.setDeveloper(developer);
				
				this.setPlayers(players);
				
				setCostPerDay(5.30);
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public int getPlayers() {
		return players;
	}

	public void setPlayers(int players) {
		this.players = players;
	}
}
