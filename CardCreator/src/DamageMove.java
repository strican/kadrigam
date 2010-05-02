
public class DamageMove implements Move
{
	/* This allows Rules to see the information for each type of move 
	  * but still protects the from modification */
	private final Card c;
	private final Player p;
	private final int damage;
	
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

        // Accessor methods
        public Card getCard()
        {
            return c;
        }

        public Player getPlayer()
        {
            return p;
        }

        public int getDamage()
        {
            return damage;
        }

}
