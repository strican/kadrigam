public class Creature extends Card
{
  private final Ability abil;
  //NOTE: Will attackPow be final?
  private int attackPow;
  private int hitPoints;
  //active = able to attack or use an ability
  private boolean active;
  private final PointList payoff;
  
  public Creature(String name, int pow, int hp, Ability abil, PointList c, PointList p)
  {
    super(name,c);
    attackPow = pow;
    hitPoints = hp;
    this.abil = abil;
    payoff = p;
    active = false;
  }
  
  public int getHP() { return hitPoints; }
  public int getPow() { return attackPow; }
  public Ability getAbility() { return abil; }
  public Effect getEffect() { return abil.getEffect(); }
  public boolean isActive() { return active; }
  public PointList getPayoff() { return payoff; }
  
  public void changeHP(int x)
  {
    hitPoints += x;
  }
  public void setActive(boolean b)
  {
    active = b;
  }
  public int attack()
  {
    setActive(false);
    return attackPow;
  }
  
  public String toString()
  {
    String s = super.toString();
    s += "/" + payoff.toString() + "| POW: " + attackPow + "| HP: " + hitPoints;
    return s;
  }
}