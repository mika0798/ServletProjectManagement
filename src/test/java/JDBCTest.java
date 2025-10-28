import com.project.config.DatabaseConnection;
import org.junit.Test;

import java.sql.Connection;

public class JDBCTest {
    @Test
    public void ConnectionTest() {
        Connection testConnection = DatabaseConnection.getConnection();
    }

}
