
package _hotelmanagement;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;

/**
 *
 * @author zayed
 */
public class DBConn {
    
    static Connection con;
    static Connection connect()
    {
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagement", "root", "");
            if(con != null)
            {
                System.out.println("Project Successfully connected with Database.");
            }
            
        }
        catch(Exception e)
        {
            
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setTitle("Error");
            a1.setContentText("Connection Error...\nPossible Fix : Start MySQL server");
            a1.showAndWait();
            
        }
        
        
        return con;
    }
    
    
    
}
