package services;

import pojo.Book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface BookService {
    int bookAvailableCount(String bookId) throws ClassNotFoundException, SQLException, IOException;

    Collection<String> getBookCategories() throws ClassNotFoundException,SQLException,IOException;

    Collection<Book> getAllRecords() throws ClassNotFoundException, SQLException, IOException;

    Collection<Book> getBooksInCategory(String category) throws ClassNotFoundException,SQLException,IOException;

    boolean insertBook(Book book) throws ClassNotFoundException,SQLException,IOException;

    boolean increaseStock(String book_id,int stock_available) throws ClassNotFoundException,SQLException,IOException;
}


