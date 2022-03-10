package com.quinbay.LibraryManagement.service;

import com.quinbay.LibraryManagement.dto.Book;
import com.quinbay.LibraryManagement.dto.User;
import com.quinbay.LibraryManagement.dto.UserBook;
import com.quinbay.LibraryManagement.entity.BookEntity;
import com.quinbay.LibraryManagement.entity.UserBookEntity;
import com.quinbay.LibraryManagement.entity.UserEntity;
import com.quinbay.LibraryManagement.exception.NoDataFoundException;
import com.quinbay.LibraryManagement.repository.BookRepository;
import com.quinbay.LibraryManagement.repository.UserBookRepository;
import com.quinbay.LibraryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ServiceApiImplementation implements ServiceApi {

    @Autowired
    BookRepository booksRepository;

    @Autowired
    UserBookRepository userBookRepository;

    @Autowired
    UserRepository userRepository;

    public static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();
        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> max = null;
        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
    }

    @Override
    public void addBooks(Book books) {
        booksRepository.save(new BookEntity(books.getId(),
                books.getName(),
                books.getWriter(),
                books.getSummary(),
                books.getCategory()));

    }

    @Override
    public void addUsers(User user) {
        userRepository.save(new UserEntity(user.getId(),
                user.getName(),
                user.getAddress(),
                user.getPhoneNum()));

    }

    public List<Book> getBooks() {
        Iterable<BookEntity> booksEntitiesList = booksRepository.findAll();
        List<Book> list = new ArrayList<>();
        for (BookEntity b : booksEntitiesList) {
            list.add(new Book(b.getId(),
                    b.getName(),
                    b.getWriter(),
                    b.getSummary(),
                    b.getCategory()));
        }
        return list;

    }

    @Override
    public void addIssue(UserBook userBook) {


        userBookRepository.save(new UserBookEntity(userBook.getId(),
                userBook.getUserId(),
                userBook.getBookId(),
                userBook.getStartDate(),
                null));
    }

    @Override
    public UserBook addReturnDate(UserBook userBook) {
        String date = "";
        boolean isPresent = false;
        Iterable<UserBookEntity> iterable = userBookRepository.findAll();
        for (UserBookEntity i : iterable) {
            if (i.getBookId() == userBook.getBookId() && i.getUserId() == userBook.getUserId()) {
                date = i.getStartDate();
                isPresent = true;
            }
        }

        if (isPresent) {
            userBookRepository.save(new UserBookEntity(userBook.getId(), userBook.getUserId(), userBook.getBookId(), date, userBook.getEndDate()));
            return userBook;
        } else {
            throw  new NoDataFoundException(); //throw new exception
        }
    }

    @Override
    public Book getPopularBook() {
        Iterable<UserBookEntity> iterable = userBookRepository.findAll();
        List<UserBook> list = new ArrayList<>();
        for (UserBookEntity i : iterable) {
            list.add(new UserBook(i.getId(), i.getUserId(), i.getBookId(), i.getStartDate(), i.getEndDate()));
        }
        ArrayList<Long> bookid = new ArrayList<>();
        for (UserBook i : list) {
            bookid.add(i.getBookId());
        }
        Long max = mostCommon(bookid);
        Iterable<BookEntity> books = booksRepository.findAll();
        Book books1 = new Book();
        for (BookEntity i : books) {
            if (i.getId() == max) {
                books1.setId(i.getId());
                books1.setName(i.getName());
                books1.setCategory(i.getCategory());
                books1.setSummary(i.getSummary());
                books1.setCategory(i.getCategory());
            }
        }


        return books1;
    }

    @Override
    public User getUserWithMaxBooks() {
        Iterable<UserBookEntity> iterable = userBookRepository.findAll();
        List<UserBook> list = new ArrayList<>();
        for (UserBookEntity i : iterable) {
            list.add(new UserBook(i.getId(), i.getUserId(), i.getBookId(), i.getStartDate(), i.getEndDate()));
        }
        ArrayList<Long> userId = new ArrayList<>();
        for (UserBook i : list) {
            userId.add(i.getUserId());
        }

        Long max = mostCommon(userId);
        Iterable<UserEntity> userEntities = userRepository.findAll();
        User user = new User();
        for (UserEntity i : userEntities) {
            if (i.getId() == max) {
                user.setId(i.getId());
                user.setName(i.getName());
                user.setAddress(i.getAddress());
                user.setPhoneNum(i.getPhoneNum());
            }
        }
        return user;
    }

}
