public class Creature implements Card
{
  private final String name;
  private final Ability abil;
  //NOTE: Will attackPow be final?
  private int attackPow;
  private int hitPoints;
  //active = able to attack or use an ability
  private boolean active;
  private final PointList cost;
  private final PointList payoff;
  
  public Creature(String name, int pow, int hp, Ability abil, PointList c, PointList p)
  {
    this.name = name;
    attackPow = pow;
    hitPoints = hp;
    this.abil = abil;
    cost = c;
    payoff = p;
    active = false;
  }
  
  public int getHP() { return hitPoints; }
  public int getPow() { return attackPow; }
  public String getName() { return name; }
  public Ability getAbility() { return abil; }
  public Effect getEffect() { return abil.getEffect(); }
  public boolean isActive() { return active; }
  public PointList getCost() { return cost; }
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
}