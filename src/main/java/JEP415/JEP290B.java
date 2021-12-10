package JEP415;

import JEP415.data.Entity;
import JEP415.data.MyEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


// The below example creates a deserialization filter to reject all classes that extended Entity
public class JEP290B {
    public static void main(String[] args) throws IOException {

        byte[] bytes = convertObjectToStream(new MyEntity());
        InputStream is = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(is);

        ois.setObjectInputFilter(createObjectFilter());

        try {
            Object obj = ois.readObject();
            System.out.println("Read obj: " + obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // reject all JComponent classes
    private static ObjectInputFilter createObjectFilter() {
        // Java 17:
        // return ObjectInputFilter.rejectFilter(Entity.class::isAssignableFrom, ObjectInputFilter.Status.UNDECIDED);

        // Java 9:
        return filterInfo -> {
            Class<?> clazz = filterInfo.serialClass();
            if (clazz != null) {
                return (Entity.class.isAssignableFrom(clazz))
                        ? ObjectInputFilter.Status.REJECTED
                        : ObjectInputFilter.Status.ALLOWED;
            }
            return ObjectInputFilter.Status.UNDECIDED;
        };
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
