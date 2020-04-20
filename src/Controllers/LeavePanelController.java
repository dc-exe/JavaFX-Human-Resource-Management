/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Leave;
import Model.LeaveDAO;
import com.jfoenix.controls.JFXButton;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LeavePanelController {

    @FXML
    private TextField empIdText;

    @FXML
    private TextField attendanceText;

    @FXML
    private TextField leaveText;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private TextArea resultArea;

    @FXML
    private TableView leaveTable;

    @FXML
    private TableColumn<Leave, Integer> empIdColumn;

    @FXML
    private TableColumn<Leave, Integer> attendanceColumn;

    @FXML
    private TableColumn<Leave, Integer> leaveColumn;

    @FXML
    private JFXButton showAllBtn;

    @FXML
    private JFXButton clear_btn;

    public void initialize() {

        empIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
        attendanceColumn.setCellValueFactory(cellData -> cellData.getValue().attendanceProperty().asObject());
        leaveColumn.setCellValueFactory(cellData -> cellData.getValue().leaveProperty().asObject());
    }

    //Search all attendance
    @FXML
    private void searchLeave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Leave> leaveData = LeaveDAO.searchLeave();
            //Populate on TableView
            populateLeave(leaveData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting Attendance information from DB.\n" + e);
            throw e;
        }
    }
    
    //Populate Leaves for TableView
    @FXML
    private void populateLeave (ObservableList<Leave> leaveData) throws ClassNotFoundException {
        //Set items to the leaveTable
        leaveTable.setItems(leaveData);
    }
    
    //Set information to Text Area
    @FXML
    private void setInfoToTextArea (Leave leave) {
        resultArea.setText("Employee_ID: " + leave.getEmployeeId() + "\n" +
                "Attendance: " + leave.getAttendance() + "\n" +
                "Leave: " + leave.getLeave());
    }
    
   //Update employee's leave with which is written on leaveText field
    @FXML
    private void updateLeave (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            LeaveDAO.updateLeave(Integer.valueOf(empIdText.getText()), Integer.valueOf(leaveText.getText()));
            resultArea.setText("Leave  has been updated for, employee id: " + empIdText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating leave: " + e);
        }
    }
    
    //Insert an leave to the DB
    @FXML
    private void insertLeave (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            LeaveDAO.insertLeave(Integer.valueOf(empIdText.getText()), Integer.valueOf(attendanceText.getText()), Integer.valueOf(leaveText.getText()));
            resultArea.setText("Attendance inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting attendance " + e);
            throw e;
        }
    }
    
    @FXML
    private void clear_all (ActionEvent event) {
        attendanceText.clear();
        leaveText.clear();
        resultArea.clear();
        empIdText.clear();
        leaveTable.getItems().clear();
    }
}
