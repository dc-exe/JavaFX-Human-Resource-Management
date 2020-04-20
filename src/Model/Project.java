/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.*;
import java.sql.*;

public class Project {
    
    private IntegerProperty project_id;
    private StringProperty project_name;
    private IntegerProperty department_id;

    public Project() {
        this.project_id = new SimpleIntegerProperty();
        this.project_name = new SimpleStringProperty();
        this.department_id = new SimpleIntegerProperty();
    }
    
    //project id
    public int getProjectId() {
        return project_id.get();
    }

    public void setProjectId(int project_id) {
        this.project_id.set(project_id);
    }

    public IntegerProperty ProjectIdProperty() {
        return project_id;
    }

    //project_name
    public String getProjectName() {
        return project_name.get();
    }

    public void setProjectName(String project_name) {
        this.project_name.set(project_name);
    }

    public StringProperty ProjectNameProperty() {
        return project_name;
    }
    
    //department_id
    public int getDepartmentId() {
        return department_id.get();
    }

    public void setDepartmentId(int departmentId) {
        this.department_id.set(departmentId);
    }

    public IntegerProperty departmentIdProperty() {
        return department_id;
    }
}
