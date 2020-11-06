package br.com.dev.model;


import com.sun.istack.NotNull;

import javax.persistence.Entity;

@Entity
public class Student extends AbstractEntity{
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


