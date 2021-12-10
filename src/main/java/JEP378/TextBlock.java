package JEP378;

public class TextBlock {

    private static final String BLOCK = """
            I am first line.
            I am second line.
            I am third line.  
            """;

    public static void main(String[] args) {
        System.out.println(BLOCK);
    }
}
