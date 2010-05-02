import java.io.Serializable;

public class Effect implements Serializable
{
  private final int mag;
  private final EffectType type;
  
  public Effect(int mag, EffectType type)
  {
    this.mag = mag;
    this.type = type;
  }
}