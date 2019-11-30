package _hotelmanagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author zayed
 */
public class _HotelManagement extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        Button admin = new Button();
        admin.setText("Admin");
        Button users = new Button();
        users.setText("Users");
        
        admin.setOnAction(Value -> {
        
            Stage stage2 = new Stage();
            AdminLogin al = new AdminLogin();
            
            al.start(stage2);
            
            System.out.println("First Window Closed....!!!");
            primaryStage.close();
        
        });
        
        users.setOnAction(Value ->{
        
            UserView uv = new UserView();
            Stage stage3 = new Stage();
            
            uv.start(stage3);
            
            primaryStage.close();
            
        
        });
        
        
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxHeight(300);
        grid.setMaxWidth(500);
        grid.setMinHeight(300);
        grid.setMinWidth(500);
        
        grid.add(admin, 0, 0);
        grid.add(users, 0, 1);
        
        
        Scene scene = new Scene(grid);
        
        primaryStage.setTitle("Hotel Management & Booking System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
