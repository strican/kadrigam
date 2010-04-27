
public enum Color
{
 NEUTRAL,
 RED,
 GREEN,
 BLUE,
 WHITE,
 BLACK;
  
 public static final int NUM_COLORS = 6;
 
 public String toString()
 {
  switch(this)
  {
  case NEUTRAL: return "Neutral";
  case RED:  return "Red";
  case GREEN:  return "Green";
  case BLUE:  return "Blue";
  case WHITE:  return "White";
  case BLACK:  return "Black";
  default:  return "";
  }
 }
}
