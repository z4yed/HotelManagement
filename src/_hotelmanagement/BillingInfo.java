package _hotelmanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author zayed
 */
public class BillingInfo extends Application{
    
    static Connection con;
    @Override
    public void start(Stage stage) {
        
        TextArea ta = new TextArea();
        Button exit = new Button("Exit");
        
        VBox layout = new VBox();
        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(ta, exit);
        
        con = DBConn.connect();
        PreparedStatement pds = null;
        ResultSet rst = null;
        try
        {
            String query = "Select * from Submissions where Flag = 1 OR Flag = 2";
            pds = con.prepareStatement(query);
            rst = pds.executeQuery();
            
            String output = "RoomNo -----  PayMethod    ----    TxID    ----    Costs\n";
            while(rst.next())
            {
                output += rst.getInt("RoomNo") + "           |   "+ rst.getString("PayMethod")+"              |      "+rst.getString("TxID")+ "     |       "+ rst.getDouble("Costs")+"\n";
            }
            ta.setText(output);
            
            pds.close();
            con.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
        
        exit.setOnAction(Value -> {
        
            AdminView av = new AdminView();
            Stage hello = new Stage();            
            av.start(hello);
            
            stage.close();
            
        });
        
        Scene scene = new Scene(layout, 600, 350);
        stage.setTitle(":::Billing Info:::::");
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
