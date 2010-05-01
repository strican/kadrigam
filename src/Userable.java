public interface Userable 
{
    public String getAccountName();

    // will make actual create card later. random for now.
    public Card createCard();
    public void deleteCard(String c);
    public CardCollection getLibrary();
    
    public void addCardToDeck(Card c, String d);
    public CardCollection getDeck(String s);
    public boolean createDeck(String s);
    public void deleteDeck(String d);
    public DeckCollection getDeckList();
    public void makeDefaultDeck();
}