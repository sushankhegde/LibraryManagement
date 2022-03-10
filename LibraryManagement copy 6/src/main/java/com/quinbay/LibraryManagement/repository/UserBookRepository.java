package com.quinbay.LibraryManagement.repository;

import com.quinbay.LibraryManagement.entity.UserBookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookRepository extends CrudRepository<UserBookEntity,Integer> {


}
