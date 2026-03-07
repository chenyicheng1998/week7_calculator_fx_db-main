import java.sql.*;

public class ResultService {

    private static final String DB_NAME = "calc_data";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Test12";

    // Load MariaDB driver
    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getDatabaseHost() {
        String host = System.getenv("DB_HOST");
        if (host == null || host.isEmpty()) host = "db"; // use Docker service name
        return host;
    }

    private static String getDatabaseUrl() {
        return "jdbc:mariadb://" + getDatabaseHost() + ":3306/" + DB_NAME +
                "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    }

    /**
     * Saves a calculator operation result to the database.
     *
     * @param operation  e.g. "Addition", "Subtraction", "Multiplication", "Division"
     * @param n1         first operand
     * @param n2         second operand
     * @param result     computed result
     */
    public static void saveResult(String operation, double n1, double n2, double result) {
        String dbUrl = getDatabaseUrl();

        try (Connection conn = DriverManager.getConnection(dbUrl, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Create table if it doesn't exist (now includes operation column)
            String createTable = """
                CREATE TABLE IF NOT EXISTS calc_results (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    operation VARCHAR(20) NOT NULL,
                    number1 DOUBLE NOT NULL,
                    number2 DOUBLE NOT NULL,
                    result DOUBLE NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;
            stmt.executeUpdate(createTable);

            // Insert the result
            String insert = "INSERT INTO calc_results (operation, number1, number2, result) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insert)) {
                ps.setString(1, operation);
                ps.setDouble(2, n1);
                ps.setDouble(3, n2);
                ps.setDouble(4, result);
                ps.executeUpdate();
            }

            System.out.println("✅ Result saved: " + operation + "(" + n1 + ", " + n2 + ") = " + result);

        } catch (SQLException e) {
            System.err.println("❌ Failed to save result to DB: " + dbUrl);
            e.printStackTrace();
        }
    }
}

