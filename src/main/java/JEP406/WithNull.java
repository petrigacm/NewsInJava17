package JEP406;

public class WithNull {

    public static void main(String[] args) {
        testString("Java 16");  // Ok
        testString("Java 11");  // LTS
        testString("");         // Ok
        testString(null);       // Unknown!

        testStringJava17("Java 16");  // Ok
        testStringJava17("Java 11");  // LTS
        testStringJava17("");         // Ok
        testStringJava17(null);       // Unknown!
    }

    static void testString(String s) {
        if (s == null) {
            System.out.println("Unknown!");
            return;
        }
        switch (s) {
            case "Java 11", "Java 17" -> System.out.println("LTS");
            default -> System.out.println("Ok");
        }
    }

    static void testStringJava17(String s) {
        switch (s) {
            case null -> System.out.println("Unknown!");
            case "Java 11", "Java 17" -> System.out.println("LTS");
            default -> System.out.println("Ok");
        }
    }
}
