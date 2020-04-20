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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import oracle.jdbc.OracleTypes;

public class ProjectDAO {

    //*******************************
    //SELECT Projects
    //*******************************
    public static ObservableList<Project> searchProjects() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM projects";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsProjs = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getDepartmentList method and get employee object
            ObservableList<Project> projList = getProjectList(rsProjs);

            //Return dept object
            return projList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //*******************************
    //SELECT a project
    //*******************************
    public static Project searchProject(String proId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM PROJECTS WHERE PROJECT_ID = " + proId;

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPro = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getProjectFromResultSet method and get project object
            Project project = getProjectFromResultSet(rsPro);

            return project;
        } catch (SQLException e) {
            System.out.println("While searching an project with " + proId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    //*************************************
    //DELETE a project
    //*************************************
    public static void deleteProWithId(String proId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt
                = "BEGIN\n"
                + "   DELETE FROM PROJECTS\n"
                + "         WHERE PROJECT_ID =" + proId + ";\n"
                + "   COMMIT;\n"
                + "END;";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    private static Project getProjectFromResultSet(ResultSet rs) throws SQLException {
        Project pro = null;
        if (rs.next()) {
            pro = new Project();
            pro.setProjectId(rs.getInt("PROJECT_ID"));
            pro.setProjectName(rs.getString("PROJECT_NAME"));
            pro.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
        }
        return pro;
    }

    //Select * from projects operation
    private static ObservableList<Project> getProjectList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Project objects
        ObservableList<Project> projList = FXCollections.observableArrayList();

        while (rs.next()) {
            Project proj = new Project();
            proj.setProjectId(rs.getInt("PROJECT_ID"));
            proj.setProjectName(rs.getString("PROJECT_NAME"));
            proj.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
            //Add project to the ObservableList
            projList.add(proj);
        }
        //return projList (ObservableList of Projects)
        return projList;
    }

    //*************************************
    //INSERT an project
    //*************************************
    public static void insertProj(Integer proid, String proname, Integer deptid) throws SQLException, ClassNotFoundException {

        String updateStmt
                = "BEGIN\n"
                + "INSERT INTO projects\n"
                + "(PROJECT_ID, PROJECT_NAME, DEPARTMENT_ID)\n"
                + "VALUES\n"
                + "('" + proid + "', '" + proname + "', '" + deptid + "');\n"
                + "END;";

        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //Procedure to Search Project by employee_id
    public static Project searchbyID(Integer empId) throws SQLException {
        String runSP = "{ call employee_project1(?, ?) }";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "darshak", "oracle123");
                CallableStatement callableStatement = conn.prepareCall(runSP)) {

            callableStatement.setInt(1, empId);

            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            // run it
            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(2);
            Project project = procedureFromResultSet(rs);

            return project;

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            throw e;
        }
    }

    private static Project procedureFromResultSet(ResultSet rs) throws SQLException {
        Project pro = null;

        while (rs.next()) {
            pro = new Project();
            pro.setProjectId(rs.getInt("PROJECT_ID"));
            pro.setProjectName(rs.getString("PROJECT_NAME"));
        }
        return pro;
    }
}
