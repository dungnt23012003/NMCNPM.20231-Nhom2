package src.main.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {
    public static Connection connect_to_sql_server() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://LAPTOP-OBVO3M5Q\\CSDL:1433;databaseName=QUAN_LY_NHAN_KHAU;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        String url = "jdbc:sqlserver://;serverName=::1;port=1433;databaseName=QUAN_LY_NHAN_KHAU;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        String username = "nmcnpm_user";
        String password = "nmcnpm2023";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
