

public class Play2Move implements Move
{
  /* This allows Rules to see the information for each type of move
   * but still protects the from modification */
 public final Card c;
 public final Player p;

 Play2Move(Player p, Card c)
 {
  this.c = c;
  this.p = p;
 }

 public boolean isLegal(Rules r)
 {
  return r.check(Type.PLAY2, this);
 }

 public void execute()
 {
  p.playCard(c);
 }

 public Card getCard()
 {
     return c;
 }

 public Player getPlayer()
 {
     return p;
 }

}
