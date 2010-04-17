
public class StandardRules implements Rules
{
	private boolean checkPlay(Move m)
	{
		return false;
	}
	
	private boolean checkDiscard(Move m)
	{
		return false;
	}
	
	private boolean checkAttack(Move m)
	{
		return false;
	}
	
	private boolean checkDamage(Move m)
	{
		return false;
	}
	
	public boolean check(Type t, Move m)
	{
		switch(t)
		{
		case PLAY:		return checkPlay(m);
		case DISCARD:	return checkDiscard(m);
		case ATTACK:	return checkAttack(m);
		case DAMAGE:	return checkDamage(m);
		default:		return false;
		}
	}
}
