public class Tool {
	// Initializations
	// private
	private int toolUseCount;
	private int toolType;
	static int objectCreationCount = 0; // static points to the same memory address
	// public
	// constructors

	public Tool() {
		this.toolUseCount = 5;
		this.toolType = 0;
		objectCreationCount++;
	}

	public Tool(int toolUseCount, int toolType) {
		this.toolUseCount = toolUseCount;
		this.toolType = toolType;
		objectCreationCount++;
	}

	// get and set tool count
	public int getToolUseCount() {
		return this.toolUseCount;
	}

	public void setToolUseCount(int toolUseCount) {
		this.toolUseCount = toolUseCount;
	}

	// get and set tool type
	public int getToolType() {
		return this.toolType;
	}

	public void setToolType(int toolType) {
		this.toolType = toolType;	
	}
}