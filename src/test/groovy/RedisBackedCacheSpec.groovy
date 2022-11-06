import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
class RedisBackedCacheSpec extends Specification {
    @Shared
    private RedisBackedCache underTest

    @Container
    static GenericContainer redis = new GenericContainer<>("redis:5.0.3-alpine")
            .withExposedPorts(6379)

    void setupSpec() {
        redis.start()
        String address = redis.host
        Integer port = redis.firstMappedPort

        underTest = new RedisBackedCache(address, port)
    }

    void cleanupSpec() {
        redis.stop()
    }

    void testSimplePutAndGet() {
        setup:
        underTest.put("test", "example")

        when:
        String retrieved = underTest.get("test")

        then:
        retrieved == "example"
    }
}