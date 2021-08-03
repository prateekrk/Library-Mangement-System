package persistence;

import exceptions.BookNotFoundException;
import exceptions.EmployeeNotFoundException;
import pojo.Book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface BookDao {

    Collection<Book> getAllRecords() throws ClassNotFoundException, SQLException, IOException;

    Book getBook(String bookId) throws ClassNotFoundException, SQLException, IOException;

    int bookAvailableCount(String bookId) throws ClassNotFoundException, SQLException, IOException;

    Collection<String> getAllCategories() throws ClassNotFoundException, SQLException, IOException;

    Collection<Book> getBooksInCategory(String category) throws ClassNotFoundException,SQLException,IOException;

}
