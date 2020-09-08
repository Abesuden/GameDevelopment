 /*   ----  TO DO --- 
  death scene for player and opponent, update moves, music...
      * timed moves (computer clock before and after)
      * timed scenery?
          - sun on left middle then right depending on time of day
      * 0070 move easter egg
      * kaio ken (increase attack, loss one hit point per turn)
      * maybe history text printout?
      * move list -or- you are told moves during keyboard tutorial
      * leg swep called johny
      * background clouds move every time theres an attack
      * Game Choices
        -  rock climbing game (corret work and climb, wrong = fall)
        -  qwerty and devorak keyboard totorials
      * music
        -  have animal sounds in background
        -  8-bit soundtrack
        -  aminous background sound swells
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>   // used with sleep()

// funct decl
  char menu             (char* menuQuit);
  // keyboarding tutorial funcitons
  // arena functions
    void screenSky        (void);
    void healthBar        (char* recognize, char* opponentMove, int* healthCounterLeft, int* healthCounterRight, int* playerPowerMoveCounter, int* opponentPowerMoveCounter);
    char fightScene       (char* recognize, char* opponentMove);
    char input            (char* recognize);
    char moveTopLeft      (char* opponentMove);
    char moveMiddleLeft   (char* opponentMove);
    char moveBottomLeft   (char* opponentMove);
    char moveTopRight     (char* recognize);
    char moveMiddleRight  (char* recognize);
    char moveBottomRight  (char* recognize);
    void healthBarRight   (char* recognize, char* opponentMove, int** healthCounterRight, int** healthCounterLeft, int** opponentPowerMoveCounter);
    void healthBarLeft    (char* recognize, char* opponentMove, int** healthCounterRight, int** healthCounterLeft, int** playerPowerMoveCounter);
    void opponentMoveFunction (char* opponentMove);
    int  blockCheckRight  (char* recognize);
    int  blockCheckLeft   (char* opponentMove);
    char fightStats       (char* recognize, char* opponentMove, int* hitpointCounterLeft, int* hitpointCounterRight, int* hpDisplayLeft, int* hpDisplayRight);
    int timeReturn        (void);
  // rock climbing functions
// Gloabal Variable Scope
  // arena variables
    // attack move list
       char move0001[15] = "kick";
       char move0002[15] = "brandon";                  // aka yoga.kick
       char move0003[25] = "yoga.kick";                // aka brandon
       char move0004[15] = "low";
       char move0005[50] = "resting.whitney.pose";     // heal 7 points (alias to yoga.heal)
       char move0006[15] = "block";
       char move0007[15] = "hide";
       char move0008[15] = "palm";
       char move0009[15] = "crane";                    // will  build up power, released with crane.kick
       char move0010[15] = "high";
       char move0011[15] = "jump";
       char move0012[20] = "maverick";                 // -2 health (alias to eviscerate) 
       char move0013[15] = "rest";
       //char move0014[15] = "yoga";
       char move0015[40] = "roundhouse.kick";          // alias to chuck.norris
       char move0016[25] = "upside.down";
       char move0017[25] = "crane.kick";               // ~power move~ evolution of crane
       char move0018[25] = "yoga.heal";                // heal 7 points (alias to resting.whitney.pose)
       //char move0019[25] = "newMove1";
       //char move0020[25] = "newMove2";
       char move0021[25] = "eviscerate";               // -2 health (alias to maverick) 
       char move0022[25] = "head.butt";                // alias to christian
       char move0023[25] = "christian";                // alias to head.butt
       char move0024[25] = "chuck.norris";             // alias to roundhouse.kick
       char move9999[25] = "dead";
       char move9998[25] = "thanatos";
     // initilization opponentMoveFunction
       int moveRollOne, moveRollTwoHeal, moveRollTwoAttack, startMove = 1;
// main
 int main(void) {
  // initilization
    // menu variables
      char quit = 'c';
      char menuQuit[15];
    // enter variables
      char enter = ' ';
    // keyboard strings
      char recognize[50] = "ready"; // was = "";
    // attack string
      char opponentMove[50] = "ready"; // was = "null";
    // health bar counters 
      int healthCounterRight    = 9;
      int healthCounterLeft     = 9;
      int hitpointCounterRight  = 9; //healthCounterRight;
      int hitpointCounterLeft   = 9; //healthCounterLeft;
      int hpDisplayRight        = (hitpointCounterRight - healthCounterRight);
      int hpDisplayLeft         = (hitpointCounterLeft - healthCounterLeft);
    // power move counters
      int playerPowerMoveCounter = 2;
      int opponentPowerMoveCounter = 2;
    // random attack sequences
      srand(time(NULL));
      //int opponentRandomMove = rand() % 2 + 1;
  //menu loop
      /*    while ((quit != 'q') && (quit != 'Q') && (quit != 'p') && (quit != 'P')) {
            // func call menu
             menu (&quit);
              if ((quit == 'q') || (quit == 'Q')) {
                quit = 't';
                //system("exit");
                system("clear");
                printf("\n\n\tPress the [x] on the top right to exit the game\n\n");
                scanf(" %c", enter);
              }
          }
      */    
    do {
      // func call menu
        menu (menuQuit);
      // menu choices
        if ((strcmp(menuQuit, "keyboard") == 0) || (strcmp(menuQuit, "Keyboard") == 0) || (strcmp(menuQuit, "KEYBOARD") == 0)) {
          printf("Broken at switch case keyboard, exiting program...");
          break;
        }
        else if ((strcmp(menuQuit, "arena") == 0) || (strcmp(menuQuit, "Arena") == 0) || (strcmp(menuQuit, "ARENA") == 0)) {
            // screen
             while ((strcmp(recognize, "exit") != 0) && (strcmp(recognize, "EXIT") != 0) && (strcmp(recognize, "Exit") != 0)){
              screenSky ();
              opponentMoveFunction (opponentMove);   // fight moves work
              healthBar (recognize, opponentMove, &healthCounterLeft, &healthCounterRight, &playerPowerMoveCounter, &opponentPowerMoveCounter);
              //opponentMoveFunction (opponentMove);   // death scene works
              fightScene (recognize, opponentMove);
              fightStats (recognize, opponentMove, &healthCounterLeft, &healthCounterRight, &hpDisplayLeft, &hpDisplayRight);
              input (recognize);
             }
            // variable reset
              // resets user input
                strcpy(recognize, "ready"); // used to be "null"
              // resets heath bar counters "aka. health" 
                healthCounterRight = 9;
                healthCounterLeft = 9;
              // resets start stance for opponent
                startMove = 1;
              // resets power move counters (turn off for easter egg >> "crane"x4 >> "exit" >> "arena" >> "crane.kick")
                playerPowerMoveCounter = 2;
                //opponentPowerMoveCounter = 2; // hehe shhhh
              // reset hitpoint counters
                hpDisplayRight = healthCounterRight;
                hpDisplayLeft = healthCounterLeft;
        }
        else if ((strcmp(menuQuit, "rock.climbing") == 0) || (strcmp(menuQuit, "Rock.climbing") == 0) || (strcmp(menuQuit, "Rock.Climbing") == 0) || (strcmp(menuQuit,  "ROCK.CLIMBING") ==   0)) {
          printf("Broken at switch case ROCK.CLIMBING, exiting program...");
          break;
        }
    } while ((strcmp(menuQuit, "quit") != 0) && (strcmp(menuQuit, "exit") != 0) && (strcmp(menuQuit, "Quit") != 0) && (strcmp(menuQuit, "Exit") != 0));
  // end of main
    return 0;
 }
