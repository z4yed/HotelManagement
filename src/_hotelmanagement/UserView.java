package _hotelmanagement;
import static _hotelmanagement.AdminView.conn;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserView extends Application{


    @Override
    public void start(Stage  primaryStage){
        
        
        Button btn1 = new Button("Show Available Room");
        Button status = new Button("My Status");
        Button btn2 = new Button("Back");

        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxHeight(350);
        grid.setMaxWidth(500);
        grid.setMinHeight(350);
        grid.setMinWidth(500);
        
        
        grid.add(btn1, 0, 0);
        grid.add(status, 0, 1);
        grid.add(btn2, 0, 3);
        
        btn1.setOnAction(Value ->{
        
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
                    
                    book.setOnAction(value -> {
                        
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
                    
                    exit.setOnAction(value -> {
                        
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
        
        });
        
        status.setOnAction(Value -> {
        
            CheckStatus cs = new CheckStatus();
            Stage stage9 = new Stage();
            cs.start(stage9);
            
            primaryStage.close();
            
        });
        
        btn2.setOnAction(Value ->{
        
            _HotelManagement hmg = new _HotelManagement();
            Stage stage2 = new Stage();
            hmg.start(stage2);
            
            primaryStage.close();
        
        });
        
        
        Scene scene = new Scene(grid);
        
        primaryStage.setTitle("Users Options");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }    
}
