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

public enum AdminButtonEnum implements ProgButton{
    
    /***/
    SEARCH_ACCOUNTS ("Search Accounts"), 
    
    /***/
    SEARCH_MOVIES ("Search Movies"),
    
    /***/
    VIEW_ACCOUNTS ("All Accounts"),
    
    /***/
    VIEW_RENTALS ("All Rentals"),
    
    /***/
    VIEW_RENTEDS ("All Rented"),
    
    /***/
    REMOVE_RENTAL ("Remove Rental"),
    
    /***/
    ADD_RENTAL ("Add A Rental"),
    
    /***/
    GET_INFO ("More Info..."),
    
    LOAD_TXT ("Load txt list"),
    
    SAVE_TXT ("Save txt list"),
    
    LOAD_FILE ("Load File"),
    
    SAVE_FILE ("Save File"),
    
    LOGOUT ("Logout")
    ;
    
    
    /***/
    private final String title;
    
    /***/
    private final static String className = "AdminButton";
    
    /***/
    private final ImageIcon image;
    
    /***/
    public static ActionListener listener;
    
    /*****************************************************************

     *****************************************************************/
    private AdminButtonEnum (String title) {
        this.title = title;
        image = new ImageIcon ("Images/ButtonImages/" + this.toString() + ".png"); 
          //this goes and collects the image for the button
        setListener();
    }
    
    /*****************************************************************
    Returns tha title of the button for painting purposes
    
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
        
    }

    @Override
    public Object getPassedObject() {
        // TODO Auto-generated method stub
        return null;
    }
}
    
 
 