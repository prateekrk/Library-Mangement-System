package presentation;

import pojo.Book;
import pojo.Employee;
import pojo.Library;
import services.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminPresentationImpl implements AdminPresentation{

    BookService bookService=new BookServiceImpl();
    LibraryManagementService libraryManagementService=new LibraryManagementServiceImpl();
    EmployeeService employeeService=new EmployeeServiceImpl();

    Scanner sc=new Scanner(System.in);

    @Override
    public void perfomMenu(int choice) {

            switch (choice) {
                case 1:
                    BookService bookService1 = new BookServiceImpl();
                    try {
                        bookService1.getAllRecords().forEach(System.out::println);

                    } catch (SQLException | IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                    break;


                case 2:
                    System.out.println("Enter Employee ID");
                    String employeeId = sc.nextLine();
                    System.out.println("--------------------------------Transactions--------------------------------");
                    try {
                        libraryManagementService.getEmployeeTransactions(employeeId).forEach(System.out::println);
                    } catch (ClassNotFoundException | SQLException | IOException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 3:
                    System.out.println("--------------------------------Catalog--------------------------------");
                    System.out.println();
                    try {
                        bookService.getAllRecords().forEach(System.out::println);
                    } catch (ClassNotFoundException | SQLException | IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    System.out.println("Enter employee id");
                    String employeeID=sc.nextLine();
                    System.out.println("Enter employee name");
                    String employeeName=sc.nextLine();
                    System.out.println("Enter employee email");
                    String employeeEmail=sc.nextLine();
                    System.out.println("Enter employee password");
                    String password=sc.nextLine();
                    System.out.println("Enter access Mode 1 for Admin 0 for Employee");
                    byte access=sc.nextByte();
                    Employee employee=new Employee(employeeID,employeeName,employeeEmail,password,access);
                    try{
                        employeeService.addEmployee(employee);
                    }
                    catch(ClassNotFoundException|SQLException|IOException e){
                            e.printStackTrace();
                    }

                    break;
                case 5:

                    break;
                default:
                    System.out.println("Enter valid choice");
                    break;
            }
    }
    @Override
    public void showMenu() {
        System.out.println("1.Get Library details\n2. View Employee Transactions\n3. View Catalog\n4. Add Employee\n5. Exit");

    }
}
