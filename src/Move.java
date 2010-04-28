public interface Move
{
	/**
	 * Tests if a move is legal based on the rule set, r, being used.
	 * 
	 * @param r		Rule set being used in the game
	 * @return		True if legal, false otherwise
	 */
	public boolean isLegal(Rules r);
	
	/**
	 * Executes the move and changes the game state
	 */
	public void execute();
}