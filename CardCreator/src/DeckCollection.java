import java.util.HashMap;
import java.util.Set;
import java.io.Serializable;

class DeckCollection implements DeckList, Serializable {
    private HashMap<String,CardCollection> index;

    public DeckCollection() {
        index = new HashMap<String,CardCollection>();
    }

    public Set deckSet(){
        return index.keySet();
    }

    public boolean addDeck(String s,CardCollection d) {
        if (index.containsKey(s))
            return false;

        index.put(s, d);

        return true;
    }

    public boolean deleteDeck(String s) {
        if (!index.containsKey(s))
            return false;

        index.remove(s);

        return true;
    }

    public CardCollection getDeck(String s) {
        return index.get(s);
    }

    public int deckSize() {
        return index.size();
    }
}
