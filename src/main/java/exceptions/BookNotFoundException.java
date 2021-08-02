package exceptions;

public class BookNotFoundException extends Exception{
    int rows;
    BookNotFoundException(int n){
        this.rows=n;
    }

    @Override
    public String toString() {
        return "Book not found";
    }
}
