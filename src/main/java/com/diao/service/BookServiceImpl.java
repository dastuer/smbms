package com.diao.service;

import com.diao.dao.BookMapper;
import com.diao.pojo.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    private BookMapper bookMapper;
    @Override
    public int addBook(Books book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    @Override
    public int updateBook(Books book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public int updateBookCover(String bookID, String cover) {
        return bookMapper.updateBookCover(bookID,cover);
    }

    @Override
    public String selectBookCoverID(int id) {
        return bookMapper.selectBookCoverID(id);
    }

    @Override
    public Books selectBookById(int id) {
        return bookMapper.selectBookById(id);
    }

    @Override
    public List<Books> selectAllBooks() {
        return bookMapper.selectAllBooks();
    }

    @Override
    public List<Books> selectBooksByName(String name) {
        return  bookMapper.selectBooksByName(name);
    }


}
