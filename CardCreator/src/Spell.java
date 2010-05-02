import java.io.Serializable;

public class Spell extends Card implements Serializable
{ 
  private Effect e;
  
  public Spell(String name, Effect e, PointList cost)
  {
    super(name,cost);
    this.e = e;
  }
  
  public Effect getEffect() { return e; }
}