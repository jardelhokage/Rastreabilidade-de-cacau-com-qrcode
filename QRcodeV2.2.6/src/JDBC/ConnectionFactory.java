
package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection conecta(){
        try{
            
            return DriverManager.getConnection("jdbc:mysql://localhost/datalog","root","");
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
