/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author HP
 */
public class PaidLeave {
    
    private IntegerProperty employee_id;
    private IntegerProperty leave;
    private IntegerProperty updated_salary;
    
    public PaidLeave() {
        this.employee_id = new SimpleIntegerProperty();
        this.leave = new SimpleIntegerProperty();
        this.updated_salary = new SimpleIntegerProperty();
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
    
    //leave
    public int getLeave() {
        return leave.get();
    }

    public void setLeave(int leave) {
        this.leave.set(leave);
    }

    public IntegerProperty leaveProperty() {
        return leave;
    }
    
    //updated_salary
    public int getSalary() {
        return updated_salary.get();
    }

    public void setSalary(int updated_salary) {
        this.updated_salary.set(updated_salary);
    }

    public IntegerProperty salaryProperty() {
        return updated_salary;
    }
    
}


