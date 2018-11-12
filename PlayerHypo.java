
public class PlayerHypo extends Player {
	// Initialization
	private int hypothermiaPts;
	private int resetHypothermiaPts;
	private String cheatCode;
	private boolean toggleHypo;
	static int hypoCreationCount = 0; // static points to the same memory address
	// constructors

	public PlayerHypo() {
		this.hypothermiaPts = 0;
		this.resetHypothermiaPts = this.hypothermiaPts;
		hypoCreationCount++;
		this.cheatCode = "I AM STILL ALIVE BITCHES!";
		this.toggleHypo = true;
	}

	public PlayerHypo(int hypothermiaPts, boolean onOff, boolean toggleHypo) {
		this.hypothermiaPts = hypothermiaPts;
		this.resetHypothermiaPts = hypothermiaPts;
		hypoCreationCount++;
		if (onOff==false) {
			this.cheatCode = "I AM STILL ALIVE BITCHES!";
		} else {
			this.cheatCode = ":::::::::::::::::::::::::";
		}
		this.toggleHypo = toggleHypo;
	}
	// reset player stats at the end of the game
	public void hypoReset() {
	      this.hypothermiaPts = this.resetHypothermiaPts; // resets hypo when called
	      objectCreationCount++;
	    }

	// get and set
	public int getHypothermiaPts() {
		return this.hypothermiaPts;
	}

	public void setHypothermiaPts(int hypotherima) {
		this.hypothermiaPts = hypotherima;
	}
	
	// reset stat
	public void setHypothermiaPtsReset(int hypotherima) {
		this.resetHypothermiaPts = hypotherima;
	}

	// cheat code displayed
	public void toggleCheatCode(boolean onOff) {
		if (onOff) {
			this.cheatCode = "I AM STILL ALIVE BITCHES!";
		} else {
			this.cheatCode = ":::::::::::::::::::::::::";
		}
	}

	public String getCheatCode() {
		return cheatCode;
	}
	
	// toggle hypothermia on or off
	public boolean getHypoStatus() {
		return this.toggleHypo;
	}
	
	public String getHypoStatusDisplay() {
		if (this.toggleHypo == true) {
			return "off";
		} else {
			return " on";
		}
	}
	
	public String getHypoStatusDisplayOpp() {
		if (this.toggleHypo == true) {
			return " on";
		} else {
			return "off";
		}
	}
	
	public void setHypoStatus(boolean toggleHypo) {
		this.toggleHypo = toggleHypo;
	}
	
	
}



