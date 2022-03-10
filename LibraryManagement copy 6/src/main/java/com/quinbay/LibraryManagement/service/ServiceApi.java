package com.quinbay.LibraryManagement.service;

import com.quinbay.LibraryManagement.dto.Book;
import com.quinbay.LibraryManagement.dto.User;
import com.quinbay.LibraryManagement.dto.UserBook;

public interface ServiceApi {
    void addBooks(Book books);
    void addUsers(User user);
    void addIssue(UserBook userBook);
    UserBook addReturnDate(UserBook userBook);
    Book getPopularBook();
    User getUserWithMaxBooks();

}
