
public class Player {
	// Initializations
	// private
	private int healthPts;
	private int thirstPts;
	private int hungerPts;
	private int sanityPts;
	private int resetHealthPts;
	private int resetThirstPts;
	private int resetHungerPts;
	private int resetSanityPts;
	private int questionNumber;
	static int objectCreationCount = 0;

	// public
	// constructors
	public Player() {
		      this.healthPts = 100; // out of 100
		      this.thirstPts = 3;  // out of 7 because can survive a week without water
		      this.hungerPts = 7;  // out of 21 because can survive three weeks without food
		      this.sanityPts = 10;   // out of 30
		      this.resetHealthPts = this.healthPts;
		      this.resetThirstPts = this.thirstPts;
		      this.resetHungerPts = this.hungerPts;
		      this.resetSanityPts = this.sanityPts;
		      this.questionNumber = 0;
		      objectCreationCount++;
		    }

	public Player(int healthPts, int thirstPts, int hungerPts, int sanityPts, int questionNumber) {
		      this.healthPts = healthPts;
		      this.thirstPts = thirstPts;
		      this.hungerPts = hungerPts;
		      this.sanityPts = sanityPts;
		      this.resetHealthPts = healthPts;
		      this.resetThirstPts = thirstPts;
		      this.resetHungerPts = hungerPts;
		      this.resetSanityPts = sanityPts;
		      this.questionNumber = questionNumber;
		      objectCreationCount++;
		    }

	// reset player stats at the end of the game
	public void playerReset() {
	      this.healthPts = this.resetHealthPts; // out of 100
	      this.thirstPts = this.resetThirstPts;  // out of 7 because can survive a week without water
	      this.hungerPts = this.resetHungerPts;  // out of 21 because can survive three weeks without food
	      this.sanityPts = this.resetSanityPts;   // out of 30
	      this.questionNumber = 0;
	      objectCreationCount++;
	    }
	
	// get for PlayerStats stats
	public int getHealthPts() {
		return this.healthPts;
	}

	public int getThirstPts() {
		return this.thirstPts;
	}

	public int getHungerPts() {
		return this.hungerPts;
	}

	public int getSanityPts() {
		return this.sanityPts;
	}

	public int getQuestionNumber() {
		return this.questionNumber;
	}

	// set for PlayerStats stats
	public void setHealthPts(int healthPts) {
		this.healthPts = healthPts;
	}

	public void setThirstPts(int thirstPts) {
		this.thirstPts = thirstPts;
	}

	public void setHungerPts(int hungerPts) {
		this.hungerPts = hungerPts;
	}

	public void setSanityPts(int sanityPts) {
		this.sanityPts = sanityPts;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	// set reset
	public void setHealthPtsReset(int healthPts) {
		this.resetHealthPts = healthPts;
	}

	public void setThirstPtsReset(int thirstPts) {
		this.resetThirstPts = thirstPts;
	}

	public void setHungerPtsReset(int hungerPts) {
		this.resetHungerPts = hungerPts;
	}

	public void setSanityPtsReset(int sanityPts) {
		this.resetSanityPts = sanityPts;
	}
}