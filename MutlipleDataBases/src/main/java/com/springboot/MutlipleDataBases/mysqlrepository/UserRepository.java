package com.springboot.MutlipleDataBases.mysqlrepository;

import com.springboot.MutlipleDataBases.mysqlentity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

