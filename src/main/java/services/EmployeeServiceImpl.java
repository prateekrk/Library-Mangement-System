package services;

import persistence.EmployeeDao;
import persistence.EmployeeDaoImpl;
import pojo.Employee;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService{

    EmployeeDao employeeDao=new EmployeeDaoImpl();

    @Override
    public Employee getEmployee(String employeeID) throws ClassNotFoundException, IOException, SQLException {
        return employeeDao.getEmployee(employeeID);
    }

    @Override
    public boolean addEmployee(Employee employee) throws ClassNotFoundException, IOException, SQLException {
        return  employeeDao.addEmployee(employee);
    }

}
