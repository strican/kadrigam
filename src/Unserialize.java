import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Unserialize {

    public static void main(String[] args) {
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
            test = (User)in.readObject();
            in.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            
        }

        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < 50; i++){
            System.out.println(test.getDeck("default").getCard(i).getName());
        }
    }
}
