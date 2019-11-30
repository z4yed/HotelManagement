
package _hotelmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
/**
 *
 * @author zayed
 */
public class AddRoom extends Application{
    
    static Connection conn;
    
    public void start(Stage primaryStage)
    {
        
        Text num = new Text("Room No. : ");
        TextField tf1 = new TextField();
        
        Text type = new Text("Type : ");
        
        ComboBox combo = new ComboBox();
        combo.getItems().addAll("Single", "Couple", "Family");
        combo.setPromptText("SELECT");
        
        Text category = new Text();
        category.setText("Category : ");
        
        ComboBox combo2 = new ComboBox();
        combo2.getItems().addAll(".    AC   .", ".Non AC.");
        combo2.setPromptText("SELECT");
        
        Text cpd = new Text("Cost/Day :");
        TextField tf2 = new TextField();
        
        Text nb = new Text("Bed(s) No. : ");
        TextField tf3 = new TextField();
        
        Text ma = new Text("Max Allow : ");
        TextField tf4 = new TextField();
        
        Text status = new Text("Status : ");
        ComboBox combo3 = new ComboBox();
        combo3.getItems().addAll("0", "1");
        combo3.setPromptText("SELECT");
        
        Button btn = new Button("Add Room");
        Button btn2 = new Button("Cancel");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        //grid.setMaxHeight(400);
        grid.setMaxWidth(700);
        grid.setMinHeight(450);
        grid.setMinWidth(700);
        
        grid.add(num, 2, 3);
        grid.add(tf1, 3, 3);
        grid.add(type, 2, 4);
        grid.add(combo, 3, 4);
        grid.add(category, 2, 5);
        grid.add(combo2, 3, 5);
        grid.add(cpd, 2, 6);
        grid.add(tf2, 3, 6);
        grid.add(nb, 2, 7);
        grid.add(tf3, 3, 7);
        grid.add(ma, 2, 8);
        grid.add(tf4, 3, 8);
        grid.add(status, 2, 9);
        grid.add(combo3, 3, 9);
        grid.add(btn, 2, 12);
        grid.add(btn2, 4, 12);
       
        
        btn.setOnAction(value -> {
        
            conn = DBConn.connect();
            int rno = Integer.parseInt(tf1.getText());
            String typ = (String) combo.getValue();
            String cat = (String) combo2.getValue();
            int bedNum = Integer.parseInt(tf2.getText());
            double costpday = Double.parseDouble(tf3.getText());
            int max_allow = Integer.parseInt(tf4.getText());
            int stt = Integer.parseInt((String) combo3.getValue());
            System.out.println("Conversion Success..");
            PreparedStatement pds = null;
            Statement stat = null;
            
            try
            {
                String query = "INSERT INTO RoomList(RoomNo, Type, Category, CostPerDay, NumberOfBeds, MaximumAllow, Status) VALUES(?,?,?,?,?,?,?)";
                pds = conn.prepareStatement(query);
                
                pds.setInt(1, rno);
                pds.setString(2, typ);
                pds.setString(3, cat);
                pds.setInt(4, bedNum);
                pds.setDouble(5, costpday);
                pds.setInt(6, max_allow);
                pds.setInt(7, stt);
                
                pds.executeUpdate();
                System.out.println("Insertion Success....");
                
                pds.close();
                conn.close();
                System.out.println("Connection Closed..");
                primaryStage.close();
                    
                
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("Success");
                a1.setContentText("Room Successfully Added...");
                a1.showAndWait();
                
                primaryStage.close();
                
            }
            catch(SQLException e)
            {
                System.out.println("SQL Error.");
                System.out.println(e);
            }
            
        
        });
        
        
        
        btn2.setOnAction(value -> {
            primaryStage.close();       
        });
        
        
        
        
        Scene scene = new Scene(grid);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Form");
        primaryStage.show();
        
        
    }
    
}
