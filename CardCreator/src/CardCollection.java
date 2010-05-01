import java.util.ArrayList;

public class CardCollection implements CardList
{
  private ArrayList<Card> cards;
  
  public CardCollection()
  {
    cards = new ArrayList<Card>();
  }
  
  public int size() { return cards.size(); }
  public boolean isEmpty() { return cards.isEmpty(); }
  public boolean hasCard(Card c) { return cards.contains(c); }

  //Not really sure about this one
  public Card next() { return (Card)cards.get(0); }
  public Card getCard(int index) { return cards.get(index); }
  public boolean addCard(Card c) { return cards.add(c); }
  
  public Card takeCard()
  {
    return (Card)cards.remove(0);
  }
  public Card takeCard(Card c) 
  { 
    return (Card)cards.remove(cards.indexOf(c)); 
  }
  
  public String toString()
  {
    int i=0;
    String s = new String();
    for (Card c : cards) {
      i++;
      s += "["+i+"] "+ c.toString() + "\n";
    }
    return s;
  }
}