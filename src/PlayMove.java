
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
		//Test Legality
		
		//Temporary solution
		return true;
	}
	
	public void execute()
	{
		
	}

}
