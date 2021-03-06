package com.library.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private long id;
    private String name;
    private String lastName;
    private LocalDate creationDate;
    private double account;


    public UserDto(String name, String lastName, LocalDate creationDate, double account) {
        this.name = name;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.account = account;
    }

}
