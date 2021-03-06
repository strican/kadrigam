import java.io.Serializable;

public class Ability implements Serializable
{
  private final Trigger t;
  private final Effect e;
  
  public Ability(Trigger t, Effect e)
  {
    this.t = t;
    this.e = e;
  }
  
  public Trigger getTrigger() { return t; }
  public Effect getEffect() { return e; }
}