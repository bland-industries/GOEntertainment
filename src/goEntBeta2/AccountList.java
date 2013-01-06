/*****************************************************************


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

/*TODO
 * Finish java style guide
 * work on serialized save and load
 * 
 */

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractListModel;

import utilities.Rental;

import java.awt.FileDialog;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class AccountList extends AbstractListModel implements Serializable{


	ArrayList<Account> accounts;

	/*****************************************************************
	 * the constructor for an account list. 
	 * loads serilizable
	 *****************************************************************/
	public AccountList () {

		accounts = new ArrayList<Account>();
		load();
	}

	/*****************************************************************
	 * adds an account to the account list
	 * @param account the account to be added
	 *****************************************************************/
	public void addAccount (Account account) {
		accounts.add(account);
		
	}
	
	/*****************************************************************
	 * returns the account list
	 *****************************************************************/
	public ArrayList<Account> getList () {
		return accounts;
	}
	
	/*****************************************************************
	 * Searches the arraylist for the username if it is in the list.
	 *****************************************************************/
	public Account getAccount (String username) {
		
		//TODO searches the arraylist for the username if it is no in the list
		//return null;
		
		for(Account acc: accounts) {
			if(acc.geteMail().equals(username)){
				System.out.println (acc.geteMail());
				return acc;
				
			}
		}
		
		return null;
	}
	

	
	/*****************************************************************
	 * Sorts the account list.
	 *****************************************************************/
	public void sortList () {
		
		Collections.sort(accounts);
	}

	/*****************************************************************
	this should be saving it as serializable
	 *****************************************************************/
	public void save () {
		try {
			FileOutputStream fos = new FileOutputStream("CerealAccounts");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(accounts);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/*****************************************************************
	this should be loading it as serializble
	 *****************************************************************/
	@SuppressWarnings("unchecked")
	public void load () {
		try {
			FileInputStream fis = new FileInputStream("CerealAccounts");
			ObjectInputStream is = new ObjectInputStream(fis);

			accounts =  (ArrayList<Account>) is.readObject();
			fireIntervalAdded(this, 0, accounts.size() - 1);

			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/*****************************************************************
	 * returns the size of the account list
	 *****************************************************************/
	@Override
	public int getSize() {
		return accounts.size();
	}

	/*****************************************************************
	 * returns the account given an index in a list
	 * @param index the position of the account in the list
	 *****************************************************************/
	@Override
	public Object getElementAt(int index) {
		return accounts.get(index);
	}
	
}
