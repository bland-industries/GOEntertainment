package Helper;

import goEntBeta2.LoginButton;
import goEntBeta2.ProgButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestingDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LoginButton b = LoginButton.New_Account;
		b.setListener(new Test1());
		b.fireAction();
	}

	

}
