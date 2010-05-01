
import java.io.Serializable;
import java.util.Set;

public class User implements Userable, Serializable {



    private final String account;
    private CardCollection collection;
    private DeckCollection decks;

    public User(String account) {
        this.account = account;
        this.collection = new CardCollection();
        this.decks = new DeckCollection();
        makeDefaultDeck();
    }

    public String getAccountName() {
        return account;
    }

    // will make actual create card later. random for now.
    public Card createCard() {
        Card random = Test.randomCard();
        if (!collection.hasCard(random.getName())) {
            //System.out.println(random.getName());
            collection.addCard(random);
        }

        return random;
    }

    public void deleteCard(String c) {
        Set set = decks.deckSet();

        // delete card from library and decks only if in them
        if (collection.removeAll(c)) {
            for (Object o : set) {
                decks.getDeck((String) o).removeAll(c);
            }
        }

    }

    public CardCollection getLibrary() {
        return collection;
    }

    public void addCardToDeck(Card c, String d) {
        decks.getDeck(d).addCard(c);
    }

    public CardCollection getDeck(String d) {
        return decks.getDeck(d);
    }

    public boolean createDeck(String s) {
        return addDeck(s, new CardCollection());
    }

    public void deleteDeck(String d) {
        decks.deleteDeck(d);
    }

    public DeckCollection getDeckList() {
        return decks;
    }

    private boolean addDeck(String s, CardCollection d) {
        return decks.addDeck(s, d);
    }

    public void makeDefaultDeck() {
        Card random;
        createDeck("default");

        for (int i = 0; i < 50; i++) {
            random = createCard();
            System.out.println(random.getName());
                addCardToDeck(random, "default");
        }
    }


    public static void main(){
        User kadriuser = new User("Kadriuser");
        System.out.println(kadriuser.getAccountName());

        for (int i = 0; i < 50; i++)
        System.out.println(kadriuser.getDeck("default").getCard(i).getName());

    }
}