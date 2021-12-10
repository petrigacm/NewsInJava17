package JEP395;

public class PersonRecord {

    record Person(String name, int age) {
    }

    public static void main(String[] args) {
        Person person = new Person("Karl", 23);
        System.out.println(person.name());
        System.out.println(person.age());
    }
}

