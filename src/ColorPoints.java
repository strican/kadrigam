import java.util.*;

public class ColorPoints implements PointList
{
  private HashMap<Color, PointVal> p;
  
  public ColorPoints(ColorPoints pts)
  {
	  for (Color c : Color.values())
		  p.put(c, new PointVal(c, pts.get(c)));
  }
  
  public ColorPoints(Map<Color, PointVal> p)
  {
	  this.p = new HashMap<Color, PointVal>(p);
  }
  
  public ColorPoints()
  {
	  p = new HashMap<Color, PointVal>();
	  for (Color c : Color.values())
		  p.put(c, new PointVal(c, 0));
  }
  
  public boolean canPay(PointVal pval)
  {
	  if (get(pval.c) < pval.getVal())
	  {
		  if (pval.c != Color.NEUTRAL)
			  return false;
	  	  if (total() < pval.getVal())
	  		  return false;
	  }
	  return true;
  }
  
  public int get(Color c)
  {
	  return p.get(c).getVal();
  }
  
  public void add(PointVal pval)
  {
	  p.get(pval.c).addVal(pval.getVal());
  }
  
  public void add(PointList p)
  {
	  for (Color c : Color.values())
		  add(new PointVal(c, p.get(c)));
  }
 
  public void pay(PointVal p)
  {
	  p.neg();
	  add(p);
  }
  
  public void pay(PointList p)
  {
	  for (Color c : Color.values())
		  pay(new PointVal(c, p.get(c)));
  }
  
  public int total()
  {
	  int acc = 0;
	  for (Color c : Color.values())
		  acc += get(c);
	  
	  return acc;
  }
}