// found [stats] with regular expression and \[.*\]

import java.util.*;
import java.util.Random;

public class WorstCase {
	private static boolean menuLoop = true;
	private static int numberCounter;
	private static int decrementWater;
	private static int dyingOfThirst;
	private static int decrementHunger;
	private static int dyingOfHunger;
	private static int goingCrazy;
	private static int youDeadYet;
	private static int soColdBro;
	private static int endOfGame = 46; // this tells the max turn of the count for the game loop, the 46th turn asks the last question to get rescued
	private static int tempCount = 0;
	private static boolean cheatCodeCounter = false;
	
//	Scanner scannerIn = new Scanner(System.in);

	public static void main(String[] args) {
		Player PlayerStats = new Player();
		PlayerHypo PlayerStatsHypo = new PlayerHypo();
		SanityCheckClass SanityCheck = new SanityCheckClass();
		Tool toolUse = new Tool();
		Scanner scannerIn = new Scanner(System.in);
		// constructing objects
		// creating variables
		// main loops
		// menu loop
		do {
			menuDisplay();
//			if (PlayerStats.getQuestionNumber() > 45) {				
//				cheatCodeCounter = 46;
//			}
			if (tempCount > 0) {
				System.out.printf("\nYour adventure awaits, please try entering 'Play' or 'Stats'");
			}
			System.out.printf("\n>> ");
			char menuIn = 'n';
			try {
				menuIn = scannerIn.nextLine().toLowerCase().charAt(0);
			} catch (Exception e) {
				System.out.printf("\nSystem Error, cant press enter. Must type in a value.\n");
			}
			if ( menuIn == 'p') {
		// lore loop
			loreDisplay();
		// game loop
		while (true) {
			// health check
				youDeadYet = PlayerStats.getHealthPts();
				if (youDeadYet < 1) {
					PlayerStats.setHealthPts(0);
				} else {
					// nothing
				}
			// Visuals
			// top screen
			mapDisplay(PlayerStats);
			// middle screen
			printLocationArea(PlayerStats); // error from lower area
			// bottom screen
			numberCounter = PlayerStats.getQuestionNumber(); // getThirstPts();
			System.out.printf("\n\n");
			System.out.printf("  ++++++++++++++++++++++++++++++++++" + "\n");
			System.out.printf("  | Player Stats|                  +");
			System.out.printf("\n");
			System.out.printf("  +=============+                  +   ");
			question(PlayerStats.getQuestionNumber());
			System.out.printf("\n");
			health(PlayerStats.getHealthPts());
//			System.out.printf("  what do you do?");
			System.out.printf("\n");
			thirst(PlayerStats.getThirstPts());
			System.out.printf("\t\t"); // print "A)"
			answerA(SanityCheck.questionA(PlayerStats, numberCounter));
			System.out.printf("\n");
			hunger(PlayerStats.getHungerPts());
			System.out.printf("\t\t"); // print "B)"
			answerB(SanityCheck.questionB(PlayerStats, numberCounter));
			System.out.printf("\n");
			sanity(PlayerStats.getSanityPts());
			System.out.printf("\t\t"); // print "C)"
			answerC(SanityCheck.questionC(PlayerStats, numberCounter));
			System.out.printf("\n");
			hypothermia(PlayerStatsHypo.getHypothermiaPts());
			System.out.printf("\t\t"); // print "D)"
			answerD(SanityCheck.questionD(PlayerStats, numberCounter));
			System.out.printf("\n");
			System.out.printf("  ++++++++++++++++++++++++++++++++++\n\n");
			// incrementation
			// question number
			char userInput;
			userInput = checkAnswer(PlayerStats, PlayerStatsHypo, toolUse);
			if ((userInput == 'q') || (userInput == 'Q')) {
				PlayerStats.setQuestionNumber(0);	// added b/c if quite game to main menu, play goes back into game at same day count. Maybe good maybe bad
													// maybe want to make this a toggle so it would "save" location while playing.
				break;
			} else {
				// nothing
			}
			if (numberCounter <= endOfGame) { // will need to make an endOfGame variable
				numberCounter++; // initilize this variable
				// pull stats
				decrementWater = PlayerStats.getThirstPts();
				decrementHunger = PlayerStats.getHungerPts();
				goingCrazy = PlayerStats.getSanityPts();
//				youDeadYet = PlayerStats.getHealthPts();
				soColdBro = PlayerStatsHypo.getHypothermiaPts();
				if ((numberCounter % 3) == 0) {
					PlayerStats.setQuestionNumber(numberCounter);
					// end of day
					// water -1 pt
					if (decrementWater > 0) {
						decrementWater--;
						PlayerStats.setThirstPts(decrementWater);
					} else {
						PlayerStats.setThirstPts(0);
						dyingOfThirst = PlayerStats.getHealthPts();
						dyingOfThirst = dyingOfThirst - 10;
						PlayerStats.setHealthPts(dyingOfThirst);
					}
					// hunger -1 pt
					if (decrementHunger > 0) {
						decrementHunger--;
						PlayerStats.setHungerPts(decrementHunger);
					} else {
						PlayerStats.setHungerPts(0);
						dyingOfHunger = PlayerStats.getHealthPts();
						dyingOfHunger = dyingOfHunger - 10;
						PlayerStats.setHealthPts(dyingOfHunger);
					}
//					if (youDeadYet <= 0) {					
//						PlayerStats.setHealthPts(0);
//						System.out.println("dead bitch"); // test death
//						endOfGame = endOfGame - 100;
//					} else {
//						// PlayerStats.setHealthPts(youDeadYet);
//					}
				} else {
					PlayerStats.setQuestionNumber(numberCounter);
				}
				// end of question, check stats and prevent from becoming negative
				if (decrementWater < 0) {
					PlayerStats.setThirstPts(0);
				} else if (decrementWater > 7) {
					PlayerStats.setThirstPts(7);
				} else {
					// nothing
				}
				if (decrementHunger < 0) {
					PlayerStats.setHungerPts(0);
				} else if (decrementHunger > 25) {
					PlayerStats.setHungerPts(25);
				} else {
					// nothing
				}
				if (goingCrazy < 0) {
					PlayerStats.setSanityPts(0);
				} else if (goingCrazy > 30) {
					PlayerStats.setSanityPts(30);
				} else {
					// nothing
				}
				if (soColdBro < 0) {
					PlayerStatsHypo.setHypothermiaPts(0);
				} else if (PlayerStatsHypo.getHypoStatus() == false) {
					PlayerStatsHypo.setHypothermiaPts(0);
				} else if (soColdBro > 9) {
					PlayerStatsHypo.setHypothermiaPts(10);
					youDeadYet = youDeadYet - 5;
					PlayerStats.setHealthPts(youDeadYet);
				} else {
					// nothing
				}
				if (youDeadYet > 100) {
					PlayerStats.setHealthPts(100);
				} else if (youDeadYet < 1) {
					PlayerStats.setHealthPts(0);	// moved to the top of the game loop
//					endOfGame = endOfGame - 100;  // artifact of ending game
					break;
				} else {
					// PlayerStats.setHealthPts(youDeadYet);
				}
			} else {
				// endScene();
				break;
			}
			// user input

		} // end of game loop
		endGameDisplay(PlayerStats, PlayerStatsHypo); // , cheatCodeCounter);
		// end of play in menu loop
			} else if (menuIn == 's') {
				statsDisplay (PlayerStats, PlayerStatsHypo);
			} else if (menuIn == 'q') {
				break;
			} else {
				tempCount++;
			}
		} while (menuLoop);
		scannerIn.close();
	} // end of main

//##########################################################################################################################

//methods
// user input
	public static char getUserInput(Player Pl) {
		// variable set
		boolean loop = true;
		int questionNumber = Pl.getQuestionNumber();
		char getInput = 'n';
		Scanner sc = new Scanner(System.in);
		// input loop
		while (loop) {
			// user input
			System.out.printf("\nYour answer>> ");
			getInput = sc.next().charAt(0);
			// check for correct input
			if (questionNumber == 0) {
				if ((getInput != 'a') && (getInput != 'A') && (getInput != 'b') && (getInput != 'B') && (getInput != 'c')
						&& (getInput != 'C') && (getInput != 'd') && (getInput != 'D') && (getInput != 'e') && (getInput != 'E') && (getInput != 'f') && (getInput != 'F') && (getInput != 'q') && (getInput != 'Q')) {
					System.out.printf("\n\t\t>>NOT A VALID CHOICE<<<\n\tPlease enter either a, b, c, or d\n");
				} else {
					loop = false;
				}
			} else {				
				if ((getInput != 'a') && (getInput != 'A') && (getInput != 'b') && (getInput != 'B') && (getInput != 'c')
						&& (getInput != 'C') && (getInput != 'd') && (getInput != 'D') && (getInput != 'q') && (getInput != 'Q')) {
					System.out.printf("\n\t\t>>NOT A VALID CHOICE<<<\n\tPlease enter either a, b, c, or d\n");
				} else {
					loop = false;
				}
			}
		}
		// sc.close();
		return getInput;
	}

