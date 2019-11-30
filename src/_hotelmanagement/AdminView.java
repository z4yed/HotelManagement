package _hotelmanagement;

import static _hotelmanagement.Action.con;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author zayed
 */
public class AdminView extends Application{
    
    static Connection conn;
    
    public void start(Stage primaryStage)
    {
        
        Button addRoom = new Button();
        addRoom.setText(" Add  Room  ");
        
        Button roomInfo = new Button();
        roomInfo.setText("Room Info ");
        
        Button booked = new Button();
        booked.setText("Booked Room");
        
        Button bill = new Button();
        bill.setText("Billing Info");
        
        Button back = new Button();
        back.setText("Back");
        
        Button requests = new Button();
        requests.setText("Booking   Requests  ");
        
        Button available = new Button();
        available.setText("Change Room Availability");
        
        Button info = new Button();
        info.setText("Transactions Info");
        
        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxHeight(350);
        grid.setMaxWidth(500);
        grid.setMinHeight(350);
        grid.setMinWidth(500);
        
        grid.add(addRoom, 1, 1);
        grid.add(roomInfo, 2, 1);
        grid.add(booked, 1, 2);
        grid.add(bill, 2, 2);
        grid.add(requests, 1, 3);
        grid.add(available, 2, 3);
        
        grid.add(info, 1, 4);
        
        grid.add(back, 1, 6);
        
        addRoom.setOnAction(value -> {
        
            Stage third = new Stage();
            AddRoom ar = new AddRoom();
            
            ar.start(third);
                   
        });
        
        roomInfo.setOnAction(Value ->{
        
            conn = DBConn.connect();
            
            if(conn != null)
            {
                try
                {
                    Statement stat = conn.createStatement();

                    String query = "Select * From RoomList";

                    ResultSet rst = stat.executeQuery(query);
                    
                    TextArea ta = new TextArea();
                    Button exit = new Button("Exit");
                    
                    
                    Stage four = new Stage();
                    
                    
                    VBox layout = new VBox();
                    layout.setSpacing(5);
                    layout.setAlignment(Pos.CENTER);
                    
                    layout.getChildren().addAll(ta, exit);
                    
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
                    
                    exit.setOnAction(value -> {
                        
                        four.close();
                        System.out.println("Window Closed.....");
                    });
                    
                    
                    
                    Scene scene2 = new Scene(layout, 800, 400);
                    
                    
                    four.setTitle("::::::Details::::");
                    four.setScene(scene2);
                    four.show();
                    
                    
                }
                catch(Exception e)
                {
                    System.out.println("Check Admin View...");
                    System.out.println(e);
                }                
            }
        
        });
        
       booked.setOnAction(Value ->{
        
            conn = DBConn.connect();
            
            if(conn != null)
            {
                try
                {
                    Statement stat = conn.createStatement();

                    String query = "Select * From Submissions where Flag = 1";

                    ResultSet rst = stat.executeQuery(query);
                    
                    TextArea ta = new TextArea();
                    Button exit = new Button("Exit");
                    
                    
                    Stage four = new Stage();
                    
                    
                    VBox layout = new VBox();
                    layout.setSpacing(5);
                    layout.setAlignment(Pos.CENTER);
                    
                    layout.getChildren().addAll(ta, exit);
                    
                    if(rst != null)
                    {
                        
                        String output = "RoomNo     -----       Name        -----       Phone       -----       NID     -----       DaysNo      -----       Costs       -----       PayMethod       -----       TxID        -----       Status\n";
                        while(rst.next())
                        {
                            output += rst.getInt(1)+"         |               "+rst.getString(2)+"     |   "+ rst.getString(3)+"   |   "+rst.getString(4)+"  | "+rst.getInt(5)+"              |             "+rst.getDouble(6)+"       |          "+rst.getString(7)+"             |             "+rst.getString(8)+"         |          "+rst.getInt(9)+"\n";
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
                    
                    exit.setOnAction(value -> {
                        
                        four.close();
                        System.out.println("Window Closed.....");
                    });
                    
                    
                    
                    Scene scene2 = new Scene(layout, 1100, 400);
                    
                    
                    four.setTitle("::::::Details::::");
                    four.setScene(scene2);
                    four.show();
                    
                    
                }
                catch(Exception e)
                {
                    System.out.println("Check Admin View...");
                    System.out.println(e);
                }               
            }
        
        });
       
       info.setOnAction(Value -> {
           
            Stage extra = new Stage();
           
            Text txt = new Text("Flag 0 : Users Requests\tFlag 1: Room that is already Booked\tFlag 2 : Room that Previously Booked But Currently Available\tFlag 9: Request Deleted By Admin");
            TextArea ta = new TextArea();
           
            Button exit = new Button("Exit");
            
            
            VBox layout = new VBox();
            layout.setSpacing(15);
            layout.setAlignment(Pos.CENTER);

            layout.getChildren().addAll(txt, ta, exit);
            
             exit.setOnAction(value -> {        
                extra.close();                
              });


            try
            {

                con = DBConn.connect();
                Statement stat = con.createStatement();
                ResultSet rst = stat.executeQuery("Select * From Submissions");
                String output = "TxID     -----       Name        -----       Phone       -----       NID     -----       DaysNo      -----       Costs       -----       PayMethod       -----       RoomNo            -----           Flag\n";
                while(rst.next())
                {
                    output += rst.getString(8)+"         |               "+rst.getString(2)+"     |   "+ rst.getString(3)+"   |   "+rst.getString(4)+"  | "+rst.getInt(5)+"              |             "+rst.getDouble(6)+"       |          "+rst.getString(7)+"             |             "+rst.getInt(1)+"           -----           "+rst.getInt(9)+"\n";
                }
                ta.setText(output);
                con.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            Scene scene5 = new Scene(layout, 1200, 450);
            
            extra.setTitle("Transactions.....");
            extra.setScene(scene5);
            extra.show();    

       });
       
       
       
       requests.setOnAction(Value -> {
       
           Action ad = new Action();
           Stage stage5 = new Stage();
           
           ad.start(stage5);
           
           primaryStage.close();
       
       });
       
       bill.setOnAction(Value -> {
       
           BillingInfo bi = new BillingInfo();
           Stage hidden = new Stage();
           bi.start(hidden);
           
           primaryStage.close();
           
       });
       
       available.setOnAction(Value -> {
       
           ChangeStatus cs = new ChangeStatus();
           Stage world = new Stage();
           cs.start(world);
           
           primaryStage.close();
       });
        
        
        back.setOnAction(Value -> {
        
            
            Stage secondaryStage = new Stage();
            AdminLogin al = new AdminLogin();
            
            al.start(secondaryStage);
            
            System.out.println("Previous Window Closed Successfully.");
            primaryStage.close();
        
        });
        
        
        Scene scene = new Scene(grid);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin View");
        primaryStage.show();
    }
    
}