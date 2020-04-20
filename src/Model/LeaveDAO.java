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
public class LeaveDAO {

    //*******************************
    //SELECT leaves
    //*******************************
    public static ObservableList<Leave> searchLeave() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM LEAVE ORDER BY EMPLOYEE_ID";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsleave = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Leave> leaveList = getLeaveList(rsleave);

            //Return employee object
            return leaveList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from leave operation
    private static ObservableList<Leave> getLeaveList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Leave> leaveList = FXCollections.observableArrayList();

        while (rs.next()) {
            Leave leave = new Leave();
            leave.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
            leave.setAttendance(rs.getInt("ATTENDANCE"));
            leave.setLeave(rs.getInt("LEAVE"));

            leaveList.add(leave);
        }
        return leaveList;
    }
    
    //*************************************
    //UPDATE an employee's attendance
    //*************************************
    public static void updateLeave(Integer empId, Integer leave) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt
                = "BEGIN\n"
                + "   UPDATE LEAVE\n"
                + "      SET LEAVE = '" + leave + "'\n"
                + "    WHERE EMPLOYEE_ID = " + empId + ";\n"
                + "   COMMIT;\n"
                + "END;";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    
    //*************************************
    //INSERT attendance
    //*************************************
    public static void insertLeave(Integer empId, Integer attendance, Integer leave) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt
                = "BEGIN\n"
                + "INSERT INTO LEAVE\n"
                + "(EMPLOYEE_ID, ATTENDANCE, LEAVE)\n"
                + "VALUES\n"
                + "(" + empId + ", " + attendance + ", " + leave + ");\n"
                + "END;";

        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
