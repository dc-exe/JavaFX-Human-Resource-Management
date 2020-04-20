/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Payroll {
    
    private IntegerProperty employee_id;
    private IntegerProperty salary;

    //Constructor
    public Payroll() {
        this.employee_id = new SimpleIntegerProperty();   
        this.salary = new SimpleIntegerProperty();
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

    //salary
    public int getSalary() {
        return salary.get();
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }

    public IntegerProperty salaryProperty() {
        return salary;
    }
}
