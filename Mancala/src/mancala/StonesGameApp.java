/*
 * StonesGameApp
 * ICS 3U1
 * Programmer: Mike Kim
 * Date: January 2nd, 2020.
 * Application file with prompts (Non GUI version). 
 */
package culminatingTask;
import java.util.Scanner;
/**
 * This class prompts the user for the amount of stones they wish to play and rotates between the player choices and turns
 * for the Stones Game. Validation ensures that the input stays within the parameters and that the user does not select
 * an empty pit.
 */
public class StonesGameApp {
	
	public static void main(String []args) {
		int numStones = 0;
		int turn = 1, p1Choice, p2Choice;
		int[] board;
		StonesPlayer player = new StonesPlayer();
		StonesLogic game = new StonesLogic();
		Scanner input = new Scanner(System.in);
	
		
		System.out.print("Enter how many stones you wish to play with (2-5): ");
		numStones = input.nextInt();
		game.distributeStones(numStones);
		
		while (numStones > 5 || numStones < 2) {
			System.out.print("Invalid amount. Enter how many stones you wish to play with (2-5): ");
			game.distributeStones(numStones);
		}
		
		game.showGameBoard();
		//Player One turn prompts and validation.
		while (turn == 1) {
			board = game.getBoard();
			System.out.print("Player One | Choose the pit for stones to be removed from: ");
			p1Choice = input.nextInt();
		
			while (p1Choice < 1 || p1Choice  > 6) {
				System.out.print("Choose a valid pit number between 1 and 6. The pit can not be empty! Enter again: ");
				p1Choice = input.nextInt();
			}
			/*The first while loop looks redundant but is necessary before the second one to check that the pit 
			  isn't empty 
			 */
			while (board[p1Choice-1] == 0 || p1Choice < 1 || p1Choice  > 6) {
					System.out.print("Choose a valid pit number between 1 and 6. The pit can not be empty! Enter again: ");
					p1Choice = input.nextInt();
			}
			//Following rules 1 and 2, the conditions will check if Player One goes again or if it switches to Player Two.
			 if (!game.playerOneTurn(p1Choice)) {
				 turn = 2;
			 }
			 
			 game.showGameBoard();
			 
			 if(game.winCondition()) {
					break;
				}
			//Player Two prompts and validation.
			while (turn == 2) {
				board = game.getBoard();
				System.out.print("Player Two | Choose the pit for stones to be removed from: ");
				p2Choice = input.nextInt();
				while (p2Choice < 1 || p2Choice  > 6) {
					System.out.print("Choose a valid pit number between 1 and 6. The pit can not be empty! Enter again: ");
					p2Choice = input.nextInt();
				}
				
				//p2Choice will be offset by a switch profile (+5/+3/+1/-1/-3/-5) to maintain pit numbers 1 through 6 for user friendliness. 
				switch(p2Choice) {
					case 1: p2Choice = 6; break;
					case 2: p2Choice = 5; break;
					case 3: p2Choice = 4; break;
					case 4: p2Choice = 3; break;
					case 5: p2Choice = 2; break;
					case 6: p2Choice = 1; break;
				}
				
				
				/* p2Choice is offset by +6 in validation to compensate for indices in the array.  Due to the nature of the code, the
				   switch profile must be applied after validation to ensure communicative prompts (avoiding do-while).*/
				while (board[p2Choice+6] == 0 || p2Choice < 1 || p2Choice  > 6) {
					System.out.print("Choose a valid pit number between 1 and 6. The pit can not be empty! Enter again: ");
					p2Choice = input.nextInt();
					
					switch(p2Choice) {
					case 1: p2Choice = 6; break;
					case 2: p2Choice = 5; break;
					case 3: p2Choice = 4; break;
					case 4: p2Choice = 3; break;
					case 5: p2Choice = 2; break;
					case 6: p2Choice = 1; break;
					}
				}
	
				 if (!game.playerTwoTurn(p2Choice)) {
					 turn = 1;
				 }
				
				game.showGameBoard();
				
				if(game.winCondition()) {
					break;
				}

			}
		}
		input.close();
	}
}