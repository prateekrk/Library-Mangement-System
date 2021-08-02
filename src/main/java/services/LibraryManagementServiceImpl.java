package services;



import helpers.BookTypes;
import persistence.BookDao;
import persistence.BookDaoImpl;
import persistence.LibraryManagementDaoImpl;
import persistence.LibrayManagementDao;
import pojo.Book;
import pojo.Library;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

public class LibraryManagementServiceImpl implements LibraryManagementService{

    LibrayManagementDao librayManagementDao=new LibraryManagementDaoImpl();
    BookDao bookDao=new BookDaoImpl();

    @Override
    public Collection<Library> getAllBooks() throws ClassNotFoundException, SQLException, IOException {
        return librayManagementDao.getAllRecords();
    }

    @Override
    public boolean insertBook(Library library) throws ClassNotFoundException, SQLException, IOException {
        return librayManagementDao.insertRecords(library);
    }

    @Override
    public boolean returnBook(String employeeID, String bookID,Date returnDate) throws ClassNotFoundException, SQLException, IOException {
        int fine;
        Library library=librayManagementDao.getDetails(employeeID,bookID);

//        System.out.println(library.getFine());
        int numDays= (int)ChronoUnit.DAYS.between(library.getScheduledReturnDate().toLocalDate(),returnDate.toLocalDate());
        Book book=bookDao.getBook(library.getBookId());
        if(numDays<=0){
            fine=0;
        }
        else{
           int rate= BookTypes.valueOf(book.getCategory()).getFine();
           fine=(numDays)*rate;
        }
        return librayManagementDao.returnBook(employeeID,bookID,returnDate,fine);
    }

    @Override
    public Library getDetails(String employeeID, String bookID) throws ClassNotFoundException, SQLException, IOException {
        return librayManagementDao.getDetails(employeeID,bookID);

    }



    @Override
    public Collection<Library> getBooksIssued(String employeeId) throws ClassNotFoundException, SQLException, IOException {
        return librayManagementDao.getBooksIssued(employeeId);
    }

}
