/*
 * StonesLogic
 * ICS 3U1
 * Programmer: Mike Kim
 * Date: January 2nd, 2020.
 * Brain of the game.
 */

package Mancala;

/**
 * This class computates the moves of the players and executes an algorithm to follow the rules of the Stones Game
 * culminating assignment.  
 */
public class StonesLogic {
	int[] pitNum;
	StonesPlayer player;
	int p1Choice, p2Choice;
	int p1Score, p2Score, turn = 1, lastPitValue=0, counter = -1, eHold, bound;
	
	public StonesLogic() {
		//Initializes board with 12 pits and 2 home bins.
		pitNum = new int[14];
		player = new StonesPlayer();

	}
	
	public void distributeStones(int numStonesToPlay){
		//Distributes the stones to be played into every pit (and home bin temporarily).
		for(int i = 0; i < 14; i++) {
			pitNum[i] = numStonesToPlay;
		}
		//pitNum[6] is player one's home bin  and pitNum[13] is player two's home bin, so this sets their score to 0.
		pitNum[6] = 0;
		pitNum[13] = 0;
	}
	
	public int[] getBoard() {	
		return (pitNum);
	}

	//Player 1
	public boolean playerOneTurn(int p1Choice) {
		
		bound = p1Choice - 1;

	
			//p1Choice is offset -1 so the array elements match the pit numbers (since indices start at 0).
			
		/* Visualization of stones board initialized with 2 stones.
			Stones  | 2  2  2  2  2  2  0  2  2  2  2  2  2  0
			Element | 1  2  3  4  5  6  7  8  9 10 11 12 13 14
			Index	| 0  1  2  3  4  5  6  7  8  9 10 11 12 13
			Pit #'s | 1  2  3  4  5  6  /  1  2  3  4  5  6  /
				                       HB1					HB2
		 */
			
			
			
			//Removes stones from Player One's selected pit and distributes evenly amongst remaining pits.
			for(int j = 0; j < pitNum[p1Choice-1]; j++) {
				//Skips opponent's home bin to Player One's first pit.
				if (p1Choice-1+j == 13) {
					bound+=1;
				}
				/* bound is used to prevent index out of bound exception e.g. user distributes from pit 1 corresponding to index 13 which
				   is already the max. Adding the j counter will throw it out of bounds and send an error. Furthermore, bound can be modified
				   without changing the condition of the loop. */
				if (bound == 13) {
					bound = 0 - j;
				} else if (bound + j == 14) {
					bound = 0 - j; 
				}
				//Adds one to the pit in front.
				pitNum[bound + j] += 1;
				//Updates the latest pit value and stores in this variable (used to check if the last stone landed in an empty pit).
				lastPitValue= pitNum[bound + j];
				counter += 1;
			}
			pitNum[p1Choice-1] = 0;
			
			/* p1Choice added to secondary counter to keep track of the last element (where the stone stopped after distribution).
			   The value is stored in the variable eHold (refer to visualization table to see corresponding pits/home bins).*/
			eHold = p1Choice + counter;
			
			if (eHold > 14) {
				eHold -= 14;
			}
			
			/*Useful for troubleshooting positions. Counter is how much the stone moved and eHold is it's current pit position.
			  eHold[7] is HB1 and eHold[14] is HB2.
			System.out.println("The counter is: "+counter);
			System.out.print("The eHold is: " +eHold +"\n");
			*/ 
			
			//Checks if last stone landed in Player One's home bin for an extra turn.
			if (eHold == 7) {
				System.out.println("Your last stone landed in your home bin. Nice move, extra turn awarded!");
				counter = -1;
				/*Turn 1 = Player One | Turn 2 = Player Two
				  return(true) = Player One | return(false) = Player Two */
				return(true);
				/* Checks if the last stone landed in any of Player One's pits. If so, Player One will
				   steal the stones from Player Two's corresponding pit--the value is added to Player One's home bin. */
			} else if(lastPitValue == 1 && eHold == 1 || lastPitValue == 1 && eHold == 2 || lastPitValue == 1 && eHold == 3 
					|| lastPitValue == 1 && eHold == 4 || lastPitValue == 1 && eHold == 5 || lastPitValue == 1 && eHold == 6) {
				if (eHold == 1) {
					pitNum[6] += pitNum[12];
					pitNum[12] = 0;
				} else if (eHold == 2) {
					pitNum[6] += pitNum[11];
					pitNum[11] = 0;
				} else if (eHold == 3) {
					pitNum[6] += pitNum[10];
					pitNum[10] = 0;
				} else if (eHold == 4) {
					pitNum[6] += pitNum[9];
					pitNum[9] = 0;
				} else if (eHold == 5) {
					pitNum[6] += pitNum[8];
					pitNum[8] = 0;
				} else {
					pitNum[6] += pitNum[7];
					pitNum[7] = 0;
				}
				
				System.out.println("Your last stone landed in one of your empty pits.\n"
						+ "The corresponding opponent pit has been emptied and added to your home bin!");
				counter = -1;
				return(false);
			} else {
				//If none of the special rules apply, it becomes Player Two's Turn.
				counter = -1;
				return(false);
			}
		}
	
