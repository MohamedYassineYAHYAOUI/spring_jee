package fr.uge.jee.springmvc.reststudents;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;


public class Student {

    private final String firstName;
    private final String lastName;
    private int age;

    Student(@JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("age") int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
