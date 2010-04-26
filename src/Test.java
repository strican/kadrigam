import java.util.Random;
import java.io.IOException;
import java.io.*;


public class Test
{
  static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));


 public static void main(String[] args)
 {
   //pointsTest();
   //cardTest();
   playerTest();
 }
 
 public static void pointsTest()
 {
   for (Color c : Color.values())
   System.out.println(c);
  
  PointList p = new ColorPoints();
  System.out.println(p);
  
  PointVal p2 = new PointVal(Color.NEUTRAL,1000);
  PointVal p3 = new PointVal(Color.RED,150);
  PointList p4 = new ColorPoints();
  
  p.add(p2);
  System.out.println(p);
  if (!p.canPay(p3))
    System.out.printf("Can't pay" + p3.toString() + "\n");
  
  p.add(p3);
  p.add(p3);
  System.out.println(p);
  if (!p.canPay(p3))
    System.out.printf("Can't pay" + p3.toString() + "\n");
 
  p4.add(p2);
  p4.add(p3);
  p.pay(p4);
  System.out.println(p);
  
  p.pay(p2);
 }
 
 public static void cardTest()
 {
  PointList p = new ColorPoints();
  
  p.add(new PointVal(Color.RED, 100));
  
  //Card c = new Creature("Chuck Norris", 1000, 1000, null, p, p);
  Card c = randomCard();
  System.out.println(c);
  
 }
 
 public static Card randomCard()
 {
   Card c;
   ColorPoints cost = new ColorPoints();
   ColorPoints payoff = new ColorPoints();
   
   Random r = new Random();
   
   PointVal costs [] = new PointVal[6];
   costs[0] = new PointVal(Color.NEUTRAL, r.nextInt(1000));
   costs[1] = new PointVal(Color.RED, r.nextInt(1000));
   costs[2] = new PointVal(Color.BLUE, r.nextInt(1000));
   costs[3] = new PointVal(Color.WHITE, r.nextInt(1000));
   costs[4] = new PointVal(Color.BLACK, r.nextInt(1000));
   costs[5] = new PointVal(Color.GREEN, r.nextInt(1000));
   
   String s1 [] = {"Deathly","Brave","Feral","Enraged","Enlightened","Chained"};
   String s2 [] = {"Warrior","Knight","Shaman","Sorceror",
     "Rogue","Druid","Beast","Elf","Ogre","Apprentice"};
   String s3 [] = {"Shattering","Corroding","Refreshing","Lifegiving",
     "Forbidden","Revealing"};
   String s4 [] = {"Pulse","Blast","Wave","Stream","Fetters","Thought","Tome"};
   
   cost.add(costs[r.nextInt(6)]);
   payoff.add(costs[r.nextInt(6)]);
   
   if (r.nextInt() % 2 == 0)
   {
     c = new Creature(s1[r.nextInt(6)]+" "+s2[r.nextInt(10)],
                      r.nextInt(15)*100,r.nextInt(25)*100,
                      null,cost,payoff);
   }
   else
   {
     c = new Spell(s3[r.nextInt(5)]+" "+s4[r.nextInt(7)],null,cost);
   }
   return c;
 }
 
 public static void printPlayerInfo(Player p)
 {
   System.out.println("--Life--\n"+p.getLife());
   System.out.println("--Hand--\n"+p.getHand());
   System.out.println("--Allies--\n"+p.getAllies());
   System.out.println("--Spell Stack--\n"+p.getSpellStack());
 }
 
 public static void playerTest()
 {
   CardPile deck = new CardPile();
   for (int i=0; i<60; i++)
     deck.addCard(randomCard());
   
   Player p = new HumanPlayer(deck);
   
   printPlayerInfo(p);
   boolean turnOngoing = true;
   boolean gameNotOver = true;
   
   while(gameNotOver)
   {  
     System.out.printf(">>>>>>>>BEGINNING OF TURN<<<<<<<<<<\n");
     //Draw & Activate
     p.drawCard();
     for (int i=0; i<p.getAllies().size(); i++)
         ((Creature)((CardCollection)(p.getAllies())).getCard(i)).setActive(true);
     printPlayerInfo(p);
     
     //Play Phase I
     int playerSelection;
     while(!p.getHand().isEmpty())
     {
       System.out.println("Please select a card to play (0 to cancel)");
       playerSelection = readInt(0,p.getHand().size());
       if (playerSelection == 0)
           break;
       //If move is legal...
       Card c = ((CardCollection)(p.getHand())).getCard(playerSelection-1);
       p.playCard(c);
       printPlayerInfo(p);
     }
     
     //Attack Phase
     int damageDealt = 0;
     while(!p.getAllies().isEmpty())
     {
       System.out.println("Please assign creatures to attack (0 to end)");
       playerSelection = readInt(0,(p.getAllies()).size());
       if (playerSelection == 0)
         break;
       //If move is legal...
       Creature c = (Creature)((CardCollection)(p.getAllies())).getCard(playerSelection-1);
       printPlayerInfo(p);
       if (c.isActive())
       {
         c.setActive(false);
         damageDealt+=c.getPow();
         }
       else
         System.out.printf("That creature cannot attack again yet!");
       System.out.println("Current damage to be dealt " + damageDealt);
     }
     
     //Discard phase
     while(p.getHand().size() > HumanPlayer.MAXHANDSIZE)
     {
       System.out.println("Too many cards in hand -- please select a card to discard");
       playerSelection = readInt(0,(p.getHand()).size());
       Card c = ((CardCollection)(p.getHand())).getCard(playerSelection-1);
       p.discardCard(c);
     }
   }
   
 }
 
 private static int readInt(int low, int high) {
  int n=0;
  try {
   n = Integer.parseInt(reader.readLine());
  }
  catch (IOException e) {
   n=-1;
  }
  catch (NumberFormatException e2) {
   n=-1;
  }
  while ((n<low)||(n>high)) {
   System.out.println("Invalid Input. Input should be in the range " + Integer.toString(low) + "-" + Integer.toString (high) + ".");
   try {
    n = Integer.parseInt(reader.readLine());
   }
   catch (IOException e) {
    n=-1;
   }
   catch (NumberFormatException e2) {
    n=-1;
   }
  }
  return n;
 }


}
