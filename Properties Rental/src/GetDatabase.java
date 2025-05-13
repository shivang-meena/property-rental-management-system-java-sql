import java.sql.Connection;
import java.sql.DriverManager;

public class GetDatabase {
    public static void main(String[] args) {
        Connection con = GetDb();
        if (con != null) {
            System.out.println("Connection established: " + con);
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }

    public static Connection GetDb() {
        try {
      
            Class.forName("com.mysql.cj.jdbc.Driver");

          
            String url = "jdbc:mysql://localhost:3306/property_management?allowPublicKeyRetrieval=true&useSSL=false";
            String user = "root";
            String password = "shiva340";
            Connection con = DriverManager.getConnection(url, user, password);

            return con;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
