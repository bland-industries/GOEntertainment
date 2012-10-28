package xtra;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class RendererThing extends JPanel implements ListCellRenderer {
		// final static ImageIcon longIcon = new ImageIcon("long.gif");
		//final static ImageIcon shortIcon = new ImageIcon("short.gif");

		// This is the only method defined by ListCellRenderer.
		// We just reconfigure the JLabel each time we're called.

		@Override
		public Component getListCellRendererComponent(
				final JList list,              // the list
				Object value,            // value to display
				int index,               // cell index
				final boolean isSelected,      // is the cell selected
				boolean cellHasFocus)    // does the cell have focus
		{

			final SingleItem item = (SingleItem) value;
			 return new JPanel() {
			      public void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        setPreferredSize(new Dimension(250, 250));
			        item.getImage().paintIcon(this, g, 0, 0);
			        g.drawString(item.getWord(), 125, 15);
			      g.drawLine(125, 15, 135, 25);

			      if (isSelected) {
						setBackground(list.getSelectionBackground());
						setForeground(list.getSelectionForeground());
					} else {
						setBackground(list.getBackground());
						setForeground(list.getForeground());
					}
					setEnabled(list.isEnabled());
					setFont(list.getFont());
					setOpaque(true);
			      }
			 };
			
			
			
			//this.add((JPanel) value);
			
			
			
//			System.out.println ("getting the renderer");
//			SingleItem item = (SingleItem) value;
//			
//			JLabel label = new JLabel(item.getImage());
//			add(label);
//			
//			label = new JLabel(item.getWord());
//			add(label);
//			
//			label = new JLabel("" + item.getNum());
//			add(label);
//			
//			setPreferredSize(new Dimension (250,250));

//			if (isSelected) {
//				setBackground(list.getSelectionBackground());
//				setForeground(list.getSelectionForeground());
//			} else {
//				setBackground(list.getBackground());
//				setForeground(list.getForeground());
//			}
//			setEnabled(list.isEnabled());
//			setFont(list.getFont());
//			setOpaque(true);
//			return this;
		}
}
