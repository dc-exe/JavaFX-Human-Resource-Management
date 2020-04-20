/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Util.DBUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane rootpane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Label invalid;

    @FXML
    private JFXButton login;
    
    @FXML
    private Label visiblePass;

    @FXML
    private ImageView eye;

    @FXML
    private void handleCloseButtonAction() {

    }

    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }

    String checkUser;
    String checkPwd;
        
    @FXML
    private void checkname(MouseEvent event1) throws IOException {
        checkUser = username.getText();
        checkPwd = password.getText();
        String uname = "admin";
        String pword = "admin";

        if (uname.equals(checkUser) && pword.equals(checkPwd)) {
            invalid.setText("");
            openDashboard();
            ((Node) (event1.getSource())).getScene().getWindow().hide();
        } else {
            password.requestFocus();
            password.setFocusColor(Paint.valueOf("RED"));
            invalid.setText("Invalid login or password. Please try again.");
        }
    }

    private void openDashboard() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Dashboard.fxml"));
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Dashboard");
        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setScene(new Scene(root));
        stage.show();

    }
    
    @FXML
    private void showPassword (MouseEvent event) {
        password.setPromptText(password.getText());
        password.setText("");
    }
    @FXML
    private void hidePassword (MouseEvent event) {
        password.setText(password.getPromptText());
        password.setPromptText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
