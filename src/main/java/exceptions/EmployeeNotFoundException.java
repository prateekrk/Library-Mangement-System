package exceptions;

public class EmployeeNotFoundException extends Exception{
    int rows;
    EmployeeNotFoundException(int n){
        this.rows=n;
    }

    @Override
    public String toString() {
        return "Employee not found";
    }
}
