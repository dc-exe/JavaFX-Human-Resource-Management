/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;  

import javafx.beans.property.*;
import java.sql.*;

public class Employee {

    //Declare Employees Table Columns
    private IntegerProperty employee_id;
    private StringProperty first_name;
    private StringProperty last_name;
    private StringProperty email;
    private SimpleObjectProperty<Date> hire_date;
    private IntegerProperty department_id;

    //Constructor
    public Employee() {
        this.employee_id = new SimpleIntegerProperty();
        this.first_name = new SimpleStringProperty();
        this.last_name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.hire_date = new SimpleObjectProperty<>();
        this.department_id = new SimpleIntegerProperty();
    }

    //employee_id
    public int getEmployeeId() {
        return employee_id.get();
    }

    public void setEmployeeId(int employeeId) {
        this.employee_id.set(employeeId);
    }

    public IntegerProperty employeeIdProperty() {
        return employee_id;
    }

    //first_name
    public String getFirstName() {
        return first_name.get();
    }

    public void setFirstName(String firstName) {
        this.first_name.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return first_name;
    }

    //last_name
    public String getLastName() {
        return last_name.get();
    }

    public void setLastName(String lastName) {
        this.last_name.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return last_name;
    }

    //email
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    //hire_date
    public Object getHireDate() {
        return hire_date.get();
    }

    public void setHireDate(Date hireDate) {
        this.hire_date.set(hireDate);
    }

    public SimpleObjectProperty<Date> hireDateProperty() {
        return hire_date;
    }

    //department_id
    public int getDepartmanId() {
        return department_id.get();
    }

    public void setDepartmantId(int departmentId) {
        this.department_id.set(departmentId);
    }

    public IntegerProperty departmentIdProperty() {
        return department_id;
    }
}