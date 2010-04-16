public interface Card
{
  public PointList getCost();
  public Ability getAbility();
  
  /* For now, each card will only have one ability */
  public void activateAbility();
  
  public Boolean isActive();
  
}