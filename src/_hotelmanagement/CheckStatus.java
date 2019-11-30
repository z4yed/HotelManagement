package _hotelmanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class CheckStatus extends Application{
    static Connection con;

    @Override
    public void start(Stage stage){
        
        Text txt1 = new Text("Enter your Transaction ID : ");
        TextField tf1 = new TextField();
        
        Button check = new Button("Check Status");
        Button back = new Button("Back");
        
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        //grid.setMaxHeight(400);
        grid.setMaxWidth(600);
        grid.setMinHeight(450);
        grid.setMinWidth(600);
        
        
        grid.add(txt1, 0, 0);
        grid.add(tf1, 1, 0);
        grid.add(check, 1, 3);
        grid.add(back, 1, 5);
        
        check.setOnAction(Value -> {
        
            con = DBConn.connect();
            String txid = tf1.getText();
            
            PreparedStatement pds = null;
            ResultSet rst = null;
            try
            {
                String query= "Select Flag from Submissions where TxID = ?";
                pds = con.prepareStatement(query);
                pds.setString(1, txid);
                rst = pds.executeQuery();
                int flg = 5;
                
                while(rst.next())
                {
                    flg = rst.getInt("Flag");
                    break;
                }
                if(flg == 1)
                {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Success");
                    a.setContentText("Congratulation!!!!!!!\nYour Request is Accepted.");
                    a.showAndWait();
                }
                if(flg == 9)
                {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Sorry");
                    a.setContentText("Sorry!!!!!\nYour Request has been Rejected.\nPossible Reason : Invalid Transaction ID.. \nContact Us via email : admin@hotelmanagement.org");
                    a.showAndWait();
                }
                if(flg == 2)
                {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Date Expired");
                    a.setContentText("Your date has Expired.\nThank Your For Using Our Service.");
                    a.showAndWait();
                }
                if(flg == 0)
                {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Have Patient");
                    a.setContentText("Your Request is Pending.... Wait For Confirmation");
                    a.showAndWait();
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            } 
            
        });
        
        back.setOnAction(Value -> {
        
            UserView uv = new UserView();
            Stage unknown = new Stage();
            uv.start(unknown);
            
            stage.close();
        
        });
        
        
        Scene scene = new Scene(grid);
        
        stage.setTitle("Status Checker Box");
        stage.setScene(scene);
        stage.show();
    }
    
}
