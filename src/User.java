import java.io.Serializable;
import java.util.ArrayList;

public class User implements Userable, Serializable{
    private final String account;
    private CardCollection collection;
    private ArrayList<CardCollection> decks= new ArrayList<CardCollection>();
    
    public User(String account) {
        this.account = account;
        makeDefaultDeck();
    }

    public String getAccountName(){
        return account;
    }

    // will make actual create card later. random for now.
    public void createCard(){
        return;
    }
    public void deleteCard(Card c){
        if (collection.hasCard(c)){
            collection.takeCard(c);
           //delete from decks
           for(int i = 0; i < decks.size(); i++){
               while(decks.get(i).hasCard(c)){
                   decks.get(i).takeCard(c);
               }
           }
        }
    }
    public Card getCard (Card c){
        return null;
    }

    public void addCardToDeck(Card c, CardPile d){
        return;
    }
    public CardPile getDeck(CardPile d){
        return null;
    }
    public CardPile createDeck(){
        return null;
    }
    public void deleteDeck(CardPile d){

    }

    public void makeDefaultDeck(){
        Card random;

        for(int i=0; i<50;i++){
            random =Test.randomCard();
            collection.addCard(random);
            decks.get(0).addCard(random);
        }
    }
}
