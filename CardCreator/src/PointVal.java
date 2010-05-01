public class PointVal 
{
	public final Color c;
	private int val;
  
	public PointVal(Color c, int val)
	{
		this.c = c;
		this.val = val;
	}
	
	public int getVal() { return val; }
	
	public void addVal(int val)
	{
		this.val += val;
	}
  
  	public void neg()
  	{
  		val = -val;
  	}
  	
  	public String toString()
  	{
  		return c + ": " + val;
  	}
}