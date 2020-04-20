/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Util.DBUtil;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.gluonhq.charm.glisten.control.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardController {

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton btn_dash;

    @FXML
    private JFXButton employee;

    @FXML
    private JFXButton find;

    @FXML
    private JFXButton projects;

    @FXML
    private JFXButton department;

    @FXML
    private JFXButton attendance;

    @FXML
    private JFXButton payroll;

    @FXML
    private JFXButton leave;

    @FXML
    private BorderPane borderpane;

    @FXML
    private ImageView close_btn;

    @FXML
    private Label panel_title;
    
    @FXML
    private static Label totalEmp;

    @FXML
    private static Label totalDept;

    @FXML
    private static Label totalPro;

    @FXML
    private void handleClose(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/close_confirmation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Confirmation");
        stage.getIcons().add(new Image("/images/icon.png"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleButtonClicks(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == btn_dash) {
            log_out("/FXML/Dashboard.fxml");
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        } else if (mouseEvent.getSource() == employee) {
            panel_title.setText("Employees Area");
            loadStage("/FXML/EmployeePanel.fxml");
        } else if (mouseEvent.getSource() == department) {
            panel_title.setText("Department Area");
            loadStage("/FXML/DepartmentPanel.fxml");
        } else if (mouseEvent.getSource() == projects) {
            panel_title.setText("Project Area");
            loadStage("/FXML/ProjectPanel.fxml");
        } else if (mouseEvent.getSource() == payroll) {
            panel_title.setText("Payroll Area");
            loadStage("/FXML/Payroll.fxml");
        } else if (mouseEvent.getSource() == attendance) {
            panel_title.setText("Attendance Area");
            loadStage("/FXML/LeavePanel.fxml");
        } else if (mouseEvent.getSource() == leave) {
            panel_title.setText("Paid Leave Area");
            loadStage("/FXML/PaidLeave.fxml");
        } else if (mouseEvent.getSource() == logout) {
            log_out("/FXML/FXMLDocument.fxml");
            ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        }
    }

    private void loadStage(String fxml) {
        borderpane.setCenter(null);
        borderpane.setLeft(null);
        borderpane.setTop(null);
        borderpane.setRight(null);
        borderpane.setBottom(null);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));

        } catch (IOException e) {
            e.printStackTrace();
        }
        borderpane.setCenter(root);
    }

    private void log_out(String fxml) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/images/icon.png"));
            stage.setTitle("Login");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
