package presentation;

import pojo.Book;
import pojo.Employee;
import pojo.Library;
import services.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementPresentationImpl implements LibraryManagementPresentation{


//    LibraryManagementService libraryManagementService=new LibraryManagementServiceImpl();
//    BookService bookService=new BookServiceImpl();
//
//    Scanner sc=new Scanner(System.in);
//    public void perfomMenu(int choice) {
//        switch(choice){
////            case 1:
////
////                List<Library> libraryDetails=new ArrayList<Library>();
////                try{
////                   libraryDetails.addAll(libraryManagementService.getAllBooks());
////                   libraryDetails.forEach(System.out::println);
////
////                }
////                catch (SQLException|IOException|ClassNotFoundException e) {
////                    e.printStackTrace();
////                    System.exit(0);
////                }
////               break;
//
//            case 1:
////
//                System.out.println("Catalog");
//
//                try {
//                    bookService.getAllRecords().forEach(System.out::println);
//                } catch (ClassNotFoundException|SQLException|IOException e) {
//                    e.printStackTrace();
//                }
//                List<Library> booksIssued=new ArrayList<>();
//                System.out.println("Enter Employee ID");
//                String employeeId1=sc.nextLine();
//                String bookId1;
//                while(true) {
//                    System.out.println("Enter Book ID");
//                    bookId1 = sc.nextLine();
////                    try {
////                        if (libraryManagementService.bookAvailableCount(bookId1) <= 0) {
////                            System.out.println("Book is not available right now");
////                            break;
////                        }
////                    } catch (SQLException | ClassNotFoundException | IOException e) {
//////                        System.out.println(e.getMessage());
////                        e.printStackTrace();
////                    }
//
//
//                    Library libraryBook = new Library();
//                    libraryBook.setIssuedDate(Date.valueOf(LocalDate.now()));
//                    libraryBook.setBookId(bookId1);
//                    libraryBook.setScheduledReturnDate(Date.valueOf(LocalDate.now().plusDays(7)));
//                    libraryBook.setEmployeeID(employeeId1);
//                    try {
//                        libraryManagementService.insertBook(libraryBook);
//                        booksIssued.add(libraryBook);
//
//                    } catch (ClassNotFoundException | SQLException | IOException e) {
////                        System.out.println(e.getMessage());
//                        e.printStackTrace();
//                    }
//                    System.out.println("Want to issue more books?(Y/N)");
//                    String ans=sc.nextLine();
//                    if(ans.equalsIgnoreCase("N")||ans.equalsIgnoreCase("no")){
//                        break;
//                    }
//                }
//                System.out.println("Books Issued:");
//                booksIssued.forEach(b->System.out.println(b.getBookId()+" To be returned on : "+b.getScheduledReturnDate()));
//                break;
//            case 2:
//                System.out.println("Enter Employee ID");
//                String employeeId=sc.nextLine();
//
//                List<Library> bookNotReturned;
//                try{
//                    bookNotReturned=new ArrayList<Library>(libraryManagementService.getBooksIssued(employeeId));
//                    if(bookNotReturned.size()==0){
//                        System.out.println("No books to be returned");
//                        break;
//                    }
//                    System.out.println("Books to be returned");
//                    bookNotReturned.forEach(l->System.out.println("Book "+l.getBookId()+" was  issued on: "+l.getIssuedDate()+" and Scheduled Return Date : "+l.getScheduledReturnDate()));
//                }
//                catch (ClassNotFoundException|SQLException|IOException e){
//                    System.out.println(e.getMessage());
//                    break;
//                }
//                System.out.println("Enter Book ID");
//                String bookId=sc.nextLine();
//
//                Date returnDate=Date.valueOf(LocalDate.now());
//
//                try {
//                    libraryManagementService.returnBook(employeeId,bookId,returnDate);
//                    int fine=libraryManagementService.getDetails(employeeId,bookId).getFine();
//                    if(fine==0){
//                        System.out.println("Returned on time no fine to be paid");
//                    }
//                    else{
//                        System.out.println("Fine to be paid : Rs."+fine);
//                    }
//                }
//                catch (SQLException|IOException|ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//
//                break;
//            case 3:
//
//                try{
//                    bookService.getAllRecords().forEach(System.out::println);
//
//                }
//                catch (SQLException|IOException|ClassNotFoundException e) {
//                    e.printStackTrace();
//                    System.exit(0);
//                }
//                break;
//            case 4:
//                System.out.println("Categories");
//                try {
//                    bookService.getAllRecords().forEach(System.out::println);
//                }
//                catch (ClassNotFoundException|SQLException|IOException e){
//                    System.out.println(e.getMessage());
//                }
//
//            default: System.exit(0);
//            break;
//
//        }
//    }
    AdminPresentation adminPresentation=new AdminPresentationImpl();
    EmployeePresentation employeePresentatio=new EmployeePresentationImpl();
    EmployeeService employeeService=new EmployeeServiceImpl();

    Scanner sc=new Scanner(System.in);

    @Override
    public void perfomMenu(int choice) {
            switch (choice) {
                case 1:
                    System.out.println("Enter AdminID");
                    String adminID = sc.nextLine();
                    System.out.println("Enter Password");
                    String adminPassword = sc.nextLine();
                    Employee admin;
                    try {
                        admin = employeeService.getEmployee(adminID);
                        if (adminPassword.equals(admin.getPassword())) {
                            boolean bool = admin.getAccess() == 1;
                            if (isAdmin(bool)) {
                                while(true) {
                                    adminPresentation.showMenu();
                                    int adminChoice = sc.nextInt();
                                    adminPresentation.perfomMenu(adminChoice);
                                    if(adminChoice==4){
                                        break;
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("Enter Valid Credentials");
                            break;
                        }
                    } catch (ClassNotFoundException | SQLException | IOException e) {
                        System.out.println("Error try again");
                        break;
                    }


                    break;
                case 2:
                    System.out.println("Enter EmployeeId");
                    String employeeID = sc.nextLine();
                    System.out.println("Enter Password");
                    String employeePassword = sc.nextLine();
                    Employee employee;
                    try {
                        employee = employeeService.getEmployee(employeeID);
                    } catch (ClassNotFoundException | SQLException | IOException e) {
                        System.out.println("Error Try Again");
                        break;
                    }
                    if (employeePassword.equals(employee.getPassword())) {
                        while (true) {
                            employeePresentatio.showMenu();
                            int empChoice = sc.nextInt();
                            employeePresentatio.perfomMenu(empChoice,employeeID);
                            if(empChoice==6){
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("Enter Valid Credentials");
                        break;
                    }

            }

        }

    public void showMenu() {
        System.out.println("1. For Admin\n 2. For Employee");
    }

    boolean isAdmin(Boolean bool){

        return bool;
    }
}
