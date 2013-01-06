package planB;
import goEntBeta2.DVD;
import goEntBeta2.RentalList;
import goEntBeta2.TVSeason;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

import utilities.ContentRating;
import utilities.ProgramStyle;
import utilities.Rental;
import utilities.RentalCategory;



public class ChoicePanel extends JPanel{

	/**button to add a tv season*/
	JButton tv;
	
	/**button to add a dvd*/
	JButton dvd;
	
	/**button to save list to a file*/
	JButton save;
	
	/**list of all rentals*/
	RentalList primeList;
	
	/**text in panel*/
	JDialog dialog;
	
	/**frame to hold panel data*/
	JFrame frame;
	
	/**list of rentals*/
	JList list;
	
	/**constructor for Choice panel that determines if a rental is a
	 * dvd or a tv season
	 * @param frame holds buttons and rental list
	 */
	public ChoicePanel (JFrame frame) {
		this.frame = frame;
		setPreferredSize(new Dimension (500,500));
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		
		primeList = new RentalList("Full Library");
		
		tv = new JButton ("TV");
		tv.addActionListener(new TVListener());
		
		dvd = new JButton ("DVD");
		dvd.addActionListener(new DVDListener());
		
		save = new JButton ("Save");
		save.addActionListener(new SaveListener());
		
		
		topPanel.add(tv);
		topPanel.add(dvd);
		topPanel.add(save);
		
		add(topPanel, BorderLayout.NORTH);
		
		//Sets up the JList to display the items
				list = new JList(primeList);

				//Place the JList into a scrollable window
				JScrollPane scrollPane = new JScrollPane(list,
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.
						HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setOpaque(false);
				scrollPane.setBorder(BorderFactory.
				createEmptyBorder(0,0,0,0));

				//Add all the parts together
				add(scrollPane, BorderLayout.CENTER);
		
		//load();
		
	}
	
	/*****************************************************************
    Loads previous list of rentals
	 *****************************************************************/
	private void load () {
		//find the file
		Scanner scan;
		Scanner lineScan;
		try {
			scan = new Scanner(new File ("RentalList.txt"));

		} 
		catch (FileNotFoundException e) {
			scan = new Scanner("not going to work");
		}

		while (scan.hasNext()) {
			String line = scan.nextLine();
			
			String[] var = line.split("ZzZ");
		
			if (var[0].equals("TV")) {
				TVSeason tv = new TVSeason (
						var[1], //title
						Integer.parseInt(var[2]), //year
						RentalCategory.valueOf(var[3]), //genre
						ContentRating.valueOf(var[4]), //rating
						Integer.parseInt(var[2]), //season
						var[6], //actors
						var[7], 
						var[8], 
						var[9], 
						var[10],
						var[11]);
			}
			else {
				DVD dvd = new DVD (
						var[1], //title
						Integer.parseInt(var[2]), //year
						RentalCategory.valueOf(var[3]), //genre
						ContentRating.valueOf(var[4]), //rating
						var[5], //director
						var[6], //actors
						var[7], 
						var[8], 
						var[9], 
						var[10],
						var[11]);
			}
		}
		
		
		
		
	}
	
	
	/*****************************************************************
	Saves the data for future use from the storage arrays.
	 *****************************************************************/
	private void save () {
		try {
			File bbfile = new File ("RentalList.txt");
			PrintWriter myoutput = new PrintWriter (bbfile);
			
			for(int i = 0; i < primeList.getSize(); i++)
				myoutput.println(((Rental) primeList.getElementAt(i)).
				saveString());
			
			myoutput.close(); 
		}
		catch (FileNotFoundException e)
		{
			System.err.println ("Could not open the file" +
		    e.getMessage());
		}
	}
	
	/*****************************************************************
    Adds rental to the list from dialog boxes
	 *****************************************************************/
	public void addRental () {
		if (dialog instanceof AddTV) {
			primeList.add(((AddTV)dialog).getTV());
		}
		else {
			primeList.add(((AddDVD)dialog).getDVD());
		}
		
		updateUI();
	}
	
	
	
	public class FinishListener implements ActionListener {

	/*****************************************************************
    closes this dialog box and adds rental to list
	 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			dialog.dispose();
			addRental();
			
			
		}
	}
	
	
	public class TVListener implements ActionListener {

	/*****************************************************************
     Listener for tv button   
	*****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			dialog = new AddTV(frame, new FinishListener());
			dialog.show();
		}
	}
	
	
	
	
	public class DVDListener implements ActionListener {

	/*****************************************************************
    listener for dvd button
	*****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			dialog = new AddDVD(frame, new FinishListener());
			dialog.show();
			

		}
	}
	
	public class SaveListener implements ActionListener {

	/*****************************************************************
    listener for save button
	*****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			save();
			primeList.save();

		}
	}
}
	
	

