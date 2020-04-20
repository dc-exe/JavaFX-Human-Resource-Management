/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Employee;
import Model.EmployeeDAO;
import Model.PaidLeave;
import Model.PaidLeaveDAO;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PaidLeaveController {

    @FXML
    private TableView paidleaveTable;
    
     @FXML
    private JFXButton show;

    @FXML
    private TableColumn<PaidLeave, Integer> empIdColumn;

    @FXML
    private TableColumn<PaidLeave, Integer> leaveColumn;

    @FXML
    private TableColumn<PaidLeave, Integer> salaryColumn;
    
    @FXML
    public void initialize() {
        empIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
        leaveColumn.setCellValueFactory(cellData -> cellData.getValue().leaveProperty().asObject());
        salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
    }    
    
    //Search all employees
    @FXML
    private void searchEmployees(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            ObservableList<PaidLeave> empData = PaidLeaveDAO.searchLeave();
            //Populate Employees on TableView
            populateEmployees(empData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    //Populate Employees for TableView
    @FXML
    private void populateEmployees (ObservableList<PaidLeave> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        paidleaveTable.setItems(empData);
    }
    
    
}
