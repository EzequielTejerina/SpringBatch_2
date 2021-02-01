package com.example.demo.springbatch.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Line implements Serializable {

    private String name;
    private LocalDate birth_date;
    private Long age;

    public Line() {
    }

    public Line(String name, LocalDate birth_date) {
        this.name = name;
        this.birth_date = birth_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
