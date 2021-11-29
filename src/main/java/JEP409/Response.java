package JEP409;

public sealed class Response permits No, Yes {


    public void is(Response e) {
        switch (e) {
            case Yes response -> System.out.println("Yes");
            case No response -> System.out.println("No");
            default -> throw new IllegalStateException("Unexpected value: " + e); // Why is this mandatory? What value could be unexpected?
        }
    }
}