	public static char checkAnswer(Player PlayerStats, PlayerHypo PlayerStatsHypo, Tool ToolUse) {

		// Initialization
		char userInput;
		int questionNum;
		int tempHealth = 0;
		int tempThirst = 0;
		int tempHunger = 0;
		int tempSanity = 0;
		int tempHypothermia = 0;
		  // tools
		int phone = 1;
		int spom = 2;
		int medkit = 3;
		int fire = 4;
		int water = 5;
		int peanuts = 6;
		// set input
		userInput = getUserInput(PlayerStats);
		// import question number
		questionNum = PlayerStats.getQuestionNumber();
		// check input
		switch (questionNum) {
		case 0:
			// PlayerStats.setSanityPts(0); // sanity test
			// PlayerStats.setHungerPts(30); // hunger test
			// PlayerStats.setThirstPts(10); // thirst test
			// PlayerStatsHypo.setHypothermiaPts(20); // hypo test
				if ((userInput == 'a') || (userInput == 'A')) {			// cell phone
					ToolUse.setToolType(1);	
				} else if ((userInput == 'b') || (userInput == 'B')) {	// sharp piece-O-metal
					ToolUse.setToolType(2);
				} else if ((userInput == 'c') || (userInput == 'C')) {	// medical kit
					ToolUse.setToolType(3);
				} else if ((userInput == 'd') || (userInput == 'D')) {	// fire starter
					ToolUse.setToolType(4);
				} else if ((userInput == 'e') || (userInput == 'E')) {	// airplane water bottles
					ToolUse.setToolType(5);
				} else if ((userInput == 'f') || (userInput == 'F')) {	// airplane peanuts
					ToolUse.setToolType(6);
				} else {
					ToolUse.setToolType(0);
				}
			break;
		case 1:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != spom)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				// hunger
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 4;
				PlayerStats.setHungerPts(tempHunger);
				// sanity
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger++;
				tempHunger++;
				PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 2;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 2:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != water)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst++;
				tempThirst++;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst--;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth--;
				tempHealth--;
				PlayerStats.setHealthPts(tempHealth);
			}
			break;
		case 3:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != phone)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// sanity
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
				// health
				tempHealth = PlayerStats.getHealthPts();
				tempHealth++;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			}
			break;
		case 4:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != water)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst++;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// thirst
				tempThirst = PlayerStats.getThirstPts();
				tempThirst++;
				PlayerStats.setThirstPts(tempThirst);
				// sanity
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);

			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			}
			break;
		case 5:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != peanuts)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger++;
				tempHunger++;
				PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth--;
				tempHealth--;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 6:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != phone)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst--;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// health
				tempHealth = PlayerStats.getHealthPts();
				tempHealth++;
				tempHealth++;
				PlayerStats.setHealthPts(tempHealth);
				// sanity
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 7:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != water)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 3;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth--;
				tempHealth--;
				PlayerStats.setHealthPts(tempHealth);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst++;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst++;
				tempThirst++;
				PlayerStats.setThirstPts(tempThirst);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 4;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 8:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != medkit)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				// nothing
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
//				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 9:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != spom)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 3;
				;
				PlayerStats.setThirstPts(tempThirst);
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 4;
				;
				PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 3;
				;
				PlayerStats.setThirstPts(tempThirst);
			}
			break;
		case 10:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != peanuts)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 4;
				;
				PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 3;
				PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger++;
				PlayerStats.setHungerPts(tempHunger);
			}
			break;
		case 11:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != fire)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 2;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 3;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 12:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != medkit)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
//				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth++;
				tempHealth++;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
//				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth++;
				tempHealth++;
				PlayerStats.setHealthPts(tempHealth);
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 13:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != peanuts)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 4;
				PlayerStats.setHungerPts(tempHunger);
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 25;
				PlayerStats.setHealthPts(tempHealth);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst--;
				PlayerStats.setHealthPts(tempHealth);
				tempHunger = PlayerStats.getHungerPts();
				tempHunger--;
				tempHunger--;
				PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 3;
				PlayerStats.setThirstPts(tempThirst);
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			}
			break;
		case 14:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != fire)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia--;
				tempHypothermia--;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia--;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			}
			break;
		case 15:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != medkit)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 10;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst--;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 16:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != fire)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia--;
				tempHypothermia--;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
//				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth++;
				tempHealth++;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 4;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 17:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != spom)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger++;
				tempHunger++;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst++;
				tempThirst++;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger++;
				tempHunger++;
				PlayerStats.setHungerPts(tempHunger);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 18:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != phone)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 2;
				PlayerStats.setSanityPts(tempSanity);
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth++;
				tempHealth++;
				PlayerStats.setHealthPts(tempHealth);
			}
			break;
		case 19:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != water)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst++;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempHunger = PlayerStats.getHungerPts();
				tempHunger++;
				tempHunger++;
				PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			}
			break;
		case 20:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != water)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst++;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempThirst = PlayerStats.getThirstPts();
				tempThirst--;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth++;
				tempHealth++;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 3;
				PlayerStats.setHealthPts(tempHealth);
			}
			break;
		case 21:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != peanuts)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger++;
				tempHunger++;
				PlayerStats.setHungerPts(tempHunger);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 2;
				PlayerStats.setHealthPts(tempHealth);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 2;
				PlayerStats.setHealthPts(tempHealth);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 2;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 22:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != spom)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 4;
				PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 2;
				PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 23:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != spom)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 15;
				PlayerStats.setHealthPts(tempHealth);
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 4;
				PlayerStats.setHungerPts(tempHunger);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 12;
				PlayerStats.setHealthPts(tempHealth);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 20;
				PlayerStats.setHealthPts(tempHealth);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 20;
				PlayerStats.setHealthPts(tempHealth);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 1;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 24:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != medkit)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 10;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 4;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 5;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 4;
				PlayerStats.setHealthPts(tempHealth);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 25:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != phone)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				 tempThirst = PlayerStats.getThirstPts();
				 tempThirst++;
				 PlayerStats.setThirstPts(tempThirst);
			}
			break;
		case 26:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != medkit)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				// nothing
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 27:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != phone)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			}
			break;
		case 28:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != peanuts)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				// nothing
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				 tempHealth = PlayerStats.getHealthPts();
				 tempHealth++;
				 tempHealth++;
				 PlayerStats.setHealthPts(tempHealth);
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 29:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != spom)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				 tempHunger = PlayerStats.getHungerPts();
				 tempHunger++;
				 tempHunger++;
				 PlayerStats.setHungerPts(tempHunger);
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			}
			break;
		case 30:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != water)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				 tempThirst = PlayerStats.getThirstPts();
				 tempThirst = tempThirst + 3;
				 PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempThirst = PlayerStats.getThirstPts();
				 tempThirst = tempThirst - 1;
				 PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			}
			break;
		case 31:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != phone)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity + 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 32:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != medkit)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				// nothing
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 33:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != peanuts)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				 tempHunger = PlayerStats.getHungerPts();
				 tempHunger = tempHunger + 3;
				 PlayerStats.setHungerPts(tempHunger);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 1;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 34:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != medkit)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				// nothing
			} else if ((userInput == 'b') || (userInput == 'B')) {
				 tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				 tempHypothermia++;
				 PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 35:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != fire)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				 tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				 tempHypothermia = tempHypothermia - 10;
				 PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				 tempHunger = PlayerStats.getHungerPts();
				 tempHunger++;
				 tempHunger++;
				 PlayerStats.setHungerPts(tempHunger);
				 tempThirst = PlayerStats.getThirstPts();
				 tempThirst++;
				 PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				 tempSanity = PlayerStats.getSanityPts();
				 tempSanity++;
				 PlayerStats.setSanityPts(tempSanity);
				 tempHealth = PlayerStats.getHealthPts();
				 tempHealth++;
				 tempHealth++;
				 PlayerStats.setHealthPts(tempHealth);
			}
			break;
		case 36:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != phone)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				PlayerStats.setQuestionNumber(46);
				numberCounter = 46;
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 37:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != medkit)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 10;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 2;
				PlayerStats.setHealthPts(tempHealth); 
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				 tempThirst = PlayerStats.getThirstPts();
				 tempThirst--;
				 PlayerStats.setThirstPts(tempThirst);
			}
			break;
		case 38:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != spom)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				 tempHunger = PlayerStats.getHungerPts();
				 tempHunger++;
				 tempHunger++;
				 PlayerStats.setHungerPts(tempHunger);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 3;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 5;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 5;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			}
			break;
		case 39:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != peanuts)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 2;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				 tempSanity = PlayerStats.getSanityPts();
				 tempSanity++;
				 PlayerStats.setSanityPts(tempSanity);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 2;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 2;
				PlayerStats.setHealthPts(tempHealth);
			}
			break;
		case 40:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != water)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				 tempThirst = PlayerStats.getThirstPts();
				 tempThirst++;
				 tempThirst++;
				 PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 41:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != spom)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				// nothing
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia++;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity--;
				PlayerStats.setSanityPts(tempSanity);
			}
			break;
		case 42:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != fire)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHypothermia = PlayerStatsHypo.getHypothermiaPts();
				tempHypothermia =tempHypothermia - 10;
				PlayerStatsHypo.setHypothermiaPts(tempHypothermia);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity++;
				PlayerStats.setSanityPts(tempSanity);
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 5;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				 tempThirst = PlayerStats.getThirstPts();
				 tempThirst++;
				 PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				 tempHunger = PlayerStats.getHungerPts();
				 tempHunger++;
				 PlayerStats.setHungerPts(tempHunger);
			}
			break;
		case 43:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != water)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				// nothing
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				// nothing
			} else if ((userInput == 'd') || (userInput == 'D')) {
				// nothing
			}
			break;
		case 44:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != medkit)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 10;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth - 5;
				PlayerStats.setHealthPts(tempHealth);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 2;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 3;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 3;
				PlayerStats.setThirstPts(tempThirst);
			}
			break;
		case 45:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != fire)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 3;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				// nothing
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempSanity = PlayerStats.getSanityPts();
				tempSanity = tempSanity - 3;
				PlayerStats.setSanityPts(tempSanity);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHealth = PlayerStats.getHealthPts();
				tempHealth = tempHealth + 5;
				PlayerStats.setHealthPts(tempHealth);
			}
			break;
		case 46:
			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != spom)) {
				System.out.printf("You do not have this tool type, please choose another answer...");
				userInput = getUserInput(PlayerStats);
			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 1;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 1;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 1;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 1;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 1;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 1;
				PlayerStats.setThirstPts(tempThirst);
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 1;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 1;
				PlayerStats.setThirstPts(tempThirst);
			}
			break;
		case 47:
