
public abstract class Card implements Comparable, Playable
{
  private String name;
  private PointList cost;
  
  public Card(String name, PointList cost)
  {
    this.name = name;
    this.cost = cost;
  }
  
  public String getName() {return name;}
  public PointList getCost() {return cost;}
  
  public int compareTo(Object o)
  {
    return name.compareTo(((Card)o).getName());
  }

  public Object display()
  {
      String s = toString();
      if (this == null)
          return "";
      else return s;
  }

  public String toString()
  {
    String s = "";
    s = name + "\n\t" + cost.toStringConcise() + "\n";
    return s;
  }
}