
public class Test
{

	public static void main(String[] args)
	{
		for (Color c : Color.values())
			System.out.println(c);
		
		PointList p = new ColorPoints();
		System.out.println(p);
		
		p.add(new PointVal(Color.RED, 100));
		System.out.println(p);
		
		Card c = new Creature("Chuck Norris", 1000, 1000, null, p, p);
		System.out.println(c);
		
	}

}
