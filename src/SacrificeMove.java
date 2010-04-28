public class SacrificeMove implements Move
{
	 /* This allows Rules to see the information for each type of move 
	  * but still protects the from modification */
	public final Card c;
	public final Player p;
	public int damage;
	
	SacrificeMove(Player p, Card c)
	{
		this.c = c;
		this.p = p;
		damage = 0;
	}
	
	public boolean isLegal(Rules r)
	{
		return r.check(Type.SACRIFICE, this);
	}
	 
	public void execute()
	{
		((Creature) c).setActive(false);
       damage = ((Creature) c).getPow();
	}

}
