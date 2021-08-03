package services;

import pojo.Book;
import pojo.Library;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

public interface LibraryManagementService {

    Collection<Library> getAllBooks()throws ClassNotFoundException,SQLException, IOException;

    boolean insertBook(Library library)throws ClassNotFoundException,SQLException, IOException;

    boolean returnBook(int issueId,String employeeID, String bookID,Date returnId) throws ClassNotFoundException,SQLException,IOException;

    Library getDetails(int issueId) throws ClassNotFoundException, SQLException, IOException;

    Collection<Library> getBooksIssued(String employeeId) throws ClassNotFoundException,SQLException,IOException;

    Collection<Library> getEmployeeTransactions(String employeeId) throws ClassNotFoundException,SQLException,IOException;
}
