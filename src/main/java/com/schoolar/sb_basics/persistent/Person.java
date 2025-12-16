package com.schoolar.sb_basics.persistent;


//@Document(collection = "persons")
public class Person {
    private String name;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
