/*****************************************************************
This is the enum that contains all the information that is to be 
used for the Login buttons. 

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public enum LoginButtonEnum implements ProgButton{
	
	/***/
	LOGIN ("LOGIN"), 
	
	/***/
	NEW_ACCOUNT ("NEW ACCOUNT"),

	/***/
	CREATE_ACCOUNT ("CREATE ACCOUNT"),
	
	/***/
	CANCEL ("Cancel");
	
	/***/
	private final String title;
	
	/***/
	private final static String className = "LoginButton";
	
	/***/
	private final ImageIcon image;
	
	/***/
	public static ActionListener listener;
	
	/***/
	private Object passedObject;
	
	/*****************************************************************

	 *****************************************************************/
	private LoginButtonEnum (String title) {
		this.title = title;
		
		 //this goes and collects the image for the button
		//all images fo the buttons most be named based on the enum value
		image = new ImageIcon ("Images/ButtonImages/" + this.toString() + ".png");
		setListener();
	}
	
	/*****************************************************************
	Returns the title of the button for painting purposes
	
	@return String of the title of the enum
	 *****************************************************************/
	@Override
	public String getTitle () {
		return title;
	}
	
	/*****************************************************************
	Returns that class name of the type of enum this is.
	
	@return String of the class name of the button.
	 *****************************************************************/
	@Override
	public String getClassName() {
		return className;
	}
	
	/*****************************************************************
	Sets the listener for this type of button.
	 *****************************************************************/
	public static void setListener () {
		listener = Listeners.getListener(className);
	}

	/*****************************************************************
	Returns the listener to be used in the buttons.
	
	@return ActionListener for this type of button.
	 *****************************************************************/
	@Override
	public ActionListener getListener() {
		return listener;
	}

	/*****************************************************************
	Returns the Image that is used for this button. It might return
	a null if there is none.
	
	@return ImageIcon of the button to be painted.
	 *****************************************************************/
	@Override
	public ImageIcon getIcon() {
		return image;
	}

	@Override
	public ProgButton getType() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setPassedObject(Object obj) {
		// TODO Auto-generated method stub
		this.passedObject = obj;
	}

	@Override
	public Object getPassedObject() {
		// TODO Auto-generated method stub
		return passedObject;
	}
 
	
}