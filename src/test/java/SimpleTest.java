import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * NOTE: This tests will NOT run because maven-surefire-plugin does NOT
 * have full support of JUnit 5.
 * <a href="https://stackoverflow.com/questions/36970384/surefire-is-not-picking-up-junit-5-tests">Source</a>
 */
public class SimpleTest {

    @Test
    void testTrue() {
        Assertions.assertTrue(true);
    }

    @Test
    void testFail() {
        Assertions.assertTrue(false);
    }
}
