package com.diao.controller;

import com.diao.pojo.Books;
import com.diao.service.BookService;
import com.diao.utils.FileProcess;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    @Autowired
    @Qualifier("bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/allBooks")
    public String getAllBooks(Model model){
        List<Books> books = bookService.selectAllBooks();
        model.addAttribute("books",books);
//        System.out.print("getAllBooks");
        return "allBooks";
    }
    @RequestMapping("/selectByName")
    public String selectBookByName(Model model,String bookName){
        List<Books> books = bookService.selectBooksByName(bookName);
        model.addAttribute("books",books);
        return "allBooks";
    }
    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "addBook";
    }
    @RequestMapping("/addBook")
    public String addBook(Books books ,@RequestParam("file") CommonsMultipartFile file,HttpServletRequest req) throws IOException {
        String coverID = UUID.randomUUID().toString().replace("-","");
        books.setCoverID(coverID);
        if (file!=null&&file.getFileItem().getSize()>0) {
            String cover = FileProcess.processFile(file, coverID,req);
            books.setCover(cover);
        }
        System.out.println(books);
        bookService.addBook(books);
        return "redirect:/book/allBooks";
    }
    @RequestMapping("/toUpdate")
    public String toUpdateBook(int id,Model model){
        Books book = bookService.selectBookById(id);
        model.addAttribute("book",book);
        return "updateBook";
    }
    @RequestMapping("/updateBook")
    public String updateBook(Books books, @RequestParam("file") CommonsMultipartFile file,
                             HttpServletRequest req) throws IOException {

        if (file!=null&&file.getFileItem().getSize()>0) {
            String bookCoverID = bookService.selectBookCoverID(books.getBookID());
            String cover = FileProcess.processFile(file, bookCoverID,req);
            books.setCover(cover);
//            System.out.println("set Cover");
        }
//        System.out.println(books);
        bookService.updateBook(books);
        return "redirect:/book/allBooks";
    }
    @RequestMapping("/deleteBook")
    public String deleteBook(int id ,HttpServletRequest req){
        String coverID = bookService.selectBookCoverID(id);
        FileProcess.deleteCover(coverID,req);
        bookService.deleteBookById(id);
        return "redirect:/book/allBooks";
    }
    @RequestMapping("/view")
    public String view( @Param("bookID") int id, Model model){
        Books books = bookService.selectBookById(id);
        model.addAttribute("book",books);
        return "view";
    }
}
