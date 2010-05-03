import java.io.File;

public abstract class Card extends SuperCard implements Comparable, Playable
{
  private String name;
  private PointList cost;
  private int cardvalue;
  private File img;

  public void setCV(int cv){
      this.cardvalue = cv;
  }

  public int getCV(){
      return cardvalue;
  }

  public Card(String name, PointList cost)
  {
    this.name = name;
    this.cost = cost;
  }

  public Card(String name, PointList cost, File img)
  {
    this.name = name;
    this.cost = cost;
    this.img = img;
  }
  
  public String getName() {return name;}
  public PointList getCost() {return cost;}
  public File getImage() {return img;}
  
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