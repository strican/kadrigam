public interface PointList
{
  public boolean canPay(PointVal p);
  public void pay(PointList p);
  public void pay(PointVal p);
  public void add(PointList p);
  public void add(PointVal p);
  public int get(Color c);
}