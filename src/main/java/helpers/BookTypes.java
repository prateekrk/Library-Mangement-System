package helpers;

public enum BookTypes {
    DATA_ANALYTICS(1,5),TECHNOLOGY(2,6),MANAGEMENT(3,7);
    private int bookCategory;
    private int fine;

    public int getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(int bookCategory) {
        this.bookCategory = bookCategory;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    private BookTypes(int bookCategory, int fine){
        this.bookCategory=bookCategory;
        this.fine=fine;
    }
}
