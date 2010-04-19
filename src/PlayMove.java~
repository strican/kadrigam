
public class PlayMove implements Move
{
	private Card c;
	private Player p;
	
	PlayMove(Player p, Card c)
	{
		this.c = c;
		this.p = p;
	}
	
	public boolean isLegal(Rules r)
	{
		return r.check(Type.PLAY, this);
	}
	
	public void execute()
	{
		p.playCard(c);
	}

}
