import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisBackedCache {
    private final JedisPool jedisPool;

    public RedisBackedCache(String address, Integer port) {
        jedisPool = new JedisPool(new JedisPoolConfig(), address, port);
    }

    public boolean isAvailable() {
        try (Jedis jedis = jedisPool.getResource()) {
            return "PONG".equals(jedis.ping());
        } catch (JedisConnectionException e) {
            return false;
        }
    }

    public void put(String test, String example) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(test, example);
        }
    }

    public String get(String test) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(test);
        }
    }
}