//			while (((userInput == 'a') || (userInput == 'A')) && (ToolUse.getToolType() != peanuts)) {
//				System.out.printf("You do not have this tool type, please choose another answer...");
//				userInput = getUserInput(PlayerStats);
//			}
			if ((userInput == 'a') || (userInput == 'A')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 1;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 1;
				PlayerStats.setThirstPts(tempThirst);
				cheatCodeCounter = true;
			} else if ((userInput == 'b') || (userInput == 'B')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 1;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 1;
				PlayerStats.setThirstPts(tempThirst);
				cheatCodeCounter = true;
			} else if ((userInput == 'c') || (userInput == 'C')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 1;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 1;
				PlayerStats.setThirstPts(tempThirst);
				cheatCodeCounter = true;
			} else if ((userInput == 'd') || (userInput == 'D')) {
				tempHunger = PlayerStats.getHungerPts();
				tempHunger = tempHunger + 1;
				PlayerStats.setHungerPts(tempHunger);
				tempThirst = PlayerStats.getThirstPts();
				tempThirst = tempThirst + 1;
				PlayerStats.setThirstPts(tempThirst);
				cheatCodeCounter = true;
			}
			break;
		}
//			    	if (this.toolType == 1) {			// cell phone
//					return 1;
//				} else if (this.toolType == 2) {	// sharp piece-O-metal
//					return 2;
//				} else if (this.toolType == 3) {	// medical kit
//					return 3;
//				} else if (this.toolType == 4) {	// fire starter
//					return 4;
//				} else if (this.toolType == 5) {	// airplane water bottles
//					return 5;
//				} else if (this.toolType == 6) {	// airplane peanuts
//					return 6;
//				}
		return userInput;
	}

// print main menu
	public static void menuDisplay() {
	System.out.printf("\n\n\n\n\n\n\n\n");
	System.out.printf("==============================================================================================================================================================================     ");   
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`   __        __             _      ____                 ____                            _                                                                                   `");
	System.out.printf("\n`   \\ \\      / /__  _ __ ___| |_   / ___|__ _ ___  ___  / ___|  ___ ___ _ __   __ _ _ __(_) ___                                                                              `");
	System.out.printf("\n`    \\ \\ /\\ / / _ \\| '__/ __| __| | |   / _` / __|/ _ \\ \\___ \\ / __/ _ \\ '_ \\ / _` | '__| |/ _ \\                                                                             `");
	System.out.printf("\n`     \\ V  V / (_) | |  \\__ \\ |_  | |__| (_| \\__ \\  __/  ___) | (_|  __/ | | | (_| | |  | | (_) |                                                                            `");
	System.out.printf("\n`      \\_/\\_/ \\___/|_|  |___/\\__|  \\____\\__,_|___/\\___| |____/ \\___\\___|_| |_|\\__,_|_|  |_|\\___/   Ocean Edition                                                             `");
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                   __      _____        _______________    __________________________________________________________________  ___                                          `");  
	System.out.printf("\n`                  (__)    (_____)      (_______________)  (__________________________________________________________________) \\\\ \\                                         `");  
	System.out.printf("\n`                                                                                                                                \\\\ `\\                                       `");  
	System.out.printf("\n`                                                                                                             ___                 \\\\  \\                                      `");  
	System.out.printf("\n`                   Settings                                Play Game                                        |    \\                \\\\  `\\                                    `");  
	System.out.printf("\n`                   --------                                ---------                                        |   _ \\                \\    \\                                   `");  
	System.out.printf("\n`         ==============================          ==============================                             | _( )_\\                \\    `\\                                 `");  
	System.out.printf("\n`        |100 | Health   | Player Stats |        |                              |                            |(_ o _)\\                \\     \\                                `");  
	System.out.printf("\n`        |  4 | Thirst    ``````````````|        |                    |o_       |                            | (_,_) _\\__---------------------------------._.                `");  
	System.out.printf("\n`        | 18 | Hunger                  |        |        ____________/_        |                          __|---~~~__o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_[][\\__             `");  
	System.out.printf("\n`        | 10 | Sanity                  |        |~~~~. (``:`--`:`--`:``)  ~~~~~|                         |___      Hawaiian Airlines  /~~~~~~~)                \\            `");  
	System.out.printf("\n`        |  0 | Hypothermia             |        |     ~~~~~~~~~~~~~~~~~~.      |                             ~~~---..._______________/      ,/_________________/            `");  
	System.out.printf("\n`        |    |                         |        |  ~  ~~~   ~.  ~    ~~~   ~.  |                                                    /      /                                `");  
	System.out.printf("\n`         ==============================          ==============================                                                    /     ,/                                 `");  
	System.out.printf("\n`                                                                                                                                  /     /                                   `");  
	System.out.printf("\n`                                                                                                                                 /    ,/                                    `");  
	System.out.printf("\n`                                                                                                                                /    /                                      `");  
	System.out.printf("\n`                                                                                                                               //  ,/                                       `");  
	System.out.printf("\n`                                                                                                                              //  /                                         `");  
	System.out.printf("\n`            _______           ___________________    ____________________________________________________________________   // ,/                                           `"); 
	System.out.printf("\n`           (_______)         (___________________)  (____________________________________________________________________) //_/                                             `"); 
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                                                                                                                                                                            `");
//	System.out.printf("\n`                                                                                                                                                                            `");
	System.out.printf("\n`                                                                                                                                                               [or 'quit']  `");
	System.out.printf("\n==============================================================================================================================================================================");
	}
	
	public static void loreDisplay() {
		Scanner sc = new Scanner(System.in);
		System.out.printf("\n\n\n\n\n\n\n\n");
		System.out.printf("==============================================================================================================================================================================\n");
		loreText();	// print 11 lines of lore text
		System.out.printf("`                   __      _____        _______________    __________________________________________________________________  ___                                          `");  
		System.out.printf("\n`                  (__)    (_____)      (_______________)  (__________________________________________________________________) \\\\ \\                                         `");  
		System.out.printf("\n`                                                                                                                                \\\\ `\\                                       `");  
		System.out.printf("\n`                                                                                                             ___                 \\\\  \\                                      `");  
		System.out.printf("\n`                                                                                                            |    \\                \\\\  `\\                                    `");  
		System.out.printf("\n`                                                                                                            |   _ \\                \\    \\                                   `");  
		System.out.printf("\n`                                                                                                            | _( )_\\                \\    `\\                                 `");  
		System.out.printf("\n`                                                                                                            |(_ o _)\\                \\     \\                                `");  
		System.out.printf("\n`                                                                                                            | (_,_) _\\__---------------------------------._.                `");  
		System.out.printf("\n`                                                                                                          __|---~~~__o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_[][\\__             `");  
		System.out.printf("\n`                                                                                                         |___      Hawaiian Airlines  /~~~~~~~)                \\            `");  
		System.out.printf("\n`                                                                                                             ~~~---..._______________/      ,/_________________/            `");  
		System.out.printf("\n`                                                                                                                                    /      /                                `");  
		System.out.printf("\n`                                                                                                                                   /     ,/                                 `");  
		System.out.printf("\n`                                                                                                                                  /     /                                   `");  
		System.out.printf("\n`                                                                                                                                 /    ,/                                    `");  
		System.out.printf("\n`                                                                                                                                /    /                                      `");  
		System.out.printf("\n`                                                                                                                               //  ,/                                       `");  
		System.out.printf("\n`                                                                                                                              //  /                                         `");  
		System.out.printf("\n`            _______           ___________________    ____________________________________________________________________   // ,/                                           `"); 
		System.out.printf("\n`           (_______)         (___________________)  (____________________________________________________________________) //_/                                             `"); 
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                              [Press 'Enter' to continue]   `");
		System.out.printf("\n==============================================================================================================================================================================");
		System.out.printf("\n");
		sc.nextLine();
	}
	
	public static void loreText(){	// print 11 lines
		System.out.printf("` _	                                                                                                                                                                    `\n");
		System.out.printf("`| |                                                                                                                                                                         `\n");
		System.out.printf("`| |     ___  _ __ ___                                                                                                                                                       `\n");
		System.out.printf("`| |    / _ \\| '__/ _ \\                                                                                                                                                      `\n");
		System.out.printf("`| |___| (_) | | |  __/                                                                                                                                                      `\n");
		System.out.printf("`|______\\___/|_|  \\___|                                                                                                                                                      `\n");
		System.out.printf("`         You have been excited to go on your trip to Hawaii since you started planning it last year. You go over the plans as you gaze out the window of the plane at the   `\n");
		System.out.printf("`    ocean. The luau's sound fun and the food is going to be amazing. As you look out over the ocean, you see nothing but deep blue colors. There is a sudden drop, like the  `\n");
		System.out.printf("`    ones that make you feel a pit in the bottom of your stomach. At first, you think nothing of it because they have been happening often recently, but then came a bang!   `\n");
		System.out.printf("`    Like something out of a horror story, the plane starts to fall. Slowly at first, but gaining speed and getting faster. Oxygen masks drop down, lights flash brightly, a `\n");
		System.out.printf("`    voice yells over the intercom and people start screaming. You quickly stand up to grab your lucky rabbits' foot but the next thing you know, every goes black...         `\n");
	}
	
