

public class Play1Move implements Move
{
  /* This allows Rules to see the information for each type of move
   * but still protects the from modification */
 private final Card c;
 private final Player p;

 Play1Move(Player p, Card c)
 {
  this.c = c;
  this.p = p;
 }

 public boolean isLegal(Rules r)
 {
  return r.check(Type.PLAY1, this);
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
