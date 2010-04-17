
public class Test {

 public static void main(String[] args)
 {
   pointsTest();
 }
 
 public static void pointsTest()
 {
   for (Color c : Color.values())
   System.out.println(c);
  
  PointList p = new ColorPoints();
  System.out.println(p);
  
  PointVal p2 = new PointVal(Color.NEUTRAL,1000);
  PointVal p3 = new PointVal(Color.RED,150);
  PointList p4 = new ColorPoints();
  
  p.add(p2);
  System.out.println(p);
  if (!p.canPay(p3))
    System.out.printf("Can't pay" + p3.toString() + "\n");
  
  p.add(p3);
  p.add(p3);
  System.out.println(p);
  if (!p.canPay(p3))
    System.out.printf("Can't pay" + p3.toString() + "\n");
 
  p4.add(p2);
  p4.add(p3);
  p.pay(p4);
  System.out.println(p);
  
  p.pay(p2);
 }

}
