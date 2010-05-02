public class SacrificeMove implements Move
{
	 /* This allows Rules to see the information for each type of move 
	  * but still protects the from modification */
	private final Card c;
	private final Player p;
	
	SacrificeMove(Player p, Card c)
	{
		this.c = c;
		this.p = p;
	}
	
	public boolean isLegal(Rules r)
	{
		return r.check(Type.SACRIFICE, this);
	}
	 
	public void execute()
	{
		p.sacrifice((Creature) c);
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
