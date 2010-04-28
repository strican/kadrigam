
public class StandardRules implements Rules
{
 private boolean checkPlay(PlayMove m)
 {
  Player p = m.p;
  Card c = m.c;
  PointList life = p.getLife();
  CardList allies = p.getAllies();
  CardList spellStack = p.getSpellStack();
  CardCollection hand = (CardCollection) p.getHand();
  
     if (life.canPay(c.getCost()))
     {
      if (c instanceof Creature)
      {
       if (allies.size() < Player.MAXALLIES)
        return true;
       else
       {
        System.out.println("Too many creatures in play");
        return false;
       }
      }
    
      else if (c instanceof Spell)
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
 
 private boolean checkDiscard(DiscardMove m)
 {
  Player p = m.p;
  CardList h = p.getHand();
  if (h.hasCard(m.c))
   return true;
  else
   return false;
 }
 
 private boolean checkAttack(AttackMove m)
 {
	 return ((Creature) m.c).isActive();
 }
 
 private boolean checkDamage(DamageMove m)
 {
	 return m.p.getAllies().hasCard(m.c) && ((Creature) m.c).getHP() >= m.damage;
 }
 
 private boolean checkSacrifice(SacrificeMove m)
 {
	 return ((Creature) m.c).isActive();
 }
 
 public boolean check(Type t, Move m)
 {
  switch(t)
  {
  case PLAY:  		return checkPlay((PlayMove) m);
  case DISCARD: 	return checkDiscard((DiscardMove) m);
  case ATTACK: 		return checkAttack((AttackMove) m);
  case DAMAGE: 		return checkDamage((DamageMove)m);
  case SACRIFICE: 	return checkSacrifice((SacrificeMove) m); 
  default:  		return false;
  }
 }
}
