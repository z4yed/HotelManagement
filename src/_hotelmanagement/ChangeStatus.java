
package _hotelmanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author zayed
 */
public class ChangeStatus extends Application{
    static Connection con;
    
    public void start(Stage primaryStage)
    {
        
        Text txt1 = new Text("Enter Booked Room No. : ");
        TextField tf1 = new TextField();
        
        Button change = new Button("Make Available");
        Button exit = new Button("Exit");
        
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        //grid.setMaxHeight(400);
        grid.setMaxWidth(700);
        grid.setMinHeight(450);
        grid.setMinWidth(700);
        
        grid.add(txt1, 0, 0);
        grid.add(tf1, 1, 0);
        grid.add(change, 1, 1);
        grid.add(exit, 1, 4);
        
        change.setOnAction(Value ->{
            
            con = DBConn.connect();
            
            int rNo = Integer.parseInt(tf1.getText());
            PreparedStatement pds = null;
            
            try
            {
                
                String query1 = "Update RoomList set Status = 0 where RoomNo = ?";
                pds = con.prepareStatement(query1);
                pds.setInt(1, rNo);
                pds.executeUpdate();
                pds.close();
                
                
                String query2 = "Update Submissions set Flag = 2 where  RoomNo = ?";
                pds = con.prepareStatement(query2);
                pds.setInt(1, rNo);
                pds.executeUpdate();
                pds.close();
                con.close();
                
                primaryStage.close();
                
                
                
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Successfull");
                a.setContentText("Status Changed Successfully...");
                a.showAndWait();
                
                AdminView av = new AdminView();
                Stage fire = new Stage();
                av.start(fire);  
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        
        });
        
        
        exit.setOnAction(Value -> {
        
                        
            AdminView av = new AdminView();
            Stage stage3 = new Stage();
            av.start(stage3);
            
            primaryStage.close();

            
        });
        
        
        
        Scene scene = new Scene(grid);
        primaryStage.setTitle("Change Status");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
