package goEntBeta2;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.Listeners;
import utilities.ProgramStyle;
import utilities.Rental;
import utilities.Rented;




/*TODO
 * in this have a few buttons to do certian this to the item displayed?
 * these will show in dialog boxes
 */


public class ViewAdminInfo  extends JPanel{

	Account account;

	Rental rental;

	Rented rented;

	public ViewAdminInfo (Object obj) {

		//sets the preferences for this JPanel
		this.setPreferredSize(ProgramStyle.infoPanelSize());
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,25,10));
		this.setLayout(new BorderLayout());


		if (obj instanceof Account){
			account = (Account) obj;
		}
		else if (obj instanceof Rental) {
			rental = (Rental) obj;
		}
		else if (obj instanceof Rented) {
			rented = (Rented) obj;
		}
		else {
			this.add(defaultPanel(), BorderLayout.CENTER);
		}




		//this.add(defaultPanel(), BorderLayout.CENTER);

	}

	/*****************************************************************

	 *****************************************************************/
	public JPanel defaultPanel () {
		JPanel panel = new JPanel();
		//panel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);

		JLabel textJLabel = new JLabel("Hi how are you");
		textJLabel.setFont(ProgramStyle.getFont(40));
		textJLabel.setForeground(ProgramStyle.WHITESIGN);
		textJLabel.setOpaque(false);
		panel.add(textJLabel, BorderLayout.NORTH);

		return panel;
	}

	/*****************************************************************

	 *****************************************************************/
	public JPanel accountPanel () {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);

		//What information do we want to show
		
		JLabel textJLabel = new JLabel(account.geteMail());
		textJLabel.setFont(ProgramStyle.getFont(40));
		textJLabel.setForeground(ProgramStyle.WHITESIGN);
		textJLabel.setOpaque(false);
		panel.add(textJLabel, BorderLayout.NORTH);
		
		
		
		
		return panel;
	}

	/*****************************************************************

	 *****************************************************************/
	public JPanel rentalPanel () {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);

		JLabel textJLabel = new JLabel(rental.getTitle());
		textJLabel.setFont(ProgramStyle.getFont(40));
		textJLabel.setForeground(ProgramStyle.WHITESIGN);
		textJLabel.setOpaque(false);
		panel.add(textJLabel, BorderLayout.NORTH);

		return panel;
	}

	/*****************************************************************

	 *****************************************************************/
	public JPanel rentedPanel () {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);

		JLabel textJLabel = new JLabel(rented.rental.getTitle());
		textJLabel.setFont(ProgramStyle.getFont(40));
		textJLabel.setForeground(ProgramStyle.WHITESIGN);
		textJLabel.setOpaque(false);
		panel.add(textJLabel, BorderLayout.NORTH);
		
		
		return panel;
	}

	/*****************************************************************

	 *****************************************************************/
	public void paintComponent (Graphics page) {

		ProgramStyle.paintSign(page, 0, 0, 
				ProgramStyle.infoPanelSize().width-20, 
				ProgramStyle.infoPanelSize().height-20,
				ProgramStyle.GREENSIGN, false);
	}

}
