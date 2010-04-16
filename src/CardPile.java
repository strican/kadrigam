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
    return (Card)s.peek();
  }
  public boolean hasCard(Card c)
  {
    return (s.search(c) != -1);
  }
}