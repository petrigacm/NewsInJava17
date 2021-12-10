package JEP415;

import JEP415.data.DdosExample;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JEP290 {

    public static void main(String[] args) throws IOException {

        byte[] bytes = convertObjectToStream(new DdosExample());
        InputStream is = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(is);

        // Setting a Custom Filter Using a Patternneed full package path
        // the maximum number of bytes in the input stream = 1024
        // allows classes in JEP415.data.*
        // allows classes in the java.base module
        // rejects all other classes !*
        ObjectInputFilter filter1 = ObjectInputFilter.Config.createFilter(
                "maxbytes=1024;JEP415.data.*;java.base/*;!*"
//                "maxbytes=1024;!JEP415.data.*;java.base/*;!*" // This pattern will REJECT all classes in JEP415.data.* java.io.InvalidClassException: filter status: REJECTED
        );
        ois.setObjectInputFilter(filter1);

        try {
            Object obj = ois.readObject();
            System.out.println("Read obj: " + obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static byte[] convertObjectToStream(Object obj) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
    }
}
