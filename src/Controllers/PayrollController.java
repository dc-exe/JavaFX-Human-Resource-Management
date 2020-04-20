/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Employee;
import Model.EmployeeDAO;
import Model.Payroll;
import Model.PayrollDAO;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PayrollController {

    @FXML
    private JFXButton addsalaryBtn;

    @FXML
    private JFXButton updatesalaryBtn;

    @FXML
    private JFXButton clear_btn;

    @FXML
    private JFXButton searchAllBtn;

    @FXML
    private TextArea resultArea;

    @FXML
    private TextField empidText;

    @FXML
    private TextField amountText;

    @FXML
    private TableView salaryTable;

    @FXML
    private TableColumn<Payroll, Integer> empIdColumn;

    @FXML
    private TableColumn<Payroll, Integer> amountColumn;

    @FXML
    private JFXButton searchsalaryBtn;

    @FXML
    public void initialize() {
        empIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
    }
    
    //Search a salary
    @FXML
    private void searchSalary (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get salary information
            Payroll pay = PayrollDAO.searchsalary(empidText.getText());
            
            //Populate salary on TableView and Display on TextArea
            populateAndShowSalary(pay);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting employee information from DB.\n" + e);
            throw e;
        }
    }
    
    //Populate Salary for TableView and Display salary on TextArea
    @FXML
    private void populateAndShowSalary(Payroll pay) throws ClassNotFoundException {
        if (pay != null) {
            populateSalary(pay);
            setSalInfoToTextArea(pay);
        } else {
            resultArea.setText("This salary does not exist!\n");
        }
    }

    //Search all employees salary
    @FXML
    private void searchSalaries(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Employees salary information
            ObservableList<Payroll> payData =PayrollDAO.searchSalaries();
            //Populate Employees on TableView
            populateSalaries(payData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    //Populate Salary
    @FXML
    private void populateSalary (Payroll pay) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Payroll> payData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        payData.add(pay);
        //Set items to the employeeTable
        salaryTable.setItems(payData);
    }
    
    //Set salary information to Text Area
    @FXML
    private void setSalInfoToTextArea (Payroll pay) {
        resultArea.setText("Employee ID: " + pay.getEmployeeId() + "\n" +
                "Salary Amount: " + pay.getSalary());
    }
    
    //Populate Employees for TableView
    @FXML
    private void populateSalaries (ObservableList<Payroll> payData) throws ClassNotFoundException {
        //Set items to the employeeTable
        salaryTable.setItems(payData);
    }
    
    //Update employee's salary with the amount which is written on amountText field
    @FXML
    private void updateEmployeeSalary (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            PayrollDAO.updateSalary(Integer.valueOf(empidText.getText()), Integer.valueOf(amountText.getText()));
            resultArea.setText("Salary has been updated for, employee id: " + empidText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating salary: " + e);
        }
    }
    
    //Insert a salary to the DB
    @FXML
    private void insertSalary (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            PayrollDAO.insertSalary(Integer.valueOf(empidText.getText()), Integer.valueOf(amountText.getText()));
            resultArea.setText("Salary inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting salary " + e);
            throw e;
        }
    }
    
    @FXML
    private void clear_all (ActionEvent event) {
        empidText.clear();
        amountText.clear();
        resultArea.clear();
        salaryTable.getItems().clear();
    }
}
