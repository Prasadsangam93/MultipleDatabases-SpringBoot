package com.springboot.MutlipleDataBases.oracleentity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="book_multiple_db")
@Entity
public class Book {
    @Id
    private Integer id;
    private  String name;



}
