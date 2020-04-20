/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Department;
import Model.DepartmentDAO;
import com.jfoenix.controls.JFXButton;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DepartmentPanelController {

    @FXML
    private JFXButton addDeptBtn;

    @FXML
    private JFXButton clear_btn;

    @FXML
    private JFXButton searchAllBtn;

    @FXML
    private TextArea resultArea;

    @FXML
    private TextField DeptnameText;

    @FXML
    private TextField DeptidText;

    @FXML
    private TableView departmentTable;

    @FXML
    private TableColumn<Department, Integer> deptIdColumn;

    @FXML
    private TableColumn<Department, String> deptNameColumn;

    @FXML
    private TableColumn<Department, Integer> totalEmpColumn;


    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
        deptIdColumn.setCellValueFactory(cellData -> cellData.getValue().departmentIdProperty().asObject());
        deptNameColumn.setCellValueFactory(cellData -> cellData.getValue().DepartmentNameProperty());
        totalEmpColumn.setCellValueFactory(cellData -> cellData.getValue().TotalEmployeesProperty().asObject());

    }

    //Search all departments
    @FXML
    private void searchDepartments(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Department information
            ObservableList<Department> deptData = DepartmentDAO.searchDepartments();
            //Populate Departments on TableView
            populateDepartments(deptData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    //Populate Department
    @FXML
    private void populateDepartment (Department dept) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Department> deptData = FXCollections.observableArrayList();
        //Add Department to the ObservableList
        deptData.add(dept);
        //Set items to the departmentTable
        departmentTable.setItems(deptData);
    }
    
    //Set Department information to Text Area
    @FXML
    private void setDeptInfoToTextArea ( Department dept) {
        resultArea.setText("Department ID: " + dept.getDepartmentId() + "\n" +
                "Department Name: " + dept.getDepartmentName() + "\n" +
                "Total Employees: " + dept.getTotalEmployees());
    }
    
    //Populate Department for TableView and Display Department on TextArea
    @FXML
    private void populateAndShowDepartment(Department dept) throws ClassNotFoundException {
        if (dept != null) {
            populateDepartment(dept);
            setDeptInfoToTextArea(dept);
        } else {
            resultArea.setText("This Department does not exist!\n");
        }
    }
    
    //Populate Departments for TableView
    @FXML
    private void populateDepartments (ObservableList<Department> deptData) throws ClassNotFoundException {
        //Set items to the DepartmentTable
        departmentTable.setItems(deptData);
    }
    
    //Insert an department to the DB
    @FXML
    private void insertDepartment (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            DepartmentDAO.insertDept(Integer.valueOf(DeptidText.getText()),DeptnameText.getText());
            resultArea.setText("Department inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting department " + e);
            throw e;
        }
    }
    
    @FXML
    private void clear_all (ActionEvent event) {
        DeptidText.clear();
        DeptnameText.clear();
        resultArea.clear();
        
        departmentTable.getItems().clear();
    }
}
