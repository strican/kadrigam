public enum Color
{
	NEUTRAL (0),
	RED (1),
	GREEN (2),
	BLUE (3),
	WHITE (4),
	BLACK (5);
  
	public static final int NUM_COLORS = 6;
	public static Color getColor(int ind)
	{
		switch (ind % NUM_COLORS)
		{
		case 0:	return NEUTRAL;
		case 1:	return RED;
		case 2:	return GREEN;
		case 3:	return BLUE;
		case 4:	return WHITE;
		case 5:	return BLACK;
		
		// Impossible 
		default:	return NEUTRAL;
		}
		
		
	}
  
	private final int index;
  
	Color (int i)
	{
		index = i;
	}
  
	public int getIndex()
	{
		return index;
	}
  
}
