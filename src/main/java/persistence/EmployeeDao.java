package persistence;

import pojo.Employee;

import java.io.IOException;
import java.sql.SQLException;

public interface EmployeeDao {

    Employee getEmployee(String employeeID) throws ClassNotFoundException, IOException, SQLException;

    boolean addEmployee(Employee employee) throws ClassNotFoundException, IOException, SQLException;
}
