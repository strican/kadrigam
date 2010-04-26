public interface Player
{
	
	  public static final int MAXHANDSIZE = 7;
	  public static final int MAXALLIES = 5;
	  public static final int INITIAL_POINTS = 100000;
	  
  /* Player assigns attackers and returns the total damage to be dealt */
  public int assignAttack();
  
  /* Player deducts dmg points from his life total.  They may spread this
   * damage among any of its color pools */
  public void takeDamage(PointList dmg);
  
  /* Player deals dmg damage to target creature */
  public void dealDamage(int dmg, Creature c);
  
  //TODO: How will this work with specific targets?
  public void activateEffect(Card c);
  
  /* Player removes a card from deck and adds to hand */
  public void drawCard();
  
  /* Puts a card from the player's hand into play.  Creatures are added
   * to the list of creatures the player controls and spells are added to
   * the stack */
  public void playCard(Card c);
  
  /* Player moves a creature to the graveyard and adds its payoff points
   * to their point total */
  public void sacrifice(Creature c);
  
  /* Moves a card from play to the graveyard */
  public void destroyCreature(Creature c);
  
  /* Player remove a card from their hand and places it in the graveyard */
  public void discardCard(Card c);
  
  public PointList getLife();
  public CardList getHand();
  public CardList getGraveyard();
  public CardList getAllies();
  public CardList getSpellStack();
  
  
}