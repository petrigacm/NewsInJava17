package JEP356;

import org.junit.jupiter.api.Test;

import java.util.random.RandomGenerator;
import java.util.stream.IntStream;
import java.util.random.RandomGeneratorFactory;

public class Generator {

    @Test
    void simpleTest() {
        // List of algorithms https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/random/package-summary.html#algorithms
        RandomGeneratorFactory<RandomGenerator> factory = RandomGeneratorFactory.of("Random");
        RandomGenerator generator = factory.create();
        System.out.println(generator.nextDouble());
    }
}
