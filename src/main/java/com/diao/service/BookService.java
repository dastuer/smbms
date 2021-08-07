package com.diao.service;

import com.diao.pojo.Books;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookService {

    int addBook(Books book);

    int deleteBookById(int id);

    int updateBook(Books book);
    int updateBookCover(String bookID,String cover);
    String selectBookCoverID(int id);
    Books selectBookById(int id);

    List<Books> selectAllBooks();
    List<Books> selectBooksByName(String name);
}
