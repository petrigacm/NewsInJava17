package JEP406;

import org.junit.jupiter.api.Test;

public class Refine {
    class Shape {
    }

    class Rectangle extends Shape {
    }

    class Triangle extends Shape {
        int calculateArea() {
            return 1000;
        }
    }

    @Test
    public void test() {
        testTriangle(new Triangle());
        testTriangle2(new Triangle());
    }

    static void testTriangle(Shape s) {
        switch (s) {
            case null:
                break;
            case Triangle t:
                if (t.calculateArea() > 100) {
                    System.out.println("Large triangle");
                    break;
                } else {
                    System.out.println("Triangle");
                }
            default:
                System.out.println("Unknown!");
        }
    }

    static void testTriangle2(Shape s) {
        switch (s) {
            case null -> {
            }
            // so-called redefined patterns or guarded patterns
            case Triangle t && (t.calculateArea() > 100) -> System.out.println("Large triangle");
            case Triangle t -> System.out.println("Triangle");
            default -> System.out.println("Unknown!");
        }
    }

}
