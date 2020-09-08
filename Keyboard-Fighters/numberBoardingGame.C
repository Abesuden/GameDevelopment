#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// function list
  void callRandom (int* randoOne, int* randoTwo);


int main(void) {
// intitilization
  // color reset
    #define RESET "\x1B[0m"

 // arrays
  int numBoard[4][3] = {
    { 7, 8, 9 },
    { 4, 5, 6 },
    { 1, 2, 3 },
    { 0, 0, 0 }
  };
  char display[4][3] = {
    {'-', '-', '-'},
    {'-', '-', '-'},
    {'-', '-', '-'},
    {'-', '-', '-'}
  };
  int randoArg[2];
 // inputs
  srand(time(NULL));
  int compNumber, userNumber;
  int randoOne;
  int randoTwo;
  int userIn;
  //rand() % (MAX +1"inclusive" - min);

// splash screen
  system("clear"); // clears the screen on linux systems
  char enter = 0;
  printf("  Welcome to the Number Boarding Game");
  printf("\n");
  printf("---------------------------------------");
  printf("\n");
  printf("Rules:");
  printf("\n");
  printf(">> Only answer with a number");
  printf("\n");
  printf(">> You must be equiped with a num pad keyboard");
  printf("\n");
  printf(">> Everything else goes");
  printf("\n");
  printf(">> If all else fails, don't forget \"\x1B[36mctrl + c"RESET);
  printf("\"");
  printf("\n");
  printf("\n");
  printf("Enjoy!");
  printf("\n");
  printf("\n");
  printf("\n");
  // waiting for player
    printf("Press enter to continue...");
    while (enter != '\r' && enter != '\n') {
      enter = getchar();
    }
    //while (getchar() != '\n');
    system("clear"); // clears the screen on linux systems

// main game loop
  do {
  // display
    callRandom(&randoOne, &randoTwo);
    printf("╔═════════╗\n");
    for (int i = 0; i < 3; i++) {
      printf("║");
      for (int j = 0; j < 3; j++) {
        if ((randoOne == i) && (randoTwo == j)) {
          display[i][j] = '+';
        }
        printf("%2c ", display[i][j]);
          display[i][j] = '-';
      }
    printf("║\n");
    }
    printf("╠═════════╝\n");
    //printf("\n %d %d", randoOne, randoTwo); // for debugging

int uI = 0;
  // scan user input 
    do {
      printf("╠> ");
      scanf(" %d", &userIn);
      if(userIn == numBoard[randoOne][randoTwo]) {
        break;
      } else {
        printf("║ hint: %d\n", numBoard[randoOne][randoTwo]);
      }
      // break;
    } while(1);
    
  } while (1);

  return 0;
}

void callRandom (int* randoOne, int* randoTwo) {
  *randoOne = rand() % (2 + 1);
  *randoTwo = rand() % (2 + 1);
}


