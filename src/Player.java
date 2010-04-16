public interface Player
{
  /* Player assigns attackers and returns the total damage to be dealt */
  public int assignAttack();
  
  /* Player deducts dmg points from his life total.  They may spread this
   * damage among any of its color pools */
  public void takeDamage(int dmg);
  
  /* Player deals dmg damage to target creature */
  public void dealDamage(int dmg, Creature c);
  
  //TODO: How will this work with specific targets?
  public void activateAbility(Card c);
  
  /* Player removes a card from deck and adds to hand */
  public void drawCard();
  
  /* Puts a card from the player's hand into play.  Creatures are added
   * to the list of creatures the player controls and spells are added to
   * the stack */
  public void playCard();
  
  /* Player moves a creature to the graveyard and adds its payoff points
   * to their point total */
  public void sacrifice(Creature c);
  
  /* Moves a card from play to the graveyard */
  public void moveToGraveyard(Card c);
  
  /* Player removea card from their hand and places it in the graveyard */
  public void discardCard();
  
  
}