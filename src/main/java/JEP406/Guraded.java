package JEP406;


public class Guraded {
    static class Shape {
    }

    static class Rectangle extends Shape {
    }

    static class Triangle extends Shape {
        int calculateArea() {
            return 1000;
        }
    }

    public static void main(String[] args) {
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
            case Triangle ignored -> System.out.println("Triangle");
            default -> System.out.println("Unknown!");
        }
    }

}
