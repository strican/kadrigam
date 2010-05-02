
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Unserialize {

    public static void main(String[] args) {
        /*
        String filename = "gordon.ser";

        if (args.length > 0) {
        filename = args[0];
        }
        User test = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
        fis = new FileInputStream(filename);
        in = new ObjectInputStream(fis);
        test = (User) in.readObject();
        in.close();
        } catch (IOException ex) {
        ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
        }


        for (int i = 0; i < 25; i++) {

        Card card = test.getLibrary().getCard(i);
        if (card instanceof Creature) {
        System.out.println(((Creature) card).getName());
        System.out.println(((Creature) card).getPow());
        System.out.println(((Creature) card).getHP());
        } else {
        System.out.println(((Spell) card).getName());
        }
        }

        for (int i = 0; i < 100; i++) {
        System.out.println(i + test.getDeckList().getDeck("default").getCard(i).getName());
        }

         */
        Login l = new Login();
        User user = l.getUsername();

        for (int i = 0; i < 25; i++) {

            Card card = user.getLibrary().getCard(i);
            if (card instanceof Creature) {
                System.out.println(((Creature) card).getName());
                System.out.println(((Creature) card).getPow());
                System.out.println(((Creature) card).getHP());
            } else {
                System.out.println(((Spell) card).getName());
            }
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(i + user.getDeckList().getDeck("default").getCard(i).getName());
        }


    }
}