// print map view
	public static void mapDisplay(Player Pl) {
		System.out.printf("\n\n\n\n\n\n\n\n");
		System.out.printf("==============================================================================================================================================================================\n");
		System.out.printf("|      ==Palmyra Atoll Islands==        `             ==The Shallows==              `             ==High Seas==  )`'-.,_)`'-.,_`               ==Plane Crash==               |\n");
		System.out.printf("|                                       `                                      ~~~  `                                          `                                             |\n");
		System.out.printf("|    ,-' '-.                  ~~        `      ~~                ~~                 `       "); pL(Pl,20); System.out.printf("         "); pL(Pl,19); System.out.printf("                        `                                             |\n");
		System.out.printf("|   /       \\                           `                                           `                        "); pL(Pl,18); System.out.printf("                 `                                             |\n");
		System.out.printf("|   |       |                           `           ~~~                           "); pL(Pl,21); System.out.printf(" `                      "); pL(Pl,12); System.out.printf("     "); pL(Pl,11); System.out.printf("             ` )`'-.,_)`'-.,_)`'-.,_                       |\n");
		System.out.printf("|    `-__.-'    __...__                 `                                           `                "); pL(Pl,13); System.out.printf("         "); pL(Pl,17); System.out.printf("      "); pL(Pl,10); System.out.printf("        `                                             |\n");
		System.out.printf("|       ~~  .-'`       ``\"\"-.__         `                        ~~~        "); pL(Pl,22); System.out.printf("       `                                          `                                             |\n");
		System.out.printf("|         .'       "); pL(Pl,45); System.out.printf("     "); pL(Pl,44); System.out.printf("     '-.      `      ~~~                                  `               "); pL(Pl,14); System.out.printf("                     "); pL(Pl,9); System.out.printf("    `                                             |\n");
		System.out.printf("|         |   "); pL(Pl,46); System.out.printf("             "); pL(Pl,43); System.out.printf("   \\     `                ~~              "); pL(Pl,23); System.out.printf("          `                          "); pL(Pl,16); System.out.printf("               `                                             |\n");
		System.out.printf("|         \\                       |     `                                           `                    "); pL(Pl,15); System.out.printf("                  "); pL(Pl,8); System.out.printf("  `                     )`'-.,_)`'-.,_)`'-.,_   |\n");
		System.out.printf("|          '-.__.--._         _"); pL(Pl,42); System.out.printf("-'      `  ~~                                       ` )`'-.,_)`'-.,_                           `                                             |\n");
		System.out.printf("|                    `--...-'`"); pL(Pl,41); System.out.printf("         `                         ~~~  "); pL(Pl,24); System.out.printf("            `                                         "); pL(Pl,7); System.out.printf("`                                             |\n");
		System.out.printf("|    ~~                    "); pL(Pl,40); System.out.printf("       ~~   `             ~~~                           `                                          `                                             |\n");
		System.out.printf("|                      "); pL(Pl,39); System.out.printf("                `                                   ~~~     `                                          `                                             |\n");
		System.out.printf("|                 "); pL(Pl,38); System.out.printf("                     `                            "); pL(Pl,25); System.out.printf("              `                                          `"); pL(Pl,6); System.out.printf("      )`'-.,_)`'-.,_)`'-.,_                 |\n");
		System.out.printf("|             "); pL(Pl,37); System.out.printf("        ,-' '-.          `   ~~~                                     `                   )`'-.,_)`'-.,_         `                                             |\n");
		System.out.printf("|                     /       \\  ~~     `                                      ~~   `                                          `                                             |\n");
		System.out.printf("| ~~        "); pL(Pl,36); System.out.printf("         |       |         `                        "); pL(Pl,26); System.out.printf("                  `                                          `   "); pL(Pl,5); System.out.printf("                                         |\n");
		System.out.printf("|     _____            `-__.-'          `               ~~                          `                                          `                            )`'-.,_)`'-.,_   |\n");
		System.out.printf("|   .'     '. "); pL(Pl,35); System.out.printf("                         `   ~~~             "); pL(Pl,27); System.out.printf("          ~~~          `                                          `       "); pL(Pl,4); System.out.printf("                                     |\n");
		System.out.printf("|  /         \\                          `                                           `    )`'-.,_)`'-.,_                        `                                             |\n");
		System.out.printf("|  |         |    "); pL(Pl,34); System.out.printf("                     `           "); pL(Pl,28); System.out.printf("         ~~                    `                                          `             "); pL(Pl,3); System.out.printf("                               |\n");
		System.out.printf("|  \\         /          "); pL(Pl,33); System.out.printf("      "); pL(Pl,32); System.out.printf("     "); pL(Pl,31); System.out.printf("  `"); pL(Pl,30); System.out.printf("   "); pL(Pl,29); System.out.printf("                           ~~~        `                                          `                 "); pL(Pl,2); System.out.printf("    "); pL(Pl,1); System.out.printf("    __|__             |\n");
		System.out.printf("|   '._____.'                           `      ~~~          ~~                      `                                   )`'-.,_`)`'-.,_                ---o-(_)-o---         |");
	}
	
	public static void pL (Player Pl, int pM) { // playerLocation(player object, int positionMarker)
		int moveCount = Pl.getQuestionNumber(); // 					^					  ^--- used to identify location method is being called from
			if ((moveCount == pM) && (moveCount != 46)) {
				System.out.printf("@");
			} else if ((moveCount == pM) && (moveCount == 46)){
				System.out.printf("[x]");
			} else if ((moveCount > 46) && (pM == 46)) {
				System.out.printf("[x]");	
			} else {
				if ((moveCount != 46) && (pM == 46)){
					System.out.printf(" X ");
				}  else {
					System.out.printf("*");					
				}
			}
	}

// print map location area
	public static void whichDay(int questionNum) {
		int day = questionNum / 3;
		System.out.printf("%d", day);
	}
	
	public static void printLocationArea(Player pl) {
		int questionNum = pl.getQuestionNumber();
		System.out.printf(
				"\n==============================================================================================================================================================================\n");
		System.out.printf("  Current Location: ");
		location(questionNum);
		System.out.printf("\t\t\t~ Day: ");
		whichDay(questionNum);
		System.out.printf(" ~");
		System.out.printf(
				"\n==============================================================================================================================================================================");
		
	}

	public static void location(int questionNumber) {
		switch (questionNumber) {
		case 0:
			System.out.printf("In The Plane"); //Flying from SMF to HNL");
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			System.out.printf("Plane Crash");
			break;
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
			System.out.printf("High Seas");
			break;
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 30:
			System.out.printf("The Shallows");
			break;
		case 31:
		case 32:
		case 33:
		case 34:
		case 35:
		case 36:
		case 37:
		case 38:
		case 39:
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
		case 45:
		case 46:
			System.out.printf("Palmyra Atoll");
			break;
		default:
			System.out.printf("YOU HAVE BEEN RESCUED!");
			break;
		}
	}

// PlayerStats stat display
	public static void health(int health) {
		System.out.printf("  + %3d | Health                   +", health); // may need System.out.printf here if have
																			// issues
		// printing stats
	}

	public static void thirst(int thirst) {
		System.out.printf("  +  %2d | Thirst                   +", thirst); // may need System.out.printf here if have
																			// issues
		// printing stats
	}

	public static void hunger(int hunger) {
		System.out.printf("  +  %2d | Hunger                   +", hunger); // may need System.out.printf here if have
																			// issues
		// printing stats
	}

	public static void sanity(int sanity) {
		System.out.printf("  +  %2d | Sanity                   +", sanity); // may need System.out.printf here if have
																			// issues
		// printing stats
	}

	public static void hypothermia(int hypothermia) {
		System.out.printf("  +  %2d | Hypothermia              +", hypothermia); // may need System.out.printf here if
																					// have
		// issues printing stats
	}

