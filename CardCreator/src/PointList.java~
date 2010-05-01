public interface PointList
{
 /**
  * Determines if player has enough points to pay for a move's cost.
  * <p>
  * Polymorphic on parameter type:
  * @param p  PointVal objected representing cost
  * <p>or<p>
  * @param p  PointList object representing multiple color costs
  * @return  boolean; True if payable, False otherwise
  */
 public boolean canPay(PointVal p);
 public boolean canPay(PointList p);
 
 /**
  * Subtracts a legal cost from the player's point totals.
  * @param p  PointVal objected representing cost
  * <p>or<p>
  * @param p  PointList object representing multiple color costs
  */
 public void pay(PointList p);
 public void pay(PointVal p);
 
 /**
  * Adds a number of points to the player's point totals.
  * @param p  PointVal objected representing cost
  * <p>or<p>
  * @param p  PointList object representing multiple color costs
  */
 public void add(PointList p);
 public void add(PointVal p);
 
 /**
  * Returns the number of points a player has of a certain color, c.
  * @param c  The color whose point value is to be returned
  * @return  int; The point value of color, c.
  */
 public int get(Color c);
 
 /**
  * Returns the total number of points a player has, summed over all colors.
  * @return  int; Total points of all colors.
  */
 public int total();
 
 public String toStringConcise();
}