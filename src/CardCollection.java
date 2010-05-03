
import java.io.Serializable;
import java.util.ArrayList;

public class CardCollection implements CardList, Serializable {

    private ArrayList<Card> cards;

    public CardCollection() {
        cards = new ArrayList<Card>();
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public boolean hasCard(Card c) {
        return cards.contains(c);
    }

    // Searches based on name
    public boolean hasCard(String c) {
        for (int i = 0; i < size(); i++) {
            if (c.compareTo(cards.get(i).getName()) == 0) {
                return true;
            }
        }

        return false;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public Card getCard(String s) {
        for (int i = 0; i < size(); i++) {
            if (getCard(i).getName().compareTo(s) == 0) {
                return getCard(i);
            }
        }
        return null;
    }

    public boolean addCard(Card c) {
        return cards.add(c);
    }

    public boolean removeAll(String c) {
        if (!hasCard(c)) {
            return false;
        }

        while (hasCard(c)) {
            for (int i = 0; i < size(); i++) {
                if (c.compareTo(cards.get(i).getName()) == 0) {
                    takeCard(cards.get(i));
                }
            }
        }
        return true;
    }

    public boolean removeOne(String c){
        if (!hasCard(c)) {
            return false;
        }

        else{
            for (int i = 0; i < size(); i++) {
                if (c.compareTo(cards.get(i).getName()) == 0) {
                    takeCard(cards.get(i));
                }
            }
            return true;
        }
    }

    public Card takeCard() {
        return (Card) cards.remove(0);
    }

    public Card takeCard(Card c) {
        return (Card) cards.remove(cards.indexOf(c));
    }

    //TODO: check this code. is there a better way?
    public Object display(int i) {
        try {
            return cards.get(i).toString();
        } catch (Exception e) {
            return "";
        }
    }

    public String toString() {
        int i = 0;
        String s = new String();
        for (Card c : cards) {
            i++;
            s += "[" + i + "] " + c.display() + "\n";
        }
        return s;
    }

    public int getCV(){
        int cv = 0;
        for (int i = 0; i < size(); i++) {
            cv += cards.get(i).getCV();
        }
        return cv;
    }

    public int getCardCount(String c) {
        int counter = 0;

        for (int i = 0; i < size(); i++) {
            if (c.compareTo(cards.get(i).getName()) == 0){
                counter++;
            }
        }

        return counter;
    }
}
