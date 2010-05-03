import java.io.Serializable;
import java.io.File;

public class Spell extends Card implements Serializable
{ 
  private Effect e;
  
  public Spell(String name, Effect e, PointList cost)
  {
    super(name,cost);
    this.e = e;
  }

  public Spell(String name, Effect e, PointList cost, File f)
  {
    super(name,cost,f);
    this.e = e;
  }
  
  public Effect getEffect() { return e; }
}