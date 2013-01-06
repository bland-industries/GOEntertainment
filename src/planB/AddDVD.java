package planB;

import goEntBeta2.DVD;
import goEntBeta2.GoButton;
import goEntBeta2.TVSeason;
import goEntBeta2.ViewUserNewAccount.DialogPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utilities.ContentRating;
import utilities.LoginButtonEnum;
import utilities.ProgramStyle;
import utilities.RentalCategory;

public class AddDVD extends JDialog{

	/**
	 * @param args
	 */

	Dimension size;

	//title, year, rentalcategory(genre), contentrating, director,Actors(top5)

	JLabel title;
	JTextField titlef;

	JLabel year;
	JTextField yearf;

	JLabel rentalcategory;
	JComboBox rentalcategoryf;

	JLabel contentrating;
	JComboBox contentratingf;

	JLabel director;
	JTextField directorf;

	JLabel Actors;
	JTextField Actors1;

	JTextField Actors2;

	JTextField Actors3;

	JTextField Actors4;

	JTextField Actors5;

	JButton imageFile;
	String imageLocal;

	JButton additem;


	ActionListener listener;

	public AddDVD (JFrame frame, ActionListener list) {

		super (frame, true);
		listener = list;


		//gets the screen size
		Dimension dim = ProgramStyle.windowSize();

		size = new Dimension (500,750);

		// Determine the new location of the window, centered on the screen
		int x = (dim.width/2) - (size.width/2);
		int y = (dim.height/2) - (size.height/2);

		// Moves the window to the center
		this.setLocation(x, y);

		//adds the panel
		this.getContentPane().add(new DialogPanel ());
		pack();



	}

	public DVD getDVD () {

		//add input info into the set methods of tv ep

		DVD dv = new DVD(titlef.getText(),
				Integer.parseInt(yearf.getText()),
				(RentalCategory)rentalcategoryf.getSelectedItem(),
				(ContentRating)contentratingf.getSelectedItem(),
				directorf.getText(),
				Actors1.getText(),
				Actors2.getText(), 
				Actors3.getText(),
				Actors4.getText(),
				Actors5.getText(),
				imageLocal);

		return dv;
	}

	public class DialogPanel extends JPanel {

		/*****************************************************************

		 *****************************************************************/
		public DialogPanel () {
			this.setPreferredSize(size);

			this.setLayout(new GridLayout(0,1));

			title = new JLabel("title");
			titlef = new JTextField();

			add(title);
			add(titlef);



			year = new JLabel("year");
			yearf = new JTextField();

			add(year);
			add(yearf);

			rentalcategory = new JLabel("genre");
			rentalcategoryf = new JComboBox(RentalCategory.values());

			add(rentalcategory);
			add(rentalcategoryf);

			contentrating = new JLabel("rating");
			contentratingf = new JComboBox(ContentRating.values());

			add(contentrating);
			add(contentratingf);

			director = new JLabel("director");
			directorf = new JTextField();

			add(director);
			add(directorf);

			Actors = new JLabel("actors/actresses");
			Actors1 = new JTextField();

			Actors2 = new JTextField();

			Actors3 = new JTextField();

			Actors4 = new JTextField();

			Actors5 = new JTextField();

			add(Actors);
			add(Actors1);
			add(Actors2);
			add(Actors3);
			add(Actors4);
			add(Actors5);

			imageFile = new JButton("Get Image File");
			imageFile.addActionListener(new ImageListener());
			add(imageFile);

			additem = new JButton ("add");
			additem.addActionListener(listener);
			add(additem);
		}
	}
	public class ImageListener implements ActionListener {

		/*****************************************************************

		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser fc = new JFileChooser();
			
			fc.showOpenDialog(null);
			File file = fc.getSelectedFile();
			imageLocal = file.getName();

		}

		/*****************************************************************

		 *****************************************************************/



	}
}