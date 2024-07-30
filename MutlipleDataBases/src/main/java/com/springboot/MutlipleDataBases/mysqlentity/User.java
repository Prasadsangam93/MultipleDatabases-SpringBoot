package com.springboot.MutlipleDataBases.mysqlentity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_multiple_db")
@Entity
public class User {
    @Id
    private Integer id;
    private  String userName;
}
