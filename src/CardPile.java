import java.util.Stack;

public class CardPile implements CardList
{
  private Stack<Card> s;
  private int size;
  
  public CardPile()
  {
    s = new Stack<Card>();
    size = 0;
  }
  
  public int size() { return size; }
  
  public boolean addCard(Card c)
  {
    size++;
    return (s.push(c) != null);
  }
  public Card takeCard()
  {
    size--;
    return (Card)s.pop();
  }

  public boolean isEmpty()
  {
    return s.empty();
  }
  public Card next()
  {
    if (!isEmpty())
      return (Card)s.peek();
    else
      return null;
  }
  public boolean hasCard(Card c)
  {
    return (s.search(c) != -1);
  }
  
  public String toString()
  {
    String s = "";
    Card top = next();
    
    if (top != null)
      s += "TOP: " + top.toString() + "\n";
    return s;
  }
  
  /* TODO: ADD A SHUFFLE METHOD */
}