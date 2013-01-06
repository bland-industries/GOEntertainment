package xtra;

import javax.swing.ImageIcon;

public class SingleItem {

	ImageIcon image;
	
	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public String getWord() {
		System.out.println ("get the word single");
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	String word;
	
	int num;
	
	public SingleItem (int num) {
		this.num = num;
		
		word = "this is the number " + num;
		
		image = new ImageIcon ("Images/MovieImages/Untitled-" + num + ".png");
		
		
		
	}
	
}
