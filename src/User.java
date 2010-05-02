import java.io.Serializable;
import java.util.Set;

public class User implements Userable, Serializable {

    private final String account;
    private CardCollection collection;
    private DeckCollection decks;

    public User(){
        account = null;
    }

    public User(String account) {
        this.account = account;
        this.collection = new CardCollection();
        this.decks = new DeckCollection();
        makeDefaultLibrary();
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

    public boolean libraryHasCard(String s) {
        return collection.hasCard(s);
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

    private ColorPoints addCP(int neutral, int red, int green, int blue, int white, int black) {
        ColorPoints cp = new ColorPoints();
        cp.add(new PointVal(Color.NEUTRAL, neutral));
        cp.add(new PointVal(Color.RED, red));
        cp.add(new PointVal(Color.GREEN, green));
        cp.add(new PointVal(Color.BLUE, blue));
        cp.add(new PointVal(Color.WHITE, white));
        cp.add(new PointVal(Color.BLACK, black));
        return cp;
    }

    private void makeDefaultLibrary() {
        createDeck("default");

        Creature libCreature;
        Spell libSpell;
        Ability ability;
        PointList cost;
        PointList payoff;
        Effect effect;

        // Create Creature 1

        ability = new Ability(Trigger.PAY, new Effect(3, EffectType.DEACTIVATE));
        cost = addCP(800, 400, 350, 0, 0, 0);
        payoff = addCP(0, 250, 0, 250, 0, 0);

        libCreature = new Creature("Purple Lightning", 400, 300, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 2
        ability = new Ability(Trigger.SACRIFICE, new Effect(300, EffectType.HEAL));
        cost = addCP(0, 800, 0, 0, 0, 0);
        payoff = addCP(0, 50, 0, 0, 0, 0);

        libCreature = new Creature("Red Communist", 500, 500, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 3
        ability = new Ability(Trigger.SACRIFICE, new Effect(850, EffectType.DEALDAMAGE));
        cost = addCP(0, 0, 5000, 0, 0, 0);
        payoff = addCP(0, 0, 0, 0, 0, 0);

        libCreature = new Creature("Red Bull", 5100, 100, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 4
        ability = new Ability(Trigger.NOATTACK, new Effect(2, EffectType.DRAW));
        cost = addCP(0, 450, 0, 0, 0, 0);
        payoff = addCP(0, 200, 0, 0, 0, 0);

        libCreature = new Creature("Rumplestiltzkin", 100, 550, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 5
        ability = new Ability(Trigger.PAY, new Effect(2, EffectType.DEACTIVATE));
        cost = addCP(0, 850, 0, 500, 0, 0);
        payoff = addCP(0, 400, 0, 450, 0, 0);

        libCreature = new Creature("American", 500, 1550, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 6
        ability = new Ability(Trigger.SACRIFICE, new Effect(500, EffectType.HEAL));
        cost = addCP(0, 500, 0, 500, 0, 0);
        payoff = addCP(0, 300, 0, 300, 0, 0);

        libCreature = new Creature("Eagle", 1200, 500, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 7
        ability = new Ability(Trigger.NOATTACK, new Effect(2, EffectType.DISCARD));
        cost = addCP(0, 750, 0, 0, 0, 0);
        payoff = addCP(0, 350, 0, 0, 0, 0);

        libCreature = new Creature("Xat", 200, 400, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 8
        ability = new Ability(Trigger.PAY, new Effect(3, EffectType.DRAW));
        cost = addCP(50, 0, 0, 500, 0, 0);
        payoff = addCP(0, 0, 0, 250, 0, 0);

        libCreature = new Creature("Gregory", 200, 100, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 9
        ability = new Ability(Trigger.SACRIFICE, new Effect(2, EffectType.DISCARD));
        cost = addCP(50, 0, 0, 850, 0, 0);
        payoff = addCP(0, 0, 0, 350, 0, 0);

        libCreature = new Creature("Victory", 100, 100, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 10
        ability = new Ability(Trigger.NOATTACK, new Effect(1, EffectType.DRAW));
        cost = addCP(50, 150, 0, 300, 0, 0);
        payoff = addCP(0, 100, 0, 150, 0, 0);

        libCreature = new Creature("Harvardian", 500, 650, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 11
        ability = new Ability(null, null);
        cost = addCP(400, 400, 0, 400, 0, 0);
        payoff = addCP(0, 400, 0, 400, 0, 0);

        libCreature = new Creature("Dictory", 1000, 1500, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 12
        ability = new Ability(null, null);
        cost = addCP(400, 50, 0, 300, 0, 0);
        payoff = addCP(0, 50, 0, 350, 0, 0);

        libCreature = new Creature("Waldo", 1000, 1050, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 13
        ability = new Ability(Trigger.SACRIFICE, new Effect(2, EffectType.DESTROY));
        cost = addCP(400, 0, 0, 650, 0, 0);
        payoff = addCP(0, 0, 0, 500, 0, 0);

        libCreature = new Creature("Giddy", 100, 400, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 14
        ability = new Ability(null, null);
        cost = addCP(650, 150, 0, 650, 0, 0);
        payoff = addCP(0, 300, 0, 450, 0, 0);

        libCreature = new Creature("Nerd", 100, 1750, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Creature 15
        ability = new Ability(Trigger.PAY, new Effect(250, EffectType.DEALDAMAGE));
        cost = addCP(3000, 3000, 0, 3000, 0, 0);
        payoff = addCP(0, 0, 0, 0, 0, 0);

        libCreature = new Creature("KickAss", 1000, 9300, ability, cost, payoff);
        libCreature.setCV(17);

        collection.addCard(libCreature);

        // Create Spell 1
        cost = addCP(3000, 3000, 0, 3000, 0, 0);
        effect = new Effect(4500, EffectType.DEALDAMAGE);

        libSpell = new Spell("Epic", effect, cost);
        collection.addCard(libSpell);

        // Create Spell 2
        cost = addCP(300, 0, 0, 600, 0, 0);
        effect = new Effect(1, EffectType.DESTROY);

        libSpell = new Spell("Death Rain", effect, cost);
        collection.addCard(libSpell);

        // Create Spell 3
        cost = addCP(300, 250, 0, 0, 0, 0);
        effect = new Effect(2750, EffectType.HEAL);

        libSpell = new Spell("Life Rain", effect, cost);
        collection.addCard(libSpell);

        // Create Spell 4
        cost = addCP(800, 0, 0, 500, 0, 0);
        effect = new Effect(2, EffectType.DEACTIVATE);

        libSpell = new Spell("Paralysis Rain", effect, cost);
        collection.addCard(libSpell);

        // Create Spell 5
        cost = addCP(500, 0, 0, 1200, 0, 0);
        effect = new Effect(3, EffectType.DRAW);

        libSpell = new Spell("Card Rain", effect, cost);
        collection.addCard(libSpell);

        // Create Spell 6
        cost = addCP(200, 550, 0, 50, 0, 0);
        effect = new Effect(3, EffectType.DISCARD);

        libSpell = new Spell("Forest", effect, cost);
        collection.addCard(libSpell);

        // Create Spell 7
        cost = addCP(200, 50, 0, 850, 0, 0);
        effect = new Effect(3, EffectType.DRAW);

        libSpell = new Spell("Blue Sky", effect, cost);
        collection.addCard(libSpell);

        // Create Spell 8
        cost = addCP(0, 50, 0, 500, 0, 0);
        effect = new Effect(1, EffectType.DEACTIVATE);

        libSpell = new Spell("Blackness", effect, cost);
        collection.addCard(libSpell);

        // Create Spell 9
        cost = addCP(100, 950, 0, 0, 0, 0);
        effect = new Effect(3, EffectType.DRAW);

        libSpell = new Spell("Brightness", effect, cost);
        collection.addCard(libSpell);

        // Create Spell 10
        cost = addCP(50, 850, 0, 450, 0, 0);
        effect = new Effect(400, EffectType.DEALDAMAGE);

        libSpell = new Spell("Italian", effect, cost);
        collection.addCard(libSpell);

    }

    private void makeDefaultDeck() {
        createDeck("default");
        for (int i = 0; i < collection.size(); i++) {
            for (int j = 0; j < 4; j++) {
                addCardToDeck(collection.getCard(i), "default");
            }
        }
    }

    public static void main() {
        User kadriuser = new User("Kadriuser");
        System.out.println(kadriuser.getAccountName());

        for (int i = 0; i < 50; i++) {
            System.out.println(kadriuser.getDeck("default").getCard(i).getName());
        }

    }
}
