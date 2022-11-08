import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

// from Testcontainers home page Quickstart
//    https://www.testcontainers.org/quickstart/junit_5_quickstart/
public class RedisBackedCacheIntStep0Test {

    private RedisBackedCache underTest;

    @BeforeEach
    public void setUp() {
        // Assume that we have Redis running locally?
        underTest = new RedisBackedCache("localhost", 6379);
        assumeTrue(underTest.isAvailable());
    }

    @Test
    public void testSimplePutAndGet() {
        underTest.put("test", "example");
        String retrieved = underTest.get("test");
        assertThat(retrieved).isEqualTo("example");
    }
}
