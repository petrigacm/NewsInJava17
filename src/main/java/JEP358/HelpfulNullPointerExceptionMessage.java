package JEP358;

public class HelpfulNullPointerExceptionMessage {
    static class Person {
        public String name;
    }

    public static void main(String[] args) {
        Person person = null;
        System.out.println(person.name.length());
    }
}
