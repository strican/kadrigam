
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Serialize {

    public static void main(String[] args) {
        String filename = "gordon.ser";

        if (args.length > 0) {
            filename = args[0];
        }

        User test = new User("gordon");
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(test);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
}
