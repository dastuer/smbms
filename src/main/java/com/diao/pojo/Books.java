package com.diao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Morty
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    /**
     *
     */
    private int bookID;
    private String bookName;
    private int bookCount;
    private String detail;
    private String cover;
    private String coverID;

}
