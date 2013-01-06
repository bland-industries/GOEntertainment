package xtra;

import java.util.Random;

import javax.swing.ImageIcon;

public class TestRental {
	
	String title;
	
	ImageIcon image;
	
	public TestRental (String title, String type) {
		this.title = title;
		
		Random gen = new Random();
		
		image = new ImageIcon("Images/CatImages/" + type + "Cat.png");
		
		
	}

	public String getTitle() {
		
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ImageIcon getImage() {
		
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

}
