/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Project;
import Model.ProjectDAO;
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

public class ProjectPanelController {
    
    @FXML
    private JFXButton addprojectBtn;
    
    @FXML
    private JFXButton clear_btn;
    
    @FXML
    private JFXButton searchAllBtn;
    
    @FXML
    private TextArea resultArea;
    
    @FXML
    private TextField ProjectidText;
    
    @FXML
    private TextField projectNameText;
    
    @FXML
    private TextField DeptidText;
    
    @FXML
    private TableView projectTable;
    
    @FXML
    private JFXButton searchprojectBtn;
    
    @FXML
    private JFXButton comprojectBtn;
    
    @FXML
    private TextField EmpidText;
    
    @FXML
    private JFXButton seachbyIdBtn;    
    
    @FXML
    private TableColumn<Project, Integer> proIdColumn;
    
    @FXML
    private TableColumn<Project, String> proNameColumn;
    
    @FXML
    private TableColumn<Project, Integer> deptidColumn;
    
    @FXML
    private void initialize() {
        proIdColumn.setCellValueFactory(cellData -> cellData.getValue().ProjectIdProperty().asObject());
        proNameColumn.setCellValueFactory(cellData -> cellData.getValue().ProjectNameProperty());
        deptidColumn.setCellValueFactory(cellData -> cellData.getValue().departmentIdProperty().asObject());
        
    }

    //Search a project
    @FXML
    private void searchProject(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Project information
            Project pro = ProjectDAO.searchProject(ProjectidText.getText());
            //Populate project on TableView and Display on TextArea
            populateAndShowProject(pro);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting project information from DB.\n" + e);
            throw e;
        }
    }

    //Search all projects
    @FXML
    private void searchProjects(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Project information
            ObservableList<Project> proData = ProjectDAO.searchProjects();
            //Populate Projects on TableView
            populateProjects(proData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }

    //Populate Projects for TableView
    @FXML
    private void populateProjects(ObservableList<Project> projData) throws ClassNotFoundException {
        //Set items to the ProjectTable
        projectTable.setItems(projData);
    }

    //Populate Project
    @FXML
    private void populateProject(Project pro) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Project> proData = FXCollections.observableArrayList();
        //Add Project to the ObservableList
        proData.add(pro);
        //Set items to the projectTable
        projectTable.setItems(proData);
    }

    //Set Project information to Text Area
    @FXML
    private void setProjInfoToTextArea(Project proj) {
        resultArea.setText("Project ID: " + proj.getProjectId() + "\n"
                + "Project Name: " + proj.getProjectName() + "\n"
                + "Department ID: " + proj.getDepartmentId());
    }

    //Populate Project for TableView and Display project on TextArea
    @FXML
    private void populateAndShowProject(Project proj) throws ClassNotFoundException {
        if (proj != null) {
            populateProject(proj);
            setProjInfoToTextArea(proj);
        } else {
            resultArea.setText("This Project does not exist!\n");
        }
    }

    //Insert an project to the DB
    @FXML
    private void insertProject(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ProjectDAO.insertProj(Integer.valueOf(ProjectidText.getText()), projectNameText.getText(), Integer.valueOf(DeptidText.getText()));
            resultArea.setText("Project inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting project " + e);
            throw e;
        }
    }

    //Delete a project with a given project Id from DB
    @FXML
    private void deleteProject(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ProjectDAO.deleteProWithId(ProjectidText.getText());
        resultArea.setText("Project Completed and removed from Database!" + "\n" + " Project id: " + ProjectidText.getText() + "\n");
    }
    
    @FXML
    private void searchProjectbyEmpId(ActionEvent event) throws SQLException {
        Project pro = ProjectDAO.searchbyID(Integer.valueOf(EmpidText.getText()));
        setInfoToTextArea(pro);
    }
    
    @FXML
    private void setInfoToTextArea(Project proj) {
        resultArea.setText("Project ID: " + proj.getProjectId() + "\n"
                + "Project Name: " + proj.getProjectName());
    }
    
    @FXML
    private void clear_all(ActionEvent event) {
        ProjectidText.clear();
        DeptidText.clear();
        projectNameText.clear();
        resultArea.clear();
        
        projectTable.getItems().clear();
    }
}
