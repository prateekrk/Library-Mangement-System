package persistence;

import exceptions.EmployeeNotFoundException;
import pojo.Book;
import pojo.Library;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

public interface LibrayManagementDao {
    Collection<Library> getAllRecords() throws ClassNotFoundException, SQLException, IOException ;
//    Collection<Book> getAllBookRecords() throws ClassNotFoundException,SQLException,IOException;

    boolean insertRecords(Library library) throws ClassNotFoundException,SQLException, IOException;
    boolean returnBook(String EmployeeID, String BookId,Date returnDate,int fine) throws ClassNotFoundException, SQLException, IOException ;
    Library getDetails(String EmployeeID, String BookId) throws ClassNotFoundException,SQLException,IOException;
//    Book getBook(String bookId) throws ClassNotFoundException,SQLException,IOException;
    Collection<Library> getBooksIssued(String employeeId) throws ClassNotFoundException,SQLException,IOException;
}
