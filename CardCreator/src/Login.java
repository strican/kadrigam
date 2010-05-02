
import java.io.*;

public class Login implements Loginable {

    String username;

    public User getUsername() {
        System.out.println("User: ");
        BufferedReader loginMan = new BufferedReader(new InputStreamReader(System.in));

        try {
            username = loginMan.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your name!");
            System.exit(1);
        }


        String filename = username + ".ser";

        User user = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            user = (User) in.readObject();
            in.close();
        } catch (IOException readEx) {
            serialize (new User(username), username);

            //readEx.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return user;

    }

    // serialize the user
    public boolean serialize(User u, String s) {

        String filename = s + ".ser";

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(u);
            out.close();
        } catch (IOException writeEx) {
            writeEx.printStackTrace();
            return false;
        }
        return true;
    }
}
