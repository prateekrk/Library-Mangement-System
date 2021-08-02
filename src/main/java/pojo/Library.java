package pojo;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Library {
    private String bookId,employeeID;
    private Date  issuedDate,returnDate,scheduledReturnDate;
    private int fine,issueID;


}
