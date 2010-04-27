
public interface Game {
  
  /* Plays through entire game */
  public void play();
  
  /* Prompts a player for a move until a legal move is input */
  //public Move getMove(Player p);
  
  /* Returns true if the game is over */
  public boolean isOver();
  
  /* Assuming game is over, returns winning player */
  public Player winner();
}