// question method
	public static void question(int questionNumber) {
		switch (questionNumber) {
		case 0:
			System.out.printf("You wake up and you learn the plane is sinking and you only have time to select one tool for your survival! Which one will you choose...");
			break;
		case 1:
			System.out.printf("There is a dead man next to you, ");
			break;
		case 2:
			System.out.printf("The sun is beating down and causing your skin to burn, ");
			break;
		case 3:
			System.out.printf("It is night time, ");
			break;
		case 4:
			System.out.printf("You wake up and have to urinate, ");
			break;
		case 5:
			System.out.printf("You get bored and try to entertain yourself, ");
			break;
		case 6:
			System.out.printf("The sun is setting, ");
			break;
		case 7:
			System.out.printf(
					"As the sun is rising, you notice a water bottle floating by and it looks to have water inside, ");
			break;
		case 8:
			System.out.printf("You spot an island made out of garbage in the distance, ");
			break;
		case 9:
			System.out.printf(
					"You see a sea turtle swimming towards you with a plastic six pack ring wrapped around its neck. There is still a water bottle attached, ");
			break;
		case 10:
			System.out.printf("You start to get hungry, ");
			break;
		case 11:
			System.out.printf(
					"A volley ball with a red hand print and grass for hair floats in the distance and it looks oddly familiar, ");
			break;
		case 12:
			System.out.printf("It starts to rain as the stars come out, ");
			break;
		case 13:
			System.out.printf(
					"You wake up to strong winds and intense rain. It looks like you are in a hurricane, ");
			break;
		case 14:
			System.out.printf("Hours pass and the sky finally clears, you are now out of the huricane. ");
			break;
		case 15:
			System.out.printf(
					"Soon after the full moon rises its light illuminates a rough wave in the distance. Before you know it, the wave is towering over you! ");
			break;
		case 16:
			System.out.printf("You have been soaked and the sun is starting to rise, ");
			break;
		case 17:
			System.out.printf("A coconut is floating by, ");
			break;
		case 18:
			System.out.printf(
					"You can see the ocean floor now! You notice a sunken shipwreck about ten feet down, ");
			break;
		case 19:
			System.out.printf(
					"The water looks very shallow where you are and there are only reefs around, ");
			break;
		case 20:
			System.out.printf("The sun is shining bright because it is mid-day, ");
			break;
		case 21:
			System.out.printf("Night is coming, ");
			break;
		case 22:
			System.out.printf(
					"You wake up to the sound of slapping on the water. It is fish trying to escape some sharks. A couple of them wind up jumping into the raft, ");
			break;
		case 23:
			System.out.printf(
					"The sharks are close to the surface. As you lean over to see some of them, you fall in and get attacked! ");
			break;
		case 24:
			System.out.printf("You managed to get back into the raft and you are bleeding, ");
			break;
		case 25:
			System.out.printf("It starts to rain again,");
			break;
		case 26:
			System.out.printf("Suddenly, you hear a pop and a following hiss. The life raft has been punctured, ");
			break;
		case 27:
			System.out.printf("The raft seems to still be taking on water,");
			break;
		case 28:
			System.out.printf("Eventually, you get the raft patched how do you celebrate,");
			break;
		case 29:
			System.out.printf("Something floating by and catches your eye. It is a lizard on a piece of wood,");
			break;
		case 30:
			System.out.printf("You seem to be thirsty,");
			break;
		case 31:
			System.out.printf("Bird calls cause you to look up and see Great Frigatebirds,");
			break;
		case 32:
			System.out.printf("You see land in the distance,");
			break;
		case 33:
			System.out.printf("You get closer to the island,");
			break;
		case 34:
			System.out.printf("You are getting closer to the island and are passing the reef when you start experiencing rough waves,");
			break;
		case 35:
			System.out.printf("You make landfall on the tiny island,");
			break;
		case 36:
			System.out.printf("You see what looks like a cell tower on the big island next to you,");
			break;
		case 37:
			System.out.printf("You eventually decided to leave the tiny island in hopes the bigger one has more to offer,");
			break;
		case 38:
			System.out.printf("A flock of birds start to dive bomb you and the raft and peck at you,");
			break;
		case 39:
			System.out.printf("Night has fallen,");
			break;
		case 40:
			System.out.printf("You are very close to the big island now and can smell the plants in the breeze,");
			break;
		case 41:
			System.out.printf("As you get close the island the waves start to get rough,");
			break;
		case 42:
			System.out.printf("You have landed on the large island's shore, what will you do before you go to sleep,");
			break;
		case 43:
			System.out.printf("Once you wake up you start to walk around and find what looks like an old overgrown dirt road,");
			break;
		case 44:
			System.out.printf("You eventually find the building with the radio antenna and go inside,");
			break;
		case 45:
			System.out.printf("It looks like no one is on the island anymore,");
			break;
		case 46:
			System.out.printf("You have turned on the radio and got it working and hear someone coming in on the same frequency,");
			break;
		case 47:
			System.out.printf("The Coast Guard get you into the helicopter and you fly off to Hawaii,");
			break;
		}
	}

