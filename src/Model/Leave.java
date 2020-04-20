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
public class Leave {
    //Declare Employees Table Columns
    private IntegerProperty employee_id;
    private IntegerProperty attendance;
    private IntegerProperty leave;


    //Constructor
    public Leave() {
        this.employee_id = new SimpleIntegerProperty();
        this.attendance = new SimpleIntegerProperty();
        this.leave = new SimpleIntegerProperty();
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

    //attendance
    public int getAttendance() {
        return attendance.get();
    }

    public void setAttendance(int attendance) {
        this.attendance.set(attendance);
    }

    public IntegerProperty attendanceProperty() {
        return attendance;
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
}
