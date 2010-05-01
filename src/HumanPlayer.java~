public class HumanPlayer implements Player
{
  private PointList life;
  private CardCollection allies;
  private CardPile spellStack;
  //Should these be CardLists or CardPiles?
  private CardPile deck;
  private CardPile graveyard;
  private CardCollection hand;
  private Rules rules;
  
  public HumanPlayer(CardPile deck)
  {
    this.deck = deck;
    
    //Initializes the players life total
    life = new ColorPoints();
    life.add(new PointVal(Color.NEUTRAL,INITIAL_POINTS));
    
    graveyard = new CardPile();
    spellStack = new CardPile();
    allies = new CardCollection();
    hand = new CardCollection();
    
    //Initializes the players hand
    for (int i=0; i<MAXHANDSIZE; i++)
      drawCard();
  }
  
  public PointList getLife() {return life;}
  public CardList getHand() {return hand;}
  public CardList getGraveyard() {return graveyard;}
  public CardList getAllies() {return allies;}
  public CardList getSpellStack() {return spellStack;}
  public CardList getDeck() {return deck;}
  
  public void drawCard()
  {
    if (!deck.isEmpty())
    {
      Card c = deck.takeCard();
      hand.addCard(c);
      //hand.addCard(deck.takeCard());
    }
    else
    {
      //Raise some exception
      System.out.println("Can't draw - deck is empty!");
    }
  }
  
  public void discardCard(Card c)
  {
    graveyard.addCard(hand.takeCard(c));
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
    if (c.getHP() <= 0)
      destroyCreature(c);
  }
  
  public void playCard(Card c)
  {
   life.pay(c.getCost());
   if (c instanceof Creature)
    allies.addCard(hand.takeCard(c));
   else if (c instanceof Spell)
    spellStack.addCard(hand.takeCard(c));
   

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