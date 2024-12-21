/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapp;

import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class LoginController implements Initializable {

    private Connection con;
    private PreparedStatement prs;
    private ResultSet res;
    
    private double  x=0;
    private double  y=0;
    
    public  void AdminLogin(){
        String sql ="SELECT * FROM admin WHERE username =? and password=?";
        
        con = database.Database();
        try {
            prs =  con.prepareStatement(sql);
            prs.setString(1, txtusername.getText());
            prs.setString(2, txtpassword.getText());
            
            res = prs.executeQuery();
            
            Alert alert;
            if(txtusername.getText().isEmpty()|| txtpassword.getText().isEmpty()){
             alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Error Maesge..");
             alert.setHeaderText(null);
             alert.setContentText("Pleas All Black Faild");
             alert.showAndWait();
             
//             res = prs.executeQuery();
             
            }
            else{
                if(res.next()){
                    
                    getData.Username= txtusername.getText();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Imformation Maesge..");
                    alert.setHeaderText(null);
                    alert.setContentText("SuccessFully");
                    alert.showAndWait();
                    
                    
                    btnlogin.getScene().getWindow().hide();
                    
                    Parent root = FXMLLoader.load(getClass().getResource("/javafxapp/Main.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    
                    root.setOnMouseDragged((MouseEvent even)->{
                        x=even.getSceneX();
                        y=even.getSceneY();
                    });
                    
                    root.setOnMouseDragged((MouseEvent even)->{
                        stage.setX(even.getSceneX()-x);
                        stage.setY(even.getSceneY()-y);
                        
                    });
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                    
                }
                else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Maesge..");
                    alert.setHeaderText(null);
                    alert.setContentText("Woring Username OR Password");
                    alert.showAndWait();
                }
            }
                
            
        } 
        catch (Exception e) {
        }
    }
           
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btncancel;
    
    public  void  btncancel(){
        System.exit(0);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
