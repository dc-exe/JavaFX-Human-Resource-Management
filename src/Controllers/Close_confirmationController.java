/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Close_confirmationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton yes;

    @FXML
    private JFXButton no;
    
    @FXML
    private void yes_close(MouseEvent event) {
        if(event.getSource() == yes) {
            System.exit(0);
        } else if (event.getSource() == no) {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