	public boolean playerTwoTurn(int p2Choice) {

			/* Visualization of stones board initialized with 2 stones.
			Stones  | 2  2  2  2  2  2  0  2  2  2  2  2  2  0
			Element | 1  2  3  4  5  6  7  8  9 10 11 12 13 14
			Index	| 0  1  2  3  4  5  6  7  8  9 10 11 12 13
			Pit #'s | 1  2  3  4  5  6  /  1  2  3  4  5  6  /
				                       HB1					HB2
		 */
			
			bound = p2Choice+6;
			
			//Removes stones from Player Two's selected pit and distributes evenly amongst remaining pits.
			for(int j = 0; j < pitNum[p2Choice+6]; j++) {
				//Mirrored logic from Player One but with the new offsets.
				if(bound+j == 6) {
					bound+=1;
				} 
			
				if (bound == 13) {
					bound = 0 - j;
				} else if (bound + j == 14) {
					bound = 0 - j; 
				}
				pitNum[bound + j] += 1;
				lastPitValue= pitNum[bound + j];
				counter += 1;
			}
			pitNum[p2Choice+6] = 0;
			
			eHold = p2Choice+7 + counter;
			if (eHold > 14) {
				eHold -= 14;
			}
			/*Useful print statements. Counter is how much times the stone moved, and eHold is the pit it landed in (1-14)
			 7 is home bin for Player One and 14 is home bin for Player Two.
			System.out.println("The counter is: "+counter);
			System.out.print("The pitHold is: " +eHold +"\n");*/
			
			//Mirrored logic from Player One but with the new offsets.
			if (eHold == 14) {
				System.out.println("Your last stone landed in your home bin. Nice move, extra turn awarded!");
				counter = -1;
				/*Turn 1 = Player One | Turn 2 = Player Two
				  return(true) = Player One | return(false) = Player Two */
				return(true);
			} else if((lastPitValue == 1 && eHold == 8) || (lastPitValue == 1 && eHold == 9) || (lastPitValue == 1 && eHold == 10) 
					|| (lastPitValue == 1 && eHold == 11) || (lastPitValue == 1 && eHold == 12) || (lastPitValue == 1 && eHold == 13)) {
				if (eHold == 8) {
					pitNum[13] += pitNum[5];
					pitNum[5] = 0;
				} else if (eHold == 9) {
					pitNum[13] += pitNum[4];
					pitNum[4] = 0;
				} else if (eHold == 10) {
					pitNum[13] += pitNum[11];
					pitNum[3] = 0;
				} else if (eHold == 11) {
					pitNum[13] += pitNum[2];
					pitNum[2] = 0;
				} else if (eHold == 12) {
					pitNum[13] += pitNum[1];
					pitNum[1] = 0;
				} else {
					pitNum[13] += pitNum[0];
					pitNum[0] = 0;
				}
				System.out.println("Your last stone landed in one of your empty pits.\n"
						+ "The corresponding opponent pit has been emptied and added to your home bin!");
				counter = -1;
				return(false);
			} else {
				counter = -1;
				return(false);
			}

		
		}

	public void showGameBoard() {
		//Console based board for testing.
				System.out.println ("_______________________");
				System.out.println("      " + pitNum[12]+ " " + pitNum[11] + " " + pitNum[10] + " " + pitNum[9] 
						+ " " + pitNum[8] + " " + pitNum[7] + " " + "\nP2  " + pitNum[13] + "             " + pitNum[6] + "  P1\n      " + 
						pitNum[0] + " " + pitNum[1] + " " + pitNum[2] + " " + pitNum[3] + " " + pitNum[4] + " " + pitNum[5]);
				System.out.println ("_______________________\nPits: 1 2 3 4 5 6\n_______________________\n");
	}
	
	public boolean winCondition() {
		//If either side has no stones to play, the game is over.
		if ((pitNum[0]+pitNum[1]+pitNum[2]+pitNum[3]+pitNum[4]+pitNum[5] == 0) || 
		(pitNum[7]+pitNum[8]+pitNum[9]+pitNum[10]+pitNum[11]+pitNum[12] == 0)) {
			//Determines and announces winner by comparing the home bin values.
			if(pitNum[6] > pitNum[13]) {
				System.out.println("Player One wins!");
			} else if (pitNum[6] < pitNum[13]) {
				System.out.println("Player Two wins!");
			} else {
				System.out.println("It's a draw!");
			}
		System.out.println("Game over!");
		return(true);
		} else {
			return(false);
		}

	}
}
