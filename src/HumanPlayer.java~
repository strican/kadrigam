public class HumanPlayer implements Player
{
  public static final int MAXHANDSIZE = 7;
  public static final int MAXALLIES = 5;
  public static final int INITIAL_POINTS = 100000;
  
  private PointList life;
  private CardCollection allies;
  private CardPile spellStack;
  //Should these be CardLists or CardPiles?
  private CardPile deck;
  private CardPile graveyard;
  private CardCollection hand;
  
  public HumanPlayer(CardPile deck)
  {
    this.deck = deck;
    
    //Initializes the players life total
    life = new ColorPoints();
    life.add(new PointVal(Color.NEUTRAL,INITIAL_POINTS));
    
    //Initializes the players hand
    for (int i=0; i<MAXHANDSIZE; i++)
      drawCard();
    
    graveyard = new CardPile();
    spellStack = new CardPile();
    allies = new CardCollection();
  }
  
  public void drawCard()
  {
    hand.addCard(deck.takeCard());
  }
  
  public void discardCard(Card c)
  {
    if (hand.hasCard(c))
      graveyard.addCard(hand.takeCard(c));
    else
      //throw some exception?
      return;
  }
  
  public void takeDamage(PointList dmg)
  {
    life.pay(dmg);
  }
  
  public void destroyCreature(Creature c)
  {
    graveyard.addCard(allies.takeCard(c));
  }
  
  public void sacrifice(Creature c)
  {
    life.add(c.getPayoff());
    destroyCreature(c);
  }
  
  public void dealDamage(int dmg, Creature c)
  {
    c.changeHP(-dmg);
  }
  
  public void playCard(Card c)
  {
    if (life.canPay(c.getCost()))
    {
      life.pay(c.getCost());
      if (c instanceof Creature)
      {
        if (allies.size() < MAXALLIES)
          allies.addCard(hand.takeCard(c));
        else
          System.out.println("Too many creatures in play");
      }
      else if (c instanceof Spell)
        spellStack.addCard(hand.takeCard(c));
    }
    else
      //raise some exception?
      System.out.println("Not enough points");
      return;
  }
  
  public void activateEffect(Card c)
  {
    /*Implementation here*/
  }
  public int assignAttack()
  {
    /*Implementation here - how will the player know which are attacking?*/
    return 0;
  }
}