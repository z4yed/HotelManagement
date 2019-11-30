
package _hotelmanagement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AdminLogin extends Application{
    
    public void start(Stage primaryStage)
    {
        
        Text txt1 = new Text("Username ");
        Text txt2 = new Text("Password");
        
        TextField tf1 = new TextField();
        PasswordField tf2 = new PasswordField();
        
        Button login = new Button();
        login.setText("Login");
        
        Button back = new Button();
        back.setText("Go Back");
        
        Label lab1 = new Label();
        
        
        
        
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxHeight(300);
        grid.setMaxWidth(700);
        grid.setMinHeight(300);
        grid.setMinWidth(700);
        
        grid.add(txt1, 0, 0);
        grid.add(tf1, 1, 0);
        grid.add(txt2, 0, 1);
        grid.add(tf2, 1, 1);
        grid.add(login, 1, 2);
        grid.add(lab1, 2, 1);
        grid.add(back, 1, 3);
        
        login.setOnAction(value -> {
        
            if(!"admin".equals(tf1.getText()) || !"admin".equals(tf2.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Invalid Username or Password.");
                System.out.println("Invalid Username or Password.");
                alert.showAndWait();
            }
            else
            {
                System.out.println("Username && Password Matched.");
                lab1.setText("");
                
                Stage third = new Stage();
                AdminView av = new AdminView();
                
                av.start(third);
                
                System.out.println("Previous Window Closed Successfully");
                primaryStage.close();              
                
            }
        });
        
        back.setOnAction(Value -> {
        
            Stage secondaryStage = new Stage();
            _HotelManagement hm = new _HotelManagement();
            hm.start(secondaryStage);
            System.out.println("Closed Previous Window Succssfully..");
            primaryStage.close();
        
        });
        
        
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        
        
        primaryStage.setTitle("Login Panel");
        
        primaryStage.show();
    }
    
}
