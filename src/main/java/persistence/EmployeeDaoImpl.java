package persistence;

import helpers.DatabaseConnection;
import pojo.Employee;

import java.io.IOException;
import java.sql.*;

public class EmployeeDaoImpl implements  EmployeeDao{
    @Override
    public Employee getEmployee(String employeeID) throws ClassNotFoundException, IOException, SQLException {
        Employee employee=new Employee();

        Connection connection= DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT  * FROM EMPLOYEES WHERE EMPLOYEE_ID=? ");
        preparedStatement.setString(1,employeeID);

        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()) {
            employee.setEmployeeId(resultSet.getString("EMPLOYEE_ID"));
            employee.setName(resultSet.getString("NAME"));
            employee.setEmail(resultSet.getString("EMAIL"));
            employee.setPassword(resultSet.getString("PASSWORD"));
            employee.setAccess(resultSet.getByte("ACCESS"));
        }

        return employee;
    }

    @Override
    public boolean addEmployee(Employee employee) throws ClassNotFoundException, IOException, SQLException {
        Connection connection= DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO EMPLOYEES(EMPLOYEE_ID,NAME,EMAIL,PASSWORD,ACCESS) VALUES (?,?,?,?,?)") ;
        preparedStatement.setString(1,employee.getEmployeeId());
        preparedStatement.setString(2,employee.getName());
        preparedStatement.setString(3, employee.getEmail());
        preparedStatement.setString(4, employee.getPassword());
        preparedStatement.setByte(5,employee.getAccess());

        int rows=preparedStatement.executeUpdate();
        return rows > 0;
    }


}
