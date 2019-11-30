package _hotelmanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Action extends Application{
    
    static Connection con;

    @Override
    public void start(Stage stage){
        
        VBox layout = new VBox();
        
        TextArea ta = new TextArea();
        
        try
        {
            
            con = DBConn.connect();
            Statement stat = con.createStatement();
            ResultSet rst = stat.executeQuery("Select * From Submissions where Flag = 0");
            String output = "RoomNo     -----       Name        -----       Phone       -----       NID     -----       DaysNo      -----       Costs       -----       PayMethod       -----       TxID\n";
            while(rst.next())
            {
                output += rst.getInt(1)+"         |               "+rst.getString(2)+"     |   "+ rst.getString(3)+"   |   "+rst.getString(4)+"  | "+rst.getInt(5)+"              |             "+rst.getDouble(6)+"       |          "+rst.getString(7)+"             |             "+rst.getString(8)+"\n";
            }
            ta.setText(output);
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
        Button action = new Button("Take Action");
        Button back = new Button("Back");
        
        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(ta, action, back);
        
        action.setOnAction(Value -> {
        
            ApproveOrDelete ad = new ApproveOrDelete();
            Stage stage6 = new Stage();
            ad.start(stage6);
            stage.close();
        
        });
        
        
        back.setOnAction(Value -> {
        
            AdminView av = new AdminView();
            Stage stage2 = new Stage();
            av.start(stage2);
            
            stage.close();
            
        });
        
        Scene scene = new Scene(layout, 1000, 450);
        
        stage.setTitle(":::::Requests:::::::");
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
}
