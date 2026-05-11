import java.io.*;

class User implements Serializable {
    String name = "Koustav";
}

public class ObjectSaver {
    public static void main(String[] args) throws Exception {
        User u = new User();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.ser"));
        out.writeObject(u);
        System.out.println("User object serialized.");
    }
}