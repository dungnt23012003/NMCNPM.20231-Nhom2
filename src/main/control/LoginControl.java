package src.main.control;
import src.test.boundary.FeaturedAppTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static src.main.control.ConnectionConfig.connect_to_sql_server;
public class LoginControl {
    public boolean login(String username, String password){
        try{
            Connection connection = connect_to_sql_server();
            Statement statement = connection.createStatement();

            String sql = String.format("exec login_ne @username = N'%s', @pass = N'%s'", username, password);
            System.out.println(sql);
            ResultSet rs  = statement.executeQuery(sql);
            while(rs.next()){
                if(rs.getString(1).equals("True")){
                    System.out.println("True");
                    return true;
                }
                else{
                    System.out.println("False");
                    return false;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        LoginControl loginControl = new LoginControl();

    }
}
