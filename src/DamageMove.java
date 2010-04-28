
public class DamageMove implements Move
{
	/* This allows Rules to see the information for each type of move 
	  * but still protects the from modification */
	public final Card c;
	public final Player p;
	public final int damage;
	
	DamageMove(Player p, Card c, int damage)
	{
		this.c = c;
		this.p = p;
		this.damage = damage;
	}
	
	public boolean isLegal(Rules r)
	{
		return r.check(Type.DAMAGE, this);
	}
	 
	public void execute()
	{
		p.dealDamage(damage, (Creature) c);
	}

}
