public interface Game {
  
  /* Plays through entire game */
  public void play();
  
  /* Prompts a player for a move until a legal move is input */
  public Move getMove(Player p);
}