// function list
 // menu function
  char menu (char* menuQuit) {
    system (   "clear"                                                          );
    printf (" ----------------------------  KILLER KEYBOARDING  -----------------------------\n"     );
    printf ("||                                                                             ||\n"    );
    printf ("||                                                                             ||\n"    );
    printf ("||                                        ,                                    ||\n"    );
    printf ("||     ----___                `    `             ,                 ___----     ||\n"    );
    printf ("||  +_____|__             TYPE`WHICH GAME MODE,TO PLAY...           __|_____+  ||\n"    );
    printf ("||      \\  |_\\                      `  \\ /, ,                       /_|  /     ||\n" );
    printf ("||       \\___/=   --   -   -  --   -  `-x-    --  --   -   --     -=\\___/      ||\n"  );
    printf ("||      ~~~~~~                      . ,/ \\` `                      ~~~~~~      ||\n"   );
    printf ("||                                 ,      `  `                                 ||\n"    );
    printf ("||                                                                             ||\n"    );
    printf ("||                                                                             ||\n"    );
    printf ("||                                                                             ||\n"    );
    printf ("||                                                                             ||\n"    );
    printf ("||                                                                             ||\n"    );
    printf ("||            KEYBOARD                ARENA              ROCK.CLIMBING         ||\n"    );
    printf ("||            --------                -----              -------------         ||\n"    );
    printf ("||        ===============        ===============        ===============        ||\n"    );
    printf ("||       | [Q][W][E]     |      |               |      | _  -  __/     |       ||\n"    );
    printf ("||       |    [R][T][Y]  |      |    o_,  _o>   |      |   ___/ \\o     |       ||\n"    );
    printf ("||       | [D][V][O]     |      |   <|     |    |      |- /   __/>     |       ||\n"    );
    printf ("||       |    [R][A][K]  |      |   / }   / }   |      | /     <\"      |       ||\n"    );
    printf ("||        ===============        ===============        ===============        ||\n"    );
    printf ("||                                                                   [or exit] ||\n"    );
    printf ("||_____________________________________________________________________________||\n"    );
    printf ("\n"                                                             );
    printf ("\t\t--> "                                                       );
    scanf  (" %s", menuQuit                                                  );
    system ("clear"                                                          );
  }
     // =============== 
     //| _  -  __/     |
     //|   ___/ \o     |
     //|- /   __/>     |
     //| /     <"      |
     // =============== 
     // ===============   
     //|     \\         | 
     //|    o/\\_  -  _ | 
     //|   <\\__,\\      |
     //|    \">   |  -  | 
     // ===============   





 // screenGround () function
  void screenSky (void) {
    // initilization
      int returnedTime = 0;
    // printout
      system("clear"                                                                               );
      returnedTime = timeReturn();
      if ((returnedTime >= 7) && (returnedTime <= 20)) {
       // day scene
        printf("                              ___          ________                            \n"  );
        printf("         _____               /   \\        (________)                          \n"  );
        printf("       .'     '.____________/     \\/\\                                        \n"  );
        printf("      /        (____________)        \\____                                    \n"  );
        printf("      |         |       /\\/               \\  __                              \n"  );
        printf("      \\         /      /      _____________\\/__\\____________                 \n" );
        printf("       '._____.'      /      (______________________________)                  \n"  );
        printf("                     /                            \\                           \n"  );
        printf("              ______/______________                \\                          \n"  );
        printf("             (_____________________)                \\                         \n"  );
        printf("                 /                                   \\    ___________         \n"  );
        printf("              /\\/                                     \\  (___________)       \n"  );
        printf("             /                                         \\  /  \\               \n"  );
        printf("      ********                                          \\/  **********        \n"  );
        printf("  *****************                                      ****************      \n"  );
        printf("********************                                    ******************     \n"  );
        printf(" ********************                                    *****************     \n"  );
      } else {
       // night scene
        printf("                   +          ___          ________                  +       \n"    );
        printf("                             /   \\        (________)                         \n"   );
        printf("                ____________/     \\/\\                               _,.__    \n"  );
        printf("               (____________)        \\____                        ,'  ,-``,  \n"   );
        printf("         +              /\\/               \\  __          +       /   /       \n"  );
        printf("                       /      _____________\\/__\\____________     |   |       \n"  );
        printf("            +         /      (______________________________)    \\    `-__,/\n"    );
        printf("                     /                            \\               `._   _,-` \n"   );
        printf("    +         ______/______________                \\                 ```     \n"   );
        printf("             (_____________________)                \\ +             +        \n"   );
        printf("                 /                                   \\    ___________        \n"   );
        printf("              /\\/                                     \\  (___________)       \n"  );
        printf("             /                                         \\  /  \\               \n"  );
        printf("      ********                                          \\/  **********       \n"   );
        printf("  *****************                                      ****************    \n"    );
        printf("********************                                    ******************   \n"    );
        printf(" ********************                                    *****************   \n"    );
      }
  }
 // healthBar () function
  void healthBar (char* recognize, char* opponentMove, int* healthCounterLeft, int* healthCounterRight, int* playerPowerMoveCounter, int* opponentPowerMoveCounter) {
    // initilization
      int returnedTime = 0;
    // printout
      returnedTime = timeReturn();
      if ((returnedTime >= 7) && (returnedTime <= 20)) {
       // day scene
        printf("    \\\\   //  ********	       "); healthBarLeft(recognize, opponentMove, &healthCounterRight, &healthCounterLeft, &playerPowerMoveCounter);  printf("  "); healthBarRight(recognize, opponentMove, &healthCounterRight, &healthCounterLeft, &opponentPowerMoveCounter); printf("              \\\\\\ *******    \n"  );
        printf("     \\\\\\//  *******                                           \\\\\\  //    \n"  );
        printf("       \\\\\\////                                                 \\\\\\//  \\ \n"  );
      } else {
       // night scene 
        printf("    \\\\ + //  ********	       "); healthBarLeft(recognize, opponentMove, &healthCounterRight, &healthCounterLeft, &playerPowerMoveCounter);  printf("  "); healthBarRight(recognize, opponentMove, &healthCounterRight, &healthCounterLeft, &opponentPowerMoveCounter); printf("              \\\\\\ *******    \n"  );
        printf("     \\\\\\//  *******                                           \\\\\\  //  + \n"  );
        printf("+      \\\\\\////                                                 \\\\\\//  \\ \n"  );
      }
  }
 // fightScene () function
  char fightScene (char* recognize, char* opponentMove) {
  //char move(char* recognize);
    printf("   /\\/  |||//                  "); moveTopLeft(opponentMove);     moveTopRight(recognize);    printf("                ||||    \\     \n"    );
    printf("  /     |||||	               "   ); moveMiddleLeft(opponentMove);  moveMiddleRight(recognize); printf("                ||||     \\/\\   \n"  );
    printf(" /     //||||\\                 "); moveBottomLeft(opponentMove);  moveBottomRight(recognize); printf("               //|||\\       \\\n"    );
    printf("``````````````````````````````````````````````````````````````````````````````"     );
 // fight stats printout function
  }
  char fightStats       (char* recognize, char* opponentMove, int* healthCounterLeft, int* healthCounterRight, int* hpDisplayLeft, int* hpDisplayRight){
    // initilization place holders
      int hpR = 0,hpL = 0;
    // math to find health change for hp display
      hpR = (*healthCounterRight - *hpDisplayRight);
      hpL = (*healthCounterLeft - *hpDisplayLeft);
      *hpDisplayRight = *healthCounterRight;
      *hpDisplayLeft = *healthCounterLeft;
    // test death on hit point place holder variables
    // print out
      printf("\n%13s\t\t\t\t\t\t%11s", opponentMove, recognize); //ready on lines: 124 & 77 for >>>player>>>, 291 & 79 for <<<opponent<<<
      // hit points
        /*if (*healthCounterRight <= 0) {
          hpR = 0;
        } else if (*healthCounterLeft <= 0) {
          hpL = 0;
        }
       // new test*/
        if ((hpR >= 10) && (hpL >= 10)) {
          printf("\n       hp: %+2d\t\t\t\t\t\t      hp: %+2d", 1, 1);
        } else if ((*healthCounterRight <= 0) && (*healthCounterLeft > 0)) {
        printf("\n       hp: %+2d\t\t\t\t\t\t      hp: KO", hpL);
        } else if ((*healthCounterLeft <= 0) && (*healthCounterRight > 0)) {
        printf("\n       hp: KO\t\t\t\t\t\t      hp: %+2d", hpR);
        } else if ((*healthCounterRight <= 0) && (*healthCounterLeft <= 0)) {
          printf("\n       hp: KO\t\t\t\t\t\t      hp: KO");
        } else {
        printf("\n       hp: %+2d\t\t\t\t\t\t      hp: %+2d", hpL, hpR);
        }
   //return;
  }
 // input (recognize) function
  char input (char* recognize) {
   // add another pointer for delta time before and after scanf
   // it can be used to determine if it was typed fast enough
   printf("\n\n\t\t\t     --> ");
   scanf(" %s", recognize);
   printf("\n");
  }
 // Character movement Functions
  // LEFT CHARACTER <<<<<<<<<<<<--Opponent--<<<<<<<<<<<<<<<
   // move generator 
    void opponentMoveFunction (char* opponentMove) { //(char* opponentMove) 
    // counter for one roll per while loop
      int counterOMF = 1;
    //char opponentMove[50] = "nullValue";
     //if (counterOMF == 1) {
      moveRollOne = rand() % 4 + 1;           // rolls for a 1:4
      moveRollTwoHeal = rand() % 4 + 1;       // rolls for a 1:4
      moveRollTwoAttack = rand() % 16 + 1;    // rolls for a 1:16
      counterOMF++;
     //}
   // analysis of rolls
      if (startMove == 1) {
        moveRollOne = 3;          // attack 
        moveRollTwoHeal = 17;     // cant generate
        moveRollTwoAttack = 16;   // rare
        startMove = 0;
      }
      if (startMove == 2) {   // <<<opponent<<< is dead
        moveRollOne = 3;          // attack 
        moveRollTwoHeal = 20;     // cant generate 
        moveRollTwoAttack = 16;   // rare
        startMove = 0;
      }
      if (moveRollOne == 2) {               // it was a 1/4 roll 
        if (moveRollTwoHeal == 3) {           // it was a 1/4 roll
          strcpy(opponentMove, "yoga.heal");              // 6 block (place holder)
          //printf("test - 2, h5pts:\n%d\n%d\n%d\n%s", moveRollOne, moveRollTwoHeal, moveRollTwoAttack, opponentMove);
          // heal 5 points
        } else {                              // it was a 3/4 roll
          strcpy(opponentMove, "hide");              // 7 hide (place holder)
          //printf("test - 3, h2pts:\n%d\n%d\n%d\n%s", moveRollOne, moveRollTwoHeal, moveRollTwoAttack, opponentMove);
          // heal 2 points 
        }
      } else {                              // it was a 3/4 roll
        if (moveRollTwoAttack == 16) {        // it was a 1/16 roll
          if (moveRollTwoHeal == 17) {            // made to prevent the first opponent move-
            strcpy(opponentMove, "ready");// "null");         // -being an attack move.
          } else if (moveRollTwoHeal == 20) {            // made to prevent the first opponent move-
            strcpy(opponentMove, "dead");         // -being an attack move.
          } else { 
          strcpy(opponentMove, "upside.down");              // 17 crane.kick (place holder)
          //printf("test - 4, a5pts:\n%d\n%d\n%d\n%s", moveRollOne, moveRollTwoHeal, moveRollTwoAttack, opponentMove);
          // 5 point attack 
          }
        } else if ((moveRollTwoAttack > 8) && (moveRollTwoAttack != 16)) {    // 7/16 roll 
          strcpy(opponentMove, "jump");              // 3 yoga.kick
          //printf("test - 5, a2pts:\n%d\n%d\n%d\n%s", moveRollOne, moveRollTwoHeal, moveRollTwoAttack, opponentMove);
          // 2 point attack 
        } else if (moveRollTwoAttack <= 8) {  // it was a 8/16 roll 
          strcpy(opponentMove, "low");              // 9 crane.kick
          //printf("test - 6, a1pt:\n%d\n%d\n%d\n%s", moveRollOne, moveRollTwoHeal, moveRollTwoAttack, opponentMove);
          // 1 point attack 
          // add random move generator for attacks
        } else {                                // error return
          printf("\n\n\terror with attack move rolls.\nopponentMove: %s\n\n", opponentMove);
        }
      }
    }

   // top of character
    char moveTopLeft(char* opponentMove) {
      if (strcmp(opponentMove, move0001) == 0) {             // kick
        printf("    <o_ "        );
      } else if (strcmp(opponentMove, move0002) == 0) {      // brandon
        printf("     \\__"        );
      } else if (strcmp(opponentMove, move0003) == 0) {      // yoga.kick
        printf("     \\__"        );
      } else if (strcmp(opponentMove, move0004) == 0) {      // low kick
        printf("        "        );
      } else if (strcmp(opponentMove, move0006) == 0) {      // block
        printf("     ox "        );
      } else if (strcmp(opponentMove, move0007) == 0) {      // hide
        printf("     ,  "        );
      } else if (strcmp(opponentMove, move0008) == 0) {      // palm
        printf("     o  "        );
      } else if (strcmp(opponentMove, move0009) == 0) {      // crane ~> build power and use crane.kick to release
        printf("    <o_ "        );
      } else if (strcmp(opponentMove, move0017) == 0) {      // crane.kick
        printf("    <o_ "        );
      } else if (strcmp(opponentMove, move0010) == 0) {      // high
        printf("     \\__"        );
      } else if (strcmp(opponentMove, move0005) == 0) {      // resting.whitney.pose
        printf("        "        );
      } else if (strcmp(opponentMove, move0011) == 0) {      // jump
        printf("    _o_."        );
      } else if (strcmp(opponentMove, move0012) == 0) {      // maverick
        printf("      o "        );
      } else if (strcmp(opponentMove, move0015) == 0) {      // roundhouse.kick
        printf("     o_ "        );
      } else if (strcmp(opponentMove, move0016) == 0) {      // upside.down
        printf("        "        );
      } else if (strcmp(opponentMove, move0018) == 0) {      // yoga.heal
        printf("        "        );
      } else if (strcmp(opponentMove, move0021) == 0) {      // eviscerate
        printf("      o "        );
      } else if (strcmp(opponentMove, move0022) == 0) {      // head.butt
        printf("        "        );
      } else if (strcmp(opponentMove, move0023) == 0) {      // christian
        printf("        "        );
      } else if (strcmp(opponentMove, move0024) == 0) {      // chuck.norris 
        printf("     o_ "        );
      } else if (strcmp(opponentMove, move9999) == 0) {      // dead
          /*  
            printf(                "   0_   "        );
            sleep(1                                  );
            printf("\b\b\b\b\b\b\b\b   \\__` "       );
            sleep(1                                  );
          */  
        printf("        "        );
      } else if (strcmp(opponentMove, move0012) == 0) {      // newMove
        printf("        "        );
      } else if (strcmp(opponentMove, move0013) == 0) {      // newMove
        printf("        "        );
      }
      else printf("     o_,"   );                     // base stance
      //return;
    }
   // middle of character 
    char moveMiddleLeft(char* opponentMove) {
      if (strcmp(opponentMove, move0001) == 0) {             // kick
        printf("     \\__"        );
      } else if (strcmp(opponentMove, move0002) == 0) {      // brandon
        printf("     (  "        );
      } else if (strcmp(opponentMove, move0003) == 0) {      // yoga.kick
        printf("     (  "        );
      } else if (strcmp(opponentMove, move0004) == 0) {      // low kick
        printf("    o,_/"       );
      } else if (strcmp(opponentMove, move0006) == 0) {      // block
        printf("    /   "        );
      } else if (strcmp(opponentMove, move0007) == 0) {      // hide
        printf("     |  "        );
      } else if (strcmp(opponentMove, move0008) == 0) {      // palm
        printf("    <|-'"        );
      } else if (strcmp(opponentMove, move0009) == 0) {      // crane ~> build power and use crane.kick to release
        printf("     |_ "        );
      } else if (strcmp(opponentMove, move0017) == 0) {      // crane.kick
        printf("     \\__"        );
      } else if (strcmp(opponentMove, move0010) == 0) {      // high
        printf("   _ /  "        );
      } else if (strcmp(opponentMove, move0005) == 0) {      // resting.whitney.pose
        printf("    _O_ "        );
      } else if (strcmp(opponentMove, move0011) == 0) {      // jump
        printf("     \\__"        );
      } else if (strcmp(opponentMove, move0012) == 0) {      // maverick
        printf("     (\\ "        );
      } else if (strcmp(opponentMove, move0015) == 0) {      // roundhouse.kick
        printf("    -|__"        );
      } else if (strcmp(opponentMove, move0016) == 0) {      // upside.down
        printf("      </"        );
      } else if (strcmp(opponentMove, move0018) == 0) {      // yoga.heal
        printf("    _O_ "        );
      } else if (strcmp(opponentMove, move0021) == 0) {      // eviscerate
        printf("     (\\ "        );
      } else if (strcmp(opponentMove, move0022) == 0) {      // head.butt
        printf("    </'O"        );
      } else if (strcmp(opponentMove, move0023) == 0) {      // christian
        printf("    </'O"        );
      } else if (strcmp(opponentMove, move0024) == 0) {      // chuck.norris 
        printf("    -|__"        );
      } else if (strcmp(opponentMove, move9999) == 0) {      // dead
          /*  
            printf(                "  (__ ` "        );
            sleep(1                                  );
            printf("\b\b\b\b\b\b\b\b   /_  `"        );
            sleep(1                                  );
          */  
        printf("    .-. "        );
      } else if (strcmp(opponentMove, move0012) == 0) {      // newMove
        printf("        "        );
      } else if (strcmp(opponentMove, move0013) == 0) {      // newMove
        printf("        "        );
      }
      else printf("    <|  "   );                     // base stance
      //return;
    }
   // bottom of character 
    char moveBottomLeft(char* opponentMove) {
      if (strcmp(opponentMove, move0001) == 0) {             // kick
        printf("     <  "        );
      } else if (strcmp(opponentMove, move0002) == 0) {      // brandon
        printf("    /o\\ "       );
      } else if (strcmp(opponentMove, move0003) == 0) {      // yoga.kick
        printf("    /o\\ "        );
      } else if (strcmp(opponentMove, move0004) == 0) {      // low kick
        printf("    |  V"        );
      } else if (strcmp(opponentMove, move0006) == 0) {      // block
        printf("   / >  "        );
      } else if (strcmp(opponentMove, move0007) == 0) {      // hide
        printf("     |  "        );
      } else if (strcmp(opponentMove, move0008) == 0) {      // palm
        printf("    / > "        );
      } else if (strcmp(opponentMove, move0009) == 0) {      // crane ~> build power and use crane.kick to release
        printf("     ) '"        );
      } else if (strcmp(opponentMove, move0017) == 0) {      // crane.kick
        printf("     <  "        );
      } else if (strcmp(opponentMove, move0010) == 0) {      // high
        printf("    o|  "        );
      } else if (strcmp(opponentMove, move0005) == 0) {      // resting.whitney.pose
        printf("   '<X>'"        );
      } else if (strcmp(opponentMove, move0011) == 0) {      // jump
        printf("     <  "        );
      } else if (strcmp(opponentMove, move0012) == 0) {      // maverick
        printf("     U))"        );
      } else if (strcmp(opponentMove, move0015) == 0) {      // roundhouse.kick
        printf("     <  "        );
      } else if (strcmp(opponentMove, move0016) == 0) {      // upside.down
        printf("    o|/ "        );
      } else if (strcmp(opponentMove, move0018) == 0) {      // yoga.heal
        printf("   '<X>'"        );
      } else if (strcmp(opponentMove, move0021) == 0) {      // eviscerate
        printf("     U))"        );
      } else if (strcmp(opponentMove, move0022) == 0) {      // head.butt
        printf("    / } "        );
      } else if (strcmp(opponentMove, move0023) == 0) {      // christian
        printf("    / } "        );
      } else if (strcmp(opponentMove, move0024) == 0) {      // chuck.norris 
        printf("     <  "        );
      } else if (strcmp(opponentMove, move9999) == 0) {      // dead
          /*  
            printf(                "     ` `"        );
            sleep(1                                  );
            printf("\b\b\b\b\b\b\b\b  0  `  "        );
            sleep(1                                  );
          */  
        printf("   | x |"        );
      } else if (strcmp(opponentMove, move0012) == 0) {      // newMove
        printf("        "        );
      } else if (strcmp(opponentMove, move0013) == 0) {      // newMove
        printf("        "        );
      }
      else printf("    / } "  );                      // base stance
      //return;
    }
  // RIGHT CHARACTER >>>>>>>>>>--Player-->>>>>>>>>>>>>>>>>
   //void healthBarLeft(char* recognize, int** healthCounterLeft, int** opponentPowerMoveCounter) {      //<<<<= = = = = = = = = = = = = = = = =
     // initilization
      // attack move list
        /*    // apparently pointers are not needed and made these global variables
        char move0001[15] = "kick";
        char move0002[15] = "brandon";                  // aka yoga.kick
        char move0003[25] = "yoga.kick";                // aka brandon
        char move0004[15] = "low";
        char move0005[50] = "resting.whitney.yoga";     // heal 7 points
        char move0006[15] = "block";
        char move0007[15] = "hide";
        char move0008[15] = "palm";
        char move0009[15] = "crane";                    // will  build up power, released with crane.kick
        char move0010[15] = "high";
        char move0011[15] = "jump";
        char move0012[20] = "maverick";
        char move0013[15] = "rest";
        char move0014[15] = "yoga";
        char move0015[40] = "round.house.kick";
        char move0016[25] = "upside.down";
        char move0017[25] = "crane.kick";               // ~power move~ evolution of crane
        */    // apparently pointers are not needed and made these global variables
   // top of character
    char moveTopRight(char* recognize) {
      if (strcmp(recognize, move0001) == 0) {             // kick
        printf(" _o>    "        );
      } else if (strcmp(recognize, move0002) == 0) {      // brandon
        printf("__ /`   "        );
      } else if (strcmp(recognize, move0003) == 0) {      // yoga.kick
        printf("__ /`   "        );
      } else if (strcmp(recognize, move0004) == 0) {      // low kick
        printf("        "        );
      } else if (strcmp(recognize, move0006) == 0) {      // block
        printf(" xo     "        );
      } else if (strcmp(recognize, move0007) == 0) {      // hide
        printf("  ,     "        );
      } else if (strcmp(recognize, move0008) == 0) {      // palm
        printf("  o     "        );
      } else if (strcmp(recognize, move0009) == 0) {      // crane ~> build power and use crane.kick to release
        printf(" _o>    "        );
      } else if (strcmp(recognize, move0017) == 0) {      // crane.kick
        printf(" _o>    "        );
      } else if (strcmp(recognize, move0010) == 0) {      // high
        printf("__/     "        );
      } else if (strcmp(recognize, move0005) == 0) {      // resting.whitney.pose
        printf("        "        );
      } else if (strcmp(recognize, move0011) == 0) {      // jump
        printf("._o_    "        );
      } else if (strcmp(recognize, move0012) == 0) {      // maverick
        printf(" o      "        );
      } else if (strcmp(recognize, move0015) == 0) {      // roundhouse.kick
        printf(" _o     "        );
      } else if (strcmp(recognize, move0016) == 0) {      // upside.down
        printf("        "        );
      } else if (strcmp(recognize, move0018) == 0) {      // yoga.heal
        printf("        "        );
      } else if (strcmp(recognize, move0021) == 0) {      // eviscerate
        printf(" o      "        );
      } else if (strcmp(recognize, move0022) == 0) {      // head.butt
        printf("        "        );
      } else if (strcmp(recognize, move0023) == 0) {      // christian
        printf("        "        );
      } else if (strcmp(recognize, move0024) == 0) {      // chuck.norris 
        printf(" _o     "        );
      } else if (strcmp(recognize, move0013) == 0) {      // newMove
        printf("        "        );
      }
      else printf(" _o>    "     );                        // base stance
      //return;
    }
   // middle of character 
    char moveMiddleRight(char* recognize) {
      if (strcmp(recognize, move0001) == 0) {             // kick
        printf("__/     "        );
      } else if (strcmp(recognize, move0002) == 0) {      // brandon
        printf("  ) ` ` "        );
      } else if (strcmp(recognize, move0003) == 0) {      // yoga.kick
        printf("  ) ` ` "        );
      } else if (strcmp(recognize, move0004) == 0) {      // low kick
        printf("\\_,o    "       );
      } else if (strcmp(recognize, move0006) == 0) {      // block
        printf("   \\    "        );
      } else if (strcmp(recognize, move0007) == 0) {      // hide
        printf("  |     "        );
      } else if (strcmp(recognize, move0008) == 0) {      // palm
        printf("'-|>    "        );
      } else if (strcmp(recognize, move0009) == 0) {      // crane ~> build power and use crane.kick to release
        printf(" _|     "        );
      } else if (strcmp(recognize, move0017) == 0) {      // crane.kick
        printf("__/     "        );
      } else if (strcmp(recognize, move0010) == 0) {      // high
        printf("  \\ _   "        );
      } else if (strcmp(recognize, move0005) == 0) {      // resting.whitney.pose
        printf(" _O_    "        );
      } else if (strcmp(recognize, move0011) == 0) {      // jump
        printf("__/     "        );
      } else if (strcmp(recognize, move0012) == 0) {      // maverick
        printf(" /)     "        );
      } else if (strcmp(recognize, move0015) == 0) {      // roundhouse.kick
        printf("__|-    "        );
      } else if (strcmp(recognize, move0016) == 0) {      // upside.down
        printf("\\>      "        );
      } else if (strcmp(recognize, move0018) == 0) {      // yoga.heal
        printf(" _O_    "        );
      } else if (strcmp(recognize, move0021) == 0) {      // eviscerate
        printf(" /)     "        );
      } else if (strcmp(recognize, move0022) == 0) {      // head.butt
        printf("O'\\>    "        );
      } else if (strcmp(recognize, move0023) == 0) {      // christian
        printf("O'\\>    "        );
      } else if (strcmp(recognize, move0024) == 0) {      // chuck.norris 
        printf("__|-    "        );
      } else if (strcmp(recognize, move0013) == 0) {      // newMove
        printf("        "        );
      }
      else printf("  |     "     );                       // base stance
      //return;
    }
   // bottom of character 
    char moveBottomRight(char* recognize) {
      if (strcmp(recognize, move0001) == 0) {             // kick
        printf("  >     "        );
      } else if (strcmp(recognize, move0002) == 0) {      // brandon
        printf(" /o\\  ` "       );
      } else if (strcmp(recognize, move0003) == 0) {      // yoga.kick
        printf(" /o\\  ` "        );
      } else if (strcmp(recognize, move0004) == 0) {      // low kick
        printf("V  |    "        );
      } else if (strcmp(recognize, move0006) == 0) {      // block
        printf("  < \\   "        );
      } else if (strcmp(recognize, move0007) == 0) {      // hide
        printf("  |     "        );
      } else if (strcmp(recognize, move0008) == 0) {      // palm
        printf(" < \\    "        );
      } else if (strcmp(recognize, move0009) == 0) {      // crane ~> build power and use crane.kick to release
        printf("' (     "        );
      } else if (strcmp(recognize, move0017) == 0) {      // crane.kick
        printf("  >     "        );
      } else if (strcmp(recognize, move0010) == 0) {      // high
        printf("  |o    "        );
      } else if (strcmp(recognize, move0005) == 0) {      // resting.whitney.pose
        printf("`<X>'   "        );
      } else if (strcmp(recognize, move0011) == 0) {      // jump
        printf("  >     "        );
      } else if (strcmp(recognize, move0012) == 0) {      // maverick
        printf("((U     "        );
      } else if (strcmp(recognize, move0015) == 0) {      // roundhouse.kick
        printf("  >     "        );
      } else if (strcmp(recognize, move0016) == 0) {      // upside.down
        printf(" \\|o    "        );
      } else if (strcmp(recognize, move0018) == 0) {      // yoga.heal
        printf("`<X>'   "        );
      } else if (strcmp(recognize, move0021) == 0) {      // eviscerate
        printf("((U     "        );
      } else if (strcmp(recognize, move0022) == 0) {      // head.butt
        printf(" { \\  "        );
      } else if (strcmp(recognize, move0023) == 0) {      // christian
        printf(" { \\  "        );
      } else if (strcmp(recognize, move0024) == 0) {      // chuck.norris 
        printf("  >     "        );
      } else if (strcmp(recognize, move0013) == 0) {      // newMove
        printf("        "        );
      }
      else printf(" / }    "     );                     // base stance
      //return;
    }

 // Health Bar Functions 
  // Right Character >>>>>>>>>>>>--Player-->>>>>>>>>>>>>>> 
    void healthBarRight(char* recognize, char* opponentMove, int** healthCounterRight, int** healthCounterLeft, int** opponentPowerMoveCounter) {
     // initilization (local)
      // attack move list
       /*    // apparently pointers are not needed and made these global variables
       char move0001[15] = "kick";
       char move0002[15] = "brandon";                  // aka yoga.kick
       char move0003[25] = "yoga.kick";                // aka brandon
       char move0004[15] = "low";
       char move0005[50] = "resting.whitney.yoga";     // heal 7 points
       char move0006[15] = "block";
       char move0007[15] = "hide";
       char move0008[15] = "palm";
       char move0009[15] = "crane";                    // will eventually heal 2 points
       char move0010[15] = "high";
       char move0011[15] = "jump";
       char move0012[20] = "maverick";
       char move0013[15] = "rest";
       char move0014[15] = "yoga";
       char move0015[40] = "round.house.kick";
       char move0016[25] = "upside.down";
       char move0017[25] = "crane.kick";               // ~power move~ evolution of crane
       */    // apparently pointers are not needed and made these global variables
      // char health loss variables
       int hitPointOne   = 1;
       int hitPointTwo   = 2;
       int hitPointThree = 3;
       int hitPointFour  = 4;
       int hitPointFive  = 5;
       int hitPointSix   = 6;
       int hitPointSeven = 7;
       int hitPointEight = 8;
       int hitPointNine  = 9;
       int hitPointTen   = 10;
       int blockCheck = 0;
     // attack strengths vs health loss
      blockCheck = blockCheckRight(recognize);
      if (blockCheck > 0) {
        blockCheck = 0;
      } else {
        if (strcmp(opponentMove,           move0001) == 0) {           // kick
          (**healthCounterRight)--;
        } else if (strcmp(opponentMove,    move0002) == 0) {          // brandon/yoga.kick
          **healthCounterRight = **healthCounterRight - hitPointTwo;
        } else if (strcmp(opponentMove,    move0003) == 0) {          // yoga.kick/brandon 
            **healthCounterRight = **healthCounterRight - hitPointTwo;
        } else if (strcmp(opponentMove,    move0004) == 0) {          // low kick
            **healthCounterRight = **healthCounterRight - hitPointOne;
        } else if (strcmp(opponentMove,    move0005) == 0) {          // resting.whitney.pose 
          **healthCounterLeft = **healthCounterLeft + hitPointFive;
        } else if (strcmp(opponentMove,    move0006) == 0) {          // block 
          (**healthCounterRight)++;
        } else if (strcmp(opponentMove,    move0007) == 0) {          // hide 
          (**healthCounterRight)++;
        } else if (strcmp(opponentMove,    move0008) == 0) {          // palm 
            **healthCounterRight = **healthCounterRight - hitPointOne;
        } else if (strcmp(opponentMove,    move0009) == 0) {          // crane ~> build power and use crane.kick to release
            (**healthCounterRight)++;
            (**opponentPowerMoveCounter)++;                            //       ~> this is where power builds up
            (**opponentPowerMoveCounter)++;
        } else if (strcmp(opponentMove,    move0017) == 0) {          // crane.kick 
            **healthCounterRight = **healthCounterRight - **opponentPowerMoveCounter;
            **opponentPowerMoveCounter = 0;
        } else if (strcmp(opponentMove,    move0010) == 0) {          // high 
            **healthCounterRight = **healthCounterRight - hitPointOne;
        } else if (strcmp(opponentMove,    move0011) == 0) {          // jump 
            **healthCounterRight = **healthCounterRight - hitPointOne;
        } else if (strcmp(opponentMove,    move0012) == 0) {          // maverick 
          **healthCounterRight = **healthCounterRight - blockCheck;
        } else if (strcmp(opponentMove,    move0015) == 0) {          // roundhouse.kick 
          **healthCounterRight = **healthCounterRight - hitPointFour;
        } else if (strcmp(opponentMove,    move0016) == 0) {          // upside.down 
          **healthCounterRight = **healthCounterRight - hitPointTwo;
        } else if (strcmp(opponentMove,    move0018) == 0) {          // yoga.heal 
          **healthCounterLeft = **healthCounterLeft + hitPointFive;
        } else if (strcmp(opponentMove,    move0021) == 0) {          // eviscerate 
          **healthCounterRight = **healthCounterRight - blockCheck;
        } else if (strcmp(opponentMove,    move0022) == 0) {          // head.butt  
          **healthCounterRight = **healthCounterRight - hitPointTwo;
        } else if (strcmp(opponentMove,    move0023) == 0) {          // christian 
          **healthCounterRight = **healthCounterRight - hitPointTwo;
        } else if (strcmp(opponentMove,    move0024) == 0) {          // chuck.norris 
          **healthCounterRight = **healthCounterRight - hitPointFour;
        } else if (strcmp(opponentMove,    move0013) == 0) {          // newMove 
          **healthCounterRight = **healthCounterRight - hitPointOne;
        }
        else (**healthCounterRight)++;                     // base stance
      }
    // Health status                               [=====]
      if        (**healthCounterRight > 10 ) {
        **healthCounterRight = 10;
        printf(  "[=====]"  );
      } else if (**healthCounterRight == 10) {
        printf(  "[=====]"  );
      } else if (**healthCounterRight == 9) {
        printf(  "[====-]"  );
      } else if (**healthCounterRight == 8) {
        printf(  "[==== ]"  );
      } else if (**healthCounterRight == 7) {
        printf(  "[===- ]"  );
      } else if (**healthCounterRight == 6) {
        printf(  "[===  ]"  );
      } else if (**healthCounterRight == 5) {
        printf(  "[==-  ]"  );
      } else if (**healthCounterRight == 4) {
        printf(  "[==   ]"  );
      } else if (**healthCounterRight == 3) {
        printf(  "[=-   ]"  );
      } else if (**healthCounterRight == 2) {
        printf(  "[=    ]"  );
      } else if (**healthCounterRight == 1) {
        printf(  "[-    ]"  );
      } else if (**healthCounterRight <= 0) {
        printf(  "[DEAD!]"  );
        **healthCounterRight = **healthCounterRight - hitPointTen;
      } else {
        printf(  "[=====]"  );
      }
      
      //return;
    }
  // Left Character <<<<<<<<<<<<<--Opponent--<<<<<<<<<<<<<< 
    void healthBarLeft(char* recognize, char* opponentMove, int** healthCounterRight, int** healthCounterLeft, int** playerPowerMoveCounter) {
     // initilization (local)
      // attack move list
       /*    // apparently pointers are not needed and made these global variables
       char move0001[15] = "kick";
       char move0002[15] = "brandon";                  // aka yoga.kick
       char move0003[25] = "yoga.kick";                // aka brandon
       char move0004[15] = "low";
       char move0005[50] = "resting.whitney.yoga";     // heal 7 points
       char move0006[15] = "block";
       char move0007[15] = "hide";
       char move0008[15] = "palm";
       char move0009[15] = "crane";                    // will eventually heal 2 points
       char move0010[15] = "high";
       char move0011[15] = "jump";
       char move0012[20] = "maverick";
       char move0013[15] = "rest";
       char move0014[15] = "yoga";
       char move0015[40] = "round.house.kick";
       char move0016[25] = "upside.down";
       char move0017[25] = "crane.kick";               // ~power move~ evolution of crane
       */    // apparently pointers are not needed and made these global variables 
      // power move counters                           // power move counters   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
       //printf("\nplayerPowerMoveCounter1 = %d", **playerPowerMoveCounter);
       //if (**playerPowerMoveCounter += 1) {
        //int craneKickCounter = 1;
        //(**playerPowerMoveCounter)++;
        //printf("\nplayerPowerMoveCounter = %d\n", **playerPowerMoveCounter);
       //}    // it seems to be a scope issue, it will not work unless it is
            //initilized each time the function loops and it wont inside an if or while loop
      // char health loss variables
       int hitPointOne   = 1;
       int hitPointTwo   = 2;
       int hitPointThree = 3;
       int hitPointFour  = 4;
       int hitPointFive  = 5;
       int hitPointSix   = 6;
       int hitPointSeven = 7;
       int hitPointEight = 8;
       int hitPointNine  = 9;
       int hitPointTen   = 10;
       int blockCheck = 0;
     // attack strengths vs health loss
      blockCheck = blockCheckLeft(opponentMove);
      if (blockCheck > 0) {
        blockCheck = 0;
      } else {
        if (strcmp(recognize,           move0001) == 0) {           // kick
          (**healthCounterLeft)--;
        } else if (strcmp(recognize,    move0002) == 0) {          // brandon/yoga.kick
          **healthCounterLeft = **healthCounterLeft - hitPointTwo;
        } else if (strcmp(recognize,    move0003) == 0) {          // yoga.kick/brandon 
          **healthCounterLeft = **healthCounterLeft - hitPointTwo;
        } else if (strcmp(recognize,    move0004) == 0) {          // low kick
          **healthCounterLeft = **healthCounterLeft - hitPointOne;
        } else if (strcmp(recognize,    move0005) == 0) {          // resting.whitney.pose 
          **healthCounterRight = **healthCounterRight + hitPointFive;
        } else if (strcmp(recognize,    move0006) == 0) {          // block 
          (**healthCounterLeft)++;
        } else if (strcmp(recognize,    move0007) == 0) {          // hide 
          (**healthCounterLeft)++;
        } else if (strcmp(recognize,    move0008) == 0) {          // palm 
          **healthCounterLeft = **healthCounterLeft - hitPointOne;
        } else if (strcmp(recognize,    move0009) == 0) {          // crane ~> build power and use crane.kick to release
          (**healthCounterLeft)++;
          (**playerPowerMoveCounter)++;                            //       ~> this is where power builds up
          (**playerPowerMoveCounter)++;
        } else if (strcmp(recognize,    move0017) == 0) {          // crane.kick 
          **healthCounterLeft = **healthCounterLeft - **playerPowerMoveCounter;
          **playerPowerMoveCounter = 0;
        } else if (strcmp(recognize,    move0010) == 0) {          // high 
          **healthCounterLeft = **healthCounterLeft - hitPointOne;
        } else if (strcmp(recognize,    move0011) == 0) {          // jump 
            **healthCounterLeft = **healthCounterLeft - hitPointOne;
        } else if (strcmp(recognize,    move0012) == 0) {          // maverick 
          **healthCounterLeft = **healthCounterLeft - blockCheck;
        } else if (strcmp(recognize,    move0015) == 0) {          // roundhouse.kick 
          **healthCounterLeft = **healthCounterLeft - hitPointFour;
        } else if (strcmp(recognize,    move0016) == 0) {          // upside.down 
          **healthCounterLeft = **healthCounterLeft - hitPointTwo;
        } else if (strcmp(recognize,    move0018) == 0) {          // yoga.heal 
          **healthCounterRight = **healthCounterRight + hitPointFive;
        } else if (strcmp(recognize,    move0021) == 0) {          // eviscerate 
          **healthCounterLeft = **healthCounterLeft - blockCheck;
        } else if (strcmp(recognize,    move0022) == 0) {          // head.butt  
          **healthCounterLeft = **healthCounterLeft - hitPointTwo;
        } else if (strcmp(recognize,    move0023) == 0) {          // christian 
          **healthCounterLeft = **healthCounterLeft - hitPointTwo;
        } else if (strcmp(recognize,    move0024) == 0) {          // chuck.norris 
          **healthCounterLeft = **healthCounterLeft - hitPointFour;
        } else if (strcmp(recognize,    move0013) == 0) {          // newMove 
          **healthCounterLeft = **healthCounterLeft - hitPointOne;
        }
        else (**healthCounterLeft)++;                     // base stance
      }
    // Health status                               [=====]
      if        (**healthCounterLeft > 10 ) {
        **healthCounterLeft = 10;
        printf(  "[=====]"  );
      } else if (**healthCounterLeft == 10) {
        printf(  "[=====]"  );
      } else if (**healthCounterLeft == 9) {
        printf(  "[====-]"  );
      } else if (**healthCounterLeft == 8) {
        printf(  "[==== ]"  );
      } else if (**healthCounterLeft == 7) {
        printf(  "[===- ]"  );
      } else if (**healthCounterLeft == 6) {
        printf(  "[===  ]"  );
      } else if (**healthCounterLeft == 5) {
        printf(  "[==-  ]"  );
      } else if (**healthCounterLeft == 4) {
        printf(  "[==   ]"  );
      } else if (**healthCounterLeft == 3) {
        printf(  "[=-   ]"  );
      } else if (**healthCounterLeft == 2) {
        printf(  "[=    ]"  );
      } else if (**healthCounterLeft == 1) {
        printf(  "[-    ]"  );
      } else if (**healthCounterLeft <= 0) {
        printf(  "[DEAD!]"  );
        **healthCounterLeft = **healthCounterLeft - hitPointTen;
        startMove = 2;          // causes death scene for <<<opponent<<<
      } else {
        printf(  "[=====]"  );
      }
      
      //return;
    }
int blockCheckRight (char* recognize) {
  int check = 0;
  if        (strcmp(recognize, move0006) == 0) {        // block  
    check++;
  } else if (strcmp(recognize, move0007) == 0) {        // hide 
    check++;
  } else {
    check = 0;
  }
return check;
}

int blockCheckLeft (char* opponentMove) {
  int check = 0;
  if        (strcmp(opponentMove, move0006) == 0) {        // block  
    check++;
  } else if (strcmp(opponentMove, move0007) == 0) {        // hide 
    check++;
  } else {
    check = 0;
  }
return check;
}

int timeReturn (void) {
  // time 
  long int timesh;
  timesh = time(NULL);
  timesh = (((timesh/3600) - 4) % 24);
  return timesh;
}