package pojo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Book {
    private String bookID,category;
    int stockAvailable;
}
