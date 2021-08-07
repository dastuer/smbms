package com.diao.dao;

import com.diao.pojo.Books;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Morty
 */
public interface BookMapper {
    /**
        增加功能add
     */
    @Insert("insert into ssmbuild.books(bookName,bookCount,detail,coverID,cover)" +
            "values(#{bookName},#{bookCount},#{detail},#{coverID},#{cover});")
    int addBook(Books book);
    /**
        删除del
     */
    @Delete("delete from ssmbuild.books where bookID=#{id}")
    int deleteBookById(@Param("id") int id);
    /**
        修改书update
     */
    int updateBook(Books book);
    /**
        查询书籍select
     */
    @Select("select * from ssmbuild.books where bookID=#{id}")
    Books selectBookById(int id);
    @Select("select sb.coverID from ssmbuild.books as sb where bookID=#{id}")
    String selectBookCoverID(int id);
    @Select("select * from ssmbuild.books")
    List<Books> selectAllBooks();
    @Select("select * from ssmbuild.books where bookName like concat('%',#{name},'%');")
    List<Books> selectBooksByName(String name);
    @Update("update ssmbuild.books set cover=#{cover} where bookID=#{id}")
    int updateBookCover(@Param("id") String bookID,@Param("cover") String cover);

}
