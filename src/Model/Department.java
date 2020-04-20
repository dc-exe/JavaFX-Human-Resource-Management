/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;  

import javafx.beans.property.*;
import java.sql.*;

public class Department {

    //Declare Employees Table Columns
    private IntegerProperty department_id;
    private StringProperty department_name;
    private IntegerProperty total_employees;

    //Constructor
    public Department() {
        this.department_id = new SimpleIntegerProperty();
        this.department_name = new SimpleStringProperty();
        this.total_employees = new SimpleIntegerProperty();
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

    //department_name
    public String getDepartmentName() {
        return department_name.get();
    }

    public void setDepartmentName(String departmentName) {
        this.department_name.set(departmentName);
    }

    public StringProperty DepartmentNameProperty() {
        return department_name;
    }

    //salary
    public int getTotalEmployees() {
        return total_employees.get();
    }

    public void setTotalEmployees(int totalEmployees) {
        this.total_employees.set(totalEmployees);
    }

    public IntegerProperty TotalEmployeesProperty() {
        return total_employees;
    }
}
