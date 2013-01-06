package xtra;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextStuff extends JPanel{

	JTextField field;
	JButton button;
	
	public TextStuff () {
		field = new JTextField(20);
		this.setPreferredSize(new Dimension (500,500));
		this.add(field);
		button = new JButton("GO");
		button.addActionListener(new action());
		this.add(button);
	}
	
	public String getWords () {
		return field.getText();
	}

	public class action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print(field.getText());
			
		}
		
		
		
	}
}
