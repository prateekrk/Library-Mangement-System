package persistence;

import exceptions.EmployeeNotFoundException;
import helpers.DatabaseConnection;
import pojo.Book;
import pojo.Library;

import java.io.IOException;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;

public class LibraryManagementDaoImpl implements LibrayManagementDao {
    public Collection<Library> getAllRecords() throws ClassNotFoundException,SQLException,IOException {
        Connection connection=DatabaseConnection.getConnection();

        Collection<Library> details=new ArrayList<Library>();

        PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM LIBRARY");

        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            Library library=new Library();

            library.setIssueID(resultSet.getInt("ISSUE_ID"));
            library.setBookId(resultSet.getString("BOOK_ID"));
            library.setEmployeeID(resultSet.getString("EMPLOYEE_ID"));
            library.setFine(resultSet.getInt("FINE"));
            library.setIssuedDate(resultSet.getDate("ISSUE_DATE"));
            library.setReturnDate(resultSet.getDate("RETURN_DATE"));
            library.setScheduledReturnDate(resultSet.getDate("SCHEDULED_RETURN"));

            details.add(library);
        }

        connection.close();
        return details;

    }

//    @Override
//    public Collection<Book> getAllBookRecords() throws ClassNotFoundException, SQLException, IOException {
//        return null;
//    }

    public boolean insertRecords(Library library) throws ClassNotFoundException, SQLException, IOException {
        Connection connection= DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO LIBRARY(EMPLOYEE_ID,BOOK_ID,ISSUE_DATE,SCHEDULED_RETURN) VALUES (?,?,?,?)") ;
        preparedStatement.setString(1,library.getEmployeeID());
        preparedStatement.setString(2,library.getBookId());
        preparedStatement.setDate(3, library.getIssuedDate());
        preparedStatement.setDate(4,Date.valueOf(library.getIssuedDate().toLocalDate().plusDays(7)));

        int rows=preparedStatement.executeUpdate();
        return rows > 0;
    }

    public boolean returnBook(int issueId,String EmployeeID, String BookId,Date returnDate,int fine) throws ClassNotFoundException, SQLException, IOException {
        Connection connection= DatabaseConnection.getConnection();

        PreparedStatement preparedStatement=connection.prepareStatement("UPDATE LIBRARY SET RETURN_DATE=?,FINE=? WHERE EMPLOYEE_ID=? AND BOOK_ID=? AND ISSUE_ID=? AND RETURN_DATE IS NULL");
        preparedStatement.setDate(1,returnDate);
        preparedStatement.setInt(2,fine);
        preparedStatement.setString(3,EmployeeID);
        preparedStatement.setString(4,BookId);
        preparedStatement.setInt(5,issueId);

        int rows=preparedStatement.executeUpdate();
        return rows>0;
    }

//    @Override
//    public Library getDetails(String employeeID, String bookId) throws ClassNotFoundException, SQLException, IOException {
//        Library library=new Library();
//        Connection connection= DatabaseConnection.getConnection();
//        PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM LIBRARY WHERE EMPLOYEE_ID=? AND BOOK_ID=? ");
//        preparedStatement.setString(1,employeeID);
//        preparedStatement.setString(2,bookId);
//        ResultSet resultSet=preparedStatement.executeQuery();
//
//        while(resultSet.next()){
//
//            library.setIssueID(resultSet.getInt("ISSUE_ID"));
//            library.setBookId(resultSet.getString("BOOK_ID"));
//            library.setEmployeeID(resultSet.getString("EMPLOYEE_ID"));
//            library.setFine(resultSet.getInt("FINE"));
//            library.setIssuedDate(resultSet.getDate("ISSUE_DATE"));
//            library.setReturnDate(resultSet.getDate("RETURN_DATE"));
//            library.setScheduledReturnDate(resultSet.getDate("SCHEDULED_RETURN"));
//        }
//
//        connection.close();
//        return library;
//
//    }
    @Override
    public Library getDetails(int issueID) throws ClassNotFoundException, SQLException, IOException {
        Library library=new Library();
        Connection connection= DatabaseConnection.getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM LIBRARY WHERE ISSUE_ID=?");
        preparedStatement.setInt(1,issueID);
//        preparedStatement.setString(2,bookId);
        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){

            library.setIssueID(resultSet.getInt("ISSUE_ID"));
            library.setBookId(resultSet.getString("BOOK_ID"));
            library.setEmployeeID(resultSet.getString("EMPLOYEE_ID"));
            library.setFine(resultSet.getInt("FINE"));
            library.setIssuedDate(resultSet.getDate("ISSUE_DATE"));
            library.setReturnDate(resultSet.getDate("RETURN_DATE"));
            library.setScheduledReturnDate(resultSet.getDate("SCHEDULED_RETURN"));
        }

        connection.close();
        return library;

    }

    @Override
    public Collection<Library> getBooksIssued(String employeeId) throws ClassNotFoundException, SQLException, IOException {
        Collection<Library> booksNotReturned=new ArrayList<>();


        Connection connection=DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT BOOK_ID,ISSUE_DATE,SCHEDULED_RETURN,ISSUE_ID FROM LIBRARY WHERE EMPLOYEE_ID=? AND RETURN_DATE IS NULL");

        preparedStatement.setString(1,employeeId);

        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            Library library=new Library();
            library.setIssueID(resultSet.getInt("ISSUE_ID"));
            library.setBookId(resultSet.getString("BOOK_ID"));
            library.setIssuedDate(resultSet.getDate("ISSUE_DATE"));
            library.setScheduledReturnDate(resultSet.getDate("SCHEDULED_RETURN"));
            booksNotReturned.add(library);
        }

        return booksNotReturned;
    }

    @Override
    public Collection<Library> getEmployeeTransactions(String employeeId) throws ClassNotFoundException, SQLException, IOException {

        Collection<Library> transactions=new ArrayList<>();

        Connection connection=DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM LIBRARY WHERE EMPLOYEE_ID=?");
        preparedStatement.setString(1,employeeId);

        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            Library library=new Library();
            library.setIssueID(resultSet.getInt("ISSUE_ID"));
            library.setBookId(resultSet.getString("BOOK_ID"));
            library.setEmployeeID(resultSet.getString("EMPLOYEE_ID"));
            library.setFine(resultSet.getInt("FINE"));
            library.setIssuedDate(resultSet.getDate("ISSUE_DATE"));
            library.setReturnDate(resultSet.getDate("RETURN_DATE"));
            library.setScheduledReturnDate(resultSet.getDate("SCHEDULED_RETURN"));
            transactions.add(library);
        }
        return transactions;
    }

//    @Override
//    public Book getBook(String bookId) throws ClassNotFoundException, SQLException, IOException {
//        Book book = new Book();
//        Connection connection = DatabaseConnection.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS WHERE BOOK_ID=?");
//        preparedStatement.setString(1,bookId);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()) {
//
//            book.setBookID(resultSet.getString("BOOK_ID"));
//            book.setCategory(resultSet.getString("BOOK_CATEGORY"));
//            book.setStockAvailable(resultSet.getInt("STOCK_AVAILABLE"));
//            System.out.println(book.getCategory());
//
//        }
//
//        connection.close();
//        return book;
//
//    }
}


