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

  @Override
  public String toString()
  {
      String s = "";
      s += type+" "+mag;
      switch(type) {
          case HEAL:
          case DEALDAMAGE:
              s+=" POINTS\n";
              break;
          case DISCARD:
          case DRAW:
              s+=" CARDS\n";
              break;
          case DEACTIVATE:
          case DESTROY:
              s+= " CREATURES\n";
              break;
          default: s+="";
      }
      return s;
  }
}