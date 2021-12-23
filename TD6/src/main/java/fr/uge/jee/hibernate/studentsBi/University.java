package fr.uge.jee.hibernate.studentsBi;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Universities")
public class University {

    @Id
    @GeneratedValue(generator = "university_gen")
    @Column(name="UNIVERSITYID")
    private long id;

    @Column(name="UNIVERSITY_NAME")
    private String name;


    public University() {
    } // for Hibernate

    public University(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
}
