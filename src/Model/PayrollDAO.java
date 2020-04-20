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
public class PayrollDAO {
    
    //*******************************
    //SELECT a salary
    //*******************************
    public static Payroll searchsalary(String empId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM payments WHERE employee_id=" + empId;

        try {
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

            Payroll payroll = getSalaryFromResultSet(rsEmp);

            //Return employee object
            return payroll;
        } catch (SQLException e) {
            System.out.println("While searching a salary with " + empId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
    private static Payroll getSalaryFromResultSet(ResultSet rs) throws SQLException {
        Payroll pay = null;
        if (rs.next()) {
            pay = new Payroll();
            pay.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
            pay.setSalary(rs.getInt("SALARY"));
        }
        return pay;
    }
    
    //*******************************
    //SELECT Salaries
    //*******************************
    public static ObservableList<Payroll> searchSalaries() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM PAYMENTS ORDER BY EMPLOYEE_ID";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Payroll> salaryList = getSalaryList(rsEmps);

            //Return employee object
            return salaryList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from payments operation
    private static ObservableList<Payroll> getSalaryList(ResultSet rs) throws SQLException, ClassNotFoundException {

        ObservableList<Payroll> payList = FXCollections.observableArrayList();

        while (rs.next()) {
            Payroll pay = new Payroll();
            pay.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
            pay.setSalary(rs.getInt("SALARY"));
            //Add to the ObservableList
            payList.add(pay);
        }
        //return empList (ObservableList of Employees)
        return payList;
    }
    
    //*************************************
    //UPDATE an employee's salary
    //*************************************
    public static void updateSalary(Integer empId, Integer salary) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt
                = "BEGIN\n"
                + "   UPDATE PAYMENTS\n"
                + "      SET SALARY = '" + salary + "'\n"
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
    //INSERT salary
    //*************************************
    public static void insertSalary(Integer deptid, Integer salary) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt
                = "BEGIN\n"
                + "INSERT INTO PAYMENTS\n"
                + "(EMPLOYEE_ID, SALARY)\n"
                + "VALUES\n"
                + "(" + deptid + ", " + salary + ");\n"
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
