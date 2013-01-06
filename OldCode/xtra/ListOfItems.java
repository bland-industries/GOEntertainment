package xtra;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListOfItems extends AbstractListModel {

	ArrayList<SingleItem> listOfItems;
	
	
	public ListOfItems () {
		listOfItems = new ArrayList<SingleItem>();
		listOfItems.add(new SingleItem(1));
		listOfItems.add(new SingleItem(2));
//		listOfItems.add(new SingleItem(3));
//		listOfItems.add(new SingleItem(1));
//		listOfItems.add(new SingleItem(2));
//		listOfItems.add(new SingleItem(3));
//		listOfItems.add(new SingleItem(1));
//		listOfItems.add(new SingleItem(2));
//		listOfItems.add(new SingleItem(3));
		
	}
	
	@Override
	public Object getElementAt(int arg0) {
		System.out.println ("getNumber: " + arg0);
		
		
		return listOfItems.get(arg0);
	}
		
		
		 /**
		   * Prompt the user for a filename, and save the scribble in that file.
		   * Serialize the vector of lines with an ObjectOutputStream.
		   * Compress the serialized objects with a GZIPOutputStream.
		   * Write the compressed, serialized data to a file with a FileOutputStream.
		   * Don't forget to flush and close the stream.
		   */
		 // public void save() {
//		    // Create a file dialog to query the user for a filename.
//		    FileDialog f = new FileDialog(frame, "Save Scribble", FileDialog.SAVE);
//		    f.show();                        // Display the dialog and block.
//		    String filename = f.getFile();   // Get the user's response
//		    if (filename != null) {          // If user didn't click "Cancel".
//		      try {
//		        // Create the necessary output streams to save the scribble.
//		        FileOutputStream fos = new FileOutputStream(filename); 
//									// Save to file
//		        GZIPOutputStream gzos = new GZIPOutputStream(fos);     
//									// Compressed
//		        ObjectOutputStream out = new ObjectOutputStream(gzos); 
//									// Save objects
//		        out.writeObject(lines);      	// Write the entire Vector of scribbles
//		        out.flush();                 		// Always flush the output.
//		        out.close();                 		// And close the stream.
//		      }
//		      // Print out exceptions.  We should really display them in a dialog...
//		      catch (IOException e) { System.out.println(e); }
//		    }
//		  }
//
//		  /**
//		   * Prompt for a filename, and load a scribble from that file.
//		   * Read compressed, serialized data with a FileInputStream.
//		   * Uncompress that data with a GZIPInputStream.
//		   * Deserialize the vector of lines with a ObjectInputStream.
//		   * Replace current data with new data, and redraw everything.
//		   */
//		  public void load() {
//		    // Create a file dialog to query the user for a filename.
//		    FileDialog f = new FileDialog(frame, "Load Scribble", FileDialog.LOAD);
//		    f.show();                         // Display the dialog and block.
//		    String filename = f.getFile();    // Get the user's response
//		    if (filename != null) {           // If user didn't click "Cancel".
//		      try {
//		        // Create necessary input streams
//		        FileInputStream fis = new FileInputStream(filename); // Read from 
//		file
//		        GZIPInputStream gzis = new GZIPInputStream(fis);     // Uncompress
//		        ObjectInputStream in = new ObjectInputStream(gzis);  // Read objects
//		        // Read in an object.  It should be a vector of scribbles
//		        Vector newlines = (Vector)in.readObject();
//		        in.close();                    // Close the stream.
//		        lines = newlines;              // Set the Vector of lines.
//		        repaint();                     // And redisplay the scribble.
//		      }
//		      // Print out exceptions.  We should really display them in a dialog...
//		      catch (Exception e) { System.out.println(e); }
//		    }
//		  }
//		

		
		
	

	@Override
	public int getSize() {
		System.out.println ("ArraySize: " + listOfItems.size());
		return listOfItems.size();
	}

}
