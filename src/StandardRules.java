
public class StandardRules implements Rules
{

    private boolean checkDraw(Move m)
    {
        if (m instanceof DrawMove)
            return !m.getPlayer().getDeck().isEmpty();

        else
            return false;
    }

 private boolean checkPlay1(Move m)
 {
  Player p = m.getPlayer();
  Card c = m.getCard();
  PointList life = p.getLife();
  CardCollection allies = p.getAllies();
  //CardList spellStack = p.getSpellStack();
  CardCollection hand = p.getHand();
  
     if (life.canPay(c.getCost()))
     {
        if (c instanceof Creature)
        {
            if (allies.size() < Player.MAXALLIES && hand.hasCard(c))
                return true;
            else
            {
                System.out.println("Too many creatures in play");
                return false;
            }
        }
    
        else if (c instanceof Spell && hand.hasCard(c))
            return true;
      
        else
            return false;
     }
     
     else
     {
       System.out.println("Not enough points");
       return false;
     }
 }
 
  private boolean checkPlay2(Move m)
 {
  Player p = m.getPlayer();
  Card c = m.getCard();
  PointList life = p.getLife();
  CardCollection allies = p.getAllies();
  //CardList spellStack = p.getSpellStack();
  CardCollection hand = p.getHand();
  
     if (life.canPay(c.getCost()))
     {
        if (c instanceof Creature)
            return false;
    
        else if (c instanceof Spell  && hand.hasCard(c))
            return true;
      
        else
            return false;
     }
     
     else
     {
       System.out.println("Not enough points");
       return false;
     }
 }

 private boolean checkDiscard(Move m)
 {
     if (m instanceof DiscardMove)
     {
        Player p = m.getPlayer();
        CardList h = p.getHand();
        if (h.hasCard(m.getCard()))
            return true;
        else
            return false;
     }

     else
         return false;
 }
 
 private boolean checkAttack(Move m)
 {
     if (m instanceof AttackMove)
     {
         Card c = m.getCard();
         if (c instanceof Creature)
            return ((Creature) c).isActive();
         else
             return false;
     }
     
     else
         return false;


 }
 
 private boolean checkDamage(Move m)
 {
     if (m instanceof DamageMove)
	 return m.getPlayer().getAllies().hasCard(m.getCard()) && ((Creature) m.getCard()).getHP() >= ((DamageMove) m).getDamage();

     else
         return false;
 }
 
 private boolean checkSacrifice(Move m)
 {
     if (m instanceof SacrificeMove)
	 return ((Creature) m.getCard()).isActive();

     else
         return false;
 }
 
 public boolean check(Type t, Move m)
 {
  switch(t)
  {
    case DRAW:          return checkDraw(m);
    case PLAY1:  	return checkPlay1(m);
    case PLAY2:         return checkPlay2(m);
    case DISCARD: 	return checkDiscard(m);
    case ATTACK:        return checkAttack(m);
    case DAMAGE: 	return checkDamage(m);
    case SACRIFICE: 	return checkSacrifice(m);
    default:  		return false;
  }
 }
}
