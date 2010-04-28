public interface Userable 
{
    public String getAccountName();

    // will make actual create card later. random for now.
    public void createCard();
    public void deleteCard(Card c);
    public Card getCard (Card c);
    
    public void addCardToDeck(Card c, CardPile d);
    public CardPile getDeck(CardPile d);
    public CardPile createDeck();
    public void deleteDeck(CardPile d);

    public void makeDefaultDeck();
}