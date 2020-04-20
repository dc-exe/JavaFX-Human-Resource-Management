/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class PaidLeaveDAO {
    
    //*******************************
    //SELECT Employees
    //*******************************
    public static ObservableList<PaidLeave> searchLeave() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM LEAVE_SALARY ORDER BY EMPLOYEE_ID";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<PaidLeave> empList = getEmployeeList(rsEmps);

            //Return employee object
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from employees operation
    private static ObservableList<PaidLeave> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<PaidLeave> empList = FXCollections.observableArrayList();

        while (rs.next()) {
            PaidLeave emp = new PaidLeave();
            emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
            emp.setLeave(rs.getInt("LEAVE"));
            emp.setSalary(rs.getInt("UPDATED_SALARY"));

            //Add employee to the ObservableList
            empList.add(emp);
        }
        //return empList (ObservableList of Employees)
        return empList;
    }
}
