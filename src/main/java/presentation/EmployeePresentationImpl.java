package presentation;

import pojo.Library;
import services.BookService;
import services.BookServiceImpl;
import services.LibraryManagementService;
import services.LibraryManagementServiceImpl;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class EmployeePresentationImpl implements EmployeePresentation{
    LibraryManagementService libraryManagementService=new LibraryManagementServiceImpl();
    BookService bookService=new BookServiceImpl();

    Scanner sc=new Scanner(System.in);
    public void performMenu(int choice,String employeeId) {
        switch (choice) {
            case 1:
                System.out.println("Catalog");

                try {
                    bookService.getAllRecords().forEach(System.out::println);
                } catch (ClassNotFoundException | SQLException | IOException e) {
                    e.printStackTrace();
                }
                List<Library> booksIssued = new ArrayList<>();
//                    System.out.println("Enter Employee ID");
//                    String employeeId1 = sc.nextLine();
                String bookId1;
                while (true) {
                    System.out.println("Enter Book ID");
                    bookId1 = sc.nextLine();
//                    try {
//                        if (libraryManagementService.bookAvailableCount(bookId1) <= 0) {
//                            System.out.println("Book is not available right now");
//                            break;
//                        }
//                    } catch (SQLException | ClassNotFoundException | IOException e) {
////                        System.out.println(e.getMessage());
//                        e.printStackTrace();
//                    }


                    Library libraryBook = new Library();
                    libraryBook.setIssuedDate(Date.valueOf(LocalDate.now()));
                    libraryBook.setBookId(bookId1);
                    libraryBook.setScheduledReturnDate(Date.valueOf(LocalDate.now().plusDays(7)));
                    libraryBook.setEmployeeID(employeeId);
                    try {
                        libraryManagementService.insertBook(libraryBook);
                        booksIssued.add(libraryBook);

                    } catch (ClassNotFoundException | SQLException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Want to issue more books?(Y/N)");
                    String ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("no")) {
                        break;
                    }
                }
                System.out.println("Books Issued:");
                booksIssued.forEach(b -> System.out.println(b.getBookId() + " To be returned on : " + b.getScheduledReturnDate()));
                break;
            case 2:
                System.out.println(employeeId);
                List<Library> bookNotReturned;
                try {
                    bookNotReturned = new ArrayList<Library>(libraryManagementService.getBooksIssued(employeeId));
                    if (bookNotReturned.size() == 0) {
                        System.out.println("No books to be returned");
                        break;
                    }
                    System.out.println("Books to be returned");
                    bookNotReturned.forEach(l -> System.out.println("Issue Id : "+l.getIssueID()+" Book " + l.getBookId() + " was  issued on: " + l.getIssuedDate() + " and Scheduled Return Date : " + l.getScheduledReturnDate()));
                } catch (ClassNotFoundException | SQLException | IOException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println("Enter Issue Id");
                int issueId=sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Book ID");
                String bookId = sc.nextLine();
                Date returnDate = Date.valueOf(LocalDate.now());

                try {
                    libraryManagementService.returnBook(issueId,employeeId, bookId, returnDate);
                    int fine = libraryManagementService.getDetails(issueId).getFine();
                    if (fine == 0) {
                        System.out.println("Returned on time no fine to be paid");
                    } else {
                        System.out.println("Fine to be paid : Rs." + fine);
                    }
                } catch (SQLException | IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }


                break;
            case 3:

                try {
                    bookService.getAllRecords().forEach(System.out::println);

                } catch (SQLException | IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
                break;
            case 4:
                System.out.println("Categories");
                final List<String> categories=new ArrayList<>();
                try {
                  categories.addAll(bookService.getBookCategories());
                   IntStream.range(0,bookService.getBookCategories().size()).forEach(index->System.out.println(index+1+". "+categories.get(index)));
                } catch (ClassNotFoundException | SQLException | IOException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("Enter choice(Serial Number of Category)");
                int serialId=sc.nextInt();
                if(serialId>(categories.size())){
                    System.out.println("Enter Valid Serial ID");
                    break;
                }

                System.out.println("Books available in "+categories.get(serialId-1));

                try {
                    bookService.getBooksInCategory(categories.get(serialId-1)).forEach(System.out::println);
                }
                catch (ClassNotFoundException|SQLException|IOException e){
                    System.out.println(e.getMessage());
                }

                break;

            case 5:
                try {
                    libraryManagementService.getEmployeeTransactions(employeeId).forEach(System.out::println);
                }
                catch (ClassNotFoundException|SQLException|IOException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 6:
                System.out.println("Books to be Returned");
                try {
                    libraryManagementService.getBooksIssued(employeeId).forEach(book->System.out.printf("%-10s%-10s%-15s%-15s\n",Integer.toString(book.getIssueID()),book.getBookId(),book.getIssuedDate().toString(),book.getScheduledReturnDate().toString()));
                }
                catch (SQLException|ClassNotFoundException|IOException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 7:
                break;
            default:
                System.out.println("Enter valid choice");
                break;
        }
    }

    @Override
    public void showMenu() {
        System.out.println("1. Issue A Book\n2. Return Book\n3. View Book catalog\n4. Search Book By Category\n5. View My Transactions\n6. Books To Be Returned \n7. Exit");

    }
}
