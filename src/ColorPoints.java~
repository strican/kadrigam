import java.util.*;

public class ColorPoints implements PointList
{
 // Implements color list as a HashMap
 private HashMap<Color, PointVal> p;

 // Copy Constructor
 public ColorPoints(ColorPoints pts)
 {
   for (Color c : Color.values())
    p.put(c, new PointVal(c, pts.get(c)));
 }
  
 // Default constructor
 public ColorPoints()
 {
  p = new HashMap<Color, PointVal>();
  for (Color c : Color.values())
   p.put(c, new PointVal(c, 0));
 }
  
 // Ensures a player can pay a certain point value
 public boolean canPay(PointVal pval)
 {
  if (get(pval.c) < pval.getVal())
  {
   if (pval.c != Color.NEUTRAL)
    return false;
       if (total() < pval.getVal())
        return false;
  }
  return true;
 }
  
 // Ensures player can pay a certain list of point values
 public boolean canPay(PointList p)
 {
  for (Color c : Color.values())
  {
   if (!canPay(new PointVal(c, p.get(c))))
    return false;
  }  
  return true;
 }
  
 // Returns the number of points a player has of a certain color, c
 public int get(Color c)
 {
  return p.get(c).getVal();
 }
  
 // Adds a number of points to the player's point total of one color
 public void add(PointVal pval)
 {
  p.get(pval.c).addVal(pval.getVal());
 }
  
 // Adds a number of points to the player's point totals for all colors
 public void add(PointList p)
 {
  for (Color c : Color.values())
   add(new PointVal(c, p.get(c)));
 }
 
 // Subtracts a legal cost from the player's point total of one color
 public void pay(PointVal p)
 {
  try
  {
   if (canPay(p))
   {
    p.neg();
    add(p);
   }
   else
    throw new NotEnoughPointsException();
  }
  
  // Display error if there are not enough points and do nothing else
  catch(NotEnoughPointsException e)
  {
   /*
    * TODO: Change to something like
    * 
    * display("Not enough points");
    * 
    */
   System.out.println("Not enough points");
  }
 }
  
 // Subtracts a legal cost from the player's point totals for all colors
 public void pay(PointList p)
 {
  for (Color c : Color.values())
   pay(new PointVal(c, p.get(c)));
 }
  

 // Returns the total number of points a player has, summed over all colors
 public int total()
 {
  int acc = 0;
  for (Color c : Color.values())
   acc += get(c);
   
  return acc;
 }
 
 public String toString()
 {
  String s = "";
  for (Color c : Color.values())
   s += p.get(c) + "\n";
  
  return s;
 }
 
 public String toStringConcise()
 {
   String s = "";
   for (Color c : Color.values())
     s += p.get(c) + " ";
   return s;
 }
}