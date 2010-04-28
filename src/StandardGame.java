public class StandardGame implements Game
{
  private Player p1;
  private Player p2;
  Rules r;
  
  public StandardGame(Player p1, Player p2)
  {
    this.p1 = p1;
    this.p2 = p2;
    r = new StandardRules();
  }
  
  public void turn(Player p, Player opponent)
  {
    System.out.printf(">>>>>>>>BEGINNING OF TURN<<<<<<<<<<\n");
     //Draw & Activate
     init_phase(p);
     
     //Sacrifice Phase
     sacrifice_phase(p);
     
     //Play Phase I
     play_phase(p,true,true);
     
     //Attack Phase
     int damageDealt;
     damageDealt = attack_phase(p);
     
     if (damageDealt > 0)
     {
       //Block phase
       block_phase(opponent,damageDealt);
       
       //Play Phase II
       play_phase(p,true,false);
     }
     
     //Discard phase
     discard_phase(p);
  }
  
  public void play()
  {
    while (!isOver())
    {
      System.out.println("=========== PLAYER 1 ============");
      turn(p1,p2);
      if (isOver())
        break;
      System.out.println("=========== PLAYER 2 ============");
      turn(p2,p1);
    }
    if (winner() == p1)
      System.out.println("Player 1 Wins!");
    else
      System.out.println("Player 2 Wins!");
  }
  
  public void init_phase(Player p)
  {
    p.drawCard();
    for (int i=0; i<p.getAllies().size(); i++)
      ((Creature)((CardCollection)(p.getAllies())).getCard(i)).setActive(true);
    Test.printPlayerInfo(p);
  }
  
  public void sacrifice_phase(Player p)
  {
    int playerSelection;
    while(!p.getAllies().isEmpty())
     {
      //"p.getSacrificeSelection()"
       System.out.println("Enter creature to sacrifice (0 to cancel)");
       playerSelection = Test.readInt(0,p.getAllies().size());
       if (playerSelection == 0)
         break;
       
       //Creature must be active
       Creature c = (Creature)((CardCollection)(p.getAllies())).getCard(playerSelection-1);
       if (c.isActive())
         p.sacrifice(c);
       
       Test.printPlayerInfo(p);
     }
  }
  
  public void play_phase(Player p, boolean canPlaySpells, boolean canPlayCreatures)
  {
    int playerSelection;
    while(!p.getHand().isEmpty())
     {
       Test.printPlayerInfo(p);
       System.out.println("Please select a card to play (0 to cancel)");
       playerSelection = Test.readInt(0,p.getHand().size());
       if (playerSelection == 0)
           break;
       
       //If move is legal...
       Card c = ((CardCollection)(p.getHand())).getCard(playerSelection-1);
       if ((canPlaySpells && c instanceof Spell) || 
           (canPlayCreatures && c instanceof Creature))
       {
         Move play = new PlayMove(p, c);
       
         if (play.isLegal(r))
           play.execute();
       }
       else
         System.out.printf("You cannot play that during this play phase");
       
       Test.printPlayerInfo(p);
     }
  }
  
  public int attack_phase(Player p)
  {
    int playerSelection;
    int damageDealt = 0;
     while(!p.getAllies().isEmpty())
     {
       System.out.println("Please assign creatures to attack (0 to end)");
       playerSelection = Test.readInt(0,(p.getAllies()).size());
       if (playerSelection == 0)
         break;
       //If move is legal...
       Creature c = (Creature)((CardCollection)(p.getAllies())).getCard(playerSelection-1);
       Move attack = new AttackMove(p, c);
       
       if (attack.isLegal(r))
        attack.execute();
       
       else
           System.out.printf("That creature cannot attack again yet!");
       
       damageDealt += ((AttackMove) attack).damage;      
       
       Test.printPlayerInfo(p);      

       System.out.println("Current damage to be dealt " + damageDealt);
     }
     return damageDealt;
  }
  
  public void block_phase(Player p, int damage)
  {
    int playerSelection;
    int damageRemaining = damage;
    
    while (damageRemaining > 0 && p.getAllies().size() > 0)
    {
      Test.printPlayerInfo(p);
      System.out.println("Please select a creature you would like to block (0 to let damage through)");
    
      //TODO: MAKE A "getAlly()" METHOD
      playerSelection = Test.readInt(0,(p.getAllies()).size());
      if (playerSelection == 0)
        break;
      Creature c = (Creature)((CardCollection)(p.getAllies())).getCard(playerSelection-1);
      
      System.out.println("Please enter damage to deal");
      int limit = Math.min(damageRemaining, c.getHP());
      playerSelection = Test.readInt(0,limit);
      p.dealDamage(playerSelection,c);
      
      damageRemaining -= playerSelection;
    }
    //TODO: Implement so we can spread damage among colors?
    PointVal splash = new PointVal(Color.NEUTRAL,damageRemaining);
    ColorPoints dmg = new ColorPoints();
    dmg.add(splash);
    p.takeDamage(dmg);
  }
  
  public void discard_phase(Player p)
  {
    int playerSelection;
    while(p.getHand().size() > HumanPlayer.MAXHANDSIZE)
     {
       System.out.println("Too many cards in hand -- please select a card to discard");
       playerSelection = Test.readInt(1,(p.getHand()).size());
       Card c = ((CardCollection)(p.getHand())).getCard(playerSelection-1);
       Move discard = new DiscardMove(p,c);
       
       if (discard.isLegal(r))
         discard.execute();
     }
  }
  
  public boolean isOver()
  {
    boolean p1lost = true;
    boolean p2lost = true;
    
    for (Color c : Color.values())
    {
      p1lost = p1lost && (p1.getLife().get(c) <= 0);
      p2lost = p2lost && (p2.getLife().get(c) <= 0);
    }
    p1lost = (p1lost || p1.getDeck().isEmpty());
    p2lost = (p2lost || p2.getDeck().isEmpty());
    
    return (p1lost || p2lost);
  }
  
  public Player winner()
  {
    boolean p1lost = true;
    boolean p2lost = true;
    
    if (isOver())
    {
      for (Color c : Color.values())
      {
        p1lost = (p1.getLife().get(c) == 0);
        p2lost = (p2.getLife().get(c) == 0);
      }
      if (p1lost && !p2lost)
        return p2;
      else if (p2lost && !p1lost)
        return p1;
      else
        //Throw a tie game exception?
        return null;
    }
    else
      return null;
  }
}