/*****************************************************************
This class is used to maintain consistency in the style of
 the GUI.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import goEntBeta2.DVD;
import goEntBeta2.TVSeason;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;



public class ProgramStyle {

	/***/
	public static final Color ORANGE = new Color (238, 204, 46);

	/***/
	public static final Color LTORANGE = new Color (237, 218, 126);

	/***/
	public static final Color GREENLIGHT = new Color (65, 187, 82);

	/**Dark Green for the info signs*/
	public static final Color GREENSIGN = new Color (0, 111, 83);

	/**White for the Signs*/
	public static final Color WHITESIGN = new Color (235, 235, 235);

	/**Orange for the Signs*/
	public static final Color ORANGESIGN = new Color (255, 208, 70);

	/***/
	public static final ImageIcon SignPost = 
			new ImageIcon("Images/SignPost.png");

	/***/
	public static String currentUser;


	public static Dimension windowSize () {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		return new Dimension (dim.width, dim.height - 45);
	}

	public static Dimension buttonPanelSize () {
		return new Dimension (270, 0);
	}

	public static Dimension titlePanelSize () {
		return new Dimension(windowSize().width - buttonPanelSize().width, 75);
	}
	
	public static Dimension infoPanelSize () {
		return new Dimension(windowSize().width - buttonPanelSize().width,300);
	}

	public static Dimension actionPanelSize () {
		Dimension dim = windowSize();
		return new Dimension(dim.width - buttonPanelSize().width-20,
				dim.height - titlePanelSize().height-20);
	}


	/*****************************************************************
	This sends back the font style that is used for the GUI at the 
	requested size.

	@param size int font size desired.
	 *****************************************************************/
	public static Font getFont (int size) {
		return new Font ("Halvetica", Font.BOLD, size);
	}

	/*****************************************************************
	This creates the look of a highway sign, that is customizable to
	the needs of the circumstance.

	@param page Graphics object to paint the image on.
	@param x int x location of the top left of the sign.
	@param y int y location of the top left of the sign.
	@param width int width of the sign.
	@param hieght int Height of the sign.
	@param color Color to make the sign.
	@param blackFrame boolean True if the frame is black, else white.
	 *****************************************************************/
	public static void paintSign (Graphics page, int x, int y,
			int width, int height, 
			Color color, boolean blackFrame) {

		//paint the square edge of the image in the color sent.
		page.setColor(color);
		page.fillRect(x, y, width, height);

		//Draws a dark gray line around the signs to set them apart
		page.setColor(Color.DARK_GRAY);
		page.drawRect(x, y, width, height);


		//Sets up the offset for the rounded outside edge frame.
		int offset =  (int) (width*.015); //bases of measurements
		x = x + offset;	//Top left corner of the frame.
		y = y + offset;	//Top left corner of the frame.
		width -= offset * 2; //Width of the frame.
		height -= offset * 2; //Height of the frame.
		int arcSize = offset * 2; //Size of the rounded corners

		//Determines the color of the frame.
		if (blackFrame)
			page.setColor(Color.BLACK);
		else
			page.setColor(WHITESIGN);

		//Draws the frame as a solid color in the middle.
		page.fillRoundRect(x, y, width, height, arcSize, arcSize);


		//Sets up the offset for the rounded inside edge frame.
		if (width > 250) //the larger the frame the thinner the line
			offset =  (int) (width*.013); //bases of measurements
		else
			offset =  (int) (width*.02); //bases of measurements
		x = x + offset;	//Top left corner of the frame.
		y = y + offset;	//Top left corner of the frame.
		width -= offset * 2; //Width of the frame.
		height -= offset * 2; //Height of the frame.
		arcSize = offset * 2; //Size of the rounded corners

		//Sets the color back to the parameter color
		page.setColor(color);
		//Fills in the center to make the frame a border only.
		page.fillRoundRect(x, y, width, height, arcSize, arcSize);


	}

	/*****************************************************************
	Paints the background image to use behind all the View windows
	 *****************************************************************/
	public static void paintBackground (Component c, Graphics page) {
		ImageIcon backGround = new ImageIcon("Images/CloudBackground.png");
		backGround.paintIcon(c, page, 0, 0);
	}

	/*****************************************************************

	 *****************************************************************/
	public static String getCurrentUser() {
		return currentUser;
	}

	/*****************************************************************

	 *****************************************************************/
	public static void setCurrentUser(String currentUser) {
		ProgramStyle.currentUser = currentUser;
	}

	/*****************************************************************

	 *****************************************************************/
	public static Dimension getStringSize (Graphics page, String title, int fontSize) {

		FontMetrics fm = page.getFontMetrics(ProgramStyle.getFont(fontSize));
		Dimension stringSize = new Dimension(fm.stringWidth(title), 
				fm.getHeight());

		return stringSize;
	}


	/*****************************************************************

	 *****************************************************************/
	public static ListCellRenderer getUserRenderer () {
		ProgramStyle temp = new ProgramStyle();
		return temp.new RentalRenderer();

	}



	/*****************************************************************

	 *****************************************************************/
	public static ListCellRenderer getAdminRenderer () {
		ProgramStyle temp = new ProgramStyle();
		return temp.new AdminRenderer();
	}

	//probably should have seperate browse cover diplay and the 
	//power search titles.

	/*****************************************************************

	 *****************************************************************/
	class UserRenderer extends JPanel implements ListCellRenderer {

		/*****************************************************************

		 *****************************************************************/
		@Override
		public Component getListCellRendererComponent(
				final JList list,              // the list
				final Object value,            // value to display
				final int index,               // cell index
				final boolean isSelected,      // is the cell selected
				final boolean cellHasFocus)    // does the cell have focus
		{
			return new JPanel() {
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					//this is where the styling needs to be built.
					//us this as a JPanel to add anything wanted.
					setPreferredSize(new Dimension(600, 140));

					System.out.println ("Renderer");
					Rental rental = ((Rental) value);

					g.setColor(isSelected ? ProgramStyle.GREENSIGN : 
						ProgramStyle.GREENSIGN);
					g.fillRect(0, 0, getWidth(), getHeight());


					rental.getImage().paintIcon(this, g, 5, 5);
					g.setColor(Color.BLACK);
					g.drawString(rental.getTitle(), 160, 70);

				}
				public Dimension getPreferredSize() {

					return new Dimension(600, 140);
				}
			};

		}
	}

	/*****************************************************************

	 *****************************************************************/
	class RentalRenderer extends JPanel implements ListCellRenderer {

		/*****************************************************************

		 *****************************************************************/
		@Override
		public Component getListCellRendererComponent(
				final JList list,              // the list
				final Object value,            // value to display
				final int index,               // cell index
				final boolean isSelected,      // is the cell selected
				final boolean cellHasFocus)    // does the cell have focus
		{
			return new JPanel() {



				public void paintComponent(Graphics g) {
					super.paintComponent(g);

					//this is where the styling needs to be built.
					//us this as a JPanel to add anything wanted.
					//setPreferredSize(getPreferredSize());

					Rental rental;
					
					if (value instanceof Rented)
						 rental = (((Rented) value).rental);
					else
						rental = ((Rental) value);

					//sets the background coloer of the panel
					g.setColor(isSelected ? ProgramStyle.GREENSIGN : 
						ProgramStyle.GREENSIGN);
					g.fillRect(0, 0, getWidth(), getHeight());

					//Displays the image of the type of rental
					ImageIcon image;
					if (rental instanceof DVD) {
						image = new ImageIcon("Images/CatImages/MovieCat.png");
						image.paintIcon(this, g, 5, 5);
						
						g.setColor(WHITESIGN);
						g.setFont(ProgramStyle.getFont(20));
						g.drawString(rental.getTitle(), 130, 25);
					}

					if (rental instanceof TVSeason) {
						image = new ImageIcon("Images/CatImages/TVCat.png");
						image.paintIcon(this, g, 5, 5);
						
						g.setColor(WHITESIGN);
						g.setFont(ProgramStyle.getFont(20));
						g.drawString(rental.getTitle(), 130, 25);
					}


					g.setColor(WHITESIGN);
					g.setFont(ProgramStyle.getFont(20));
					g.drawString(rental.getTitle(), 130, 25);

				}
				
				
				
				
				public Dimension getPreferredSize() {

					return new Dimension(actionPanelSize().width - 40, 140);
				}
			};

		}
	}



	/*****************************************************************

	 *****************************************************************/

	//NOTE: maybe split the admin renderer for the account and the rental lists.
	//and maybe the rented lists


	class AdminRenderer extends JPanel implements ListCellRenderer {

		/*****************************************************************

		 *****************************************************************/
		@Override
		public Component getListCellRendererComponent(
				JList list,              // the list
				Object value,            // value to display
				int index,               // cell index
				boolean isSelected,      // is the cell selected
				boolean cellHasFocus)    // does the cell have focus
		{
			return new JPanel() {
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					//this is where the styling needs to be built.
					//us this as a JPanel to add anything wanted.
				}
			};

		}

	}

}
