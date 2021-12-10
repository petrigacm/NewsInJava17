package JEP406;

public class InstanceOf {

    void test1(Object obj) {
        if (obj instanceof Point) {
            Point point = (Point) obj;    // grr...
            point.getX();
            point.getY();
        }
    }


    void test2(Object obj) {
        if (obj instanceof Point point) {
            point.getX();
            point.getY();
        }
    }
}
