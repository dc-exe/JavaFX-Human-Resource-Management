/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import Util.DBUtil;

/**
 *
 * @author HP
 */
public class DepartmentDAO {

    //*******************************
    //SELECT Departments
    //*******************************
    public static ObservableList<Department> searchDepartments() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM departments";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsDepts = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getDepartmentList method and get employee object
            ObservableList<Department> deptList = getDepartmentList(rsDepts);

            //Return dept object
            return deptList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from departments operation
    private static ObservableList<Department> getDepartmentList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Department objects
        ObservableList<Department> deptList = FXCollections.observableArrayList();

        while (rs.next()) {
            Department dept = new Department();
            dept.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
            dept.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
            dept.setTotalEmployees(rs.getInt("TOTAL_EMPLOYEES"));
            //Add department to the ObservableList
            deptList.add(dept);
        }
        //return empList (ObservableList of Employees)
        return deptList;
    }
    
    //*************************************
    //INSERT an department
    //*************************************
    public static void insertDept(Integer deptid, String deptname) throws SQLException, ClassNotFoundException {

        String updateStmt
                = "BEGIN\n"
                + "INSERT INTO departments\n"
                + "(DEPARTMENT_ID, DEPARTMENT_NAME)\n"
                + "VALUES\n"
                + "('" + deptid + "', '" + deptname + "');\n"
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
