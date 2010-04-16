public class ColorPoints implements PointList
{
  private PointVal p[];
  
  public ColorPoints(int vals[])
  {
    p = new PointVal[Color.NUM_COLORS];
    for (int i = 0; i<Color.NUM_COLORS; i++)
      p[i] = new PointVal(Color.getColor(i),vals[i]);
  }
  
  public int get(Color c)
  {
    return p[c.getIndex()].getVal();
  }
  
  public void add(PointVal p)
  {
    (this.p[(p.c).getIndex()]).addVal(p.getVal());
  }
  
  public void add(PointList p)
  {
    for (int i=0; i<Color.NUM_COLORS; i++)
    {
      (this.p[i]).addVal(p.get(Color.getColor(i)));
    }
  }
 
  public void pay(PointVal p)
  {
    (this.p[(p.c).getIndex()]).addVal(-p.getVal());
  }
  
  public void pay(PointList p)
  {
    for (int i=0; i<Color.NUM_COLORS; i++)
    {
      (this.p[i]).addVal(-p.get(Color.getColor(i)));
    }
  }
}