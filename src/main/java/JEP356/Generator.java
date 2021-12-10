package JEP356;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class Generator {
    public static void main(String[] args) {
        RandomGeneratorFactory<RandomGenerator> factory = RandomGeneratorFactory.of("Random");
        RandomGenerator generator = factory.create();
        System.out.println(generator.nextDouble());
    }
}

