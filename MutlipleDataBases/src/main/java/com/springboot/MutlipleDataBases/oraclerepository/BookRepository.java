package com.springboot.MutlipleDataBases.oraclerepository;

import com.springboot.MutlipleDataBases.oracleentity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}

