package planB;

import javax.swing.JFrame;

public class IgnitionAdd {

	/******************************************************************
	 Launches the plan B for adding new dvds and tv seasons
	 *****************************************************************/
	public static void main(String[] args){
		JFrame frame = new JFrame ("GO Ent");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//Set the log in view 
		
		frame.getContentPane().add(new ChoicePanel(frame));

		//show the background
		frame.pack();
		frame.setVisible(true);

	}

}