// answer methods
	public static void answerA(int questionNumber) {
		System.out.printf("A) ");
		switch (questionNumber) {
		case 0:
			System.out.printf("Cell Phone\t\t\t    D) Fire Starter");
			break;
		case 1:
			System.out.printf(
					"Use a sharp peice-O-metal to cut his tough out and eat it before pushing him over (tool) ");
			break;
		case 2:
			System.out.printf("Drink a water bottle to cool off while under the life raft's tarp (tool) ");
			break;
		case 3:
			System.out.printf("Stay up looking for a signal with your cell phone (tool) ");
			break;
		case 4:
			System.out.printf("Urinate in a empty water bottle and save it for later (tool) ");
			break;
		case 5:
			System.out.printf("Eat some peanuts (tool) ");
			break;
		case 6:
			System.out.printf(
					"Stay up and take selfies because this will make a great Instagram story (tool) ");
			break;
		case 7:
			System.out.printf("Drink the water from the airplane because it is safe (tool) ");
			break;
		case 8:
			System.out
					.printf("Ignore the floating plastic and look through the medical kit to see what's inside (tool)");
			break;
		case 9:
			System.out.printf(
					"Pull off the water bottle and use the sharp piece-O-metal to kill and eat the sea turtle (tool) ");
			break;
		case 10:
			System.out.printf("Eat some peanuts from the plane (tool) ");
			break;
		case 11:
			System.out.printf(
					"Light a match because being a pyromaniac is more interesting then what is out at sea (tool) ");
			break;
		case 12:
			System.out.printf(
					"Use the med-kit to coat myself in petroleum jelly in hopes the oil will repeal the water while I sleep (tool) ");
			break;
		case 13:
			System.out.printf("Get ready by eating peanuts (tool) ");
			break;
		case 14:
			System.out
					.printf("Light a match in hopes to get some warmth after the cold rain (tool) ");
			break;
		case 15:
			System.out.printf("Ignore it and use the med kit (tool) ");
			break;
		case 16:
			System.out.printf("Light a match to warm myself back up (tool) ");
			break;
		case 17:
			System.out.printf(
					"Use the sharp piece-O-metal to open the coconut so to drink and eat whats inside (tool) ");
			break;
		case 18:
			System.out.printf("Open my phone to see if there is service (tool) ");
			break;
		case 19:
			System.out.printf("Take a sip of water (tool) ");
			break;
		case 20:
			System.out.printf("Take another sip of water (tool) ");
			break;
		case 21:
			System.out.printf("Snack on peanuts before going to bed (tool) ");
			break;
		case 22:
			System.out.printf("Use the sharp piece-O-metal to maximize the meat quantity (tool) ");
			break;
		case 23:
			System.out.printf(
					"Slash their dorsal fin off during the attacked. I've heard it is a delicacy in places and would like to try it (tool) ");
			break;
		case 24:
			System.out.printf("Use the med kit to bandage up the wounds(tool) ");
			break;
		case 25:
			System.out.printf("Take a picture, with your phone, of the gnarly wound for social media (tool) ");
			break;
		case 26:
			System.out.printf("Try to use something from the first aid kit to patch the hole (tool)");
			break;
		case 27:
			System.out.printf("Try calling someone to fix the problem (tool) ");
			break;
		case 28:
			System.out.printf("Eat one of the last peanut bags (tool)");
			break;
		case 29:
			System.out.printf("Jump in to catch it and cut it with the sharp piece-O-metal (tool) ");
			break;
		case 30:
			System.out.printf("Drink one of the last water bottles from the airplane(tool) ");
			break;
		case 31:
			System.out.printf("Take a picture with your phone to help document and report its sighting to the Fish and Wildlife Services(tool) ");
			break;
		case 32:
			System.out.printf("Rummage through the health kit because it helps contain the excitement (tool)");
			break;
		case 33:
			System.out.printf("Eat the last bag of peanuts because all this excitement is getting you hungry(tool) ");
			break;
		case 34:
			System.out.printf("Hold onto the med kit so it doesn't fall over (tool)");
			break;
		case 35:
			System.out.printf("Collect wood and make a fire to dry off (tool) ");
			break;
		case 36:
			System.out.printf("Your phone has signal! Call the coast guard! (tool)");
			break;
		case 37:
			System.out.printf("Change out fresh bandages from the med kit and apply to the shark bites(tool) ");
			break;
		case 38:
			System.out.printf("Use the sharp piece-O-metal to stab them until you get one and eat it raw (tool) ");
			break;
		case 39:
			System.out.printf("Wish you still had peanuts and go to sleep (tool) ");
			break;
		case 40:
			System.out.printf("Drink the last water bottle from the airplane (tool) ");
			break;
		case 41:
			System.out.printf("Put the sharp peice-O-metal in a safe spot so it does not puncture the raft(tool)");
			break;
		case 42:
			System.out.printf("use the fire starter to make a fire with wood found on the beach(tool) ");
			break;
		case 43:
			System.out.printf("Wish you had water left but follow the road(tool)");
			break;
		case 44:
			System.out.printf("Change out the bandages with the medical kit(tool) ");
			break;
		case 45:
			System.out.printf("Try lighting the building on fire (tool) ");
			break;
		case 46:
			System.out.printf("Use the sharp piece-O-metal to cut some wires and use Morris code(tool)");
			break;
		case 47:
			System.out.printf("\"Finally, I get to go to the luau’s and eat the amazing food\" (tool)");
			break;
		default:
			Random rand = new Random();
			int rand_int1 = rand.nextInt(40);
			if ((rand_int1 > 0) && (rand_int1 < 11)) {
				System.out.printf("::::::::::::::::::::::::::::::::::::::::::::::::::::::");				
			} else if ((rand_int1 > 10) && (rand_int1 < 21)) {
				System.out.printf(":::::::::::::::::::::::::::::::::::::::::::::::");				
			} else if ((rand_int1 > 20) && (rand_int1 < 31)) {
				System.out.printf("::::::::::::::::::::::::::::::::::::::::::::::::::::");				
			} else {
				System.out.printf(":::::::::::::::::::::::::::::::::::::::::::::::::");				
			}
		}
	}

	public static void answerB(int questionNumber) {
		System.out.printf("B) ");
		switch (questionNumber) {
		case 0:
			System.out.printf("Sharp Piece-O-Metal\t\t    E) Airplane Water Bottles");
			break;
		case 1:
			System.out.printf("Search his body and find peanuts and eat them before pushing him over ");
			break;
		case 2:
			System.out.printf("Get cover under the life rafts tarp but start sweating  ");
			break;
		case 3:
			System.out.printf("Stay up and look at starts for navigation ");
			break;
		case 4:
			System.out.printf("Use the tarp to capture the urine and drink it ");
			break;
		case 5:
			System.out.printf("Sit out in the sun because having a tan is in style ");
			break;
		case 6:
			System.out.printf("Drink some of the ocean water ");
			break;
		case 7:
			System.out.printf("Take a sip because it could be tainted ");
			break;
		case 8:
			System.out.printf("Jump in and try to swim to it ");
			break;
		case 9:
			System.out.printf(
					"Do nothing because the stupid sea turtles lay eggs which close my beautiful California beach for a week every year");
			break;
		case 10:
			System.out.printf("Jump into the ocean and catch a fish by hand ");
			break;
		case 11:
			System.out.printf("Swim out to get a closer look since it looks oddly familiar ");
			break;
		case 12:
			System.out.printf(
					"Hide under the tarp to keep out of direct contact with rain but sleep in a puddle of water ");
			break;
		case 13:
			System.out.printf("I am done! Try to drown myself ");
			break;
		case 14:
			System.out.printf("Lay out in the sun to soak up some warmth ");
			break;
		case 15:
			System.out.printf("Use my hands to start paddling away as fast as I can ");
			break;
		case 16:
			System.out.printf("Take a quick nap under the tarp ");
			break;
		case 17:
			System.out.printf(
					"Spend half the day trying to open it. Succeed in opening and eating the inside but the milk leaks out ");
			break;
		case 18:
			System.out.printf("Stay in the raft where it is safe but contemplate about what happened");
			break;
		case 19:
			System.out.printf("Swim down and try to catch some fish ");
			break;
		case 20:
			System.out.printf("Try drinking some ocean water ");
			break;
		case 21:
			System.out.printf("Go to bed ");
			break;
		case 22:
			System.out.printf("Eat them raw like sushi ");
			break;
		case 23:
			System.out.printf(
					"Punch their nose and scratch at their eyes because I heard they are sensitive areas of their face ");
			break;
		case 24:
			System.out.printf("Wash off the wounds with salt water ");
			break;
		case 25:
			System.out.printf("Start crying ");
			break;
		case 26:
			System.out.printf("Investigate and use the patch kit that came with the life raft");
			break;
		case 27:
			System.out.printf("Like I said, I am lazy... ");
			break;
		case 28:
			System.out.printf("Laugh over the near-death experience with your invisible friend ");
			break;
		case 29:
			System.out.printf("Yell at it to \"GO AWAY!\"");
			break;
		case 30:
			System.out.printf("Take a sip of the ocean water ");
			break;
		case 31:
			System.out.printf("Relax and find comfort that land must be nearby ");
			break;
		case 32:
			System.out.printf("Start yelling \"HELP!\" in hopes someone will hear you");
			break;
		case 33:
			System.out.printf("Tell your imaginary friend how excited you are ");
			break;
		case 34:
			System.out.printf("Jump into the water and push the raft towards the island ");
			break;
		case 35:
			System.out.printf("Look for food or water ");
			break;
		case 36:
			System.out.printf("Continue on your way and leave the tiny island");
			break;
		case 37:
			System.out.printf("Take a nap ");
			break;
		case 38:
			System.out.printf("Scream \"I WILL FIND YOUR EGGS AND EAT THEM!!!\" ");
			break;
		case 39:
			System.out.printf("Stay up and stare at the stars trying to find your location ");
			break;
		case 40:
			System.out.printf("Take the small paddle attached to the life raft and start paddling");
			break;
		case 41:
			System.out.printf("Hold on because it worked before");
			break;
		case 42:
			System.out.printf("Build a makeshift shelter ");
			break;
		case 43:
			System.out.printf("Follow where it leads to");
			break;
		case 44:
			System.out.printf("Walk over to a broken window with bare feet ");
			break;
		case 45:
			System.out.printf("Start working of getting the radio up and running");
			break;
		case 46:
			System.out.printf("Tell them about your imaginary friend ");
			break;
		case 47:
			System.out.printf("\"I'm so thirsty for some long island's\"");
			break;
		default:
			Random rand = new Random();
			int rand_int1 = rand.nextInt(40);
			if ((rand_int1 > 0) && (rand_int1 < 11)) {
				System.out.printf("::::::::::::::::::::::::::::::::::::::::::::::::::::::");				
			} else if ((rand_int1 > 10) && (rand_int1 < 21)) {
				System.out.printf(":::::::::::::::::::::::::::::::::::::::::::::::");				
			} else if ((rand_int1 > 20) && (rand_int1 < 31)) {
				System.out.printf("::::::::::::::::::::::::::::::::::::::::::::::::::::");				
			} else {
				System.out.printf(":::::::::::::::::::::::::::::::::::::::::::::::::");				
			}
		}
	}

	public static void answerC(int questionNumber) {
		System.out.printf("C) ");
		switch (questionNumber) {
		case 0:
			System.out.printf("Medical Kit\t\t\t    F) Airplane Peanuts");
			break;
		case 1:
			System.out.printf("Push him over because he starting to smell bad");
			break;
		case 2:
			System.out.printf("Jump in the cold water to cool off ");
			break;
		case 3:
			System.out.printf("Go to sleep under tarp ");
			break;
		case 4:
			System.out.printf("I have always wanted to urinate off a boat into the ocean ");
			break;
		case 5:
			System.out.printf("Yell \"HELP!\" in hope someone will hear you ");
			break;
		case 6:
			System.out.printf("Stay up and look at stars for navigation ");
			break;
		case 7:
			System.out.printf("CHUG IT! Even though it looks tainted ");
			break;
		case 8:
			System.out.printf("Wait to see if I get closer");
			break;
		case 9:
			System.out.printf("Pull off the water bottle and leave the sea turtle to its demise, there ugly anyways");
			break;
		case 10:
			System.out.printf("Wait for my luck to bring me food");
			break;
		case 11:
			System.out.printf("It's Wilson! Say Hi and talk with him about the plane crash ");
			break;
		case 12:
			System.out.printf("Stay up and try to keep the water out of the raft ");
			break;
		case 13:
			System.out.printf("Use the tarp to cypin rain into my mouth and hydrate ");
			break;
		case 14:
			System.out.printf("Use the opportunity to get water out of the raft ");
			break;
		case 15:
			System.out.printf("Jump out and body surf it like a pro ");
			break;
		case 16:
			System.out.printf("Tan, it always helps me when I am stressed even though I usually burn ");
			break;
		case 17:
			System.out.printf("Pick it up and see how far I can throw it ");
			break;
		case 18:
			System.out.printf("Dive down and explore it because it looks like fun ");
			break;
		case 19:
			System.out.printf("Wait for something to happen because you feel lucky");
			break;
		case 20:
			System.out.printf("Get under the tarp and take a nap ");
			break;
		case 21:
			System.out.printf("Stay up trying to navigate with the stars ");
			break;
		case 22:
			System.out.printf("Release them because it is the right thing to do ");
			break;
		case 23:
			System.out.printf("Keep as still as possible, maybe they won't see me ");
			break;
		case 24:
			System.out.printf("Use my blood to attract more sharks because they look cool ");
			break;
		case 25:
			System.out.printf("Hide under the tarp and try to keep water out of the raft");
			break;
		case 26:
			System.out.printf("Do nothing because I am to lazy ");
			break;
		case 27:
			System.out.printf("Use the last patch in the kit to patch the second hole");
			break;
		case 28:
			System.out.printf("Take a celebratory nap ");
			break;
		case 29:
			System.out.printf("Throw your keys at it");
			break;
		case 30:
			System.out.printf("Lick your dry lips and think of better days");
			break;
		case 31:
			System.out.printf("Close your mouth because they may poop");
			break;
		case 32:
			System.out.printf("Cry out in shear happiness");
			break;
		case 33:
			System.out.printf("Remember how once you learned to spell \"HELP\" on the beach with sticks");
			break;
		case 34:
			System.out.printf("Use the small paddle that comes with the life raft to paddle to shore");
			break;
		case 35:
			System.out.printf("Walk around the entire island in ten minutes");
			break;
		case 36:
			System.out.printf("Contemplate how, if you could make it to the island, then you could possibly radio for help");
			break;
		case 37:
			System.out.printf("Wash the sand off your feet with some ocean water");
			break;
		case 38:
			System.out.printf("Wave your arms in a futile attempt to get rid of them ");
			break;
		case 39:
			System.out.printf("Go to sleep ");
			break;
		case 40:
			System.out.printf("Tell you imaginary friend how much more excited you are this time");
			break;
		case 41:
			System.out.printf("Jump into the water and practice your back stroke ");
			break;
		case 42:
			System.out.printf("Look for water ");
			break;
		case 43:
			System.out.printf("Ignore it and crash through the forest");
			break;
		case 44:
			System.out.printf("Freeze in terror because you think you hear a low rumbling noise ");
			break;
		case 45:
			System.out.printf("Have a quick panic attack ");
			break;
		case 46:
			System.out.printf("Calmly tell the person that you are stranded on an island called Palmyra Atoll");
			break;
		case 47:
			System.out.printf("\"Time to get my tan on, because it is in style\"");
			break;
		default:
			Random rand = new Random();
			int rand_int1 = rand.nextInt(40);
			if ((rand_int1 > 0) && (rand_int1 < 11)) {
				System.out.printf("::::::::::::::::::::::::::::::::::::::::::::::::::::::");				
			} else if ((rand_int1 > 10) && (rand_int1 < 21)) {
				System.out.printf(":::::::::::::::::::::::::::::::::::::::::::::::");				
			} else if ((rand_int1 > 20) && (rand_int1 < 31)) {
				System.out.printf("::::::::::::::::::::::::::::::::::::::::::::::::::::");				
			} else {
				System.out.printf(":::::::::::::::::::::::::::::::::::::::::::::::::");				
			}
		}
	}

	public static void answerD(int questionNumber) {
		if (questionNumber == 0) {
			// nothing
		} else {
			System.out.printf("D) ");
		}
		switch (questionNumber) {
		case 0:
			System.out.printf("");
			break;
		case 1:
			System.out.printf("Bury the man at sea ");
			break;
		case 2:
			System.out.printf("Do nothing, tanning is in style ");
			break;
		case 3:
			System.out.printf("Go for a polar bear dive because \"It's popular bro!\" ");
			break;
		case 4:
			System.out.printf("I have always loved jumping in the ocean and relieving myself ");
			break;
		case 5:
			System.out.printf("Try to remember if you left the oven on before you left for Hawaii ");
			break;
		case 6:
			System.out.printf("Go to sleep under tarp ");
			break;
		case 7:
			System.out.printf("Who knows where that's been... Do nothing...");
			break;
		case 8:
			System.out.printf(
					"Reflect on the times I littered and see where it actually winds up. Promise myself to start recycling ");
			break;
		case 9:
			System.out.printf(
					"Help rescue the sea turtle, pull off the water bottle and tear the up the plastic rings ");
			break;
		case 10:
			System.out.printf("Take off my stylish leather wrist band and eat it ");
			break;
		case 11:
			System.out.printf("What's special about a volley ball with red paint? Do nothing...");
			break;
		case 12:
			System.out
					.printf("Cover myself with the tarp and stay up trying to keep the water out of the raft ");
			break;
		case 13:
			System.out.printf("Hide under the tarp shivering ");
			break;
		case 14:
			System.out.printf(
					"Take a victory lap by swimming around the raft because I am STILL ALIVE BITCHES! ");
			break;
		case 15:
			System.out.printf("Hold on for dear life! ");
			break;
		case 16:
			System.out.printf(
					"Take a victory lap by swimming around the raft because I am STILL ALIVE BITCHES! ");
			break;
		case 17:
			System.out.printf("Do nothing... Let it float by");
			break;
		case 18:
			System.out.printf("Take a nap instead ");
			break;
		case 19:
			System.out.printf("Dive down and collect a souvenir ");
			break;
		case 20:
			System.out.printf("Relax and try to tan ");
			break;
		case 21:
			System.out.printf("Stay up talking to yourself ");
			break;
		case 22:
			System.out.printf("Do nothing... Fish are slimy and disgusting and can get themselves out of the raft");
			break;
		case 23:
			System.out.printf("Reach into their mouth and grab their uvula to make them gage ");
			break;
		case 24:
			System.out.printf("Fall asleep and let my body naturally heal ");
			break;
		case 25:
			System.out.printf("Use the tarp to siphon water and drink it ");
			break;
		case 26:
			System.out.printf("Wait until the last minute to patch the hole because I am an adrenaline junky");
			break;
		case 27:
			System.out.printf("Jump out and swim because the raft is sinking anyways ");
			break;
		case 28:
			System.out.printf("Watch the stars and relax ");
			break;
		case 29:
			System.out.printf("Jump in and try to catch it to keep as a pet ");
			break;
		case 30:
			System.out.printf("Jump into the ocean in hopes osmosis will take water into your cells ");
			break;
		case 31:
			System.out.printf("Yell at them and say they are ugly");
			break;
		case 32:
			System.out.printf("Start paddling to try and get closer to land");
			break;
		case 33:
			System.out.printf("Start thinking of the first thing you will do when you get rescued");
			break;
		case 34:
			System.out.printf("Hold on tight and hope for the best");
			break;
		case 35:
			System.out.printf("Take a nap underneath a palm tree ");
			break;
		case 36:
			System.out.printf("Wish you had chosen to grab the cell phone instead");
			break;
		case 37:
			System.out.printf("Take a sip of the ocean water ");
			break;
		case 38:
			System.out.printf("Jump overboard and dive to get away from them ");
			break;
		case 39:
			System.out.printf("Take a quick dive in the ocean before going to sleep ");
			break;
		case 40:
			System.out.printf("Meditate while concentrating on your breathing to control the excitement");
			break;
		case 41:
			System.out.printf("Throw the paddle as far as you can ");
			break;
		case 42:
			System.out.printf("Forage for food ");
			break;
		case 43:
			System.out.printf("It's a trap, go the opposite way around the island");
			break;
		case 44:
			System.out.printf("Look for some food and water ");
			break;
		case 45:
			System.out.printf("Look for first aid supplies ");
			break;
		case 46:
			System.out.printf("Yell into the phone \"HELP!\" at the top of your lungs");
			break;
		case 47:
			System.out.printf("\"Aloha!!!\"");
			break;
		default:
			Random rand = new Random();
			int rand_int1 = rand.nextInt(40);
			if ((rand_int1 > 0) && (rand_int1 < 11)) {
				System.out.printf("::::::::::::::::::::::::::::::::::::::::::::::::::::::");				
			} else if ((rand_int1 > 10) && (rand_int1 < 21)) {
				System.out.printf(":::::::::::::::::::::::::::::::::::::::::::::::");				
			} else if ((rand_int1 > 20) && (rand_int1 < 31)) {
				System.out.printf("::::::::::::::::::::::::::::::::::::::::::::::::::::");				
			} else {
				System.out.printf(":::::::::::::::::::::::::::::::::::::::::::::::::");				
			}
		}
	}
	
	public static void statsDisplay (Player Pl, PlayerHypo PlHypo) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.printf("=====================================\n");   
			System.out.printf("|       ==Player Stats Menu==       |\n");
			System.out.printf("|                                   |\n");
			System.out.printf("|      Toggle " + PlHypo.getHypoStatusDisplay() +" hypothermia?      |\n");
			System.out.printf("|                                   |\n");
			System.out.printf("=====================================\n");
			System.out.printf(">> ");
			char hypoIn = 'n';
			try {
				hypoIn = sc.nextLine().toLowerCase().charAt(0);				
				if ((hypoIn == 'y') && (PlHypo.getHypoStatus() == true)) {
					PlHypo.setHypoStatus(false);
				} else if ((hypoIn == 'y') && (PlHypo.getHypoStatus() == false)) {
					PlHypo.setHypoStatus(true);
				} else if ((hypoIn == 'n') && (PlHypo.getHypoStatus() == true)){
					 PlHypo.setHypoStatus(true);
				} else if ((hypoIn == 'n') && (PlHypo.getHypoStatus() == false)) {
					PlHypo.setHypoStatus(false);
				} else if (hypoIn == 'q') {
					break;
				} else {
					// nothing
				}	
					System.out.printf("=====================================\n");
					System.out.printf("|                                   |\n");
					System.out.printf("|            Toggled: " + PlHypo.getHypoStatusDisplayOpp() + "           |\n");
					System.out.printf("|                                   |\n");
					System.out.printf("|     -------------------------     |\n");
					System.out.printf("|                                   |\n");
					System.out.printf("|         Enter Cheat Code          |\n");
					System.out.printf("|          Recieved After           |\n");
					System.out.printf("|           Beating Game            |\n");
					System.out.printf("|         With Hypothermia          |\n");
					System.out.printf("|            Turned On:             |\n");
					System.out.printf("|                                   |\n");
					System.out.printf("=========>> ");
					String cheatCode = sc.nextLine().toUpperCase();
					if (cheatCode.equals("I AM STILL ALIVE BITCHES!")) { //I AM STILL ALIVE BITCHES!
						System.out.printf("\n=====================================\n");
						System.out.printf("|                                   |\n");
						System.out.printf("|   Correct!                        |\n");
						System.out.printf("|      You can now change your      |\n");
						System.out.printf("|      players starting stats:      |\n");
						System.out.printf("|                                   |\n");
						System.out.printf("|   ----------------------------    |\n");
						System.out.printf("|                                   |\n");
						System.out.printf("|                                   |\n");
						System.out.printf("|   Health (max: 100)               |\n");
						System.out.printf("|                                   |\n");
						System.out.printf("=========>> ");
						int setHealth = Pl.getHealthPts();
						while(true) {
							try{
								setHealth = sc.nextInt();
								break;
							} catch (Exception ex) {
								System.out.printf("Error: You need to input a number\n");
								System.out.printf("Press [ENTER] to continue...\n");
								String temp1 = sc.nextLine();
								System.out.printf("=========>> ");
							}
						}
						Pl.setHealthPts(setHealth);
						Pl.setHealthPtsReset(setHealth);
						System.out.printf("\n=====================================\n");
						System.out.printf("|                                   |\n");
						System.out.printf("|   Thirst (max: 7)                 |\n");
						System.out.printf("|                                   |\n");
						System.out.printf("=========>> ");
						int setThirst = Pl.getThirstPts();
						while(true) {
							try{
								setThirst = sc.nextInt();								
								break;
							} catch (Exception ex) {
								System.out.printf("Error: You need to input a number\n");
								System.out.printf("Press [ENTER] to continue...\n");
								String temp1 = sc.nextLine();
								System.out.printf("=========>> ");
							}
						}
						Pl.setThirstPts(setThirst);
						Pl.setThirstPtsReset(setThirst);
						System.out.printf("\n=====================================\n");
						System.out.printf("|                                   |\n");
						System.out.printf("|   Hunger (max: 25)                |\n");
						System.out.printf("|                                   |\n");
						System.out.printf("=========>> ");
						int setHunger = Pl.getHungerPts();
						while(true) {
							try{
								setHunger = sc.nextInt();
								break;
							} catch (Exception ex) {
								System.out.printf("Error: You need to input a number\n");
								System.out.printf("Press [ENTER] to continue...\n");
								String temp1 = sc.nextLine();
								System.out.printf("=========>> ");
							}
						}
						Pl.setHungerPts(setHunger);
						Pl.setHungerPtsReset(setHunger);
						System.out.printf("\n=====================================\n");
						System.out.printf("|                                   |\n");
						System.out.printf("|   Sanity (max: 30)                |\n");
						System.out.printf("|                                   |\n");
						System.out.printf("=========>> ");
						int setSanity = Pl.getSanityPts();
						while(true) {
							try{
								setSanity = sc.nextInt();
								break;
							} catch (Exception ex) {
								System.out.printf("Error: You need to input a number\n");
								System.out.printf("Press [ENTER] to continue...\n");
								String temp1 = sc.nextLine();
								System.out.printf("=========>> ");
							}
						}
						Pl.setSanityPts(setSanity);
						Pl.setSanityPtsReset(setSanity);
						System.out.printf("\n=====================================\n");
						System.out.printf("|                                   |\n");
						System.out.printf("|   Hypothermia (max: 10)           |\n");
						System.out.printf("|                                   |\n");
						System.out.printf("=========>> ");
						int setHypothermia = PlHypo.getHypothermiaPts();
						while(true) {
							try{
								setHypothermia = sc.nextInt();
								break;
							} catch (Exception ex) {
								System.out.printf("Error: You need to input a number\n");
								System.out.printf("Press [ENTER] to continue...\n");
								String temp1 = sc.nextLine();
								System.out.printf("=========>> ");
							}
						}
						PlHypo.setHypothermiaPts(setHypothermia);
						PlHypo.setHypothermiaPtsReset(setHypothermia);
						System.out.printf("\n=====================================\n");
						System.out.printf("|                                   |\n");
						System.out.printf("|             Complete!             |\n");
						System.out.printf("|    ---------------------------    |\n");
						System.out.printf("|    Press [ENTER] For Main Menu    |\n");
						System.out.printf("|                                   |\n");
						System.out.printf("=====================================\n");
						String passiveWait1 = sc.nextLine();
						passiveWait1 = sc.nextLine();
						break;
					} else if ((cheatCode.charAt(0) == 'q') || (cheatCode.charAt(0) == 'Q')) {  
						break;
					} else {
						System.out.printf("\n=====================================");
						System.out.printf("\n| Sorry, that is the incorrect cheat|");
						System.out.printf("\n| code. Press enter to proceed to   |");
						System.out.printf("\n| the main menu...                  |\n");
						System.out.printf("=====================================\n");
						String passiveWait2 = sc.nextLine();
						break;
					}
			} catch (Exception ex) {
				System.out.printf("\nError: Please enter a valid character...");
			}
		}
	}

	public static void endGameDisplay (Player Pl, PlayerHypo hypothermia) { //, int cheatcodeCounter) {
		Scanner sc = new Scanner(System.in);
//		PlayerHypo Hypothermia = new PlayerHypo();
//		if (Pl.getQuestionNumber() < 46) {
//			hypothermia.toggleCheatCode(false);
//		} else {
//			// nothing
//		}
		// reset player stats
		Pl.playerReset();
		hypothermia.hypoReset();
		String code;
//		System.out.println(cheatCodeCounter);
		if (cheatCodeCounter == true) {
			code = "I AM STILL ALIVE BITCHES!";
		} else {
			code = ":::::::::::::::::::::::::";
		}
		Pl.setQuestionNumber(0); 	// sets the game back to the first day, check and it works
		System.out.printf("\n\n\n\n\n\n\n\n");
		System.out.printf("==============================================================================================================================================================================");   
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                                                                                                                                                                            `");
		System.out.printf("\n`                TTTTTTTTTTTTTTTTTTTTTTTHHHHHHHHH     HHHHHHHHHEEEEEEEEEEEEEEEEEEEEEE     EEEEEEEEEEEEEEEEEEEEEENNNNNNNN        NNNNNNNNDDDDDDDDDDDDD                        `");
		System.out.printf("\n`                T:::::::::::::::::::::TH:::::::H     H:::::::HE::::::::::::::::::::E     E::::::::::::::::::::EN:::::::N       N::::::ND::::::::::::DDD                     `");
		System.out.printf("\n`                T:::::::::::::::::::::TH:::::::H     H:::::::HE::::::::::::::::::::E     E::::::::::::::::::::EN::::::::N      N::::::ND:::::::::::::::DD                   `");
		System.out.printf("\n`                T:::::TT:::::::TT:::::THH::::::H     H::::::HHEE::::::EEEEEEEEE::::E     EE::::::EEEEEEEEE::::EN:::::::::N     N::::::NDDD:::::DDDDD:::::D                  `");
		System.out.printf("\n`                TTTTTT  T:::::T  TTTTTT  H:::::H     H:::::H    E:::::E       EEEEEE       E:::::E       EEEEEEN::::::::::N    N::::::N  D:::::D    D:::::D                 `");
		System.out.printf("\n`                        T:::::T          H:::::H     H:::::H    E:::::E                    E:::::E             N:::::::::::N   N::::::N  D:::::D     D:::::D                `");
		System.out.printf("\n`                        T:::::T          H::::::HHHHH::::::H    E::::::EEEEEEEEEE          E::::::EEEEEEEEEE   N:::::::N::::N  N::::::N  D:::::D     D:::::D                `");
		System.out.printf("\n`                        T:::::T          H:::::::::::::::::H    E:::::::::::::::E          E:::::::::::::::E   N::::::N N::::N N::::::N  D:::::D     D:::::D                `");
		System.out.printf("\n`                        T:::::T          H:::::::::::::::::H    E:::::::::::::::E          E:::::::::::::::E   N::::::N  N::::N:::::::N  D:::::D     D:::::D                `");
		System.out.printf("\n`                        T:::::T          H::::::HHHHH::::::H    E::::::EEEEEEEEEE          E::::::EEEEEEEEEE   N::::::N   N:::::::::::N  D:::::D     D:::::D                `");
		System.out.printf("\n`                        T:::::T          H:::::H     H:::::H    E:::::E                    E:::::E             N::::::N    N::::::::::N  D:::::D     D:::::D                `");
		System.out.printf("\n`                        T:::::T          H:::::H     H:::::H    E:::::E       EEEEEE       E:::::E       EEEEEEN::::::N     N:::::::::N  D:::::D    D:::::D                 `");
		System.out.printf("\n`                      TT:::::::TT      HH::::::H     H::::::HHEE::::::EEEEEEEE:::::E     EE::::::EEEEEEEE:::::EN::::::N      N::::::::NDDD:::::DDDDD:::::D                  `");
		System.out.printf("\n`                      T:::::::::T      H:::::::H     H:::::::HE::::::::::::::::::::E     E::::::::::::::::::::EN::::::N       N:::::::ND:::::::::::::::DD                   `");
		System.out.printf("\n`                      T:::::::::T      H:::::::H     H:::::::HE::::::::::::::::::::E     E::::::::::::::::::::EN::::::N        N::::::ND::::::::::::DDD                     `");
		System.out.printf("\n`                      TTTTTTTTTTT      HHHHHHHHH     HHHHHHHHHEEEEEEEEEEEEEEEEEEEEEE     EEEEEEEEEEEEEEEEEEEEEENNNNNNNN         NNNNNNNDDDDDDDDDDDDD                        `");
		System.out.printf("\n`                                                                                                                                                                            `");  
		System.out.printf("\n`                                                                                                                                                                            `");  
		System.out.printf("\n`                                                                 ===========================================                                                                `");  
		System.out.printf("\n`                                                                    Cheat Code: " + code + "                                                                   `");  
		System.out.printf("\n`                                                                 ===========================================                                                                `");  
		System.out.printf("\n`                                                                                                                                                                            `");  
		System.out.printf("\n`                                                                                                                                                                            `");  
		System.out.printf("\n`                                                                                                                                                                            `");  
		System.out.printf("\n`                                                                                                                                                                            `");  
		System.out.printf("\n`                                                                                                                                                                            `");  
		System.out.printf("\n`                                                                                                                                              [Press 'Enter' to continue]   `");  
		System.out.printf("\n==============================================================================================================================================================================\n");
		sc.nextLine();
	}
}
//line count