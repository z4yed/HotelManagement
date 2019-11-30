
package _hotelmanagement;
import static _hotelmanagement.AdminView.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class BookingForm extends Application {

    static Connection con;
    
    @Override
    public void start(Stage stage){
        
        GridPane grid = new GridPane();
        
        Text rnum = new Text("Select RoomNo : ");
        TextField tf = new TextField();
        
        Text name = new Text("Name : ");
        TextField tf1 = new TextField();
        tf1.setPromptText("@Prefer 10-14 Character ");
        
        Text phone = new Text("Phone : ");
        TextField tf2 = new TextField();
        tf2.setPromptText("@Prefer 11 Character");
        
        Text nid = new Text("NID : ");
        TextField tf3 = new TextField();
        tf3.setPromptText("@Prefer 10 digit");
        
        Text dayNum = new Text("Number of Days : ");
        TextField day = new TextField();
        day.setPromptText("e.g. 1, 2, 3, 4 ...9 ");
        
        Text txt1 = new Text("Amount to pay : ");
        Text txt2 = new Text("");
        
        Text procedure = new Text("Payment Method : ");
        
        Button calculate = new Button("Calculate Cost");
        
        ComboBox combo = new ComboBox();
        combo.getItems().addAll("bKash" , "Rocket");
        combo.setPromptText("SELECT");
        
        Text trans = new Text("TX ID : ");
        TextField tx = new TextField();
        
        Button payProcedure = new Button("Show Payment Procedure");
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        Button back = new Button("Back");
        
        
        calculate.setOnAction(Value -> {      
                          
             int roomNo = Integer.parseInt(tf.getText()) ;
             int dayNo = Integer.parseInt(day.getText());
             double cost = 0;
             
             con = DBConn.connect();
             PreparedStatement pds = null;
             ResultSet rst = null;
             try
             {
                 String query = "Select * from RoomList where RoomNo = ?;";
                 pds = con.prepareStatement(query);
                 pds.setInt(1, roomNo);
                 rst = pds.executeQuery();
                 
                 while(rst.next()) 
                 {
                     cost = rst.getDouble(4);
                     cost = cost * dayNo;
                     txt2.setText(""+cost);
                     break;
                 }   
                 con.close();
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
        });
        
        cancel.setOnAction(Value -> {
        
            System.out.println("Booking Cancelled.");
            stage.close();
        
        });
        
        payProcedure.setOnAction(Value -> {
        
            String cmb = (String) combo.getValue();
            if(cmb == "bKash")
            {
                    
                Alert a2 = new Alert(Alert.AlertType.INFORMATION);
                a2.setTitle("Cash er Ceye bKash Valo");
                a2.setContentText("1. Dial *247#\n2. Send Money to '017********'(agent)\n3. You will get Transaction ID through an SMS\n4. Copy it and Paste it in TX ID  Field");
                a2.setHeaderText(null);
                        
                a2.showAndWait();
                
            }
            else if(cmb == "Rocket")
            {
                Alert a2 = new Alert(Alert.AlertType.INFORMATION);
                a2.setTitle("Takar Rocker!!!!!!!");
                a2.setContentText("1. Dial *322#\n2. Send Money to '018********'(agent)\n3. You will get Transaction ID through an SMS\n4. Copy it and Paste it in TX ID  Field");
                a2.setHeaderText(null);
                        
                a2.showAndWait();
            }
            else
            {
                Alert a2 = new Alert(Alert.AlertType.ERROR);
                a2.setTitle("Error");
                a2.setContentText("Method is not Selected..");
                a2.setHeaderText(null);
                        
                a2.showAndWait();
            }
        });
        
        
                
        back.setOnAction(value -> {
        
            conn = DBConn.connect();
            
            if(conn != null)
            {
                try
                {
                    Statement stat = conn.createStatement();

                    String query = "Select * From RoomList where Status = 0";

                    ResultSet rst = stat.executeQuery(query);
                    
                    TextArea ta = new TextArea();
                    Button book = new Button("Book");
                    Button exit = new Button("Exit");
                    
                    
                    Stage four = new Stage();
                    
                    
                    VBox layout = new VBox();
                    layout.setSpacing(5);
                    layout.setAlignment(Pos.CENTER);
                    
                    book.setOnAction(alue -> {
                        
                        BookingForm bf = new BookingForm();
                        Stage stage5 = new Stage();
                        bf.start(stage5);
                        
                        four.close();
                    
                    });
                    
                    layout.getChildren().addAll(ta, book, exit);
                    
                    if(rst != null)
                    {
                        
                        String output = "Room No.  ---- Type     ----  Category  ---- Cost/Day  ---- Bed(s) No.  ---- MaximumALlow  ---- Status\n";
                        
                        while(rst.next())
                        {
                            
                            output += rst.getInt(1)+"            ---- "+rst.getString(2)+"  ---- "+rst.getString(3)+"    ----  "+rst.getDouble(4)+"     ---- "+rst.getInt(5)+"                ----    "+rst.getInt(6)+"                      ---- "+rst.getInt(7)+"\n";
                            
                        }
                        ta.setText(output);
                        conn.close();
                        
                    }
                    else
                    {
                        Alert a2 = new Alert(Alert.AlertType.ERROR);
                        a2.setTitle("Error");
                        a2.setContentText("Query Error...");
                        a2.setHeaderText(null);
                        
                        a2.showAndWait();
                    }
                    
                    exit.setOnAction(alue -> {
                        
                        four.close();
                        System.out.println("Window Closed.....");
                    });
                    
                    
                    
                    Scene scene2 = new Scene(layout, 800, 400);
                    
                    
                    four.setTitle("::::::Available Room List::::");
                    four.setScene(scene2);
                    four.show();
                    
                    
                }
                catch(Exception e)
                {
                    System.out.println("Check Admin View...");
                    System.out.println(e);
                } 
                
            }
            
            stage.close();
            
        });
        
        submit.setOnAction(Value -> {
        
            con = DBConn.connect();
            
            int rNo = Integer.parseInt(tf.getText());
            String nm = tf1.getText();
            String phn = tf2.getText();
            String nd= tf3.getText();
            int days = Integer.parseInt(day.getText());
            double csts = Double.parseDouble(txt2.getText());
            String method = (String) combo.getValue();
            String txid = tx.getText();
            int flag = 0;
            System.out.println("Conversion Success..");
            
            PreparedStatement pds = null;
            
            try
            {
                String query = "Insert Into Submissions(RoomNo, Name, Phone, NID, DaysNo, Costs, PayMethod, TxID, Flag) VALUES(?,?,?,?,?,?,?,?,?)";
                pds = con.prepareStatement(query);
                
                pds.setInt(1, rNo);
                pds.setString(2, nm);
                pds.setString(3, phn);
                pds.setString(4, nd);
                pds.setInt(5, days);
                pds.setDouble(6, csts);
                pds.setString(7, method);
                pds.setString(8, txid);
                pds.setInt(9, flag);
                
                pds.executeUpdate();
                System.out.println("Insertion Success.....");
                
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Success");
                a.setContentText("Application Submitted to Admin Successfully. \nWait For Confirmation.");
                
                a.showAndWait();
                System.out.println("Connection Closed Successfully.");
                con.close();
                stage.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        });
        
        
        
        
       
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);
        //grid.setMaxHeight(350);
        grid.setMaxWidth(500);
        grid.setMinHeight(600);
        grid.setMinWidth(550);
        
        grid.add(rnum, 0, 0);
        grid.add(tf, 1, 0);
        grid.add(name, 0, 1);
        grid.add(tf1, 1, 1);
        grid.add(phone, 0, 2);
        grid.add(tf2, 1, 2);
        grid.add(nid, 0, 3);
        grid.add(tf3, 1, 3);
        grid.add(dayNum, 0, 4);
        grid.add(day, 1, 4);
        grid.add(calculate, 1, 5);
        grid.add(txt1, 0, 6);
        grid.add(txt2, 1, 6);
        
        grid.add(procedure, 0, 7);
        grid.add(combo, 1, 7);
        grid.add(payProcedure, 2, 7);
        
        grid.add(trans, 0, 8);
        grid.add(tx, 1, 8);
        
        grid.add(back, 0, 10);
        grid.add(submit, 1, 10);
        grid.add(cancel, 2, 10);
        
        
        
        
       
        
        
        
        
        
        
        
        
        Scene scene = new Scene(grid);
        stage.setTitle(":::::::::::Booking Form:::::::");
        stage.setScene(scene);
        stage.show(); 
        
    }
    
}