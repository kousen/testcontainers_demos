import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class OracleJDBCDriverTest {

    @Test
    public void testOracleWithNoSpecifiedVersion() throws SQLException {
        performSimpleTest("jdbc:tc:oracle://hostname/databasename");
    }

    @SuppressWarnings("SameParameterValue")
    private void performSimpleTest(String jdbcUrl) throws SQLException {
        HikariDataSource dataSource = getDataSource(jdbcUrl, 1);
        new QueryRunner(dataSource)
                .query(
                        "SELECT 1 FROM dual",
                        (ResultSetHandler<Object>) rs -> {
                            rs.next();
                            int resultSetInt = rs.getInt(1);
                            assertThat(resultSetInt)
                                    .as("A basic SELECT query succeeds")
                                    .isEqualTo(1);
                            return true;
                        }
                );
        dataSource.close();
    }

    @SuppressWarnings("SameParameterValue")
    private HikariDataSource getDataSource(String jdbcUrl, int poolSize) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setConnectionTestQuery("SELECT 1 FROM dual");
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setMaximumPoolSize(poolSize);

        return new HikariDataSource(hikariConfig);
    }
}