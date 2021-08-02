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
    public Collection<Book> getAllRecords() throws ClassNotFoundException, SQLException, IOException {
        return bookDao.getAllRecords();
    }
}
