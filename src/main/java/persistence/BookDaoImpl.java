package persistence;

import exceptions.EmployeeNotFoundException;
import helpers.DatabaseConnection;
import pojo.Book;
import pojo.Library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookDaoImpl implements BookDao {


    @Override
    public Collection<Book> getAllRecords() throws ClassNotFoundException, SQLException, IOException {
        Connection connection=DatabaseConnection.getConnection();

        Collection<Book> books=new ArrayList<Book>();

        PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM BOOKS");

        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            Book book=new Book();

            book.setBookID(resultSet.getString("BOOK_ID"));
            book.setCategory(resultSet.getString("BOOK_CATEGORY"));
            book.setStockAvailable(resultSet.getInt("STOCK_AVAILABLE"));
            book.setName(resultSet.getString("BOOK_NAME"));


            books.add(book);
        }

        connection.close();
        return books;
    }


    @Override
    public Book getBook(String bookId) throws ClassNotFoundException, SQLException, IOException{
        Book book = new Book();
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOKS WHERE BOOK_ID=?");
        preparedStatement.setString(1,bookId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            book.setBookID(resultSet.getString("BOOK_ID"));
            book.setCategory(resultSet.getString("BOOK_CATEGORY"));
            book.setStockAvailable(resultSet.getInt("STOCK_AVAILABLE"));
            System.out.println(book.getCategory());

        }

        connection.close();
        return book;

    }

    @Override
    public int bookAvailableCount(String bookId) throws ClassNotFoundException, SQLException, IOException {
        int count=0;
        Connection connection=DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT COUNT(BOOK_CATEGORY) FROM BOOKS WHERE BOOK_ID=?");
        ResultSet resultSet=preparedStatement.executeQuery();
        count=resultSet.getInt("COUNT(BOOK_CATEGORY");

        return count;
    }

    @Override
    public Collection<String> getAllCategories() throws ClassNotFoundException, SQLException, IOException {

        Collection<String> bookCategories=new ArrayList<>();

        Connection connection=DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT DISTINCT(BOOK_CATEGORY) FROM BOOKS");

        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            bookCategories.add(resultSet.getString("BOOK_CATEGORY"));
        }

        return bookCategories;
    }

    @Override
    public Collection<Book> getBooksInCategory(String category) throws ClassNotFoundException, SQLException, IOException {

        Collection<Book> booksInCategory=new ArrayList<>();
        Book book=new Book();

        Connection connection=DatabaseConnection.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM BOOKS WHERE BOOK_CATEGORY=?");
        preparedStatement.setString(1,category);

        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            book.setName(resultSet.getString("BOOK_NAME"));
            book.setCategory(category);
            book.setStockAvailable(resultSet.getInt("STOCK_AVAILABLE"));
            booksInCategory.add(book);
        }
        return  booksInCategory;
    }
}
