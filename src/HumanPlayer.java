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
  private GameBoard board;
  private String name;
  private Type phase;
  private int turn_damage;
  
  public HumanPlayer(CardPile deck)
  {
    this.deck = deck;
    name = "Human Player";

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

    turn_damage = 0;
    phase = Type.WAIT;
    
  }

    public HumanPlayer(CardPile deck, String name)
  {
    this.deck = deck;
    this.name = name;

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
  public CardCollection getHand() {return hand;}
  public CardList getGraveyard() {return graveyard;}
  public CardCollection getAllies() {return allies;}
  public CardList getSpellStack() {return spellStack;}
  public CardList getDeck() {return deck;}
  public String getName() {return name;};
  public Type getPhase() {return phase;};
  public int getDamage() {return turn_damage;};

  public void setPhase(Type phase)
  {
      this.phase = phase;
  }

  public void addDamage(int i)
  {
      turn_damage += i;
  }

  public void subDamage(int i)
  {
      turn_damage -= i;
  }
  
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
  
  public Card getCard(Type t)
  {
    Card c = null;

    if (t == Type.SACRIFICE || t == Type.ATTACK || t == Type.DAMAGE)
    {
      //board.readCardFromAllies
      int playerSelection = Test.readInt(0,allies.size());
      if (playerSelection != 0)
        c = allies.getCard(playerSelection-1);
    }
    else if (t == Type.PLAY1 || t == Type.PLAY2)
    {
      int playerSelection = Test.readInt(0,hand.size());
      if (playerSelection != 0)
        c = hand.getCard(playerSelection-1);
    }
    else if (t == Type.DISCARD)
    {
      int playerSelection = Test.readInt(1,hand.size());
      c = hand.getCard(playerSelection-1);
    }
    
    return c;
  }
  
  public int getInt(Type t, int min, int max)
  {
    //Won't this only be invoked in a damage phase?
    int input = 0;
    /*
     * displayMessage(t);
     * input = readInt();
     */
    input = Test.readInt(min,max);
    return input;
  }
  
  public void displayMessage(String m)
  {
    /* Should display in the GUI */
    System.out.println(m);
  }
}