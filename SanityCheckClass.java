public class SanityCheckClass {
	  // Initializations
      static int objectCreationCount = 0;   // static points to the same memory address
  // constructors
    public SanityCheckClass() {
      objectCreationCount++;
    }
  // sanity check for each answer
    // answer A
    public int questionA(Player pl, int questionNum) {
    	int sanity = pl.getSanityPts();
    	if (sanity <= 0) {
    		questionNum = 1000; // when the question case is checked, it will default to a :::::::: to prevent the player from seeing answer
    		return questionNum;
    	} else {
    		return questionNum;    		
    	}
    }
    // answer B
    public int questionB(Player pl, int questionNum) {
    	int sanity = pl.getSanityPts();
    	if (sanity < 3) {
    		questionNum = 1000; // when the question case is checked, it will default to a :::::::: to prevent the player from seeing answer
    		return questionNum;
    	} else {
    		return questionNum;    		
    	}
    }
    // answer C
    public int questionC(Player pl, int questionNum) {
    	int sanity = pl.getSanityPts();
    	if (sanity < 5) {
    		questionNum = 1000; // when the question case is checked, it will default to a :::::::: to prevent the player from seeing answer
    		return questionNum;
    	} else {
    		return questionNum;    		
    	}
    }
    // answer D
    public int questionD(Player pl, int questionNum) {
    	int sanity = pl.getSanityPts();
    	if (sanity < 7) {
    		questionNum = 1000; // when the question case is checked, it will default to a :::::::: to prevent the player from seeing answer
    		return questionNum;
    	} else {
    		return questionNum;    		
    	}
    }
      
	
}
