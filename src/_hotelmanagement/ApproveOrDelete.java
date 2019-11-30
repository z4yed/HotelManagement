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

public class ApproveOrDelete extends Application{
    
    static Connection con;

    @Override
    public void start(Stage stage){
       
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        //grid.setMaxHeight(400);
        grid.setMaxWidth(700);
        grid.setMinHeight(450);
        grid.setMinWidth(700);
        
        Button approve = new Button();
        approve.setText("Approve");
        
        Button delete = new Button();
        delete.setText("Delete");
        
        Button back = new Button("Back");
        
        Text txt = new Text("Select Room No. : ");
        TextField tf = new TextField();
        
        Text tx = new Text("Transaction ID : ");
        TextField tf1 = new TextField();
        
        grid.add(txt, 0, 0);
        grid.add(tf, 1, 0);
        grid.add(tx, 0, 1);
        grid.add(tf1, 1, 1);
        grid.add(approve, 0, 3);
        grid.add(delete, 1, 3);
        grid.add(back, 0, 6);

        
        back.setOnAction(Value -> {
        
            Action ac = new Action();
            Stage stage7 = new Stage();
            ac.start(stage7);
            
            stage.close();
        
        });
        
        approve.setOnAction(Value -> {
        
            con = DBConn.connect();
            int rNo = Integer.parseInt(tf.getText());
            System.out.println("Conversion Successfull....");
            
            String txID = tf1.getText();
            
            PreparedStatement  pds = null;
            try
            {
                String query = "Update RoomList set Status = 1 where RoomNo = ?";   
                pds = con.prepareStatement(query);
                pds.setInt(1, rNo);
                pds.executeUpdate();
                pds.close();
                
                
                String query2 = "Update Submissions set Flag = 1 where RoomNo = ? AND TxID = ?";
                pds = con.prepareStatement(query2);
                pds.setInt(1, rNo);
                pds.setString(2, txID);
                pds.executeUpdate();
                
                pds.close();
                con.close();
                
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success");
                a.setContentText("Room Booked Successfully....");
                a.showAndWait();
                
               Action av= new Action();
               Stage stage8 = new Stage();
               av.start(stage8);
                
               stage.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            
        });
        delete.setOnAction(Value -> {
        
            con = DBConn.connect();
            int rNo = Integer.parseInt(tf.getText());
            System.out.println("Conversion Successfull....");
            try
            {
            
                String query = "Update Submissions set Flag = 9 where RoomNo = ?";
                PreparedStatement pds = con.prepareStatement(query);
                pds.setInt(1, rNo);
                pds.executeUpdate();
                
                pds.close();
                con.close();
                
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success");
                a.setContentText("Request Deleted Successfully.");
                a.showAndWait();
                
               Action av= new Action();
               Stage stage8 = new Stage();
               av.start(stage8);
                
               stage.close();
                   
                
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
        
        });
        
        
        Scene scene = new Scene(grid);
        
        stage.setTitle(":::::Action:::::::");
        stage.setScene(scene);
        stage.show();
        
    }
    
}
