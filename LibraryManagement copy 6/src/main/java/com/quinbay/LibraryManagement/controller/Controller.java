package com.quinbay.LibraryManagement.controller;

import com.quinbay.LibraryManagement.dto.Book;
import com.quinbay.LibraryManagement.dto.User;
import com.quinbay.LibraryManagement.dto.UserBook;
import com.quinbay.LibraryManagement.service.ServiceApiImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("services")
public class Controller {
    @Autowired
    ServiceApiImplementation serviceApiImplementation;

    @PostMapping(value = "/Book",consumes = "application/json")
    public void addBooks(@RequestBody Book books) {
        serviceApiImplementation.addBooks(books);
    }

    @PostMapping(value = "/User", consumes = "application/json")
    public void addUser(@RequestBody User user) {
        serviceApiImplementation.addUsers(user);
    }

    @PostMapping(value="/issue" , consumes = "application/json")
    public void Issue(@RequestBody UserBook userBookHistory){
        serviceApiImplementation.addIssue(userBookHistory);
    }

    @PutMapping(value="/Return" , consumes = "application/json")
    public UserBook returnDate(@RequestBody UserBook userBook){
        return serviceApiImplementation.addReturnDate(userBook);
    }

    @GetMapping(value = "/getfamousbook", consumes = "application/json", produces = "application/json")
    public Book getAllImformationBook() {
        return serviceApiImplementation.getPopularBook();
    }

    @GetMapping(value = "/getuser", consumes = "application/json", produces = "application/json")
    public User getImformationUserMaxBooks(){
        return serviceApiImplementation.getUserWithMaxBooks();
    }



}



