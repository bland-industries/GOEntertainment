package xtra;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import utilities.ProgramStyle;


public class TestSign extends JPanel{

	
	
	
	public TestSign() {
		setPreferredSize(new Dimension(1000, 800));
		
		
	}
	
	public void paintComponent(Graphics g) {
       ProgramStyle.paintSign(g, 0, 0, 1000, 800, ProgramStyle.GREENSIGN, false);
       
       ProgramStyle.paintSign(g, 100, 100, 150, 50, ProgramStyle.ORANGESIGN, true);
	}
	
	
}
