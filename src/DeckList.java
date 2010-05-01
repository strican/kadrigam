import java.util.Set;

public interface DeckList {
    public boolean addDeck(String s, CardCollection d);
    public CardCollection getDeck(String s);
    public boolean deleteDeck(String s);
    public int deckSize();
    public Set deckSet();
}
