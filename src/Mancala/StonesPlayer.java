/*
 * StonesPlayer
 * ICS 3U1
 * Programmer: Mike Kim
 * Date: January 13, 2020.
 * Relays information between StonesGameApp and StonesLogic.
 */
package Mancala;

public class StonesPlayer {
	private int pOnePit;
	private int pTwoPit;
	
	/**
	 *Constructor
	 *Pre: none
	 *Post: Initializes variables for the session stones and player choices.
	 */
	public StonesPlayer() {
		pOnePit = 0;
		pTwoPit = 0;	
	}

	/**
	 * Gets Player One's pit choice.
	 * Pre: none
	 * Post: Returns Player One's choice for the selected pit.
	 */
	public int getPOneChoice() {
		return(pOnePit);
	}
	/**
	 *Sets Player One's pit choice.
	 *Pre: none
	 *Post: Updates the pit Player One selected.
	 */
	public void setPOneChoice(int p1Choice) {
		p1Choice = pOnePit;
	}
	
	/**
	 * Gets Player Two's pit choice.
	 * Pre: none
	 * Post: Returns Player Two's choice for the selected pit.
	 */
	public int getPTwoChoice() {
		return(pTwoPit);
	}
	/**
	 *Sets Player Two's pit choice.
	 *Pre: none
	 *Post: Updates the pit Player Two selected.
	 */
	public void setPTwoChoice(int p2Choice) {
		p2Choice = pTwoPit;
	}
}
