/*****************************************************************


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete 
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import utilities.*;

public class TVEpisode extends Rental{
	
	/** The Season*/ 
	private int season;
	
	/** The episode number*/
	private int episode;

	/*****************************************************************
	
	 *****************************************************************/
	public TVEpisode(){
		super();
		setCostPerDay(2.12);
	}
	
	public TVEpisode(String title){
		super(title);
		setCostPerDay(2.12);
	}
	
	public TVEpisode(String title, int year, RentalCategory type, 
			ContentRating content, int season, int episode){

				super(title, year, type, content);
				
				this.setSeason(season);
				
				this.setEpisode(episode);
				
				setCostPerDay(2.12);
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}
}