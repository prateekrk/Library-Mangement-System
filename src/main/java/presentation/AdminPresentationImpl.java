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
                        bookService1.getAllRecords().forEach(book->System.out.printf("%-10s%-10s%-15s\n",book.getBookID(),book.getName(),book.getCategory(), book.getStockAvailable()));

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
                        libraryManagementService.getEmployeeTransactions(employeeId).forEach(book->System.out.printf("%-10s%-50s%-50s%-15s%-15s%-2s\n",Integer.toString(book.getIssueID()),book.getBookId(),book.getIssuedDate().toString(),book.getScheduledReturnDate().toString(),book.getReturnDate(),book.getFine()));
                    } catch (ClassNotFoundException | SQLException | IOException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 3:
                    System.out.println("--------------------------------Catalog--------------------------------");
                    System.out.println();
                    try {
                        bookService.getAllRecords().forEach(book->System.out.printf("%-10s%-10s%-15s%-3s\n",book.getBookID(),book.getName(),book.getCategory(), Integer.toString(book.getStockAvailable())));
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
                    System.out.println("Enter Book_Id");
                    String book_id=sc.nextLine();
                    System.out.println("Enter Book_name");
                    String book_name=sc.nextLine();
                    System.out.println("Enter Book_category");
                    String book_category=sc.nextLine();
                    System.out.println("Enter Stock");
                    int stock_available=sc.nextInt();

                    Book book=new Book(book_id,book_name,book_category,stock_available);
                    try{
                        bookService.insertBook(book);
                    }
                    catch (SQLException|ClassNotFoundException|IOException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Enter Book_id");
                    String book_id1=sc.nextLine();
                    System.out.println("Enter Stock");
                    int stock_available1=sc.nextInt();
                    try{
                        bookService.increaseStock(book_id1,stock_available1);
                    }
                    catch (SQLException|ClassNotFoundException|IOException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:break;
                default:
                    System.out.println("Enter valid choice");
                    break;
            }
    }
    @Override
    public void showMenu() {
        System.out.println("1.Get Library details\n2. View Employee Transactions\n3. View Catalog\n4. Add Employee\n5. Add Book\n6.Increase Book Stock\n7. Exit");

    }
}
