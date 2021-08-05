package services;

import persistence.BookDao;
import persistence.BookDaoImpl;
import pojo.Book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class BookServiceImpl implements BookService {

    BookDao bookDao=new BookDaoImpl();

    @Override
    public int bookAvailableCount(String bookId) throws ClassNotFoundException, SQLException, IOException {
        return bookDao.bookAvailableCount(bookId);
    }

    @Override
    public Collection<String> getBookCategories() throws ClassNotFoundException, SQLException, IOException {
        return bookDao.getAllCategories();
    }

    @Override
    public Collection<Book> getAllRecords() throws ClassNotFoundException, SQLException, IOException {
        return bookDao.getAllRecords();
    }

    @Override
    public Collection<Book> getBooksInCategory(String category) throws ClassNotFoundException, SQLException, IOException {
        return bookDao.getBooksInCategory(category);
    }

    @Override
    public boolean insertBook(Book book) throws ClassNotFoundException, SQLException, IOException {
        return bookDao.insertBook(book);
    }

    @Override
    public boolean increaseStock(String book_id, int stock_available) throws ClassNotFoundException, SQLException, IOException {
        return bookDao.increaseStock(book_id,stock_available);
    }
}
