package xtra;

import goEntBeta2.DVD;
import goEntBeta2.TVEpisode;
import goEntBeta2.VideoGame;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

import utilities.ContentRating;
import utilities.Rental;
import utilities.RentalCategory;

public class TestRentalList  extends AbstractListModel implements Serializable {

	ArrayList<Rental> rentals;
	
	public TestRentalList () {
		rentals = new ArrayList<Rental>();
		
		rentals.add(new DVD("Fifth Element", 1995, RentalCategory.ACTION, ContentRating.PG13, "Luc Besson", null));
		rentals.add(new VideoGame("Zelda", 2005, RentalCategory.CHILDRENS, ContentRating.E, "Nindendo", 1));
		rentals.add(new TVEpisode("Capitol Critters", 1989, RentalCategory.HORROR, ContentRating.E10, 4, 3));
		rentals.add(new DVD("Fifth Element", 1995, RentalCategory.ACTION, ContentRating.PG13, "Luc Besson", null));
		rentals.add(new VideoGame("Zelda", 2005, RentalCategory.CHILDRENS, ContentRating.E, "Nindendo", 1));
		rentals.add(new TVEpisode("Capitol Critters", 1989, RentalCategory.HORROR, ContentRating.E10, 4, 3));
		rentals.add(new DVD("Fifth Element", 1995, RentalCategory.ACTION, ContentRating.PG13, "Luc Besson", null));
		rentals.add(new VideoGame("Zelda", 2005, RentalCategory.CHILDRENS, ContentRating.E, "Nindendo", 1));
		rentals.add(new TVEpisode("Capitol Critters", 1989, RentalCategory.HORROR, ContentRating.E10, 4, 3));
	}
	
	
	
	
	
	@Override
	public Object getElementAt(int index) {
		System.out.println ("get index: " + index);
		// TODO Auto-generated method stub
		return rentals.get(index);//.getTitle();
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return rentals.size();
	}

}
